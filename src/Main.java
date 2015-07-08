import UserData.*;
import WebService.WebData;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args){

        try{

            UserData.login();
            WebData.handleWebData();
        } catch (MalformedURLException e) {

            System.err.println(e.getMessage());
        } catch (IOException e){

            System.err.print(e.getMessage());
        } catch (LoginAttemptException e){
            
            System.err.println(e.getMessage());
        } catch(Exception e){

            System.err.println(e.getMessage());
        }
    }
}
