package Presentation.View.Subviews.PlayerScreenElements;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.LogoutController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;

import javax.swing.*;
import java.awt.*;

/**
 *  Used to create a button to allow the user to logout
 *
 * @author ICE 9
 */
public class LogoutButton {

    private JButton logoutButton;

    /**
     * Constructor used to give the user the option to logout in a window
     * @param view the mainview
     * @param prevView A string of the previous view
     * @param formManager the manager to check the user input
     * @param userManager the manager of user data
     */
    public LogoutButton(MainView view, String prevView, FormManager formManager, UserManager userManager){
        ButtonShape buttonShape = new ButtonShape();
        ColorLibrary colorLibrary=new ColorLibrary();
        logoutButton = new JButton("LOGOUT");
        LogoutController controller = new LogoutController();
        controller.registerManagers(formManager, userManager);
        controller.registerView(view);
        controller.setPrevView(prevView);
        logoutButton.addActionListener(controller);
        logoutButton = buttonShape.makeFancyButton(logoutButton,colorLibrary.getColor(0), Color.white);
        logoutButton.setActionCommand("LOGOUT");
    }

    /**
     * A getter for the button
     * @return JButton
     */
    public JButton getLogout() {
        return logoutButton;
    }

}
