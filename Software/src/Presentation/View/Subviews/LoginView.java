package Presentation.View.Subviews;

import Business.Entities.User;
import Presentation.Controller.Subcontrollers.LoginController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class used to design the visuals in Swing regarding the user login
 *
 * @author ICE 9
 */

public class LoginView {

    private JBackgroundPanel viewImagePanel;
    private JButton submit;
    private JButton reset;
    private JButton newGame;
    private JButton continueGame;
    private JButton showStatistics;
    private JButton logout;
    private LoginController controller;
    private ButtonShape buttonShape;
    private ColorLibrary colorLibrary;
    private MainView mainView;
    private JTextField loginText;
    private JPasswordField passwordText;

    /**
     * Constructor used in the main
     */
        public LoginView() {

        }


    /**
     * Used to define the window for the user interaction
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
            Box panelLeft = setFormLeft();
            panelLeft.setBorder(new EmptyBorder(50,0,50,0));
            Box panelRight = setFormRight();
            panelRight.setBorder(new EmptyBorder(70,0,50,0));
            panel.add(panelLeft);
            panel.add(panelRight);
            JLabel title = new JLabel("LOGIN");
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Verdana",Font.BOLD,30));
            viewImagePanel.add(title);
            viewImagePanel.add(panel);
        }
    /**
     * Used to design the area where the user will input information
     * @return the newly designed graphic of type box
     */
    public Box setFormLeft(){
            Box box = Box.createVerticalBox();
            box.createRigidArea(new Dimension(150,150));
            JPanel form = new JPanel();
            form.setOpaque(false);
            form.setBorder(new EmptyBorder(10,60,50,0));
            form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
            JLabel login = new JLabel("username/email");
            loginText = new JTextField();
            JLabel password = new JLabel("password");
            password.setBorder(new EmptyBorder(20,0,0,0));
            passwordText = new JPasswordField();
            JPanel buttonsPannel = new JPanel();
            setButtonsLeft();
            buttonsPannel.setLayout(new GridLayout(1,2,0,30));
            buttonsPannel.setOpaque(false);
            buttonsPannel.setBorder(new EmptyBorder(20,0,0,0));
            buttonsPannel.add(submit);
            buttonsPannel.add(reset);

            form.add(login);
            form.add(loginText);
            form.add(password);
            form.add(passwordText);
            form.add(buttonsPannel);
            box.add(form);
            return box;
        }

    /**
     * Used to show the user the main options
     * @return the designed box
     */
    public Box setFormRight(){
            JPanel form = new JPanel();
            form.setBorder(new EmptyBorder(10,60,50,0));
            form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
            form.setOpaque(false);
            JLabel toDo = new JLabel("What do you want to do?");
            setButtonsRight();
            Box box = Box.createVerticalBox();
            box.createRigidArea(new Dimension(150,150));
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
     * a getter for the panel
     * @return JBackgroundPanel
     */
    public JBackgroundPanel getPanel() {
        return viewImagePanel;
    }

    /**
     * A setter for the buttons that pertain to the login options
     */
    public void setButtonsLeft(){
        submit = new JButton("SUBMIT");
        submit = buttonShape.makeFancyButton(submit,colorLibrary.getColor(0),Color.white);
        reset = new JButton("RESET");
        reset = buttonShape.makeFancyButton(reset,colorLibrary.getColor(0),Color.white);
        reset.addActionListener(controller);
        reset.setActionCommand("RESET LOGIN");
        submit.addActionListener(controller);
        submit.setActionCommand("SUBMIT LOGIN");
    }

    /**
     * A setter for the buttons that pertain to the main options
     */
    public void setButtonsRight(){
        continueGame= new JButton("CONTINUE GAME");
        continueGame = buttonShape.makeFancyButton(continueGame,colorLibrary.getColor(0),Color.white);
        newGame= new JButton("NEW GAME");
        newGame = buttonShape.makeFancyButton(newGame,colorLibrary.getColor(0),Color.white);
        showStatistics = new JButton("SHOW STATISTICS");
        showStatistics = buttonShape.makeFancyButton(showStatistics,colorLibrary.getColor(0),Color.white);
        logout = new JButton("LOGOUT");
        logout = buttonShape.makeFancyButton(logout,Color.white,colorLibrary.getColor(0));
        continueGame.addActionListener(controller);
        continueGame.setActionCommand("CONTINUE GAME LOGIN");
        newGame.addActionListener(controller);
        newGame.setActionCommand("NEW GAME LOGIN");
        showStatistics.addActionListener(controller);
        showStatistics.setActionCommand("SHOW STATISTICS LOGIN");
        logout.addActionListener(controller);
        logout.setActionCommand("LOGOUT");

    }

    /**
     * Obtains the text from the text field provided in the string in the parameter
     * @param param a string that indicates what textfield to obtain
     * @return a string of the textfield
     */
    public String getParam(String param){
        switch(param){
            case "username" -> {
                return loginText.getText();
            }
            case "password"-> {
                return passwordText.getText();
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Used to reset the fields to default after they have been entered
     */
    public void clearFields(){
        loginText.setText("");
        passwordText.setText("");
    }






    /**
     * A setter for the controller
     * @param loginController a LoginController to manage this view
     */
    public void registerController(LoginController loginController) {
        this.controller = loginController;
    }

    /**
     * Used to give the main view from the parameters
     * @param view the MainView
     */
    public void registerView(MainView view) {
        this.mainView = view;
    }
}
