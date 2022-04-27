package display;

import engine.Dominoes;
import engine.Tiles;
import input.Input;

public class Board {

    static Dominoes dominoes = new Dominoes();

    public static void displayBoard(Tiles startTile, Tiles leftTile, Tiles rightTile) {
        if (leftTile == null && rightTile == null) {
            displayStart(startTile);
        } else {
            displayEdgeTiles(leftTile, rightTile);
        }
    }

    private static void displayStart(Tiles startTile) {
        System.out.println("-----------------");
        System.out.println("Starting tile");
        System.out.println(startTile);
    }

    private static void displayEdgeTiles(Tiles leftTile, Tiles rightTile) {
        System.out.println("-----------------");
        System.out.println("The tiles on the edge of the board are\n");
        System.out.println("Left: " + leftTile + "   |   " + "Right: " + rightTile);
    }

    public static void intro() {
        System.out.println("Welcome to dominoes");
        System.out.println("A few things to note");
        System.out.println(" - This game is a 1 vs 1 between you and the computer");
        System.out.println(" - A tile with 0 on it e.g. [0 | 4] is the same as blank and therefore a wild card");
        Input.input("Press any key to continue");
    }
}
