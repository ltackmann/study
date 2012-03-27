class VarTest
{
    public static void Main()
    {
        // use reserved word as keyword
        string @string = "this is a ";
        System.Console.WriteLine(@string + "legal string");
    
        // declare and assign multiple variables
        int i, j;
        i = j = 42;
        System.Console.WriteLine("is {0} == {1}: {2}", i, j, i == j);
    }
}
