package engine;

import org.junit.Assert;
import org.junit.Test;

public class TestHand {

    @Test
    public void testGetTotal() {
        Hand testHand = new Hand();
        Tile testTile1 = new Tile(0,3);
        Tile testTile2 = new Tile(6,4);
        Tile testTile3 = new Tile(3,3);

        testHand.addTile(testTile1);
        testHand.addTile(testTile2);
        testHand.addTile(testTile3);

        Assert.assertEquals(19, testHand.getTotal());
    }
}
