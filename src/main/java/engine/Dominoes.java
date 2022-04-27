package engine;

import input.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dominoes {
    private List<Tiles> deck = new ArrayList<>();
    private int noOfPlayers;

    public void setUpGame() {
        generateDeck();
        noOfPlayers = Integer.parseInt(Input.input("Enter number of players"));
        dealHands(noOfPlayers);
    }

    public void generateDeck() {
        for (int i = 0; i < 7 ; i++) {
            for (int x = i; x < 7; x++) {
                deck.add(new Tiles(i, x));
            }
        }
    }

    public void dealHands(int noOfPlayers) {
        Collections.shuffle(deck);
        if (noOfPlayers == 2) {
            twoPlayerSetup();
        } else if (noOfPlayers == 3) {
            threePlayerSetup();
        } else if (noOfPlayers == 4) {
            fourPlayerSetup();
        }
    }

    private void twoPlayerSetup() {
        Hands userHand = new Hands();
        Hands computerHand = new Hands();
    }

    private void threePlayerSetup() {
        Hands userHand = new Hands();
        Hands computer1Hand = new Hands();
        Hands computer2Hand = new Hands();
    }

    private void fourPlayerSetup() {
        Hands userHand = new Hands();
        Hands computer1Hand = new Hands();
        Hands computer2Hand = new Hands();
        Hands computer3Hand = new Hands();
    }
}
