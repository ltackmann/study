using System;

class MainTest 
{
    public static int Main(string[] args) 
    {
        Console.WriteLine("Main method called with {0} arguments", args.Length);
        
        Console.WriteLine("The CommandLine was {0}", Environment.CommandLine);
        return 0;
    }
}

