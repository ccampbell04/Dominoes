package engine;

public class Tiles {

    private int side1;
    private int side2;
    private boolean isRotated;

    public Tiles(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
        this.isRotated = false;
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

    public boolean getIsRotated() {
        return isRotated;
    }

    public void rotate() {
        isRotated = !isRotated;
    }

    public boolean isDouble() {
        return side1 == side2;
    }

    @Override
    public String toString() {
        return "[" + side1 + " | " + side2 + "]";
    }
}
