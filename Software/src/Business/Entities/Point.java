package Business.Entities;

/**
 * A class used to represet a point on the graph of the game
 */

public class Point {
    private int x;
    private int y;

    /**
     * constructor used when we need a point
     * @param x coordinate x
     * @param y coordinate y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for x coordinate
     * @return an int
     */
    public int getX() {
        return x;
    }

    /**
     * getter for the y coordinate
     * @return an int
     */
    public int getY() {
        return y;
    }

}

