package Presentation.View.Subviews.StatisticsGraphs.BarFeatures;

/**
 * Class used to nicely format one statistic visual
 *
 * @author ICE 9
 */

public class NiceScale {

    private double min;
    private double max;
    private int maxTicks = 10;
    private double tickSpacing;
    private double range;
    private double niceMin;
    /**
     * constructor
     * @param MIN minimum number
     * @param MAX maximum number
     */
    public NiceScale(final double MIN, final double MAX) {
        min = MIN;
        max = MAX;
        calculate();
    }

    private void calculate() {
        range = niceNum(max - min, false);
        tickSpacing = niceNum(range / (maxTicks - 1), true);
        niceMin = Math.floor(min / tickSpacing) * tickSpacing;
    }

    private double niceNum(final double RANGE, final boolean ROUND) {
        double exponent;     // exponent of RANGE
        double fraction;     // fractional part of RANGE
        double niceFraction; // nice, rounded fraction

        exponent = Math.floor(Math.log10(RANGE));
        fraction = RANGE / Math.pow(10, exponent);

        if (ROUND) {
            if (fraction < 1.5) {
                niceFraction = 1;
            } else if (fraction < 3) {
                niceFraction = 2;
            } else if (fraction < 7) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        } else {
            if (fraction <= 1) {
                niceFraction = 1;
            } else if (fraction <= 2) {
                niceFraction = 2;
            } else if (fraction <= 5) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        }
        return niceFraction * Math.pow(10, exponent);
    }

    /**
     * gets the spaing
     * @return double
     */
    public double getTickSpacing() {
        return tickSpacing;
    }

    /**
     * Obtains a minimum value
     * @return a double
     */
    public double getNiceMin() {
        return niceMin;
    }

    /**
     * Gets the max number of hash's on the graph
     * @return int
     */
    public int getMaxTicks() {
        return maxTicks;
    }

    /**
     * Sets the max for the scale
     * @param max maximum value
     */
    public void setMax(double max) {
        this.max = max;
        calculate();
    }

}