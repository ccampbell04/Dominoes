import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDelimitedFile {

    private String fileDelimitor = ",";
    private String filePathPrefix = "src/main/resources/";

    public void setFileDelimitor(String fileDelimitor){
        this.fileDelimitor = fileDelimitor;
    }

    public List<String[]> getFileData(String fileName){
        List<String[]> fileData = new ArrayList<String[]>();
        try {
            File propertyFile = new File(filePathPrefix+ fileName);
            Scanner propertyReader = new Scanner(propertyFile);
            while (propertyReader.hasNextLine()) {
                String fileRow = propertyReader.nextLine();
                fileData.add(fileRow.split(fileDelimitor));
            }
            propertyReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileData;
    }
}