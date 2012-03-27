open System
open System.Text.RegularExpressions

// http://www.quanttec.com/fparsec/tutorial.html
// TODO move to utils module
let readLines(filePath) = IO.File.ReadLines(filePath)

let (|Integer|_|) (str: string) =
   let mutable intvalue = 0
   if System.Int32.TryParse(str, &intvalue) then Some(intvalue)
   else None

let (|Float|_|) (str: string) =
   let mutable floatvalue = 0.0
   if System.Double.TryParse(str, &floatvalue) then Some(floatvalue)
   else None

let parse str =
   match str with
     | Integer i -> printfn "%d : Integer" i
     | Float f -> printfn "%f : Floating point" f
     | _ -> printfn "%s : Not matched." str

let main() =
    let sourceLines = readLines("example1.dart")
    for line in sourceLines do
        parse line
    // comment the line out below to see terminal out put in MS Windows
    //Console.ReadKey() |> ignore

main() 

