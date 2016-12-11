open System

#load "Foundation.fs"
open Foundation.Util
#load "LegalParty.fs"
open LifeKernel.LegalParty

let madsSkjern = {
    Id = 1;
    Name = { FirstName = "Mads"; LastName = "Skjern"}
    BirthDate = birthDateOfAge(43,3,14) |> Option.get;
    Gender = Male
    Info = { PrimaryContactPoint = SocialMediaAddress ("madsSkjern", Twitter);
        Address = Some {StreetName = "Korsbæk Hovedvej"; StreetNumber = "3"; PostalCode = "2300"; Country = "Danmark"}
    }  
}

printfn "%A" madsSkjern

Console.ReadKey(true) |> ignore