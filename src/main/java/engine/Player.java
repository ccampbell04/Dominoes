package engine;

import display.Board;
import input.Input;

public class Player {
    private Hand playerHand;
    private PlayerType type;
    private String header;
    private int score;
    Dominoes dominoes = new Dominoes();

    public Player(Hand hand, PlayerType type, String header) {
        this.playerHand = hand;
        this.type = type;
        this.header = header;
    }

    public void printHeader() {
        System.out.println(header);
    }

    public void setScore(Hand hand) {
        int score = 0;

        for (Tile tile: hand.getHand()) {
            score += tile.getTotal();
        }

        this.score = 5*(Math.round(score/5));
    }

    public int getScore() {
        return score;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public boolean isUser() {
        return type == PlayerType.USER;
    }

    private boolean validChoice(Tile tile) {
        int leftNum = dominoes.getLeftNum();
        int rightNum = dominoes.getRightNum();

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

    private boolean ableToPLay(Hand playerHand) {
        boolean validTile = false;
        for (Tile tile : playerHand.getHand()) {
            validTile = validChoice(tile);
            if (validTile) {
                break;
            }
        }

        return validTile;
    }

    private int bestCard() {
        int leftNum = dominoes.getLeftNum();
        int rightNum = dominoes.getRightNum();
        int bestTilePos = 0;
        int counter = -1;

        for (Tile tile : playerHand.getHand()) {
            counter += 1;
            if (tile.getSide1() == leftNum || tile.getSide2() == leftNum) {
                bestTilePos = compareBestTiles(tile, bestTilePos, playerHand, counter);
            } else if (tile.getSide1() == rightNum || tile.getSide2() == rightNum) {
                bestTilePos = compareBestTiles(tile, bestTilePos, playerHand, counter);
            }
        }

        Tile tile = playerHand.getHand().get(bestTilePos);

        if (validChoice(tile)) {
            return bestTilePos;
        }
        return bestWild(playerHand);
    }

    private int compareBestTiles(Tile tile, int bestTilePos, Hand hand, int counter) {
        int tileHighestSide = setHighestSide(tile);
        int bestTileHighestSide = setHighestSide(hand.getHand().get(bestTilePos));

        if (tileHighestSide > bestTileHighestSide) {
            return counter;
        } else if (bestTileHighestSide > tileHighestSide) {
            return bestTilePos;
        } else {
            return counter;
        }
    }

    private int setHighestSide(Tile tile) {
        int highestSide;

        if (tile.getSide1() > tile.getSide2()) {
            highestSide = tile.getSide1();
        } else if (tile.getSide2() > tile.getSide1()) {
            highestSide = tile.getSide2();
        } else {
            highestSide = tile.getSide1();
        }

        return highestSide;
    }

    private int bestWild(Hand hand) {
        int counter = 0;
        for (Tile tile : hand.getHand()) {
            if (tile.getSide1() == 0 || tile.getSide2() == 0) {
                return counter;
            }
            counter++;
        }
        return counter;
    }

    private void updateTiles(Tile played) {
        int leftNum = dominoes.getLeftNum();
        int rightNum = dominoes.getRightNum();

        if (played.getSide1() == rightNum) {
            dominoes.setRightTile(played);
        } else if (played.getSide2() == rightNum) {
            played.rotate();
            dominoes.setRightTile(played);
        } else if (played.getSide1() == leftNum) {
            played.rotate();
            dominoes.setLeftTile(played);
        } else if (played.getSide2() == leftNum) {
            dominoes.setLeftTile(played);
        } else if (played.getSide1() == 0 || played.getSide2() == 0) {
            if (played.getSide2() != 0) {
                played.rotate();
            }
            dominoes.setLeftTile(played);
        } else if (leftNum == 0 || rightNum == 0) {
            dominoes.setLeftTile(played);
        }
    }

    public void playTile() {
        int posOfTile = 0;
        boolean validChoice = false;

        if (isUser()) {
            while (!validChoice) {
                posOfTile = Integer.parseInt(Input.input("What position tile do you want to play - 1 to " +
                        playerHand.length()));
                posOfTile -= 1;
                Tile tile = playerHand.getHand().get(posOfTile);
                validChoice = validChoice(tile);

                if (!validChoice) {
                    System.out.println("Invalid tile");
                }
            }
        } else {
            posOfTile = bestCard();
        }
        Tile played = playerHand.playTile(posOfTile);
        System.out.println(type + " played " + played);
        updateTiles(played);
    }

    public void cantPlay() {
        while (!ableToPLay(playerHand) && !dominoes.isDeckEmpty()) {
            System.out.println("Can't play any cards, picking one up");
            playerHand.addTile(dominoes.pickUp());
        }

        if (ableToPLay(playerHand)) {
            playTile();
        }else if (dominoes.isDeckEmpty()) {
            System.out.println("Deck is empty ending turn");
        }
    }

    public void turn() {
        if (isUser()) {
            Board.displayBoard(dominoes.getStartTile(),
                    dominoes.getLeftTile(),
                    dominoes.getRightTile());
            System.out.println("Here is your hand -\n");
            playerHand.printHand();
        }
        boolean ableToPlay = ableToPLay(playerHand);

        if (ableToPlay) {
            playTile();
        } else {
            cantPlay();
        }
    }
}
