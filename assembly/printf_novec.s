# static variables
.data
_format_string: .asciz "hello integer %d\n"

# program source
.text
# named section _main
.globl _main
_main:
  # ABI requires aligning stack pointer
  subq $8, %rsp
  # ABI requires storing number of vector registers used in var args functions (in this case one)
  movb $1, %al
  # Load first arg to printf from static content
  leaq _format_string(%rip), %rdi
  # Load second arg to printf
  movq $42, %rsi
  call _printf

  # exit with zero
  movq $0, %rdi
  call _exit
