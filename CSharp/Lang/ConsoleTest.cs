using System;

namespace Lang
{
	class ConsoleTest 
	{
	    public static void Main() 
	    {
	        ConsoleTest test = new ConsoleTest();
	        
	        //test.ReadLineTest();
	        //test.ReadKeyTest();
	        test.ReadTest();
	    }

	    public void ReadLineTest()
	    {
	        Console.Write("type something and press enter: ");
	        
	        string line = Console.ReadLine();
	        Console.WriteLine("you pressed <{0}>", line);
	    }

	    public void ReadKeyTest() 
	    {
	        Console.Write("\npress any key: ");
	        
	        ConsoleKeyInfo keyInfo = Console.ReadKey();
	        Console.Write("\nYou pressed: ");
	        Console.WriteLine(keyInfo.KeyChar);
	    }

	    public void ReadTest() 
	    {
	        Console.Write("\npress any key - ");

	        int val = Console.Read();
	        Console.WriteLine("you pressed <{0}> with integer value {1}", (char) val, val);
	    }
	}
}

