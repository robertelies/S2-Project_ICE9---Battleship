package Presentation.View.Subviews;

import Business.Entities.User;
import Presentation.Controller.Subcontrollers.RegisterController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class to construct the visualization when a user is signing up
 *
 * @author ICE 9
 */

public class RegisterView {
    private JBackgroundPanel viewImagePanel;
    private JButton submit;
    private JButton reset;
    private JButton continueGame;
    private JButton newGame;
    private JButton logout;
    private JButton showStatistics;
    private RegisterController controller;
    private ButtonShape buttonShape;
    private ColorLibrary colorLibrary;
    private MainView mainView;
    private JTextField usernameText;
    private JTextField emailText;
    private JPasswordField passwordText;
    private JPasswordField passwordConfirmText;


    /**
     * Constructor used in the main
     */
    public RegisterView() {

    }

    /**
     * Used to design the visuals for the user
     */
    public void setView(){
        buttonShape=new ButtonShape();
        colorLibrary=new ColorLibrary();
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel,BoxLayout.Y_AXIS));
        viewImagePanel.setBorder(new EmptyBorder(190,100,300,100));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.setBackground(Color.white);
        Box panelLeft = setPanelLeft();
        panelLeft.setBorder(new EmptyBorder(50,0,50,0));
        Box panelRight = setPanelRight();
        panelRight.setBorder(new EmptyBorder(70,0,50,0));
        panel.add(panelLeft);
        panel.add(panelRight);
        JLabel title = new JLabel("REGISTER");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana",Font.BOLD,30));
        viewImagePanel.add(title);
        viewImagePanel.add(panel);
    }
    /**
     * Creates the left portion of the screen when a user is signing up
     * @return the location where the user will enter data as type Box
     */
    public Box setPanelLeft() {
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(150, 150));
        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(10,60,50,0));
        JLabel username = new JLabel("username");
        usernameText = new JTextField();
        JLabel email = new JLabel("email");
        email.setBorder(new EmptyBorder(20,0,0,0));
        emailText = new JTextField();
        JLabel password = new JLabel("password");
        password.setBorder(new EmptyBorder(20,0,0,0));
        passwordText = new JPasswordField();
        JLabel passwordConfirm = new JLabel("password confirmation");
        passwordConfirm.setBorder(new EmptyBorder(20,0,0,0));
        passwordConfirmText = new JPasswordField();
        JPanel buttonsPannel = new JPanel();
        buttonsPannel.setOpaque(false);
        buttonsPannel.setBorder(new EmptyBorder(20,0,0,0));
        setButtonsLeft();
        buttonsPannel.setLayout(new GridLayout(1, 2));
        buttonsPannel.add(submit);
        buttonsPannel.add(reset);
        form.add(username);
        form.add(usernameText);
        form.add(email);
        form.add(emailText);
        form.add(password);
        form.add(passwordText);
        form.add(passwordConfirm);
        form.add(passwordConfirmText);
        form.add(buttonsPannel);
        box.add(form);
        return box;
    }

    /**
     * Organizes the right side of the screen that provides the user with the main options
     * @return the constructed Box
     */
    public Box setPanelRight() {
        JLabel toDo = new JLabel("What do you want to do?");
        setButtonsRight();
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(150, 150));
        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setBorder(new EmptyBorder(30,60,50,0));
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        form.add(toDo);
        form.add(new JLabel("  "));
        form.add(continueGame);
        form.add(new JLabel("  "));
        form.add(newGame);
        form.add(new JLabel("  "));
        form.add(showStatistics);
        form.add(new JLabel("  "));
        form.add(logout);
        box.add(form);
        return box;
    }

    /**
     * A getter for the panel
     * @return a JBackgroundPanel
     */
    public JBackgroundPanel getPanel() {
        return viewImagePanel;
    }

    /**
     * Used to set the buttons on the left side related to registering
     */
    public void setButtonsLeft(){
        submit = new JButton("SUBMIT");
        submit=buttonShape.makeFancyButton(submit,colorLibrary.getColor(0),Color.white);
        reset = new JButton("RESET");
        reset = buttonShape.makeFancyButton(reset,colorLibrary.getColor(0),Color.white);
        reset.addActionListener(controller);
        reset.setActionCommand("RESET REGISTER");
        submit.addActionListener(controller);
        submit.setActionCommand("SUBMIT REGISTER");
    }

    /**
     * Used to set the buttons on the right side related to a game
     */
    public void setButtonsRight(){
        continueGame = new JButton("CONTINUE GAME");
        continueGame = buttonShape.makeFancyButton(continueGame,colorLibrary.getColor(0),Color.white);
        continueGame.addActionListener(controller);
        continueGame.setActionCommand("CONTINUE GAME REGISTER");
        newGame = new JButton("NEW GAME");
        newGame = buttonShape.makeFancyButton(newGame,colorLibrary.getColor(0),Color.white);
        newGame.addActionListener(controller);
        newGame.setActionCommand("NEW GAME REGISTER");
        showStatistics = new JButton("SHOW STATISTICS");
        showStatistics = buttonShape.makeFancyButton(showStatistics,colorLibrary.getColor(0),Color.white);
        showStatistics.addActionListener(controller);
        showStatistics.setActionCommand("SHOW STATISTICS REGISTER");
        logout = new JButton("LOGOUT");
        logout = buttonShape.makeFancyButton(logout,Color.white,colorLibrary.getColor(0));
        logout.addActionListener(controller);
        logout.setActionCommand("LOGOUT");
    }

    /**
     * Used to check the user has filled the field
     * @param param The specific optionality of type String
     * @return a String of the text field contents
     */
    public String getParam(String param){
        switch(param){
            case "username" -> {
                return usernameText.getText();
            }
            case "email" -> {
                return emailText.getText();
            }
            case "password"-> {
                return passwordText.getText();
            }
            case "passwordConfirm" -> {
                return passwordConfirmText.getText();
            }
            default -> {
                return null;
            }
        }
    }


    /**
     * Used when resetting the input fields
     */
    public void clearFields(){
        usernameText.setText("");
        emailText.setText("");
        passwordText.setText("");
        passwordConfirmText.setText("");
    }



    /**
     * Used as a setter for the controller
     * @param registerController a RegisterController to set our controller equivalent too
     */
    public void registerController(RegisterController registerController) {
        this.controller = registerController;
    }

    /**
     * Given the param it acts a setter for the mainview
     * @param view a MainView
     */
    public void registerView(MainView view) {
        this.mainView = view;
    }
}
