module ProgramSettings =
    let version = "1.0.0.0"
    let debugMode = ref false


// You can  open modules like you can a namespace, which
// brings all their functions and values into scope.
open ProgramSettings
do printfn "Version is %s" ProgramSettings.version
debugMode := true