package UserData;

/**
 * Throws exceptions after the user has failed to succesfully log in either by passing wrong login, or password.
 */
public class LoginAttemptException extends Exception {

    @Override
    public String getMessage(){

        return "Login failed.";
    }
}
