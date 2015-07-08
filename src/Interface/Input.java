package Interface;


import java.io.Console;
import java.io.IOException;

public class Input {

    public static char[] getPassword()
            throws IOException{

        Console console = System.console();

        return console.readPassword();
    }
}
