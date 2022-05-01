package data;

import java.util.ArrayList;
import java.util.List;

public class LoadFile implements Load {

    private String filePathPrefix = "src/main/resources/";
    private FakeScanner propertyReader = null;

    public void setPropertyReader(FakeScanner propertyReader) {
        this.propertyReader = propertyReader;
    }

    public FakeScanner getPropertyReader() {
        return propertyReader;
    }

    public List<String> getFileData(String fileName){
        List<String> fileData = new ArrayList<String>();
        if (propertyReader == null || propertyReader.getFileName() != fileName) {
            propertyReader = new FakeScanner(filePathPrefix + fileName, null);
        }
        try {
            while (propertyReader.hasNextLine()) {
                String fileRow = propertyReader.nextLine();
                fileData.add(fileRow);
            }
            propertyReader.close();
        } catch (Exception exp) {
            System.out.println("An error occurred in " + "getFileData");
            exp.printStackTrace();
        }
        return fileData;
    }

    public List<String> getLastLines( String fileName,int numberOfLines){
        List<String> lastLines = new ArrayList<String>();
        List<String> dataFile = this.getFileData(fileName);
        for (int counter=dataFile.size() - numberOfLines;counter < dataFile.size();counter++){
            lastLines.add(dataFile.get(counter));
        }
        return lastLines;
    }
}

