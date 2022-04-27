package engine;

import display.Board;
import input.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dominoes {
    private List<Tiles> deck = new ArrayList<>();
    public Hands userHand = new Hands();
    public Hands computerHand = new Hands();
    public Tiles startTile;
    public Tiles leftTile;
    public Tiles rightTile;

    public void setUpGame() {
        Board.intro();
        generateDeck();
        Collections.shuffle(deck);
        setStartTile();
        dealHands();
        int startPlayer = setStartPlayer();
        play(startPlayer);
    }

    private void generateDeck() {
        for (int i = 0; i < 7 ; i++) {
            for (int x = i; x < 7; x++) {
                deck.add(new Tiles(i, x));
            }
        }
    }

    private void setStartTile() {
        startTile = deck.remove(deck.size() - 1);
    }

    private void dealHands() {
        for (int i = 0; i < 7; i++) {
            userHand.addTile(deck.remove(deck.size() - 1));
            computerHand.addTile(deck.remove(deck.size() - 1));
        }
    }

    private int setStartPlayer() {
        Tiles highestUserTile = new Tiles(0,0);
        Tiles highestComputerTile = new Tiles(0,0);

        for (Tiles tile : userHand.getHand()){
            if (tile.isDouble()) {
                highestUserTile = tile;
            }
        }

        if (highestUserTile.getTotal() == 0) {
            highestUserTile = checkHighestNonDouble(userHand);
        }

        for (Tiles tile : computerHand.getHand()){
            if (tile.isDouble()) {
                highestComputerTile = tile;
            }
        }

        if (highestComputerTile.getTotal()==0) {
            highestComputerTile = checkHighestNonDouble(computerHand);
        }

        int result = highestUserTile.compareTo(highestComputerTile);

        if (result > 0) {
            return 0;
        }

        return 1;
    }

    private Tiles checkHighestNonDouble(Hands hand) {
        Tiles highestTile = new Tiles(0,0);
        int result;
        for (Tiles tile: hand.getHand()) {
            result = tile.compareTo(highestTile);
            if (result > 0) {
                highestTile = tile;
            }
        }

        return highestTile;
    }

    private void userTurn() {
        Board.displayBoard(startTile, leftTile, rightTile);
        int leftNum = getLeftNum();
        int rightNum = getRightNum();

        System.out.println("Here is your hand - \n");
        userHand.printHand();

        boolean ableToPlay = ableToPlay(userHand, leftNum, rightNum);

        if (ableToPlay) {
            int posOfTile = Integer.parseInt(Input.input("What position tile do you want to play"));
            Tiles played = userHand.playTile(posOfTile - 1);
            updateTiles(played, leftNum, rightNum);
        } else {
            while (!ableToPlay) {
                System.out.println("You can't play any cards... picking one up");
                userHand.addTile(deck.remove(deck.size() - 1));
                ableToPlay = ableToPlay(userHand, leftNum, rightNum);
            }
        }
    }

    private void computerTurn() {

    }

    private void updateTiles(Tiles played, int leftNum, int rightNum) {
        if (played.getSide1() == rightNum) {
            rightTile = played;
        } else if (played.getSide2() == rightNum) {
            played.rotate();
            rightTile = played;
        } else if (played.getSide1() == leftNum) {
            played.rotate();
            leftTile = played;
        } else if (played.getSide2() == leftNum) {
            leftTile = played;
        }
    }

    private boolean ableToPlay(Hands hand, int leftNum, int rightNum) {
        for (Tiles tile : hand.getHand()) {
            if (tile.getSide1() == leftNum || tile.getSide2() == leftNum) {
                return true;
            } else if (tile.getSide1() == rightNum || tile.getSide2() == rightNum) {
                return true;
            } else if (tile.getSide1() == 0 || tile.getSide2() == 0) {
                return true;
            }
        }

        return false;
    }

    private int getLeftNum() {
        if (leftTile != null) {
            return leftTile.getSide1();
        }
        return startTile.getSide1();
    }

    private int getRightNum() {
        if (rightTile != null) {
            return rightTile.getSide2();
        }
        return startTile.getSide2();
    }

    private boolean checkWinner(Hands hand) {
        boolean winner = false;

        if (hand.length() == 0) {
            winner = true;
        }

        return winner;
    }

    private void play(int StartPlayer) {
        boolean gameOver = false;

        if (StartPlayer == 0) {
            while (!gameOver) {
                userTurn();
                gameOver = checkWinner(userHand);

                if (gameOver) {
                    break;
                }

                computerTurn();
                gameOver = checkWinner(computerHand);
            }
        } else {
            while (!gameOver) {
                computerTurn();
                gameOver = checkWinner(computerHand);

                if (gameOver) {
                    break;
                }

                userTurn();
                gameOver = checkWinner(userHand);
            }
        }
    }
}
