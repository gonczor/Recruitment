import Interface.*;
import Password.*;

import java.io.Console;
import java.io.IOException;

public class Main {

    public static void main(String[] args){

        Password password = new Password();
        //Input input = new Input();
        //Output output = new Output();

        try{

            if(password.passwordIsOK(Input.getPassword())){

                Output.PasswordCorrectInfo();
            }
            else
                Output.PasswordIncorrectInfo();

        } catch (IOException e){

            System.err.print(e.getMessage());
        } catch(Exception e){

            System.err.println(e.getMessage());
        }
    }
}
