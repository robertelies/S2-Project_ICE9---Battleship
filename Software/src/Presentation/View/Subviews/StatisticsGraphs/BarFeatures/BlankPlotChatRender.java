package Presentation.View.Subviews.StatisticsGraphs.BarFeatures;

import java.awt.*;

/**
 * An abstract class used to represent what will be A BlankPlotChat
 *
 * @author ICE 9
 */

public abstract class BlankPlotChatRender {

    /**
     * a getter
     * @param index the location of the label we want
     * @return String
     */
    public abstract String getLabelText(int index);

    /**
     * Used for displaying statistics
     * @param chart THe BlankPlotChart to be used for display
     * @param g2 graphics created
     * @param size in SeriesSize
     * @param index the location
     */
    public abstract void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);
}