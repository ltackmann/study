# initialize static variables
.data
_hello:
  .asciz "hello world\n"

# program source section
.text
# creates named section _main
.globl _main
_main:
  subq $8, %rsp

  movb $0, %al
  leaq _hello(%rip), %rdi
  call _printf

  movq $0, %rdi
  call _exit
