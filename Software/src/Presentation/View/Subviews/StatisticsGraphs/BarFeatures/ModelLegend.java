package Presentation.View.Subviews.StatisticsGraphs.BarFeatures;

import java.awt.*;

/**
 * Class used to provide information about a graphics to the user
 *
 * @author ICE 9
 */

public class ModelLegend {


    private String name;
    private Color color;

    /**
     * A getter for the name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * A getter for the color
     * @return Color
     */
    public Color getColor() {
        return color;
    }


    /**
     * Constructor used on a piece of graph
     * @param name name associated
     * @param color color associated
     */
    public ModelLegend(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}
