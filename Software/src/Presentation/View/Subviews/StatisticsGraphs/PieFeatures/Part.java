package Presentation.View.Subviews.StatisticsGraphs.PieFeatures;

import java.awt.*;

/**
 * A class used to represent a slice of pie of a pie Chart
 *
 * @author ICE 9
 */

public class Part {
    double value;
    Color color;

    /**
     * Constructor used to make a slice of pie
     * @param value the amount
     * @param color the shade of the rainbow desired
     */
    public Part(double value, Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * a getter for the value
     * @return a double
     */
    public double getValue(){
        return value;
    }

    /**
     * A getter for the color
     * @return a color
     */
    public Color getColor(){
        return color;
    }
}
