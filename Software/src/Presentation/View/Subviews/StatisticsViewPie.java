package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.StatisticsController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.LegendItem;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.ModelLegend;
import Presentation.View.Subviews.StatisticsGraphs.PieFeatures.PieChart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

/**
 * A class used for displaying statistics about a game
 *
 * @author ICE 9
 */

public class StatisticsViewPie {

    private JBackgroundPanel viewImagePanel;
    private StatisticsController controller;
    private JButton goBackPie;
    private MainView mainView;
    private FormManager formManager;
    private UserManager userManager;
    private ColorLibrary colorLibrary;
    private PieChart pie;

    /**
     * Constructor used when user wants to see
     */
    public StatisticsViewPie() {
        this.colorLibrary=new ColorLibrary();
    }


    private void setButton() {
        ButtonShape buttonShape= new ButtonShape();
        goBackPie = new JButton("GO BACK");
        goBackPie = buttonShape.makeFancyButton(goBackPie,Color.white,colorLibrary.getColor(0));
        goBackPie.addActionListener(controller);
        goBackPie.setActionCommand("GO BACK PIE");
    }

    /**
     * Used to design the visuals for the Piechart
     */
    public void setPie(){
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel,BoxLayout.Y_AXIS));
        viewImagePanel.setBorder(new EmptyBorder(120,50,100,50));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(20,100,20,100));
        JLabel statistics = new JLabel("PLAYER VICTORIES");
        statistics.setForeground(Color.white);
        statistics.setFont(new Font("Verdana", Font.BOLD,30));
        viewImagePanel.add(statistics);
        pie = new PieChart((double)controller.getStatsPiechart().get(0),(double)controller.getStatsPiechart().get(1));
        pie.setSize(100,100);
        pie.setBorder(new EmptyBorder(200,0,200,0));
        panel.add(pie);
        setButton();
        panel.add(setPanelInfo());
        viewImagePanel.add(panel);
    }

    /**
     * A getter to obtain the panel
     * @return a JBackgroundPanel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }

    /**
     * a getter for the pie
     * @return PieChart
     */
    public PieChart getPie() {
        return pie;
    }

    /**
     * A setter for the controller
     * @param statisticsController controller designed for this view
     */
    public void registerController(StatisticsController statisticsController) {
        this.controller = statisticsController;
    }


    /**
     * A setter for the managers
     * @param formManager to manage the user input
     * @param userManager to manage user data
     */
    public void registerManagers(FormManager formManager, UserManager userManager) {
        this.userManager= userManager;
        this.formManager = formManager;
    }

    /**
     * A setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.mainView = view;
    }

    private JPanel addLegend(String name, Color color) {
        JPanel panelLegend = new JPanel();
        ModelLegend data = new ModelLegend(name, color);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
        panelLegend.setOpaque(false);
        return panelLegend;
    }

    /**
     * Sets the data to be displayed
     * @return a Box of the information
     */
    public Box setPanelInfo(){
        JPanel panelAux = new JPanel();
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(100,100));
        box.setOpaque(false);
        panelAux.setOpaque(false);
        panelAux.setBorder(new EmptyBorder(200,0,200,0));
        panelAux.setLayout( new BoxLayout(panelAux,BoxLayout.Y_AXIS));
        panelAux.add(addLegend("player's victories",colorLibrary.getColor(1)));
        panelAux.add(addLegend("AI's victories",colorLibrary.getColor(5)));
        panelAux.add(goBackPie);
        LogoutButton logout = new LogoutButton(mainView,"11",formManager,userManager);
        panelAux.add(logout.getLogout());
        box.add(panelAux);
        return box;
    }

    /**
     * getter for the controller
     * @return StatisticsController
     */
    public StatisticsController getController() {
        return controller;
    }

    /**
     * Used to obtain the color
     * @return ColorLibrary
     */
    public ColorLibrary getColorLibrary() {
        return colorLibrary;
    }
}
