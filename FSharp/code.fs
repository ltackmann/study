(* sandbox for test code *)
// hello world
let _ = printf "Hello world\n"

// function and value declaration
let pi = 3.14;; 
let area r = pi * r * r;;
printfn "%f\n" (area(1.0))

// nested function
let volume r = 4.0/3.0 * pi * (cube r) 
    where 
        cube x = x*x*x

// create list
1::2::3::[];;
[1;2;3];;
[1..3];;

// list insert

// iterate list
[for x in 1..3 -> x * x];;
