package login;

import data.AllCustomers;
import data.Customer;
import engine.Dominoes;
import input.Input;

import java.util.List;

public class LogIn {
    protected Input userInput = new Input();

    public void setUserInput(Input userInput){
        this.userInput = userInput;
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

    protected boolean noAccount(String emailAddress) {
        boolean accountCreated = false;
        System.out.println("You are not a user");
        String choice = userInput.input("Would you like to register (y/n)");
        if (choice.equalsIgnoreCase("y")) {
            accountCreated = true;
            Register.register(emailAddress);
        }

        return accountCreated;
    }

    public boolean logIn() {
        boolean loggedIn = false;
        String emailAddress = userInput.input("Enter email address");
        String password = getPassword(emailAddress);
        if (password.equals("")) {
            loggedIn = noAccount(emailAddress);
        }
        else if (password.equals(userInput.input("Enter password"))){
            System.out.println("You are logged in");
            loggedIn = true;
        }
        else {
            System.out.println("Wrong password, no second chances");
        }

        return loggedIn;
    }
}