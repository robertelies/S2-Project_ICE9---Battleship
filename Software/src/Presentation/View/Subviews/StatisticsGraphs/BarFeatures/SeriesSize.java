package Presentation.View.Subviews.StatisticsGraphs.BarFeatures;

/**
 * A class used to determine sizes and scales for the visualization
 *
 * @author ICE 9
 */

public class SeriesSize {


    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * A getter for the x component
     * @return a double
     */
    public double getX() {
        return x;
    }

    /**
     * A getter for the y component
     * @return a double
     */
    public double getY() {
        return y;
    }

    /**
     * A getter for the horizontal size
     * @return a double
     */
    public double getWidth() {
        return width;
    }

    /**
     * A getter for the vertical size
     * @return a double
     */
    public double getHeight() {
        return height;
    }

    /**
     * Constructor used when creating a plot chart
     * @param x coordinate x
     * @param y coordinate y
     * @param width the size horizontally
     * @param height size vertically
     */
    public SeriesSize(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}