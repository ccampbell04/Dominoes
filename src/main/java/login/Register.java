package login;

import input.Input;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Register {
    public static void register(String emailAddress) {
        System.out.println("Register for an account -");
        String firstName = Input.input("Enter your first name");
        String surname = Input.input("Enter your surname");
        String password = Input.input("Enter your password");

        FileWriter fw = null;
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
