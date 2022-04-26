import java.util.List;
import java.util.Scanner;

public class LogIn {
    private Scanner userInput = new Scanner(System.in);

    private String input(String message) {
        System.out.println(message);
        return userInput.nextLine();
    }

    private String getPassword(String emailAddress){
        AllCustomers allCustomers = new AllCustomers();
        String password = "";
        List<Customer> listOfCustomers = allCustomers.getListOfCustomers();
        for (Customer customer :listOfCustomers){
            if (customer.getEmailAddress().equals(emailAddress)){
                password = customer.getPassword();
            }
        }
        return password;
    }

    public void logIn() {
        String emailAddress = input("Enter email address");
        String password = getPassword(emailAddress);
        if (password == "") {
            System.out.println("You are not a user");
        }
        else if (password.equals(input("Enter password"))){
            System.out.println("You are logged in");
        }
        else {
            System.out.println("Wrong password, no second chances");
        }
    }

    public static void main(String[] args){
        LogIn logIn = new LogIn();
        logIn.logIn();
    }
}