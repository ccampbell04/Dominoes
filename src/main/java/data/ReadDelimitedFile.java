package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDelimitedFile {

    private String fileDelimiter = ",";

    private Load loadFile = new LoadFile();

    public void setFileDelimiter(String fileDelimiter){
        this.fileDelimiter = fileDelimiter;
    }

    public void setLoadFile(Load loadFile) {
        this.loadFile = loadFile;
    }

    public List<String[]> getFileData(String fileName) {
        List<String[]> delimtedData = new ArrayList<String[]>();

        List<String> fileData = loadFile.getFileData(fileName);
        for (String row : fileData) {
            delimtedData.add(row.split(fileDelimiter));
        }
        return delimtedData;
    }
}