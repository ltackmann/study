open System

#load "Foundation.fs"
open Foundation.Util

let birthDate = birthDateOfAge(43,3,14);;

printfn "%A" <| Option.get birthDate

Console.ReadKey(true) |> ignore


// Create product

// Create an agreement on product
// - Agreements are between a life insurance company and a organization/company/person
// - Product has a start and a end date
// - Product defines covers
// - Covers can be risk or savings

// Create person
// - A person has one or more jobs
// - Each job pays a salary (fixed, periodic, lump sum)


// Create policy for person on agreement
// - NewDefaultPolicyEvent
// - Trigger policy leter sent via primary channel

// Set contribution parameters on policy
// - Event

// Advance policy one month
// - check that contributions are deducted
