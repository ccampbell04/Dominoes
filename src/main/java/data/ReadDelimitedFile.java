package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDelimitedFile {

    private String fileDelimiter = ",";
    private String filePathPrefix = "src/main/resources/";

    public void setFileDelimiter(String fileDelimiter){
        this.fileDelimiter = fileDelimiter;
    }

    public List<String[]> getFileData(String fileName){
        List<String[]> fileData = new ArrayList<String[]>();
        try {
            File propertyFile = new File(filePathPrefix+ fileName);
            Scanner propertyReader = new Scanner(propertyFile);
            while (propertyReader.hasNextLine()) {
                String fileRow = propertyReader.nextLine();
                fileData.add(fileRow.split(fileDelimiter));
            }
            propertyReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileData;
    }
}