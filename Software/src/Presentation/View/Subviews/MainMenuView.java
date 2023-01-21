package Presentation.View.Subviews;

import Presentation.Controller.Subcontrollers.MainMenuController;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A class used to visualize the main menu
 *
 * @author ICE 9
 */

public class MainMenuView {
    private JBackgroundPanel viewImagePanel;
    private JButton register;
    private JButton login;
    private JLabel spaces;
    private MainMenuController mainMenuController;

    /**
     * Constructor used in the first screen
     */
    public MainMenuView() {

    }

    /**
     * A getter the for panel
     * @return the panel of type JBackgroundPanel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }

    /**
     * A setter for the controller
     * @param controller MainMenuController for this view
     */
    public void registerController(MainMenuController controller){
        mainMenuController = controller;

    }

    /**
     * Used to set  the visuals related to the main menu
     */
    public void updateView(){
        ColorLibrary colorLibrary = new ColorLibrary();
        ButtonShape buttonShape = new ButtonShape();
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setOpaque(false);
        JPanel sub = new JPanel();
        sub.setOpaque(false);
        viewImagePanel.setBorder(new EmptyBorder(200,0,200,0));
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel, BoxLayout.Y_AXIS));
        register = new JButton("REGISTER");
        register = buttonShape.makeFancyButton(register,Color.white,colorLibrary.getColor(0));
        register.addActionListener(mainMenuController);
        register.setActionCommand("REGISTER MENU");
        login = new JButton("LOGIN");
        login = buttonShape.makeFancyButton(login,Color.white,colorLibrary.getColor(0));
        login.addActionListener(mainMenuController);
        login.setActionCommand("LOGIN MENU");
        JLabel title = new JLabel("BATTLESHIPS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Market",Font.BOLD,70));
        spaces = new JLabel(" ");
        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(0,0,170,200));
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
        menu.add(register);
        menu.add(spaces);
        menu.add(login);
        sub.add(title);
        viewImagePanel.add(sub);
        viewImagePanel.add(menu);
    }
}
