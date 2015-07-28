# https://en.wikibooks.org/wiki/X86_Assembly/Interfacing_with_C_stdlib_and_own_libraries
# program source
.text
# named section _main
.globl _main
_main:
  # ABI requires aligning stack pointer
  subq $8, %rsp
  # store 0 (exit code) in rdi
  movq $0, %rdi
  # call exit(0) defined in libc
  call _exit
