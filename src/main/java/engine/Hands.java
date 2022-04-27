package engine;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    private List<Tiles> hand = new ArrayList<>();

    public List<Tiles> getHand() {
        return hand;
    }

    public void addTile(Tiles tile) {
        hand.add(tile);
    }

    public Tiles playTile(int index) {
        return hand.remove(index);
    }

    public void clear() {
        hand.clear();
    }

    public int length() {
        return hand.size();
    }

    public void printHand() {
        for (Tiles tile:hand) {
            System.out.println(tile);
        }
    }
}
