package engine;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Tile> hand = new ArrayList<>();

    public List<Tile> getHand() {
        return hand;
    }

    public void addTile(Tile tile) {
        hand.add(tile);
    }

    public Tile playTile(int index) {
        return hand.remove(index);
    }

    public void clear() {
        hand.clear();
    }

    public int length() {
        return hand.size();
    }

    public void printHand() {
        for (Tile tile:hand) {
            System.out.println(tile);
        }
    }
}
