# program source section
.text
# creates named section _main
.globl _main
_main:
  # align stack pointer
  subq $8, %rsp
  # prepare data for put chat
  movq $72, %rdi  # h
  call _putchar
  movq $101, %rdi # e
  call _putchar
  movq $108, %rdi # l
  call _putchar
  movq $108, %rdi # l
  call _putchar
  movq $111, %rdi  # o
  call _putchar
  movq $10, %rdi   # newline
  call _putchar

  # store 0 (exit code) in rdi
  movq $0, %rdi
  # call exit(0) defined in libc
  call _exit
