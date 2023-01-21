package Presentation.View.Subviews.PlayerScreenElements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Used to show the rules relating to the game
 *
 * @author ICE 9
 */

public class RulesPanel extends JPanel {

    /**
     * Constructor used in the BattleView
     */
    public RulesPanel(){
        this.setLayout(new GridLayout(1,2));
        this.setBorder(new EmptyBorder(30,50,30,50));
        this.add(setPanelLeft());
        this.add(setPanelRight());
        this.setBackground(Color.white);
    }

    private JPanel setPanelRight() {
        JPanel panelRight= new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight,BoxLayout.Y_AXIS));
        panelRight.setBorder(new EmptyBorder(0,90,0,90));
        String fleet = "FLEET";
        panelRight.add(new JLabel(fleet));
        panelRight.add(new JLabel("  "));
        String[] columnNames = {"Quantity","Ship","Size(squares)"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        JTable shipsTypes = new JTable(model);
        shipsTypes.setBorder(new EmptyBorder(0,50,0,80));
        model.addRow(newVector("1x","Aircraft Carrier","5"));
        model.addRow(newVector("1x","SpeedBoat","3"));
        model.addRow(newVector("1x","Destroyer","4"));
        model.addRow(newVector("2x","Submarine","3"));
        panelRight.add(shipsTypes.getTableHeader(),BorderLayout.NORTH);
        panelRight.add(shipsTypes,BorderLayout.CENTER);
        return panelRight;
    }

    private Vector newVector(String firstElement,String secondElement,String thirdElement){
        Vector v = new Vector();
        v.add(firstElement);
        v.add(secondElement);
        v.add(thirdElement);
        return v;
    }

    private JPanel setPanelLeft() {
        JPanel panelLeft= new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft,BoxLayout.Y_AXIS));
        panelLeft.setBorder(new EmptyBorder(0,90,0,0));
        String rules = "RULES OF THE GAME";
        panelLeft.add(new JLabel(rules));
        panelLeft.add(new JLabel("  "));
        panelLeft.add(new JLabel("2. Take turns firing a salvo at your enemy pressing the desired square")).setFont(new Font("Verdana",Font.PLAIN,12));
        panelLeft.add(new JLabel("3. If the square does not contain any ship, the square will be automatically painted")).setFont(new Font("Verdana",Font.PLAIN,12));
        panelLeft.add(new JLabel("in the lighter version of your color. Else, the square will be painted in the darker version")).setFont(new Font("Verdana",Font.PLAIN,12));
        panelLeft.add(new JLabel("4. keep shooting until there is only one user who has at least one ship sailing")).setFont(new Font("Verdana",Font.PLAIN,12));
        return panelLeft;
    }
}
