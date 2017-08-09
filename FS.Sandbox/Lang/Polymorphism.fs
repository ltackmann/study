open System

type A = { thing: int }
type B = { label: string }

type ThingThatShows =
    static member Show(x:A) = sprintf "%A" x
    static member Show(x:B) = sprintf "%A" x

{ thing = 98 } |> ThingThatShows.Show |> Console.WriteLine
{ label = "Car" } |> ThingThatShows.Show |> Console.WriteLine