package engine;

public class Tiles implements Comparable<Tiles> {

    private int side1;
    private int side2;

    public Tiles(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    public int getTotal() {
        return side1 + side2;
    }

    public void rotate() {
        int temp = side1;
        side1 = side2;
        side2 = temp;
    }

    public boolean isDouble() {
        return side1 == side2;
    }

    @Override
    public String toString() {
        return "[" + side1 + " | " + side2 + "]";
    }

    @Override
    public int compareTo(Tiles compTile) {
        int biggestSide;
        int biggestCompSide;

        if (this.getSide1() > this.getSide2()) {
            biggestSide = this.getSide1();
        } else {
            biggestSide = this.getSide2();
        }

        if (compTile.getSide1() > compTile.getSide2()) {
            biggestCompSide = compTile.getSide1();
        } else {
            biggestCompSide = compTile.getSide2();
        }

        return biggestSide - biggestCompSide;
    }
}
