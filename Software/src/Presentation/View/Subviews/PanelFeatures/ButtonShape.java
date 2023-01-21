package Presentation.View.Subviews.PanelFeatures;

import javax.swing.*;
import java.awt.*;

/**
 * A class created to make our own button
 *
 * @author ICE 9
 */

public class ButtonShape {



    /**
     * Used to give the button a color
     */
    public ButtonShape() {

    }

    /**
     * Used to make a multicolor button
     * @param button the button to design
     * @param background the back color
     * @param foreground the top color or front color
     * @return a JButon
     */
    public JButton makeFancyButton(JButton button, Color background, Color foreground) {
        button.setSize(new Dimension(100,50));
        button.setForeground(foreground);
        button.setBackground(background);
        button.setOpaque(true);
        return button;
    }
}
