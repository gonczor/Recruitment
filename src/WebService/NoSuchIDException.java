package WebService;

public class NoSuchIDException extends Exception {

    @Override
    public String getMessage() {
        return "Entered ID has not been found. Please try again.";
    }
}
