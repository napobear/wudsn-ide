The Directives:  

".byte"
".by"	
".word"
".wo"		
".dword"
".dw"		
".text"
".te"
".align"
".assert"
".asserterror"
".pseudopc"	
".import"	
".pc"		
".macro"
".pseudocommand"
".print"
".printnow"
".error"
".if"	
".else"	
".for"	
".eval"	
".fill"	
".function"
".return"
".struct"
".namespace"
".filenamespace"	
".importonce"
".var"		
".const"
".enum"	
".label"
".define"
".easteregg"



Asm Commands (Including the illegal ones);
"tas"|"php"|"sax"|"rti"|"sta"|"tsx"|"cpy"|"cpx"|"pha"|"bvs"|"bpl"|"dcp"|"adc"|
"tya"|"eor"|"iny"|"inx"|"lax"|"bcs"|"bvc"|"las"|"bit"|"slo"|"txs"|"inc"|"bcc"|
"lsr"|"sre"|"plp"|"txa"|"rla"|"jmp"|"jsr"|"rra"|"pla"|"sei"|"ahx"|"sed"|"sec"|
"ora"|"isc"|"bne"|"and"|"anc"|"cmp"|"bmi"|"asl"|"ldy"|"clv"|"ldx"|"ror"|"cli"|
"alr"|"dey"|"rol"|"cld"|"dex"|"clc"|"arr"|"brk"|"lda"|"shy"|"axs"|"shx"|"sty"|
"stx"|"beq"|"nop"|"dec"|"sbc2"|"sbc"|"tay"|"xaa"|"tax"|"rts"		


DTV Commands:
"sac"|"sir"|"bra"		

Default macros: 
:BasicUpstart(address)
:BasicUpstart2(address)


Default Constants:
PI
E
AT_ABSOLUTE
AT_ZEROPAGE
AT_ABSOLUTEX
AT_ABSOLUTEY
AT_IMMEDIATE
AT_INDIRECT
AT_IZEROPAGEX
AT_IZEROPAGEY
AT_NONE
BLACK
WHITE
RED
CYAN
PURPLE
GREEN
BLUE
YELLOW
ORANGE
BROWN
LIGHT_RED
DARK_GRAY
DARK_GREY
GRAY
GREY
LIGHT_GREEN
LIGHT_BLUE
LIGHT_GRAY
LIGHT_GREY
BF_C64FILE
BF_KOALA
BF_FLI
BF_BITMAP_SINGLECOLOR
PLA
SAC_IMM
SLO_ZP
SLO_ZPX
SLO_IZPX
SLO_IZPY
SLO_ABS
SLO_ABSX
SLO_ABSY
PHA
BVC_REL
BRK
CLC
CLD
PLP
EOR_IMM
EOR_ZP
EOR_ZPX
EOR_IZPX
EOR_IZPY
EOR_ABS
EOR_ABSX
EOR_ABSY
INC_ZP
INC_ZPX
INC_ABS
INC_ABSX
PHP
RLA_ZP
RLA_ZPX
RLA_IZPX
RLA_IZPY
RLA_ABS
RLA_ABSX
RLA_ABSY
BRA_REL
JMP_ABS
JMP_IND
BVS_REL
ROR
ROR_ZP
ROR_ZPX
ROR_ABS
ROR_ABSX
CLV
STA_ZP
STA_ZPX
STA_IZPX
STA_IZPY
STA_ABS
STA_ABSX
STA_ABSY
ARR_IMM
SBC_IMM
SBC_ZP
SBC_ZPX
SBC_IZPX
SBC_IZPY
SBC_ABS
SBC_ABSX
SBC_ABSY
CLI
BEQ_REL
LSR
LSR_ZP
LSR_ZPX
LSR_ABS
LSR_ABSX
CPX_IMM
CPX_ZP
CPX_ABS
CPY_IMM
CPY_ZP
CPY_ABS
NOP
BNE_REL
JSR_ABS
ANC_IMM
LDA_IMM
LDA_ZP
LDA_ZPX
LDA_IZPX
LDA_IZPY
LDA_ABS
LDA_ABSX
LDA_ABSY
AND_IMM
AND_ZP
AND_ZPX
AND_IZPX
AND_IZPY
AND_ABS
AND_ABSX
AND_ABSY
RTI
BIT_ZP
BIT_ZPX
BIT_ABS
BIT_ABSX
STX_ZP
STX_ZPY
STX_ABS
TAY
TAX
RTS
TAS_ABSY
SAX_ZP
SAX_ZPY
SAX_IZPX
SAX_ABS
ROL
ROL_ZP
ROL_ZPX
ROL_ABS
ROL_ABSX
INX
INY
STY_ZP
STY_ZPX
STY_ABS
ASL
ASL_ZP
ASL_ZPX
ASL_ABS
ASL_ABSX
TYA
BMI_REL
AHX_IZPY
AHX_ABSY
DEX
DEY
CMP_IMM
CMP_ZP
CMP_ZPX
CMP_IZPX
CMP_IZPY
CMP_ABS
CMP_ABSX
CMP_ABSY
AXS_IMM
ADC_IMM
ADC_ZP
ADC_ZPX
ADC_IZPX
ADC_IZPY
ADC_ABS
ADC_ABSX
ADC_ABSY
TXS
DEC_ZP
DEC_ZPX
DEC_ABS
DEC_ABSX
ISC_ZP
ISC_ZPX
ISC_IZPX
ISC_IZPY
ISC_ABS
ISC_ABSX
ISC_ABSY
ALR_IMM
SRE_ZP
SRE_ZPX
SRE_IZPX
SRE_IZPY
SRE_ABS
SRE_ABSX
SRE_ABSY
TSX
LAS_ABSY
LAX_IMM
LAX_ZP
LAX_ZPY
LAX_IZPX
LAX_IZPY
LAX_ABS
LAX_ABSY
TXA
BCC_REL
SHY_ABSX
SHX_ABSY
RRA_ZP
RRA_ZPX
RRA_IZPX
RRA_IZPY
RRA_ABS
RRA_ABSX
RRA_ABSY
BPL_REL
SEI
XAA_IMM
SIR_IMM
DCP_ZP
DCP_ZPX
DCP_IZPX
DCP_IZPY
DCP_ABS
DCP_ABSX
DCP_ABSY
SED
ORA_IMM
ORA_ZP
ORA_ZPX
ORA_IZPX
ORA_IZPY
ORA_ABS
ORA_ABSX
ORA_ABSY
SEC
ANC2_IMM
BCS_REL
SBC2_IMM
LDY_IMM
LDY_ZP
LDY_ZPX
LDY_ABS
LDY_ABSX
LDX_IMM
LDX_ZP
LDX_ZPY
LDX_ABS
LDX_ABSY

Default functions:
abs
acos
asin
atan
atan2
cbrt
ceil
cos
cosh
exp
expm1
floor
hypot
IEEEremainder
log
log10
log1p
max
min
pow
random
round
signum
sin
sinh
sqrt
tan
tanh
toDegrees
toRadians
mod
toIntString
toHexString
toOctalString
toBinaryString
Vector
Vector
Matrix
RotationMatrix
ScaleMatrix
MoveMatrix
PerspectiveMatrix
createFile
List
List
Hashtable
CmdArgument
LoadSid
LoadPicture
LoadBinary
asmCommandSize