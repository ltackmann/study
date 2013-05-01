// Multiplication: The program computes the product R0*R1 and stores the result in R2.
//
// R2 = 0
// while(R1 > 0) {
//   R2 = R2 + R0
//   R1 = R1 - 1
// }

// R2 = 0
@R2
M=0

(LOOP)
  // goto END if R1 <= 0
  @R1
  D=M
  @END
  D,JLE

  // R2 = R0 + R2
  @R0
  D=M
  @R2
  M=D+M

  // R1 = R1 - 1
  @R1
  M=M-1 
 
  // goto LOOP
  @LOOP
  0;JMP 
(END)
