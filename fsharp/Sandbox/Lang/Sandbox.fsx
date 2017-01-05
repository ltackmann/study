#I @"../../packages/Fuchu/lib"
#r "Fuchu.dll"

//#load @"Sandbox.fs"

open Fuchu

let simpleTest = 
    testCase "A simple test" <| 
        fun _ -> Assert.Equal("2+2", 4, 2+2)

run simpleTest