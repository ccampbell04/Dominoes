package engine;

import display.Board;
import input.Input;
import login.LogIn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dominoes {
    private final List<Tile> deck = new ArrayList<>();
    private final Hand initialUserHand = new Hand();
    private final Hand initialComputerHand = new Hand();
    private Tile startTile;
    private Tile leftTile;
    private Tile rightTile;
    public int userScore = 0;
    public int computerScore = 0;

    public Tile pickUp() {
        return deck.remove(0);
    }

    public boolean isDeckEmpty() {
        return deck.size() == 0;
    }

    public Tile getStartTile() {
        return startTile;
    }

    private void setStartTile() {
        startTile = deck.remove(0);
    }

    public Tile getLeftTile() {
        return leftTile;
    }

    public void setLeftTile(Tile leftTile) {
        this.leftTile = leftTile;
    }

    public Tile getRightTile() {
        return rightTile;
    }

    public void setRightTile(Tile rightTile) {
        this.rightTile = rightTile;
    }

    private void generateDeck() {
        for (int i = 0; i < 7 ; i++) {
            for (int x = i; x < 7; x++) {
                deck.add(new Tile(i, x));
            }
        }
    }

    private void dealHands() {
        initialUserHand.clear();
        initialComputerHand.clear();
        for (int i = 0; i < 7; i++) {
            initialUserHand.addTile(deck.remove(0));
            initialComputerHand.addTile(deck.remove(0));
        }
    }

    private int setStartPlayer() {
        Tile highestUserTile = new Tile(0,0);
        Tile highestComputerTile = new Tile(0,0);

        for (Tile tile : initialUserHand.getHand()){
            if (tile.isDouble()) {
                highestUserTile = tile;
            }
        }

        if (highestUserTile.getTotal() == 0) {
            highestUserTile = checkHighestNonDouble(initialUserHand);
        }

        for (Tile tile : initialComputerHand.getHand()){
            if (tile.isDouble()) {
                highestComputerTile = tile;
            }
        }

        if (highestComputerTile.getTotal()==0) {
            highestComputerTile = checkHighestNonDouble(initialComputerHand);
        }

        int result = highestUserTile.compareTo(highestComputerTile);

        if (result > 0) {
            return 0;
        }

        return 1;
    }

    private Tile checkHighestNonDouble(Hand hand) {
        Tile highestTile = new Tile(0,0);
        int result;
        for (Tile tile: hand.getHand()) {
            result = tile.compareTo(highestTile);
            if (result > 0) {
                highestTile = tile;
            }
        }

        return highestTile;
    }

    public int getLeftNum() {
        if (leftTile != null) {
            return leftTile.getSide1();
        }
        return startTile.getSide1();
    }

    public int getRightNum() {
        if (rightTile != null) {
            return rightTile.getSide2();
        }
        return startTile.getSide2();
    }

    private boolean checkGameWinner(Hand hand) {
        boolean winner = false;

        if (hand.length() == 0) {
            winner = true;
        }

        return winner;
    }

    private void play(int startPlayer) {
        boolean gameOver = false;
        final String userTurn = "--- USER TURN ---";
        final String compTurn = "--- COMPUTER TURN ---";
        final String scores = "--- SCORES ---";

        Player player1 = new Player(initialUserHand, PlayerType.USER, userTurn);
        Player player2 = new Player(initialComputerHand, PlayerType.COMPUTER, compTurn);

        if (startPlayer == 1) {
            player1.setPlayerHand(initialComputerHand);
            player1.setType(PlayerType.COMPUTER);
            player1.setHeader(compTurn);
            player2.setPlayerHand(initialUserHand);
            player2.setType(PlayerType.USER);
            player2.setHeader(userTurn);
        }

        while(!gameOver) {
            player1.printHeader();
            player1.turn();
            gameOver = checkGameWinner(player1.getPlayerHand());

            if (gameOver) {
                break;
            }

            player2.printHeader();
            player2.turn();
            gameOver = checkGameWinner(player2.getPlayerHand());
        }

        player1.setScore(player2.getPlayerHand());
        player2.setScore(player1.getPlayerHand());

        System.out.println(scores);
        System.out.println(player1.getType() + " score: " + player1.getScore());
        System.out.println(player2.getType() + " score: " + player2.getScore());
    }

    public void setUpGame() {
        String choice = "y";
        while (choice.equals("y")) {
            while (userScore < 250 && computerScore < 250) {
                Board.intro();
                generateDeck();
                Collections.shuffle(deck);
                setStartTile();
                dealHands();
                int startPlayer = setStartPlayer();
                play(startPlayer);
                System.out.println("First to 250 wins!");
            }
            choice = Input.input("Thanks for playing!\n Would you like to play again (y/n)");
        }
    }

    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        Dominoes dominoes = new Dominoes();

        if (logIn.logIn()) {
            dominoes.setUpGame();
        }
    }
}
