package engine;

import display.Board;
import input.Input;
import login.LogIn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dominoes {
    final String userTurn = "--- USER TURN ---";
    final String compTurn = "--- COMPUTER TURN ---";
    final String scores = "--- SCORES ---";
    protected final List<Tile> deck = new ArrayList<>();
    protected Hand initialUserHand = new Hand();
    private  Hand initialComputerHand = new Hand();
    private Tile startTile;
    private Tile leftTile;
    private Tile rightTile;
    private static final Dominoes instance = new Dominoes();

    public void setInitialUserHand(Hand initialUserHand) {
        this.initialUserHand = initialUserHand;
    }

    public void setInitialComputerHand(Hand initialComputerHand) {
        this.initialComputerHand = initialComputerHand;
    }

    private Dominoes() {}

    public static Dominoes getInstance() {
        return instance;
    }

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

    protected void setStartTile(Tile tile) {
        this.startTile = tile;
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

    protected void generateDeck() {
        deck.clear();
        for (int i = 0; i < 7 ; i++) {
            for (int x = i; x < 7; x++) {
                deck.add(new Tile(i, x));
            }
        }
    }

    protected void dealHands() {
        initialUserHand.clear();
        initialComputerHand.clear();
        for (int i = 0; i < 7; i++) {
            initialUserHand.addTile(deck.remove(0));
            initialComputerHand.addTile(deck.remove(0));
        }
    }

    protected Tile bestTile(Tile highestTile, Hand userHand) {
        boolean updated = false;

        for (Tile tile : userHand.getHand()){
            if (tile.isDouble()) {
                if (tile.getTotal() > highestTile.getTotal()) {
                    highestTile = tile;
                    updated = true;
                }
            }
        }

        if (!updated) {
            highestTile = checkHighestNonDouble(initialUserHand);
        }

        return highestTile;
    }

    protected Tile checkHighestNonDouble(Hand hand) {
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

    protected int setStartPlayer() {
        Tile highestUserTile = new Tile(0, 0);
        Tile highestComputerTile = new Tile(0, 0);

        highestUserTile = bestTile(highestUserTile, initialUserHand);
        highestComputerTile = bestTile(highestComputerTile, initialComputerHand);

        int result = highestUserTile.compareTo(highestComputerTile);

        if (result > 0) {
            return 0;
        }

        return 1;
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

    protected boolean checkGameWinner(Hand hand, Player player) {
        boolean winner = false;

        if (hand.length() == 0) {
            System.out.println(player.getType() + " wins!");
            winner = true;
        }

        return winner;
    }

    private void endGame(Player player1, Player player2) {
        player1.setScore(player2.getPlayerHand());
        player2.setScore(player1.getPlayerHand());

        System.out.println(scores);
        System.out.println(player1.getType() + " score: " + player1.getScore());
        System.out.println(player2.getType() + " score: " + player2.getScore());
    }

    private void play(int startPlayer) {
        Player player1 = new Player(initialUserHand, PlayerType.USER, userTurn);
        Player player2 = new Player(initialComputerHand, PlayerType.COMPUTER, compTurn);
        boolean gameOver = false;

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
            gameOver = checkGameWinner(player1.getPlayerHand(), player1);

            if (gameOver) {
                break;
            }

            player2.printHeader();
            player2.turn();
            gameOver = checkGameWinner(player2.getPlayerHand(), player2);
        }

        endGame(player1, player2);
    }

    public void setUpGame() {
        Board.intro();
        String choice = "y";

        while (choice.equals("y")) {

            generateDeck();
            Collections.shuffle(deck);
            setStartTile();
            dealHands();
            int startPlayer = setStartPlayer();
            play(startPlayer);

            choice = Input.input("Thanks for playing!\nWould you like to play again (y/n)");
        }
    }

    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        Dominoes dominoes = getInstance();

        if (logIn.logIn()) {
            dominoes.setUpGame();
        }
    }
}
