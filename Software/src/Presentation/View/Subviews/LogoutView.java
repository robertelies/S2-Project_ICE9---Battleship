package Presentation.View.Subviews;

import Presentation.Controller.Subcontrollers.LogoutController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A class used to visualize when a user logs out
 *
 * @author ICE 9
 */

public class LogoutView extends JPanel {

    private JBackgroundPanel viewImagePanel;
    private JButton submit;
    private JButton reset;
    private JButton deleteAccount;
    private JButton logButton;
    private JButton goBack;
    private LogoutController controller;
    private ButtonShape buttonShape;
    private ColorLibrary colorLibrary;
    private JTextField logoutText;
    private JPasswordField passwordText;


    /**
     * Constructor called in the main
     */
    public LogoutView() {

    }

    /**
     * Used to create the window for the user
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
        JLabel title = new JLabel("LOGOUT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana",Font.BOLD,30));
        viewImagePanel.add(title);
        viewImagePanel.add(panel);
    }
    /**
     * Used to recieve the username and password from the user on the left side of the screen
     * @return the constructed box
     */
    public Box setFormLeft(){
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(150,150));
        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setBorder(new EmptyBorder(10,60,50,0));
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        JLabel logout = new JLabel("username/email");
        logoutText = new JTextField();
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

        form.add(logout);
        form.add(logoutText);
        form.add(password);
        form.add(passwordText);
        form.add(buttonsPannel);
        box.add(form);
        return box;
    }

    /**
     * Used for containing the buttons and other optionalities
     * @return the new JPanel in a box
     */
    public Box setFormRight(){
        JPanel form = new JPanel();
        form.setBorder(new EmptyBorder(10,60,50,0));
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        form.setOpaque(false);
        JLabel toDo = new JLabel("Do you want to delete the account?");
        setDeleteLogoutGoBack();
        Box box = Box.createVerticalBox();
        box.createRigidArea(new Dimension(150,180));
        form.add(toDo);
        form.add(new JLabel("  "));
        form.add(deleteAccount);
        form.add(new JLabel("  "));
        form.add(logButton);
        form.add(new JLabel("  "));
        form.add(goBack);
        box.add(form);
        return box;
    }

    /**
     * A getter for the panel
     * @return the panel of type JBackgroundPanel
     */
    public JBackgroundPanel getPanel() {
        return viewImagePanel;
    }

    /**
     * Used to set up the buttons pertaining to the logging out
     */
    public void setButtonsLeft(){
        submit = new JButton("SUBMIT");
        submit = buttonShape.makeFancyButton(submit,colorLibrary.getColor(0),Color.white);
        reset = new JButton("RESET");
        reset = buttonShape.makeFancyButton(reset,colorLibrary.getColor(0),Color.white);
        reset.addActionListener(controller);
        reset.setActionCommand("RESET LOGOUT");
        submit.addActionListener(controller);
        submit.setActionCommand("SUBMIT LOGOUT");
    }

    /**
     * Used to set the Buttons: Delete, Logout, GoBack
     */
    public void setDeleteLogoutGoBack(){
        deleteAccount= new JButton("DELETE ACCOUNT");
        deleteAccount = buttonShape.makeFancyButton(deleteAccount,colorLibrary.getColor(0),Color.white);
        deleteAccount.addActionListener(controller);
        deleteAccount.setActionCommand("DELETE ACCOUNT");
        logButton= new JButton("EXIT");
        logButton = buttonShape.makeFancyButton(logButton,colorLibrary.getColor(0),Color.white);
        logButton.addActionListener(controller);
        logButton.setActionCommand("LOGOUT ACCOUNT");
        goBack= new JButton("GO BACK");
        goBack = buttonShape.makeFancyButton(goBack,colorLibrary.getColor(0),Color.white);
        goBack.addActionListener(controller);
        goBack.setActionCommand("GO BACK");
    }



    /**
     * Used to reset the input fields
     */
    public void clearFields(){
        logoutText.setText("");
        passwordText.setText("");
    }


    /**
     * A setter for the controller
     * @param logoutController LogoutController that manages the logic
     */
    public void registerController(LogoutController logoutController) {
        this.controller = logoutController;
    }
}
