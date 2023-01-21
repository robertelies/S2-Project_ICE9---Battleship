package Presentation.View.Subviews.StatisticsGraphs.BarFeatures;

import javax.swing.*;
import java.awt.*;

/**
 * Class used to place a componenet on the legend
 *
 * @author ICE 9
 */

public class LegendItem extends JPanel {
    private LabelColor lbColor;
    private JLabel lbName;

    /**
     * Constructor used by a legend
     * @param data relevant information for display purposes
     */
    public LegendItem(ModelLegend data) {
        initComponents();
        setOpaque(false);
        lbColor.setBackground(data.getColor());
        lbName.setText(data.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbColor = new LabelColor();
        lbName = new JLabel();

        lbColor.setText("labelColor1");

        lbName.setForeground(new Color(100, 100, 100));
        lbName.setText("Name");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbColor, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbName)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbName)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(lbColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }
}