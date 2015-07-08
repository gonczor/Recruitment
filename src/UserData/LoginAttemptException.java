package UserData;

public class LoginAttemptException extends Exception {

    @Override
    public String getMessage(){

        return "Login failed.";
    }
}
