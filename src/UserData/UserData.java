package UserData;

import java.io.Console;
import java.io.IOException;
import Interface.Input;
import Interface.Output;

public class UserData {

    //Temporary solution. Target solution will keep encrypted password in separate file.
    private final String CORRECT_PASSWORD = "pass";
    private final String USERNAME = "User";
    private String userName;
    private char[] password;
    private int loginAttemptCount;
    private final int MAX_LOGIN_ATTEMPTS = 3;

    public void login()
            throws IOException, LoginAttemptException{
/*
        //TODO
        //refactor code so that it is cleaner


        Output.OrderUserNameEntry();
        do {

            userName = Input.readUserNameFromUser();

            if(userNameIsCorrect(userName)){

                Output.UserNameCorrectInfo();
            }
            else {

                Output.UserNameIncorrectInfo();
            }
        }while (!userNameIsCorrect(userName));

        Output.OrderPasswordEntry();
        setLoginAttemptCount();
        do {

            password = Input.readPasswordFromUser();

            if(passwordIsOK(password)){

                Output.PasswordCorrectInfo();
                return;
            }

            else {

                Output.PasswordIncorrectInfo();
                incrementLoginAttemptCount();
                //TODO think of a way to improve this part
                if (!userIsEligibleForEnteringPassword()){

                    Output.TerminatingProgrammeInfo();
                    throw new LoginAttemptException();
                }
            }
        }while (userIsEligibleForEnteringPassword());
*/
    }

    private boolean userNameIsCorrect(String userName){

        if(userName.equals(getUserNameFromFile()))
            return true;
        else
            return false;
    }

    private String getUserNameFromFile(){

        //TODO
        //implement reading user name from file. this is temporary solution only
        return USERNAME;
    }

    public boolean passwordIsOK(char[] enteredPassword)
            throws IOException{

        String passwordToCheck = convertPasswordToString(enteredPassword);
        if(passwordToCheck.equals(getPasswordFromFile()))
            return true;
        else
            return false;
    }

    private static String convertPasswordToString(char[] enteredPassword){

        return new String(enteredPassword);
    }

    private String getPasswordFromFile(){

        //TODO
        //implement reading password from file
        return CORRECT_PASSWORD;
    }

    private boolean userIsEligibleForEnteringPassword(){

        //>= comparison used instead of == to ensure that unexpected change in
        //loginAttemptCount value won't make infinite login attempts possible
        if(loginAttemptCount >= MAX_LOGIN_ATTEMPTS)
            return false;
        else
            return true;
    }

    private void setLoginAttemptCount(){

        loginAttemptCount = 0;
    }

    private void incrementLoginAttemptCount(){

        loginAttemptCount++;
    }

}