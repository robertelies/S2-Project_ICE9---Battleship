package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.StatisticsController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.BarChart;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.ModelChart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class used for displaying stats about a game
 *
 * @author ICE 9
 */

public class StatisticsViewBar {

    private JBackgroundPanel viewImagePanel;
    private StatisticsController controller;
    private JButton goBack;
    private BarChart chart;
    private MainView mainView;
    private FormManager formManager;
    private UserManager userManager;
    private LinkedHashMap<Integer, Integer> dataBar;

    /**
     * Constructor used to show user
     */
    public StatisticsViewBar() {

    }

    private void setButton() {
        ButtonShape buttonShape= new ButtonShape();
        ColorLibrary colorLibrary = new ColorLibrary();
        goBack = new JButton("GO BACK");
        goBack = buttonShape.makeFancyButton(goBack,Color.white,colorLibrary.getColor(0));
        goBack.addActionListener(controller);
        goBack.setActionCommand("GO BACK BAR");
    }

    /**
     * Used to visualize the data
     */
    public void setBar() {
        ColorLibrary colorLibrary = new ColorLibrary();
        chart = new BarChart();
        chart.addLegend("Total number of attacks per game", colorLibrary.getColor(2));
        dataBar = controller.setStatsBarchart();
        for (Integer key: controller.setStatsBarchart().keySet()){
            chart.addData(new ModelChart(key.toString(), new double []{dataBar.get(key)}));
        }
        chart.setBorder(new EmptyBorder(20,20,20,20));
    }

    /**
     * Used to give the chart data
     */
    public void setChartWithCurrentData(){
        dataBar = controller.setStatsBarchart();
        chart.clearData();
        for (Integer key: controller.setStatsBarchart().keySet()){
            chart.addData(new ModelChart(key.toString(),new double []{dataBar.get(key)}));
        }
        chart.setBorder(new EmptyBorder(20,20,20,20));
    }



    /**
     * Used to define elements of the visualization
     */
    public void updateChart(){
        setBar();
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel,BoxLayout.Y_AXIS));
        viewImagePanel.setBorder(new EmptyBorder(120,50,100,50));
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        JLabel statistics = new JLabel("TOTAL ATTACKS");
        statistics.setForeground(Color.white);
        statistics.setFont(new Font("Verdana", Font.BOLD,30));
        viewImagePanel.add(statistics);
        panel.add(chart);
        setButton();
        panel.add(goBack);
        LogoutButton logout = new LogoutButton(mainView,"12",formManager,userManager);
        panel.add(logout.getLogout());
        viewImagePanel.add(panel);
    }

    /**
     * A getter for the panel
     * @return JBackgroundPanel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }

    /**
     * A setter for the controller
     * @param statisticsController controller designed for this view
     */
    public void registerController(StatisticsController statisticsController) {
        this.controller = statisticsController;
    }

    /**
     * Used to set the managers
     * @param formManager for user input
     * @param userManager for user data
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

    /**
     * a getter
     * @return BarChart
     */
    public BarChart getBar() {
        return chart;
    }

    /**
     * Setter
     * @param chart the BarChart to be used
     */
    public void setChart(BarChart chart){
        this.chart=chart;
    }

    /**
     * getter for the controller
     * @return StatisticsController
     */
    public StatisticsController getController(){
        return controller;
    }
}
