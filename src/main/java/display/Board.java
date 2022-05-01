package display;

import engine.Dominoes;
import engine.Tile;
import input.Input;

public class Board {

    protected static Input userInput = new Input();
    static Dominoes dominoes = Dominoes.getInstance();

    public static void displayBoard(Tile startTile, Tile leftTile, Tile rightTile) {
        if (leftTile == null && rightTile == null) {
            displayStart(startTile);
        } else if (leftTile == null) {
            leftNull(startTile, rightTile);
        } else if (rightTile == null){
            rightNull(startTile, leftTile);
        }else{
            displayEdgeTiles(leftTile, rightTile);
        }
    }

    private static void displayStart(Tile startTile) {
        System.out.println("-----------------");
        System.out.println("Starting tile");
        System.out.println(startTile);
    }

    private static void leftNull(Tile startTile, Tile rightTile) {
        System.out.println("-----------------");
        System.out.println("The tiles on the edge of the board are\n");
        System.out.println("Left: " + startTile + "   |   " + "Right: " + rightTile);
    }

    private static void rightNull(Tile startTile, Tile leftTile) {
        System.out.println("-----------------");
        System.out.println("The tiles on the edge of the board are\n");
        System.out.println("Left: " + leftTile + "   |   " + "Right: " + startTile);
    }

    private static void displayEdgeTiles(Tile leftTile, Tile rightTile) {
        System.out.println("-----------------");
        System.out.println("The tiles on the edge of the board are\n");
        System.out.println("Left: " + leftTile + "   |   " + "Right: " + rightTile);
    }

    public static void intro() {
        System.out.println("Welcome to dominoes");
        System.out.println("The game will be score like this -");
        System.out.println(" - The amount of points each player receives is the total of all tiles remaining in the opponents hand rounded to nearest 5");
        System.out.println("A few things to note");
        System.out.println(" - If a player cannot play, they continue picking up dominoes until they can play");
        System.out.println(" - This game is a 1 vs 1 between you and the computer");
        System.out.println(" - A tile with 0 on it e.g. [0 | 4] is the same as blank and therefore a wild card");
        userInput.input("Press any key to continue");
    }
}
