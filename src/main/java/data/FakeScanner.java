package data;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FakeScanner {
    private int counter = 0;
    private int maxRows;
    private List<String> data;
    private Scanner scanner;
    private String fileName;


    FakeScanner(String fileName, List<String> data){
        this.data = data;
        this.fileName = fileName;
        if (data != null) {
            maxRows = data.size();
        }
        try {
            File propertyFile = new File(fileName);
            scanner = new Scanner(propertyFile);
        } catch (IOException e) {
            System.out.println("An error occurred in " + "getPropertyReader");
            e.printStackTrace();
        }
    }

    public String getFileName(){
        return fileName;
    }

    public void setData(List<String> data) {
        this.data = data;
        maxRows = data.size();
    }

    public boolean hasNextLine() {
        boolean result;
        if (data != null){
            result = maxRows > counter;
        } else {
            result = scanner.hasNextLine();
        }
        return result;
    }


    public String nextLine() {
        String result;
        if (data != null) {
            result = data.get(counter);
            counter += 1;
        } else {
            result = scanner.nextLine();
        }
        return result;
    }

    public void close(){
        scanner.close();
    }
}
