import Interface.Input;
import Interface.Output;
import LocalService.LocalData;
import UserData.*;
import WebService.NoSuchIDException;
import WebService.WebData;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

    private static String materialID;
    private static LocalData localData;
    private static WebData webData;

    /**
     * Used to handle the programme and catch exceptions that are not user's fault.
     * Handles following exceptions:
     * <ul>
     *     <li>MalformedIRLException</li>
     *     <li>IOException</li>
     *     <li>LoginAttemptException</li>
     *     <li>Exception</li>
     * </ul>
     * @param args array of String variables. Has no effect here.
     */
    public static void main(String[] args){

        try{

            UserData.login();
            handleData();

        } catch (MalformedURLException e) {

            Output.showExceptionMessage(e.getMessage());
        } catch (IOException e){

            Output.showExceptionMessage(e.getMessage());
        } catch (LoginAttemptException e){

            Output.showExceptionMessage(e.getMessage());
        } catch(Exception e){

            Output.showExceptionMessage(e.getMessage());
        }
    }

    /**
     * Performs data operations after successful logging in.
     * <p>
     *     The sequence of operations is as follows:
     *     <ul>
     *         <li>download company list from website;</li>
     *         <li>Download material list from website;</li>
     *         <li>Download material details from website</li>
     *         <li>Enter, read and delete material details from local storage.</li>
     *     </ul>
     * </p>
     * @throws LoginAttemptException
     * @throws IOException
     */
    public static void handleData()
            throws LoginAttemptException, IOException {

        webData = new WebData();

        webData.getCompanyListFromWebsite();
        Output.orderEnteringCompanyID();
        handleMaterialList();
        Output.orderEnteringMaterialID();
        handleMaterialData();

        Output.askWhetherUserWantsToEnterData();

        if (userConfirms())
            handleEditingMaterialData();
    }

    private static void handleMaterialList()
            throws IOException{

        boolean nextIterationRequired;
        String companyID;

        do{

            companyID = Input.readCompanyID();
            nextIterationRequired = false;

            try {

                webData.getMaterialListFromWebsite(companyID);
            } catch (NoSuchIDException e){

                Output.showExceptionMessage(e.getMessage());
                nextIterationRequired = true;
            }
        }while (nextIterationRequired);

    }

    private static void handleMaterialData()
            throws IOException{

        boolean nextIterationRequired;

        do {

            materialID = Input.readMaterialID();
            nextIterationRequired = false;
            try{

                if(!WebData.materialOfGivenIDExists(Integer.parseInt(materialID)))
                    throw new NoSuchIDException();

                getMaterialDetailsFromWebsiteOrLocalStorage(materialID);

            } catch (NoSuchIDException e){

                nextIterationRequired = true;
                Output.showExceptionMessage(e.getMessage());
            }
        }while (nextIterationRequired);
    }

    private static void getMaterialDetailsFromWebsiteOrLocalStorage(String materialID)
            throws  NoSuchIDException, IOException{

        if(localDataExists(materialID)){

            localData = new LocalData(materialID);
            localData.getMaterialDetailsFromLocalStorage();

        } else{

            webData.getMaterialDetailsListFromWebsite(materialID);
        }
    }

    private static void handleEditingMaterialData()
            throws IOException{

        boolean nextIterationRequired;

        do{
            nextIterationRequired = false;
            Output.orderEnteringMaterialID();
            materialID = Input.readMaterialID();

            try {

                if(WebData.materialOfGivenIDExists(Integer.parseInt(materialID)))
                    throw new NoSuchIDException();

                localData = new LocalData(materialID);
                localData.enterNewMaterialDetails();

                Output.askWhetherUserWantsToResetData();
                if(userConfirms()){

                    deleteData(localData);
                    webData.getMaterialDetailsListFromWebsite(materialID);
                }
            } catch (NoSuchIDException e){

                Output.showExceptionMessage(e.getMessage());
                nextIterationRequired = true;
            }
        }while (nextIterationRequired);
    }

    private static boolean userConfirms(){

        String decision;

        //It is assumed that in final version making decision other than y/n will be impossible,
        //however for the time being additional check is being made
        do {
            decision = Input.userDecision();
            if (decision.equalsIgnoreCase("y"))
                return true;
            else if(decision.equalsIgnoreCase("n"))
                return false;
            else
                Output.informThatDecisionIsIncorrect();

        }while (true);
    }

    private static boolean localDataExists(String fileToCheck){

        return LocalData.localDataExists(fileToCheck);
    }

    private static void deleteData(LocalData localData)
            throws IOException{

        if(!localData.deleteLocalData())
            throw new IOException();
    }
}
