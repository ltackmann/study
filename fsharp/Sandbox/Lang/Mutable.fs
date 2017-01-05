//  F# has the mutable keyword. This creates a variable who’s value can be changed by using the left arrow operator (<-).
open System

let mutable x = "the original value.\n";;
printfn "x’s value is ‘%s'" x;;

x <- "the new one.";;
printfn "x’s value is now ‘%s'\n" x;;

Console.ReadKey(true) |> ignore