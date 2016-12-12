namespace LifeKernel

open System
open Foundation.Util

module LegalParty =
    type Gender = Male | Female

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

    type LegalPartyInfo = {
        PrimaryContactPoint : ContactPoint
        Address : PhysicalAddress option
    }

    // TODO deathDate, version of LegalPartyInfo
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

module LegalPartyRepository =
    open LegalParty 
    let mutable private persons : Map<int, Person> = Map.empty

    let createPerson(personId : int, personName : PersonName, birthDate : SafeDate, gender : Gender, personInfo : LegalPartyInfo) =
        match persons.TryFind(personId) with
        | Some(person) -> None // person already exists
        | None -> 
            let newPerson = { Id = personId; 
                Name = personName; 
                BirthDate = birthDate;
                Gender = gender; 
                Info = personInfo
            } 
            persons <- persons.Add(personId, newPerson)
            Some newPerson
    
    let updateLegalPartyInfo(personId : int, personInfo : LegalPartyInfo) =
        match persons.TryFind(personId) with
        | None -> None     // cannot update info of non existing person
        | Some(person) ->  // person already exists
            let clonedPerson = { Id = person.Id; 
                Name = person.Name; 
                BirthDate = person.BirthDate;
                Gender = person.gender; 
                Info = personInfo
            } 
            persons <- persons.Add(personId, newPerson)
            Some newPerson
