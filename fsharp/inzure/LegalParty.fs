namespace LifeKernel

open System
open Foundation.Util

module LegalParty =
    type Gender = Male | Female

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
    type Person = {
        Id : int;
        Name : PersonName;
        BirthDate : SafeDate;
        Gender : Gender;
        Info : LegalPartyInfo
    }

    type Company = {
        CompanyId: int;
        Name : string;
        Info : LegalPartyInfo
    }

    type LegalParty =
        | Person
        | Company



