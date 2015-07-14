package WebService;

import Interface.Input;
import Interface.Output;
import UserData.LoginAttemptException;

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
    private static MaterialDetails materialDetails;

    /*
    Exception thrown from method and not dealt with to
    ensure all exceptions that are not caused by user are dealt with
    on the same abstraction level.
    */
    public static void handleWebData()
            throws IOException{

        companies = new ArrayList<>();
        materials = new ArrayList<>();
        materialDetails = new MaterialDetails();

        basicUrl = new URL("http://193.142.112.220:8337/");

        String rawCompanyList = getRawCompanyList(basicUrl);
        createCompanyList(rawCompanyList, companies);
        passCompanyListToPrint(companies);

        boolean nextIterationRequired;

        do {

            nextIterationRequired = false;

            try{

                //TODO only debugging purpose!!!
                //String companyIDToChoose = Input.readCompanyID();
                String companyIDToChoose = "1";

                String rawMaterialList = getRawMaterialList(companyIDToChoose, basicUrl);
                createMaterialList(rawMaterialList, materials);
                passMaterialListToPrint(materials);

                //TODO only for debugging purpose!!!
                //String materialID = Input.readMaterialID();
                String materialID = "1";

                String rawMaterialDetails = getRawMaterialDetails(materialID, basicUrl);
                createMaterialDetailsStructure(rawMaterialDetails, materialDetails);
                passMaterialDetailsToPrint(materialDetails);
            } catch (NoSuchIDException e){

                Output.showNoSuchIDException(e.getMessage());
                nextIterationRequired = true;
            }

        }while (nextIterationRequired);
    }

    private static String getRawCompanyList(URL url)
            throws IOException{

        URL companyListUrl = new URL(url, "companyList");
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(companyListUrl.openStream())
        );

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
            throws NoSuchIDException, IOException{

        if(!companyOfGivenIDExists(Integer.parseInt(companyID))){
            throw new NoSuchIDException();
        }

        String URLEnding = "materialList?companyID=" + companyID;

        URL materialListURL = new URL(basicURL, URLEnding);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(materialListURL.openStream())
        );

        String temporary =  bufferedReader.readLine();

        bufferedReader.close();
        return temporary;
    }

    private static boolean companyOfGivenIDExists(int companyID){

        Company company;
        for(int i = 0; i < companies.size(); i++){
            company = companies.get(i);
            if(company.ID == companyID)
                return true;
        }
        return false;
    }

    private static void createMaterialList(String rawMaterialList,
                                           ArrayList<Material> materialList) {

        int startIndex = 0;
        String patternToExtract = "\"name\":\"(\\D*)\",\"ID\":(\\d*),\"companyID\":(\\d*)";

        Pattern pattern = Pattern.compile(patternToExtract);
        Matcher matcher = pattern.matcher(rawMaterialList);

        while (matcher.find(startIndex)) {

            createNewMaterialListElement(matcher, materialList);
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

    private static String getRawMaterialDetails(String materialID, URL url)
            throws IOException, NoSuchIDException{

        if(!materialOfGivenIDExists(Integer.parseInt(materialID)))
            throw new NoSuchIDException();

        String URLEnding = "materialDetails?ID=" + materialID;

        URL materialDetailsURL = new URL(url, URLEnding);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(materialDetailsURL.openStream())
        );

        String temporary =  bufferedReader.readLine();

        bufferedReader.close();
        return temporary;
    }

    private static boolean materialOfGivenIDExists(int materialID){

        Material material;
        for(int i = 0; i < materials.size(); i ++){

            material = materials.get(i);
            if(material.ID == materialID)
                return true;
        }
        return false;
    }

    private static void createMaterialDetailsStructure(String rawMaterialData,
                                                       MaterialDetails materialDetails){

        int startIndex = 0;
        String patternToExtract =
                "\"name\":\"(\\D*)\"," +
                "\"description\":\"(\\D*)\"," +
                "\"notes\":\"(\\D*)\"," +
                "\"supplier\":\"(\\D*)\"," +
                "\"price\":(\\d*)," +
                "\"currency\":\"(\\D*)\"," +
                "\"ID\":(\\d*)";

        Pattern pattern = Pattern.compile(patternToExtract);
        Matcher matcher = pattern.matcher(rawMaterialData);

        if(matcher.find()){

            materialDetails.name = matcher.group(1);
            materialDetails.description = matcher.group(2);
            materialDetails.notes = matcher.group(3);
            materialDetails.supplier = matcher.group(4);
            materialDetails.price = Integer.parseInt(matcher.group(5));
            materialDetails.currency = matcher.group(6);
            materialDetails.ID = Integer.parseInt(matcher.group(7));
        }
    }

    private static void passMaterialDetailsToPrint(MaterialDetails materialDetails){

        //different approach here is used to reduce number of input parameters
        String stringToPass = "Name: " + materialDetails.name + "\n" +
                "ID: " + materialDetails.ID +  "\n" +
                "Description: " + materialDetails.description + "\n" +
                "Notes: " + materialDetails.notes + "\n" +
                "Supplier: " + materialDetails.supplier + "\n" +
                "Price: " + materialDetails.price + " " + materialDetails.currency + "\n";

        Output.printMaterialDetails(stringToPass);
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

class MaterialDetails{

    String name;
    String description;
    String notes;
    String supplier;
    int price;
    String currency;
    int ID;
}