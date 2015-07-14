package Interface;

import java.util.ArrayList;

public class Output {

    public static void OrderUserNameEntry(){

        PrintStatements.print("Please, enter user name");
    }

    public static void UserNameCorrectInfo() {

        PrintStatements.print("Entered user name is correct.");
    }

    public static void UserNameIncorrectInfo() {

        PrintStatements.print("Entered user name does not exist in database.");
        PrintStatements.print("Please try again.");
    }

    public static void OrderPasswordEntry(){

        PrintStatements.print("Enter password");
    }

    public static void PasswordCorrectInfo(){

        PrintStatements.print("Password is OK.");
    }

    public static void PasswordIncorrectInfo(){

        PrintStatements.print("Password is incorrect.");
    }

    public static void TerminatingProgrammeInfo(){

        PrintStatements.print("Terminating programme.");
    }

    //TODO create an array with company lists
    public static void showCompanyList(String name, int ID){

        PrintStatements.print("Companies with their ID:");
        PrintStatements.print("ID: " + ID + " name: " + name);
    }

    public static void showMaterialList(String materialName, int id, int companyID){

        PrintStatements.print("Materials available:");
        PrintStatements.print("Name: " + materialName +" id: " + id + "company ID: " +companyID);
    }
}
