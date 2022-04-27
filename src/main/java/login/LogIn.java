package login;

import data.AllCustomers;
import data.Customer;
import engine.Dominoes;
import input.Input;

import java.util.List;

public class LogIn {

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

    public boolean noAccount(String emailAddress) {
        boolean accountCreated = false;
        System.out.println("You are not a user");
        String choice = Input.input("Would you like to register (y/n)");
        if (choice.equalsIgnoreCase("y")) {
            accountCreated = true;
            Register.register(emailAddress);
        }

        return accountCreated;
    }

    public void logIn() {
        boolean loggedIn = false;
        String emailAddress = Input.input("Enter email address");
        String password = getPassword(emailAddress);
        if (password.equals("")) {
            loggedIn = noAccount(emailAddress);
        }
        else if (password.equals(Input.input("Enter password"))){
            System.out.println("You are logged in");
            loggedIn = true;
        }
        else {
            System.out.println("Wrong password, no second chances");
        }

        if (loggedIn) {
            Dominoes.setUpGame();
        }
    }

    public static void main(String[] args){
        LogIn logIn = new LogIn();
        logIn.logIn();
    }
}