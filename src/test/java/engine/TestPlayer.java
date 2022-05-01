package engine;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestPlayer {

    @Test
    public void testSetScore() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        List<Tile> testHand = new ArrayList<>();
        Tile testTile1 = new Tile(5,5);
        Tile testTile2 = new Tile(1,1);
        testHand.add(testTile1);
        testHand.add(testTile2);

        Mockito.when(mockHand.getHand()).thenReturn(testHand);

        testPlayer.setScore(mockHand);

        Assert.assertEquals(10, testPlayer.getScore());
    }

    @Test
    public void testValidChoice() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 1);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(3,3);
        dominoes.setRightTile(rightTile);

        Tile testTile = new Tile(3, 6);

        Assert.assertTrue(testPlayer.validChoice(testTile));
    }

    @Test
    public void testWildValidChoice() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 1);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(3,3);
        dominoes.setRightTile(rightTile);

        Tile testTile = new Tile(4, 0);

        Assert.assertTrue(testPlayer.validChoice(testTile));
    }

    @Test
    public void testBestCard() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 3);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(5,3);
        dominoes.setRightTile(rightTile);

        List<Tile> testHand = new ArrayList<>();
        Tile testTile1 = new Tile(1,1);
        Tile testTile2 = new Tile(5,5);
        testHand.add(testTile1);
        testHand.add(testTile2);

        Mockito.when(mockHand.getHand()).thenReturn(testHand);

        Assert.assertEquals(0, testPlayer.bestCard());
    }

    @Test
    public void testBestWildCard() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 3);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(5,3);
        dominoes.setRightTile(rightTile);

        List<Tile> testHand = new ArrayList<>();
        Tile testTile1 = new Tile(6,2);
        Tile testTile2 = new Tile(5,0);
        testHand.add(testTile1);
        testHand.add(testTile2);

        Mockito.when(mockHand.getHand()).thenReturn(testHand);

        Assert.assertEquals(1, testPlayer.bestCard());
    }

    @Test
    public void testCompareBestTiles() {
        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 3);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(5,5);
        dominoes.setRightTile(rightTile);

        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        List<Tile> testHand = new ArrayList<>();
        Tile testTile1 = new Tile(3,2);
        Tile testTile2 = new Tile(5,6);
        Tile testTile3 = new Tile(1,2);
        testHand.add(testTile1);
        testHand.add(testTile2);
        testHand.add(testTile3);

        Mockito.when(mockHand.getHand()).thenReturn(testHand);

        Assert.assertEquals(1, testPlayer.compareBestTiles(
                testTile3,
                1,
                mockHand,
                2));
    }

    @Test
    public void testSetHighestSide() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Tile testTile = new Tile(4,6);

        Assert.assertEquals(6, testPlayer.setHighestSide(testTile));
    }

    @Test
    public void testBestWild() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Tile wild1 = new Tile(0,0);
        Tile wild2 = new Tile(0,1);
        Tile wild3 = new Tile(6,0);
        List<Tile> testTiles = new ArrayList<>();
        testTiles.add(wild1);
        testTiles.add(wild2);
        testTiles.add(wild3);

        Mockito.when(mockHand.getHand()).thenReturn(testTiles);

        Assert.assertEquals(2, testPlayer.bestWild(mockHand));
    }

    @Test
    public void testUpdateTiles() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Tile testTile = new Tile(4,5);

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 5);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(6,5);
        dominoes.setRightTile(rightTile);

        testPlayer.updateTiles(testTile);

        Assert.assertEquals(leftTile, dominoes.getLeftTile());
        Assert.assertEquals(testTile, dominoes.getRightTile());
    }

    @Test
    public void testUpdateWildTile() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Tile testTile = new Tile(4,5);

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 5);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(6,0);
        dominoes.setRightTile(rightTile);

        testPlayer.updateTiles(testTile);

        Assert.assertEquals(leftTile, dominoes.getLeftTile());
        Assert.assertEquals(testTile, dominoes.getRightTile());
    }

    @Test
    public void testUpdateTilesWithWild() {
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Tile testTile = new Tile(0,5);

        Dominoes dominoes = Dominoes.getInstance();
        Tile leftTile = new Tile(1, 3);
        dominoes.setLeftTile(leftTile);

        Tile rightTile = new Tile(6,2);
        dominoes.setRightTile(rightTile);

        testPlayer.updateTiles(testTile);

        testTile.rotate();
        Assert.assertEquals(testTile, dominoes.getLeftTile());
        Assert.assertEquals(rightTile, dominoes.getRightTile());
    }
}
