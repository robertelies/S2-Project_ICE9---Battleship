package Presentation.View.Subviews.PanelFeatures;

import java.awt.*;

/**
 * Class used to keep track of the colors our group has used throughout the project
 *
 * @author ICE 9
 */

public class ColorLibrary {
    private Color[] colors = {
            new Color(0,105,148),
            new Color(215, 185, 246),
            new Color(139, 229, 222),
            new Color(239, 169, 100),
            new Color(185, 243, 128),
            new Color(220, 220, 220)
    };

    /**
     * Constructor use d to obtain a color
     */
    public ColorLibrary(){
    }

    /**
     * A getter for the color we desired
     * @param index the location of the color we want
     * @return the color's RGB value
     */
    public Color getColor(int index){
        return colors[index];
    }

}
