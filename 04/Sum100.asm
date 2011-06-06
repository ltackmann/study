// naive asm implementation of the sum of the first 100 integers
// 
// int i=1;
// int sum = 0;
// while(i-100 < 0) {
//   sum = sum + i;
//   i = i + 1; 
// }
//

// i=1
@i
M=1

// sum = 0
@sum
M=0

(LOOP)
  @i
  D=M
  @100
  // d = i - 100
  D=D-A
  @END
  // if D > 0 goto END
  D;JGT

  // sum = i + sum
  @i
  D=M
  @sum
  M=D+M

  // i = i + 1
  @i 
  M=M+1
  
  // goto LOOP
  @LOOP
  0;JMP
(END)

// infinite loop
@END
0;JMP


