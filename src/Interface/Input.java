package Interface;


import java.io.Console;
import java.io.IOException;

public class Input {

    /*
    Calling System.console() in each method is necessary as they are static
     */
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
}
