/**
 * Copyright (C) 2009 - 2020 <a href="https://www.wudsn.com" target="_top">Peter Dell</a>
 *
 * This file is part of WUDSN IDE.
 * 
 * WUDSN IDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * WUDSN IDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with WUDSN IDE.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.wudsn.ide.asm.editor;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import com.wudsn.ide.asm.compiler.parser.CompilerSourcePartitionScanner;
import com.wudsn.ide.asm.preferences.AssemblerPreferences;
import com.wudsn.ide.asm.preferences.AssemblerPreferencesChangeListener;
import com.wudsn.ide.asm.preferences.AssemblerPreferencesConstants;

/**
 * Source configuration for the assembler editor. Provides syntax highlighting.
 * 
 * Created and disposed by {@link AssemblerEditor}.
 * 
 * @author Peter Dell
 * @author Andy Reek
 */
final class AssemblerSourceViewerConfiguration extends TextSourceViewerConfiguration implements
	AssemblerPreferencesChangeListener {

    /**
     * The underlying assembler editor.
     */
    private AssemblerEditor editor;

    /**
     * Rule scanner for single line comments.
     */
    private AssemblerRuleBasedScanner commentSingleScanner;

    /**
     * Rule scanner for multiple lines comments.
     */
    private AssemblerRuleBasedScanner commentMultipleScanner;

    /**
     * Rule scanner for strings.
     */
    private AssemblerRuleBasedScanner stringScanner;

    /**
     * Rule scanner for assembler instructions.
     */
    private AssemblerSourceScanner instructionScanner;

    /**
     * Creates a new instance. Called by
     * {@link AssemblerEditor#initializeEditor()}.
     * 
     * @param editor
     *            The underlying assembler editor, not <code>null</code>.
     * 
     * @param preferenceStore
     *            The preferences store, not <code>null</code>.
     */
    AssemblerSourceViewerConfiguration(AssemblerEditor editor, IPreferenceStore preferenceStore) {
	super(preferenceStore);
	if (editor == null) {
	    throw new IllegalArgumentException("Parameter 'editor' must not be null.");
	}
	this.editor = editor;
	editor.getPlugin().addPreferencesChangeListener(this);

    }

    /**
     * Called by
     * {@link AssemblerEditor#updateIdentifiers(com.wudsn.ide.asm.compiler.parser.CompilerSourceFile)}
     * 
     * @return The instruction scanner, not <code>null</code>.
     */
    final AssemblerSourceScanner getAssemblerInstructionScanner() {
	if (instructionScanner == null) {
	    throw new IllegalStateException("Instruction scanner not yet created");
	}
	return instructionScanner;
    }

    /**
     * Remove all rule scanners from property change listener. Used by
     * {@link AssemblerEditor#dispose()}.
     */
    final void dispose() {
	if (commentSingleScanner != null) {
	    commentSingleScanner.dispose();
	    commentSingleScanner = null;
	}
	if (commentMultipleScanner != null) {
	    commentMultipleScanner.dispose();
	    commentMultipleScanner = null;
	}
	if (stringScanner != null) {
	    stringScanner.dispose();
	    stringScanner = null;
	}
	if (instructionScanner != null) {
	    instructionScanner.dispose();
	    instructionScanner = null;
	}
	editor.getPlugin().removePreferencesChangeListener(this);
    }

    @Override
    public void preferencesChanged(AssemblerPreferences preferences, Set<String> changedPropertyNames) {
	if (preferences == null) {
	    throw new IllegalArgumentException("Parameter 'preferences' must not be null.");
	}
	if (changedPropertyNames == null) {
	    throw new IllegalArgumentException("Parameter 'changedPropertyNames' must not be null.");
	}
	boolean refresh = false;
	refresh |= commentSingleScanner.preferencesChanged(preferences, changedPropertyNames);
	refresh |= commentMultipleScanner.preferencesChanged(preferences, changedPropertyNames);
	refresh |= stringScanner.preferencesChanged(preferences, changedPropertyNames);
	refresh |= instructionScanner.preferencesChanged(preferences, changedPropertyNames);
	if (refresh) {
	    editor.refreshSourceViewer();
	}
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
	ContentAssistant assistant = new ContentAssistant();
	assistant
		.setContentAssistProcessor(new AssemblerContentAssistProcessor(editor), IDocument.DEFAULT_CONTENT_TYPE);
	assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
	assistant.enableAutoActivation(true);
	assistant.enableAutoInsert(true);
	assistant.setAutoActivationDelay(500);
	assistant.enableColoredLabels(true);

	return assistant;
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
	if (sourceViewer == null) {
	    throw new IllegalArgumentException("Parameter 'sourceViewer' must not be null.");
	}
	PresentationReconciler reconciler = new PresentationReconciler();
	DefaultDamagerRepairer dr;

	commentSingleScanner = new AssemblerRuleBasedScanner(
		AssemblerPreferencesConstants.EDITOR_TEXT_ATTRIBUTE_COMMENT);
	dr = new DefaultDamagerRepairer(commentSingleScanner);
	reconciler.setDamager(dr, CompilerSourcePartitionScanner.PARTITION_COMMENT_SINGLE);
	reconciler.setRepairer(dr, CompilerSourcePartitionScanner.PARTITION_COMMENT_SINGLE);

	commentMultipleScanner = new AssemblerRuleBasedScanner(
		AssemblerPreferencesConstants.EDITOR_TEXT_ATTRIBUTE_COMMENT);
	dr = new DefaultDamagerRepairer(commentMultipleScanner);
	reconciler.setDamager(dr, CompilerSourcePartitionScanner.PARTITION_COMMENT_MULTIPLE);
	reconciler.setRepairer(dr, CompilerSourcePartitionScanner.PARTITION_COMMENT_MULTIPLE);

	stringScanner = new AssemblerRuleBasedScanner(AssemblerPreferencesConstants.EDITOR_TEXT_ATTRIBUTE_STRING);
	dr = new DefaultDamagerRepairer(stringScanner);
	reconciler.setDamager(dr, CompilerSourcePartitionScanner.PARTITION_STRING);
	reconciler.setRepairer(dr, CompilerSourcePartitionScanner.PARTITION_STRING);

	instructionScanner = new AssemblerSourceScanner(editor);
	dr = new DefaultDamagerRepairer(instructionScanner);
	reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
	reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

	return reconciler;
    }

    @Override
    public IReconciler getReconciler(ISourceViewer sourceViewer) {
	if (sourceViewer == null) {
	    throw new IllegalArgumentException("Parameter 'sourceViewer' must not be null.");
	}
	IReconcilingStrategy reconcilingStrategy = new AssemblerReconcilingStategy(editor);

	MonoReconciler reconciler = new MonoReconciler(reconcilingStrategy, false);
	reconciler.setProgressMonitor(new NullProgressMonitor());
	reconciler.setDelay(500);

	return reconciler;
    }

    @Override
    protected Map<String, IAdaptable> getHyperlinkDetectorTargets(ISourceViewer sourceViewer) {
	Map<String, IAdaptable> targets = super.getHyperlinkDetectorTargets(sourceViewer);
	targets.put(AssemblerHyperlinkDetector.TARGET, editor);
	return targets;
    }

}
