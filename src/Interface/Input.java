package Interface;


import java.io.Console;
import java.io.IOException;

public class Input {


    private static Console console;

    /**
     * Reads password without echoing it on keyboard.
     * @return password as char[]
     * @throws IOException
     */
    public static char[] readPasswordFromUser()
            throws IOException{

        console = System.console();
        char[] temporary = console.readPassword();
        return temporary;
    }

    /**
     * Reads login from user
     * @return login as a String
     */
    public static String readUserNameFromUser(){

        console = System.console();
        String temporary = console.readLine();
        return temporary;
    }

    /**
     * Reads company ID
     * @return company ID as a String
     */
    public static String readCompanyID(){


        console = System.console();
        return console.readLine();

        //return "1";
    }

    /**
     * Reads material ID
     * @return material ID as a String
     */
    public static String readMaterialID(){

        console = System.console();
        return console.readLine();
    }

    /**
     * Returns y/n depending on the user's decision
     * @return y/n as a String
     */
    public static String userDecision(){

        console = System.console();
        return console.readLine();

    }

    /**
     * Returns description of a single detail of a material to be inserted to local database.
     * @return detail description as a String
     */
    public static String getDetail(){

        console = System.console();
        return console.readLine();
    }
}
