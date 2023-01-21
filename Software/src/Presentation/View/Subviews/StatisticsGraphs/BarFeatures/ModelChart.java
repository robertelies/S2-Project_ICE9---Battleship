package Presentation.View.Subviews.StatisticsGraphs.BarFeatures;

/**
 * Class used to visualize the statics of a user in a game
 */

public class ModelChart {
    private String label;
    private double values[];

    /**
     * A getter for the label
     * @return String
     */
    public String getLabel() {
        return label;
    }

    /**
     * A getter for all the values in chart
     * @return array of doubles
     */
    public double[] getValues() {
        return values;
    }

    /**
     * Constructor
     * @param label String of the relavent information
     * @param values an array of doubles with numerical information
     */
    public ModelChart(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    /**
     * A getter for the maximum value
     * @return double
     */
    public double getMaxValues() {
        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
