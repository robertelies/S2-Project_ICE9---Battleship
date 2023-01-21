package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.StatisticsMenuController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A class used to represent the menu for the statistics
 *
 * @author ICE 9
 */

public class StatisticsMenu {
    private JBackgroundPanel viewImagePanel;
    private JButton pie;
    private JButton bar;
    private JButton goBack;
    private FormManager formManager;
    private UserManager userManager;
    private StatisticsMenuController controller;
    private MainView view;


    /**
     * Constructor for the user to select from menu
     */
    public StatisticsMenu() {


    }


    /**
     * Used to set the visuals
     */
    public void setView(){
        ColorLibrary colorLibrary = new ColorLibrary();
        ButtonShape buttonShape = new ButtonShape();
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setOpaque(false);
        JPanel sub = new JPanel();
        sub.setOpaque(false);
        viewImagePanel.setBorder(new EmptyBorder(200,0,200,0));
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel, BoxLayout.Y_AXIS));

        pie = new JButton("SEE PLAYER VICTORIES");
        pie = buttonShape.makeFancyButton(pie, Color.white,colorLibrary.getColor(0));
        pie.addActionListener(controller);
        pie.setActionCommand("SEE PLAYER VICTORIES");

        bar = new JButton("SEE TOTAL ATTACKS");
        bar = buttonShape.makeFancyButton(bar,Color.white,colorLibrary.getColor(0));
        bar.addActionListener(controller);
        bar.setActionCommand("SEE TOTAL ATTACKS");

        goBack = new JButton("GO BACK");
        goBack = buttonShape.makeFancyButton(goBack,Color.white,colorLibrary.getColor(0));
        goBack.addActionListener(controller);
        goBack.setActionCommand("GO BACK");

        JLabel title = new JLabel("STATISTICS");

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Market",Font.BOLD,70));
        //spaces = new JLabel("    ");
        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(0,0,170,200));
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
        menu.add(pie);
        menu.add(new JLabel("  "));
        menu.add(bar);
        menu.add(new JLabel("  "));
        menu.add(setExitButtonsPanel());
        sub.add(title);
        viewImagePanel.add(sub);
        viewImagePanel.add(menu);
    }
    /**
     * A setter for the panel of the exit button
     * @return the panel of type JPanel
     */
    public JPanel setExitButtonsPanel(){
        JPanel exitButtons = new JPanel();
        exitButtons.setLayout(new BoxLayout(exitButtons,BoxLayout.X_AXIS));
        exitButtons.add(goBack);
        LogoutButton logout = new LogoutButton(view,"10",formManager,userManager);
        exitButtons.add(logout.getLogout());
        return exitButtons;
    }

    /**
     * A getter for the panel
     * @return the panel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }

    /**
     * used to set the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /**
     * Used to set the controller
     * @param statisticsMenuController controller designed for this
     */
    public void registerController(StatisticsMenuController statisticsMenuController) {
        this.controller = statisticsMenuController;
    }

    /**
     * Setters for the managers
     * @param formManager checks user input
     * @param userManager checks user data
     */
    public void registerManagers(FormManager formManager, UserManager userManager) {
        this.formManager = formManager;
        this.userManager = userManager;
    }
}
