package engine;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestDominoes {
    @Test
    public void testGenerateDeck() {
        Dominoes dominoes = Dominoes.getInstance();

        dominoes.generateDeck();
        Assert.assertEquals(28, dominoes.deck.size());
    }

    @Test
    public void testDealHands() {
        Hand userHand = new Hand();
        Dominoes dominoes = Dominoes.getInstance();

        dominoes.setInitialUserHand(userHand);

        dominoes.generateDeck();
        dominoes.dealHands();
        Assert.assertEquals(7, dominoes.initialUserHand.length());
    }

    @Test
    public void testBestTileWithDouble() {
        Hand mockHand = mock(Hand.class);
        Dominoes dominoes = Dominoes.getInstance();

        List<Tile> testHand = new ArrayList<>();
        Tile testTile1 = new Tile(3,3);
        Tile testTile2 = new Tile(6,5);
        testHand.add(testTile1);
        testHand.add(testTile2);

        Mockito.when(mockHand.getHand()).thenReturn(testHand);

        Assert.assertEquals(testTile1, dominoes.bestTile(new Tile(0,0), mockHand));
    }

    @Test
    public void checkHighestNonDouble() {
        Dominoes dominoes = Dominoes.getInstance();
        Hand mockHand = mock(Hand.class);

        List<Tile> testHand = new ArrayList<>();
        Tile testTile1 = new Tile(3,1);
        Tile testTile2 = new Tile(2,5);
        testHand.add(testTile1);
        testHand.add(testTile2);

        Mockito.when(mockHand.getHand()).thenReturn(testHand);

        Assert.assertEquals(testTile2, dominoes.checkHighestNonDouble(mockHand));
    }

    @Test
    public void testSetStartPlayer() {
        Dominoes dominoes = Dominoes.getInstance();

        Hand mockUserHand = mock(Hand.class);
        dominoes.setInitialUserHand(mockUserHand);

        List<Tile> testUserHand = new ArrayList<>();
        Tile testTile1 = new Tile(3,3);
        Tile testTile2 = new Tile(6,5);
        testUserHand.add(testTile1);
        testUserHand.add(testTile2);
        Mockito.when(mockUserHand.getHand()).thenReturn(testUserHand);

        Hand mockCompHand = mock(Hand.class);
        dominoes.setInitialComputerHand(mockCompHand);

        List<Tile> testCompHand = new ArrayList<>();
        Tile testTile3 = new Tile(4,4);
        Tile testTile4 = new Tile(6,3);
        testCompHand.add(testTile3);
        testCompHand.add(testTile4);
        Mockito.when(mockCompHand.getHand()).thenReturn(testCompHand);

        Assert.assertEquals(1, dominoes.setStartPlayer());
    }

    @Test
    public void getLeftNumNull() {
        Dominoes dominoes = Dominoes.getInstance();

        Tile startTile = new Tile(3,4);
        dominoes.setLeftTile(null);
        dominoes.setStartTile(startTile);

        Assert.assertEquals(3, dominoes.getLeftNum());
    }

    @Test
    public void getLeftNum() {
        Dominoes dominoes = Dominoes.getInstance();

        dominoes.setLeftTile(new Tile(5,4));

        Assert.assertEquals(5, dominoes.getLeftNum());
    }

    @Test
    public void getRightNumNull() {
        Dominoes dominoes = Dominoes.getInstance();

        dominoes.setRightTile(new Tile(3,6));

        Assert.assertEquals(6, dominoes.getRightNum());
    }

    @Test
    public void testCheckGameWinnerFalse() {
        Dominoes dominoes = Dominoes.getInstance();
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Mockito.when(mockHand.length()).thenReturn(2);

        Assert.assertFalse(dominoes.checkGameWinner(mockHand, testPlayer));
    }

    @Test
    public void testCheckGameWinnerTrue() {
        Dominoes dominoes = Dominoes.getInstance();
        Hand mockHand = mock(Hand.class);
        Player testPlayer = new Player(mockHand, PlayerType.USER, "");

        Mockito.when(mockHand.length()).thenReturn(0);

        Assert.assertTrue(dominoes.checkGameWinner(mockHand, testPlayer));
    }
}
