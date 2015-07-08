package Interface;


import java.io.Console;
import java.io.IOException;

public class Input {

    /*
    Calling System.console() in each method is necessary as they are static
     */
    //TODO refactor System.console()
    private static Console console;


    public static char[] readPasswordFromUser()
            throws IOException{

        console = System.console();
        char[] temporary = console.readPassword();
        return temporary;
    }

    public static String readUserNameFromUser(){

        console = System.console();
        String temporary = console.readLine();
        return temporary;
    }

    public static String readCompanyID(){

        console = System.console();
        return console.readLine();
    }
}
