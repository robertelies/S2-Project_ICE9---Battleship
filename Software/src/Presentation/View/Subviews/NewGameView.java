package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.NewGameController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Used to Visualize a new game in SWING format
 *
 * @author ICE 9
 */

public class NewGameView{
    private JBackgroundPanel viewImagePanel;
    private JButton submit;
    private JTextField textName;
    private NewGameController controller;
    private JComboBox numPlayersCombo;
    private final ColorLibrary colorLibrary;
    private MainView mainView;
    private FormManager formManager;
    private UserManager userManager;
    private boolean pressed;

    /**
     * Constructor used when the user wants to create a new game
     */
    public NewGameView() {

        colorLibrary=new ColorLibrary();
    }

    /**
     * Used to set the view, inlcuding the layout, background, boarder, and title
     */
    public void setView(){
        viewImagePanel= new JBackgroundPanel("Images/background.png");
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel,BoxLayout.Y_AXIS));
        viewImagePanel.setBorder(new EmptyBorder(190,100,400,100));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.setBackground(Color.white);
        Box panelLeft = setPanelLeft();
        panelLeft.setBorder(new EmptyBorder(50,0,50,0));
        Box panelRight = setPanelRight();
        panelRight.setBorder(new EmptyBorder(70,0,50,0));
        panel.add(panelLeft);
        panel.add(panelRight);
        JLabel title = new JLabel("NEW GAME");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana",Font.BOLD,30));
        viewImagePanel.add(title);
        viewImagePanel.add(panel);
    }

    /**
     * A getter for the panel
     * @return a JBackgroundPanel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }

    /**
     * Sets Up the Left part of the panel with optionalities about the new Game
     * @return the newly constructed Panel of type Box
     */
    public Box setPanelLeft(){
        ButtonShape buttonShape= new ButtonShape();
        Box box= Box.createVerticalBox();
        Box.createRigidArea(new Dimension(100,100));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
        panel1.setBorder(new EmptyBorder(10,90,50,30));
        panel1.setOpaque(false);
        box.setOpaque(false);
        JLabel enterData = new JLabel("Game name");
        JLabel numPlayers = new JLabel("Num Players");
        numPlayers.setBorder(new EmptyBorder(20,0,0,0));
        textName = new JTextField();
        textName.setBackground(Color.white);

        numPlayersCombo = new JComboBox();
        numPlayersCombo.addItem(2);
        numPlayersCombo.addItem(3);
        numPlayersCombo.addItem(4);
        numPlayersCombo.addActionListener(controller);
        numPlayersCombo.setActionCommand("SELECTION");
        numPlayersCombo.setSelectedItem(2);
        numPlayersCombo.setBackground(Color.white);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setBorder(new EmptyBorder(10,0,90,0));
        buttons.setLayout(new GridLayout(1,2));
        submit = new JButton("SUBMIT");
        submit.addActionListener(controller);
        submit.setActionCommand("SUBMIT NEW GAME");
        submit= buttonShape.makeFancyButton(submit,colorLibrary.getColor(0),Color.white);
        JButton reset = new JButton("RESET");
        reset.addActionListener(controller);
        reset.setActionCommand("RESET NEW GAME");
        reset= buttonShape.makeFancyButton(reset,colorLibrary.getColor(0),Color.white);
        buttons.add(submit);
        buttons.add(reset);

        panel1.add(enterData);
        panel1.add(textName);
        panel1.add(numPlayers);
        panel1.add(numPlayersCombo);
        panel1.add(buttons);

        box.add(panel1);

        return box;

    }

    /**
     * Sets up the right portion of the panel with the choice of the players color
     * @return the newly constructed panel of type box
     */
    public Box setPanelRight(){
        Box box = Box.createVerticalBox();
        Box.createRigidArea(new Dimension(200,200));
        JPanel panel2 = new JPanel();
        panel2.setBorder(new EmptyBorder(10,0,50,0));
        panel2.setOpaque(false);
        box.setOpaque(false);
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        JLabel enterData = new JLabel("Player's color");
        enterData.setBorder(new EmptyBorder(0,0,0,80));
        panel2.add(enterData);
        panel2.add(setColors());
        LogoutButton logout = new LogoutButton(mainView,"4",formManager,userManager);
        panel2.add(logout.getLogout());
        box.add(panel2);
        return box;
    }

    /**
     * Used to set the colors for the players choice in the above method
     * @return a JPanel with the four colors
     */
    public JPanel setColors(){
        JPanel colors = new JPanel();
        colors.setOpaque(false);
        colors.setLayout(new GridLayout(2,2));
        colors.setBorder(new EmptyBorder(20,80,90,80));
        JButton cyan = new JButton();
        JButton magenta = new JButton();
        JButton green = new JButton();
        JButton orange = new JButton();

        cyan.setBackground(colorLibrary.getColor(2));
        cyan.setSize(new Dimension(20,20));
        cyan.setActionCommand("CYAN");
        cyan.addActionListener(controller);

        magenta.setBackground(colorLibrary.getColor(1));
        magenta.setSize(new Dimension(20,20));
        magenta.setActionCommand("PURPLE");
        magenta.addActionListener(controller);

        green.setBackground(colorLibrary.getColor(4));
        green.setSize(new Dimension(20,20));
        green.setActionCommand("GREEN");
        green.addActionListener(controller);

        orange.setBackground(colorLibrary.getColor(3));
        orange.setSize(new Dimension(20,20));
        orange.setActionCommand("ORANGE");
        orange.addActionListener(controller);

        colors.add(cyan);
        colors.add(magenta);
        colors.add(green);
        colors.add(orange);

        return colors;
    }

    /**
     * A getter for the submit button
     * @return JButton
     */
    public JButton getSubmit(){
        return submit;
    }

    /**
     * A getter for the number of players option
     * @return JComboBox
     */
    public JComboBox getCombo() {
        return numPlayersCombo;
    }

    /**
     * A getter for the controller
     * @return NewGameController
     */
    public NewGameController getController(){
        return controller;
    }

    /**
     * A setter to determine a button has been pressed
     * @param pressed a boolean that is true if it was pressed
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Used to indicate a button has been pressed
     * @return a boolean true if pressed
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Used when obtaining information or checking the box has been filled
     * @return JTextField
     */
    public JTextField getTextName(){
        return textName;
    }

    /**
     * Used to reset after user submission
     */
    public void clearElements() {
        numPlayersCombo.setSelectedItem(0);
        textName.setText("");
    }

    /**
     * Used to pass the mainview to this class
     * @param view the mainview
     */
    public void registerView(MainView view) {
        this.mainView = view;
    }

    /**
     * Used as setters for the managers for the forms and users
     * @param formManager used to make sure the user input is good
     * @param userManager the user logic
     */
    public void registerManagers(FormManager formManager, UserManager userManager) {
        this.formManager = formManager;
        this.userManager = userManager;
    }

    /**
     * A setter for the controller
     * @param newGameController the controller for this view
     */
    public void registerController(NewGameController newGameController) {
        this.controller = newGameController;
    }
}
