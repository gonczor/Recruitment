package Password;

import java.io.IOException;

public class Password {

    //Temporary solution. Target solution will keep encrypted password in separate file.
    private final String CORRECT_PASSWORD = "pass";

    public boolean passwordIsOK(char[] enteredPassword)
            throws IOException{

        String passwordToCheck = convertPasswordToString(enteredPassword);
        if(passwordToCheck.equals(CORRECT_PASSWORD))
            return true;
        else
            return false;
    }

    private static String convertPasswordToString(char[] enteredPassword){

        return new String(enteredPassword);
    }

}
