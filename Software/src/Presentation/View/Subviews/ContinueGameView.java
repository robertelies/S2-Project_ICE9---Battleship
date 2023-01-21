package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.ContinueGameController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

/**
 * Used to provide the user with options regarding a game
 *
 * @author ICE 9
 */

public class ContinueGameView{
    private JBackgroundPanel viewImagePanel;
    private JPanel left;
    private JButton submitConfig;
    private JComboBox combo;
    private ContinueGameController controller;
    private MainView view;
    private ColorLibrary colorLibrary;
    private FormManager formManager;
    private UserManager userManager;

    /**
     * Constructor called in the main
     */
    public ContinueGameView(){

        colorLibrary = new ColorLibrary();

    }


    private Box setRightSection() {
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(70,150));
        ButtonShape buttonShape = new ButtonShape();
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setBorder(new EmptyBorder(10,60,50,0));
        right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
        JLabel quest = new JLabel("What game do you want to use?");
        submitConfig = new JButton("SUBMIT CONFIGURATION");
        submitConfig.addActionListener(controller);
        submitConfig = buttonShape.makeFancyButton(submitConfig,colorLibrary.getColor(0), Color.white);
        submitConfig.setActionCommand("SUBMIT GAME SELECTED");
        combo = new JComboBox();
        combo = controller.updateComboBox(combo);
        right.add(quest);
        right.add(combo);
        right.add(submitConfig);
        LogoutButton logout = new LogoutButton(view,"5",formManager,userManager);
        right.add(logout.getLogout());
        box.add(right);
        return box;
    }


    private Box setLeftSection() {
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(70,150));
        left = new JPanel();
        left.setLayout( new BoxLayout(left,BoxLayout.Y_AXIS));
        left.setOpaque(false);
        left.setBorder(new EmptyBorder(10,200,50,0));
        JLabel available = new JLabel("Available games: ");
        available.setOpaque(false);
        available.setForeground(colorLibrary.getColor(0));
        available.setFont(new Font("Verdana",Font.BOLD,15));
        left.add(available);
        left = controller.updatePanel(left);
        box.add(left);
        return box;
    }

    /**
     * A getter for the panel
     * @return a JBackgroundPanel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }

    /**
     * A getter for the left part of the window
     * @return JPanel
     */
    public JPanel getLeft(){
        return left;
    }

    /**
     * A getter for the combo box
     * @return JComboBox
     */
    public JComboBox getCombo() {
        return combo;
    }

    /***
     * A getter for the controller related
     * @return ContinueGameController
     */
    public ContinueGameController getController(){
        return controller;
    }


    /**
     * A setter for the controller related to the view
     * @param continueGameController the controller designed for this class
     */
    public void registerController(ContinueGameController continueGameController) {
        this.controller = continueGameController;
    }

    /**
     * Used to set associated the necessary managers to the class
     * @param formManager to check completion of view
     * @param userManager to obtain user logic
     */
    public void registerManagers(FormManager formManager, UserManager userManager) {
        this.formManager = formManager;
        this.userManager = userManager;
    }

    /**
     * Constructs the window for the user interaction
     */
    public void updateView(){
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setBorder(new EmptyBorder(190,100,400,100));
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel,BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.setBackground(Color.white);
        Box panelLeft = setLeftSection();
        panelLeft.setBorder(new EmptyBorder(50,0,50,0));
        Box panelRight = setRightSection();
        panelRight.setBorder(new EmptyBorder(70,0,50,0));
        panel.add(panelLeft);
        panel.add(panelRight);
        JLabel title = new JLabel("CONTINUE GAME");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana",Font.BOLD,30));
        viewImagePanel.add(title);
        viewImagePanel.add(panel);
    }

    /**
     * Gives the mainview
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /*public String selectFile()
    {
        JFileChooser fileChooser = new JFileChooser("Files/Games");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Games", "json"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            return fileChooser.getSelectedFile().getName();
        }

        return null;
    }*/
}
