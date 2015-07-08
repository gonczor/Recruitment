import Interface.*;
import UserData.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        UserData userData = new UserData();

        try{

            userData.Login();
        } catch (IOException e){

            System.err.print(e.getMessage());
        } catch (LoginAttemptException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){

            System.err.println(e.getMessage());
        }
    }
}
