open System
open Fuchu

let simpleTest = 
    testCase "A simple test" <| 
        fun _ -> Assert.Equal("2+2", 4, 2+2)

[<EntryPoint>]
let main argv =
    printfn "Running tests" 
    run simpleTest
    //Console.ReadLine() |> ignore
    0 // return an integer exit code
