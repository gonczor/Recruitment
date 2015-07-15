package UserData;

import java.io.*;

import Interface.Input;
import Interface.Output;

public class UserData {

    private static final String CORRECT_PASSWORD = "pass";
    private static final String USERNAME = "User";
    private static String userName;
    private static char[] password;
    private static int loginAttemptCount;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    /**
     * Reads user's login and password. The amount of tries while entering login is not limited, while
     * the amount of tries while entering password is limited to 3 times.
     * @throws IOException
     * @throws LoginAttemptException
     */
    public static void login()
            throws IOException, LoginAttemptException{

        readUserName();
        readPassword();
    }

    private static void readUserName()
            throws IOException{

        Output.OrderUserNameEntry();
        boolean nextIterationRequired;

        do {

            userName = Input.readUserNameFromUser();

            if(userNameIsCorrect(userName)){

                Output.UserNameCorrectInfo();
                nextIterationRequired = false;
            }
            else {

                Output.userNameIncorrectInfo();
                nextIterationRequired = true;
            }
        }while (nextIterationRequired);
    }

    private static void readPassword()
            throws LoginAttemptException, IOException{

        Output.orderPasswordEntry();
        setLoginAttemptCount();

        do {

            password = Input.readPasswordFromUser();

            if(passwordIsOK(password)) {

                Output.passwordCorrectInfo();
                return;
            }
            else {

                Output.passwordIncorrectInfo();
                incrementLoginAttemptCount();

                if (!userIsEligibleForEnteringPassword()){

                    Output.terminatingProgrammeInfo();
                    throw new LoginAttemptException();
                }
            }
        }while (userIsEligibleForEnteringPassword());
    }

    private static boolean userNameIsCorrect(String userName)
            throws IOException{

        if(userName.equals(USERNAME))
            return true;
        else
            return false;
    }

    private static boolean passwordIsOK(char[] enteredPassword)
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

    private static boolean userIsEligibleForEnteringPassword(){

        //>= comparison used instead of == to ensure that unexpected change in
        //loginAttemptCount value won't make infinite login attempts possible
        if(loginAttemptCount >= MAX_LOGIN_ATTEMPTS)
            return false;
        else
            return true;
    }

    private static void setLoginAttemptCount(){

        loginAttemptCount = 0;
    }

    private static void incrementLoginAttemptCount(){

        loginAttemptCount++;
    }

}