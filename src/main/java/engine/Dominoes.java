package engine;

import display.Board;
import input.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dominoes {
    private List<Tiles> deck = new ArrayList<>();
    public Hands userHand = new Hands();
    public Hands computerHand = new Hands();
    public Tiles startTile;
    public Tiles leftTile;
    public Tiles rightTile;
    public int userScore = 0;
    public int computerScore = 0;

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
        userHand.clear();
        computerHand.clear();
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
        boolean validChoice = false;
        boolean tilesInDeck = true;

        if (ableToPlay) {
            int posOfTile = 0;
            while (!validChoice) {
                posOfTile = Integer.parseInt(Input.input("What position tile do you want to play"));
                validChoice = validChoice(userHand, posOfTile - 1, leftNum, rightNum);

                if (!validChoice) {
                    System.out.println("Invalid tile");
                }
            }

            Tiles played = userHand.playTile(posOfTile - 1);
            updateTiles(played, leftNum, rightNum);
        } else {
            while (!ableToPlay && tilesInDeck) {
                System.out.println("You can't play any cards... picking one up");
                try {
                    computerHand.addTile(deck.remove(deck.size() - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Deck is empty");
                    tilesInDeck = false;
                }
                ableToPlay = ableToPlay(userHand, leftNum, rightNum);
            }
            System.out.println("You now have a valid tile!");
            userHand.printHand();
            int posOfTile = Integer.parseInt(Input.input("What position tile do you want to play"));
            Tiles played = userHand.playTile(posOfTile - 1);
            updateTiles(played, leftNum, rightNum);
        }
    }

    private void computerTurn() {
        int leftNum = getLeftNum();
        int rightNum = getRightNum();
        boolean tilesInDeck = true;

        boolean ableToPlay = ableToPlay(computerHand, leftNum, rightNum);

        if (ableToPlay) {
            int posOfBest = bestCard(computerHand, leftNum, rightNum);
            Tiles played = computerHand.playTile(posOfBest);
            System.out.println("Computer plays " + played);
            updateTiles(played, leftNum, rightNum);
        } else {
            while (!ableToPlay && tilesInDeck) {
                try {
                    computerHand.addTile(deck.remove(deck.size() - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Deck is now empty");
                    tilesInDeck = false;
                }

                ableToPlay = ableToPlay(computerHand, leftNum, rightNum);
                System.out.println("Computer unable to play... picking up a tile");
            }
            int posOfBest = bestCard(computerHand, leftNum, rightNum);
            Tiles played = computerHand.playTile(posOfBest);
            System.out.println("Computer plays " + played);
            updateTiles(played, leftNum, rightNum);
        }
    }

    private int bestCard(Hands hand, int leftNum, int rightNum) {
        int bestTilePos = 0;
        int counter = -1;
        for (Tiles tile : hand.getHand()) {
            counter += 1;
            if (tile.getSide1() == leftNum || tile.getSide2() == leftNum) {
                bestTilePos = compareBestTiles(tile, bestTilePos, hand, counter);
            } else if (tile.getSide1() == rightNum || tile.getSide2() == rightNum) {
                bestTilePos = compareBestTiles(tile, bestTilePos, hand, counter);
            }
        }

        if (validChoice(hand, bestTilePos, leftNum, rightNum)) {
            return bestTilePos;
        }
        return bestWild(hand);
    }

    private int bestWild(Hands hand) {
        int counter = 0;
        for (Tiles tile : hand.getHand()) {
            if (tile.getSide1() == 0 || tile.getSide2() == 0) {
                return counter;
            }
            counter++;
        }
        return counter;
    }
    private boolean validChoice(Hands hand, int pos, int leftNum, int rightNum ) {
        Tiles tile = hand.getHand().get(pos);

        if (tile.getSide1() == leftNum || tile.getSide2() == leftNum) {
            return true;
        } else if (tile.getSide1() == rightNum || tile.getSide2() == rightNum) {
            return true;
        } else if (rightNum == 0 || leftNum == 0) {
            return true;
        } else if (tile.getSide1() == 0 || tile.getSide2() == 0) {
            return true;
        }
        return false;
    }

    private int compareBestTiles(Tiles tile, int bestTilePos, Hands hand, int counter) {
        int tileHighestSide;
        int bestTileHighestSide;

        if (tile.getSide1() > tile.getSide2()) {
            tileHighestSide = tile.getSide1();
        } else if (tile.getSide2() > tile.getSide1()) {
            tileHighestSide = tile.getSide2();
        } else {
            tileHighestSide = tile.getSide1();
        }

        Tiles bestTile = hand.getHand().get(bestTilePos);

        if (bestTile.getSide1() > bestTile.getSide2()) {
            bestTileHighestSide = bestTile.getSide1();
        } else if (bestTile.getSide2() > bestTile.getSide1()) {
            bestTileHighestSide = bestTile.getSide2();
        } else {
            bestTileHighestSide = bestTile.getSide1();
        }

        if (tileHighestSide > bestTileHighestSide) {
            return counter;
        } else if (bestTileHighestSide > tileHighestSide) {
            return bestTilePos;
        } else {
            return counter;
        }
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
        } else if (played.getSide1() == 0 || played.getSide2() == 0) {
            if (played.getSide2() != 0) {
                    played.rotate();
                }
            leftTile = played;
        } else if (leftNum == 0 || rightNum == 0) {
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
            } else if (leftNum == 0 || rightNum == 0) {
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

    private boolean checkGameWinner(Hands hand) {
        boolean winner = false;

        if (hand.length() == 0) {
            winner = true;
        }

        return winner;
    }

    private int getScore(Hands hand) {
        int score = 0;

        for (Tiles tile : hand.getHand()) {
            score += tile.getSide1() + tile.getSide2();
        }

        score = 5*(Math.round(score/5));

        return score;
    }

    private void play(int startPlayer) {
        boolean gameOver = false;
        final String userTurn = "--- USER TURN ---";
        final String compTurn = "--- COMPUTER TURN ---";
        final String scores = "--- SCORES ---";

        startPlayer = 1;

        if (startPlayer == 0) {
            while (!gameOver) {
                System.out.println(userTurn);
                userTurn();
                gameOver = checkGameWinner(userHand);

                if (gameOver) {
                    break;
                }

                System.out.println(compTurn);
                computerTurn();
                gameOver = checkGameWinner(computerHand);
            }
        } else {
            while (!gameOver) {
                System.out.println(compTurn);
                computerTurn();
                gameOver = checkGameWinner(computerHand);

                if (gameOver) {
                    break;
                }

                System.out.println(userTurn);
                userTurn();
                gameOver = checkGameWinner(userHand);
            }
        }

        userScore = getScore(computerHand);
        computerScore = getScore(userHand);

        System.out.println(scores);
        System.out.println("User score: " + userScore);
        System.out.println("Computer score: " + computerScore);
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
}
