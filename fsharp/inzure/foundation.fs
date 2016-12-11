namespace Core

open System

module Foundation =
    type SafeDate = SafeDate of System.DateTime

    let birthDateOfAge (ageYears : int, ageMonths : int, ageDays : int) = 
        let min = new System.DateTime(1910,1,1)
        // TODO find a way of letting now be dynamic
        let now = DateTime.Now
        let birthDate = new DateTime(now.Year - ageYears, ageMonths, ageDays)
        if birthDate < min || birthDate > now
        then None
        else Some (SafeDate birthDate)


