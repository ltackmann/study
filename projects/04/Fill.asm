// I/O handling: Infinite loop that blackens the screen when any key is pressed

// while(true) {
//  row=0
//  color=0
//  if(key) {
//    color = [1111 1111 1111 1111]
//  else
//    color = [0000 0000 0000 0000]
//  }
//
//  while(row-512 < 0) {
//    row_adr = SCREEN + row*32
//
//    column=0
//    while(column-32 < 0) {
//        adr = row_adr + column
//        M[adr] = color 
//        column++
//    }
//    row++
//  }
// }

// black is -1 and white is 0 (since we use 16 bit two complement words)
@color
M=-1

// set screen pointer to start of screen memory map
@SCREEN
D=A
@pointer
M=D

// max number of words in screen memory mao (512 rows with 256 pixes = 512rows x 32words = 16384)  
@16666
D=A
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
@END_SCREEN_LOOP
0;JMP
