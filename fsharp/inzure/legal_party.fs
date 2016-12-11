open System
open LegalParty





type Gender = Make | Female

[<StructuralEquality; NoComparison>]
type PersonName = {
    FirstName : string;
    LastName : string
}

type SocialMediaChannel = Facebook | Linkedin | Twitter 

type ContactPoint =
    | PhysicalAddress
    | WebAddress of string
    | EmailAddress of string
    | SocialMediaAddress of string * SocialMediaChannel

// TODO represent country as a type
type PhysicalAddress = {
    StreetName : string;
    StreetNumber : string;
    PostalCode : string;
    Country : string
}

// PrimaryContactPoint, List<ContactPoint>
type LegalPartyInfo = {
    PrimaryContactPoint : ContactPoint
    Address : PhysicalAddress option
}

// TODO deathDate, Gender
[<NoEquality; NoComparison>]
type Person = {
    Id : int;
    Name : PersonName;
    BirthDate : SafeDate;
    Gender : Gender;
    Info : LegalPartyInfo
}

[<NoEquality; NoComparison>]
type Company = {
    Id : int;
    Name : string;
    Info : LegalPartyInfo
}

type LegalParty =
    | Person
    | Company

let madsSkjern = {
    Id=1; 
    Name = { FirstName = "Mads";  
        LastName = "Skjern"
    };  
    BirthDate = birthDateOfAge(43; 3; 14); 
    Gender = Male; 
    Info = { PrimaryContactPoint = ("madsSkjern", Twitter);
        Address = {StreetName = "Korsbæk Hovedvej"; StreetNumber = "3"; PostalCode = "2300"; Country = "Danmark"}
    }    
}

let printBlogInfo (owner, title, url) = printfn “%s’s blog [%s] is online at ‘%s'” owner title url;;



