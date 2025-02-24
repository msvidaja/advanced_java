using System;

public class CommandLineArguments
{
    public static void Main(string[] args)
    {
        // 1. Start
        // 2. Check if the required number of command-line arguments is provided.
        if (args.Length != 2)
        {
            Console.WriteLine("Error: Incorrect number of arguments. Please provide two arguments.");
            return; // 3. Terminate if incorrect number of arguments
        }

        // 4. Assign the first command-line argument to a string variable.
        string myString = args[0];

        // 5. Try to convert the second command-line argument into an integer variable.
        int myInt;
        if (int.TryParse(args[1], out myInt))
        {
            // 6. If conversion is successful, display both variables
            Console.WriteLine("String: " + myString);
            Console.WriteLine("Integer: " + myInt);
        }
        else
        {
            // 7. If conversion fails, display an error message
            Console.WriteLine("Error: Failed to convert the second argument to an integer.");
        }

        // 8. End
    }
}