package engine;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Tile> hand = new ArrayList<>();

    public void setHand(List<Tile> hand) {
        this.hand = hand;
    }

    public List<Tile> getHand() {
        return hand;
    }

    public int getTotal() {
        int total = 0;
        for (Tile tile : hand) {
            total += tile.getTotal();
        }
        return total;
    }

    public void addTile(Tile tile) {
        hand.add(tile);
    }

    public Tile placeTile(int index) {
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
