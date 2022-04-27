package input;

import java.util.Scanner;

public class Input {
    private static Scanner userInput = new Scanner(System.in);

    public static String input(String message) {
        System.out.println(message);
        return userInput.nextLine();
    }
}
