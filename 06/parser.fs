(* parser.fs 
 * compile: fsc -o parser.exe parser.fs 
 * run: mono parser.exe
 *)
module Assembler.Parser

open System.IO 

// read file into sequence
let readFile filePath = File.ReadAllLines(filePath) |> Seq.cast<string>


//let isCode s =


//printfn "%A" 
// Parser.readFile @"test/max/MaxL.asm" 

