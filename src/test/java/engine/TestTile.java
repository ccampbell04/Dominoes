package engine;

import org.junit.Assert;
import org.junit.Test;

public class TestTile {

    @Test
    public void testGetTotal() {
        Tile testTile = new Tile(3,5);
        Assert.assertEquals(8, testTile.getTotal());
    }

    @Test
    public void testRotate() {
        Tile testTile = new Tile(3,5);
        testTile.rotate();

        Assert.assertEquals(5, testTile.getSide1());
    }

    @Test
    public void testIsDouble() {
        Tile testTile = new Tile(4,4);
        Assert.assertTrue(testTile.isDouble());
    }

    @Test
    public void testIsWild() {
        Tile wildTile = new Tile(3,0);
        Assert.assertTrue(wildTile.isWild());
    }

    @Test
    public void testCompareTo() {
        Tile bigTile = new Tile(4,5);
        Tile smallTile = new Tile(2,1);

        Assert.assertEquals(3, bigTile.compareTo(smallTile));
    }
}
