package input;

import java.util.Scanner;

public class Input {
    private Scanner userInput = new Scanner(System.in);

    public void setUserInput(Scanner userInput){
        this.userInput = userInput;
    }

    public String input(String message) {
        System.out.println(message);
        return userInput.nextLine();
    }
}
