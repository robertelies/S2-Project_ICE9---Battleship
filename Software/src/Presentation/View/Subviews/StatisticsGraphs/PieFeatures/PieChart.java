package Presentation.View.Subviews.StatisticsGraphs.PieFeatures;

import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.LegendItem;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.ModelLegend;

import javax.swing.*;
import java.awt.*;

/**
 * Used to be able to visualize our data in a PIEchart format
 *
 * @author ICE 9
 */

public class PieChart extends JComponent {
    private ColorLibrary colorLibrary;
    private Part[] slices;

    /**
     * Constructor for visualizing
     * @param user user data
     * @param rest remaining
     */
    public PieChart(double user, double rest) {
        this.colorLibrary=new ColorLibrary();
        this.setSize(getPreferredSize().width,getPreferredSize().height);
        slices= new Part[]{
                new Part(user, colorLibrary.getColor(1)),
                new Part(rest,colorLibrary.getColor(5))
        };
    }

    /**
     * Used to allocate a size of the pie chart
     * @param i the number associated with the slice location
     * @param value the value the piece of the pie holds
     * @param color color for visuals
     */
    public void setSlice(int i, double value,Color color){
        slices[i]=new Part(value,color);
    }

    /**
     * Used to draw components
     * @param g graphics to be drawn
     */
    public void paint(Graphics g) {
        drawPie((Graphics2D) g, getBounds(), slices);
    }

    /**
     * Used to draw the PIE
     * @param g graphics
     * @param area a Rectangle's area
     * @param slices the parts that make up the pie
     */
    void drawPie(Graphics2D g, Rectangle area, Part[] slices) {
        double total = 0.0D;
        for (int i = 0; i < slices.length; i++) {
            total += slices[i].getValue();
        }
        double curValue = 0.0D;
        int startAngle;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].getValue() * 360 / total);
            g.setColor(slices[i].getColor());
            g.fillArc(area.x, area.y, 300, 300, startAngle, arcAngle);
            curValue += slices[i].getValue();
        }
    }
}