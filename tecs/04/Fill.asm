// I/O handling: Infinite loop that blackens the screen when any key is pressed

// while(true) {
//  max = SCREN + (256 * 32)
//  pointer = SCREEN
//  while(pointer-max < 0) {
//    color=0
//    if(key) {
//      color = -1
//    }
//    M[pointer] = color 
//    pointer++
//  }
// }

(INPUT_LOOP)

// set screen pointer to start of screen memory map
@SCREEN
D=A
@pointer
M=D

// number of words in screen memory (256 rows with 512 pixes = 256rows x 32words = 8192)  
@8192
D=A
@SCREEN
D=D+A
@max
M=D

(SCREEN_LOOP)
  // if pointer-max > 0 goto END_SCREEN_LOOP
  @pointer
  D=M
  @max
  D=D-M
  @END_SCREEN_LOOP
  D;JGT 

  // set screen color to white (0) when no keyboard input exists 
  @color
  M=0
  @KBD
  D=M
  @SKIP_BLACK
  D;JEQ 
  // black is -1 and white is 0 (since we use 16 bit two complement words)
  @color
  M=-1
  (SKIP_BLACK)

  //
  @color
  D=M
  @pointer
  A=M
  M=D

  // pointer = pointer + 1
  @pointer
  M=M+1

  // goto SCREEN_LOOP
  @SCREEN_LOOP
  0;JMP
(END_SCREEN_LOOP)

// infinite loop
@INPUT_LOOP
0;JMP
