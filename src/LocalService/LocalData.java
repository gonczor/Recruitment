package LocalService;

import Interface.Input;
import Interface.Output;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalData {

    private static String dataDirectoryPath = "LocalMaterialData";
    //The material ID is also the name of the file in which data is stored
    private static String materialID;
    private File file;
    private File directory;

    public LocalData(String receivedMaterialID)
            throws IOException{

        materialID = receivedMaterialID;

        file = new File(dataDirectoryPath + File.separator + materialID);
        directory = new File(dataDirectoryPath);
        if(!directory.exists()){

            directory.mkdirs();
        }
        if (!file.exists()){

            file.createNewFile();
        }
    }

    public static boolean localDataExists(String fileToCheck){

        File searchedFile = new File(dataDirectoryPath + File.separator + fileToCheck);
        if(searchedFile.exists())
            return true;
        else
            return false;
    }

    public void getMaterialDetailsFromLocalStorage() throws IOException{

        Charset charset = Charset.defaultCharset();
        Path path = Paths.get(dataDirectoryPath + File.separator + materialID);
        BufferedReader reader = Files.newBufferedReader(path, charset);

        String line;
        String dataToWrite = "";

        while ((line = reader.readLine()) != null){
            dataToWrite += line;
            dataToWrite += "\n";
        }

        Output.printMaterialDetails(dataToWrite);
    }

    public void enterNewMaterialDetails()
            throws IOException{

        String newDetailsToEnter = createStringToWrite(materialID);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(newDetailsToEnter);
        fileWriter.flush();
        fileWriter.close();
    }

    private static String createStringToWrite(String ID){

        String newDetailsToEnter = "";
        String detailName;

        detailName = "Name: ";
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += detailName + Input.getDetail() + "\n";

        //ID is cannot be edited, thus it is not read from user
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += "ID: " + ID + "\n";

        detailName = "Description: ";
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += detailName + Input.getDetail() + "\n";

        detailName = "Notes: ";
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += detailName + Input.getDetail() + "\n";

        detailName = "Supplier; ";
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += detailName + Input.getDetail() + "\n";

        detailName = "Price: ";
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += detailName + Input.getDetail() + " ";

        detailName = "Currency: ";
        Output.getDetailToEnterField(detailName);
        newDetailsToEnter += " " + Input.getDetail() + "\n";

        return newDetailsToEnter;
    }

    public boolean deleteLocalData(){

        return file.delete();
    }
}
