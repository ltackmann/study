# https://en.wikibooks.org/wiki/X86_Assembly/Interfacing_with_C_stdlib_and_own_libraries
# http://nickdesaulniers.github.io/blog/2014/04/18/lets-write-some-x86-64/
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
