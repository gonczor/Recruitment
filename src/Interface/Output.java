package Interface;

public class Output {

    public static void OrderPasswordEntry(){

        PrintStatements.print("Enter password");
    }

    public static void PasswordCorrectInfo(){

        PrintStatements.print("Password is OK.");
    }

    public static void PasswordIncorrectInfo(){

        PrintStatements.print("Password is incorrect.");
        PrintStatements.print("Terminating programme.");
    }
}
