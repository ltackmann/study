// a reference cell is a pointer to variable which you modify on the heap.
// To use reference cells you use (:=) to assign a new value and (!) to dereference. 
open System
let refCell = ref 42;;
printfn "\nValue is ‘%i'\n" !refCell

refCell := -1;;
printfn "\nValue is ‘%i'\n" !refCell

Console.ReadKey(true) |> ignore