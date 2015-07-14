package WebService;

import Interface.Input;
import Interface.Output;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebData {

    private static URL basicUrl;
    private static ArrayList<Company> companies;
    private static ArrayList<Material> materials;

    /*
    Exception thrown from method and not dealt with to
    ensure all exceptions that are not caused by user are dealt with
    on the same abstraction level.
    */
    public static void handleWebData()
            throws IOException{

        companies = new ArrayList<>();
        materials = new ArrayList<>();

        basicUrl = new URL("http://193.142.112.220:8337/");
        String rawCompanyList = getRawCompanyList(basicUrl);
        createCompanyList(rawCompanyList, companies);
        passCompanyListToPrint(companies);

        //TODO only debugging purpose!!!
        //String companyIDToChoose = Input.readCompanyID();
        String companyIDToChoose = "1";

        //TODO throw and handle here exception if no such an ID exists
        String rawMaterialList = getRawMaterialList(companyIDToChoose, basicUrl);
        createMaterialList(rawMaterialList, materials);

    }

    private static String getRawCompanyList(URL url)
            throws IOException{

        URL companyListUrl = new URL(url, "companyList");
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(companyListUrl.openStream())
        );

        /*
        Even though only one line of data is expected,
        the possibility of reading more in case of the change of
        data structure is assured here
         */
        String temporary = "";
        temporary = bufferedReader.readLine();

        bufferedReader.close();
        return temporary;
    }

    private static void createCompanyList(String rawCompanyList,
                                          ArrayList<Company> companyList){

        int startIndex = 0;
        String patternToExtract = "\"(\\w*)\":\"(\\D*)\",\"(\\w*)\":(\\d*)";

        Pattern pattern = Pattern.compile(patternToExtract);
        Matcher matcher = pattern.matcher(rawCompanyList);

        while (matcher.find(startIndex)){

            createNewCompanyListElement(matcher, companyList);
            startIndex = matcher.end();
        }
    }

    private static void createNewCompanyListElement(Matcher matcher,
                                             ArrayList<Company> arrayList){
        Company company = new Company();
        company.name = matcher.group(2);
        company.ID = Integer.parseInt(matcher.group(4));
        arrayList.add(company);
    }

    //Method created to avoid importing WebData in Output
    private static void passCompanyListToPrint(ArrayList<Company> companyList){

        Company temporary;
        for (int i = 0; i < companyList.size(); i++) {

            temporary = companyList.get(i);
            Output.showCompanyList(temporary.name, temporary.ID);
        }
    }

    private static String getRawMaterialList (String companyID, URL basicURL)
            throws IOException{

        String URLEnding = "materialList?companyID=" + companyID;

        URL materialListURL = new URL(basicURL, URLEnding);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(materialListURL.openStream())
        );

        String temporary =  bufferedReader.readLine();

        bufferedReader.close();
        return temporary;
    }

    private static void createMaterialList(String rawMaterialList,
                                           ArrayList<Material> materialList) {

        int startIndex = 0;
        String patternToExtract = "\"name\":\"(\\D*)\",\"ID\":(\\d*),\"companyID\":(\\d*)";

        Pattern pattern = Pattern.compile(patternToExtract);
        Matcher matcher = pattern.matcher(rawMaterialList);

        while (matcher.find(startIndex)) {

            createNewMaterialListElement(matcher, materialList);
            System.out.println(matcher.group(1) + ", " + matcher.group(2) +", " + matcher.group(3));
            startIndex = matcher.end();
        }
    }

    private static void createNewMaterialListElement(Matcher matcher,
                                                     ArrayList<Material> arrayList){
        Material material = new Material();

        material.name = matcher.group(1);
        material.ID = Integer.parseInt(matcher.group(2));
        material.providingCompanyID = Integer.parseInt(matcher.group(3));
        arrayList.add(material);
    }

    private static void passMaterialListToPrint(ArrayList<Material> materials){

        Material material;
        for (int i = 0; i < materials.size(); i++){
            material = materials.get(i);
            Output.showMaterialList(material.name, material.ID, material.providingCompanyID);
        }
    }
}

class Company{

    String name;
    int ID;
}

class Material{

    String name;
    int providingCompanyID;
    int ID;
}