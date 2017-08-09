open System

#load "Foundation.fs"
open Foundation.Util
#load "LegalParty.fs"
open LifeKernel.LegalParty
open LifeKernel.LegalPartyRepository

let madsSkjern = createPerson(1,
    { FirstName = "Mads"; LastName = "Skjern"},
    birthDateOfAge(43,3,14) |> Option.get,
    Male,
    { PrimaryContactPoint = SocialMediaAddress ("madsSkjern", Twitter);
        Address = Some {StreetName = "Korsbæk Hovedvej"; StreetNumber = "3"; PostalCode = "2300"; Country = "Danmark"}
    })  

printfn "%A" madsSkjern

Console.ReadKey(true) |> ignore

// TODO figure out if LegalPartyInfo has history or versions

// case create person twice with same id fails

// case create person

// retrive person and alter address

// viewing person at old date gets old WebAddress

// legal party info cannot be changed back in time