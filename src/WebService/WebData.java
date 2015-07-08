package WebService;

import Interface.Input;
import Interface.Output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebData {

    private static URL basicUrl;

    /*
    Exception thrown from method and not dealt with to
    ensure all exceptions that are not caused by user are dealt with
    on the same abstraction level.
    */
    public static void handleWebData()
            throws IOException{

        basicUrl = new URL("http://193.142.112.220:8337/");
        String rawCompanyList = getRawCompanyList(basicUrl);
        // createCompanyArray(companyList);
        Output.showCompanyList(rawCompanyList);

        String companyIDToChoose = Input.readCompanyID();
        //TODO throw and handle here exception if no such an ID exists
        String rawMaterialList = getRawMaterialList(companyIDToChoose, basicUrl);
        Output.showMaterialsAssginedToCOmpany(rawMaterialList);

    }

    private static String getRawCompanyList(URL url)
            throws IOException{

        URL companyListUrl = new URL(url, "companyList");
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(companyListUrl.openStream())
        );

        //only one line is read since expected data is in one line
        //temporary variable required to close the stream before terminating the method
        String temporary =  bufferedReader.readLine();

        bufferedReader.close();
        return temporary;
    }

    //TODO use pattern and regex to create an array
    private static String[] createCompanyArray(String rawCompanyList){

        return null;
    }

    private static String getRawMaterialList (String companyID, URL basicURL)
            throws IOException{

        //TODO temporary solution. Check in documentation if it can be done more gracefully
        String URLEnding = "materialList?companyID=" + companyID;

        URL materialListURL = new URL(basicURL, URLEnding);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(materialListURL.openStream())
        );

        String temporary =  bufferedReader.readLine();

        bufferedReader.close();
        return temporary;
    }
}

class Company{


}

class Material{

}