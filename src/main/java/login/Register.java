package login;

import input.Input;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Register {
    public static Input userInput = new Input();

    public static void register(String emailAddress) {
        System.out.println("Register for an account -");
        String firstName = userInput.input("Enter your first name");
        String surname = userInput.input("Enter your surname");
        String password = userInput.input("Enter your password");

        FileWriter fw;
        try {
            fw = new FileWriter("src/main/resources/customer.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(emailAddress + "," +
                    firstName + "," +
                    surname + "," +
                    password);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
