package Interface;

public class Output {

    /**
     * Displays <i>Please, enter user name.</i> message.
     */
    public static void OrderUserNameEntry(){

        PrintStatements.print("Please, enter user name.");
    }

    /**
     * Displays <i>Entered user name is correct.</i> message.
     */
    public static void UserNameCorrectInfo() {

        PrintStatements.print("Entered user name is correct.");
    }

    /**
     * Displays <br>
     * <i>
     *     Entered user name does not exist in database.<br>
     *     Please try again.
     * </i>
     * message.
     */
    public static void userNameIncorrectInfo() {

        PrintStatements.print("Entered user name does not exist in database.");
        PrintStatements.print("Please try again.");
    }

    /**
     * Displays <i>Enter password</i> message.
     */
    public static void orderPasswordEntry(){

        PrintStatements.print("Enter password");
    }

    /**
     * Displays <i>Password is OK.</i> message
     */
    public static void passwordCorrectInfo(){

        PrintStatements.print("Password is OK.");
    }

    /**
     * Displays <i>Password is incorrect.</i> message
     */
    public static void passwordIncorrectInfo(){

        PrintStatements.print("Password is incorrect.");
    }

    /**
     * Displays <i>Terminating programme message</i> message.
     */
    public static void terminatingProgrammeInfo(){

        PrintStatements.print("Terminating programme.");
    }

    /**
     * Displays single element of a list of companies in two columns. The first one with ID, and second one with its name.
     * @param name the name of a company to display
     * @param ID the ID of a company to display.
     */
    public static void showCompanyList(String name, int ID){

        PrintStatements.print("ID: " + ID + " name: " + name);
    }

    /**
     * Displays <i>Please, enter the ID of the company you want to see</i> message.
     */
    public static void orderEnteringCompanyID(){

        PrintStatements.print("Please, enter the ID of the company you want to see");
    }

    /**
     * Displays single element of a list of materials in three columns.
     * @param materialName string containing the name of a material
     * @param id int containing the ID of a material
     * @param companyID int containing the ID of a company owning the material
     */
    public static void showMaterialList(String materialName, int id, int companyID){

        PrintStatements.print("Name: " + materialName +" id: " + id + " company ID: " +companyID);
    }

    /**
     * Displays info about a material: its name, ID, id of the owning company, details,
     * notes, supplier, price and currency. Info is displayed in following pattern:<br>
     *     The name of a parameter: the value of a parameter.
     * @param details formatted string with all info about a material
     */
    public static void printMaterialDetails(String details){

        PrintStatements.print(details);
    }

    /**
     * Displays <i>Please enter material I</i> message.
     */
    public static void orderEnteringMaterialID(){

        PrintStatements.print("Please enter material ID");
    }

    /**
     * Displays info about a thrown exception.
     * @param error the return value of getMessage() method from any exception.
     */
    public static void showExceptionMessage(String error){

        System.err.println(error);
    }

    /**
     * Displays <br>
     *     <i>Do you want to enter new data?<br>
     *         y/n</i>
     *         <br>
     * message.
     */
    public static void askWhetherUserWantsToEnterData(){

        PrintStatements.print("Do you want to enter new data?");
        PrintStatements.print("y/n");
    }

    /**
     * Displays <i>Incorrect decision.</i> message.
     */
    public static void informThatDecisionIsIncorrect(){

        PrintStatements.print("Incorrect decision.");
    }

    /**
     * Displays the name of a details.
     * @param detailName
     */
    public static void getDetailToEnterField(String detailName){

        PrintStatements.print(detailName);
    }

    /**
     * Displays<br>
     *     <i>Do you want to reset data?<br>
     *         y/n<br></i>
     * message.
     */
    public static void askWhetherUserWantsToResetData(){

        PrintStatements.print("Do you want to reset data?");
        PrintStatements.print("y/n");
    }
}
