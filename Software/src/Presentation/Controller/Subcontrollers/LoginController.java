package Presentation.Controller.Subcontrollers;

import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to determine the logic behind when a user logs in
 *
 * @author ICE 9
 */

public class LoginController implements ActionListener {
    private MainView view;
    private FormManager formManager;
    private UserManager userManager;
    private GameManager gameManager;
    private Boolean submitted;
    private LoginView loginView;
    private LogoutController logoutController;

    /**
     * Constructor used in the main
     */
    public LoginController(){

        this.submitted=false;
    }

    /**
     * to determine what to do with the performed action
     * @param e the action performed
     */
    public void actionPerformed(ActionEvent e) {
        loginView=view.getLogView();
         switch(e.getActionCommand()){
            case "SUBMIT LOGIN":
                if(formManager.checkAllFieldsFilled("login")) {
                    if(formManager.checkCorrectPassword()) {
                        gameManager.setUser(userManager.getUserPlaying());
                        submitted = true;
                        JOptionPane.showMessageDialog(view,"User registered correctly");
                        showGame("3");
                    }
                }else{
                    submitted=false;
                    JOptionPane.showMessageDialog(view,"You have to fill all fields before continuing!");
                }
                break;
             case "RESET LOGIN":
                 loginView.clearFields();
                 break;
             case "NEW GAME LOGIN":
                 if(submitted) {
                     showGame("4");
                 }else{
                     JOptionPane.showMessageDialog(view,"You have to fill all fields before continuing!");
                 }
                 break;
             case "CONTINUE GAME LOGIN":
                 if(submitted) {
                     view.getContGameView().getController().updateComboBox(view.getContGameView().getCombo());
                     view.getContGameView().getController().updatePanel(view.getContGameView().getLeft());
                     showGame("5");
                 }else{
                     JOptionPane.showMessageDialog(view,"You have to fill all fields before continuing!");
                 }
                 break;
             case "SHOW STATISTICS LOGIN":
                 if(submitted) {
                     showGame("10");
                 }else{
                     JOptionPane.showMessageDialog(view,"You have to fill all fields before continuing!");
                 }
                 break;
             case "LOGOUT":
                 if(submitted){
                     logoutController.setPrevView("3");
                     showGame("13");
                 }else{
                     JOptionPane.showMessageDialog(view,"You have to fill all fields before continuing!");
                 }
                 break;
        }
    }

    /**
     * Used in determining what to show based on user input
     * @param caseShow a string that will determine what to show
     */
    public void showGame(String caseShow){
        view.getCl().show(view.getContentPane(),caseShow);
    }


    /**
     * a setter
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /**
     * A setter for the managers and logout contorller
     * @param formManager to check user input
     * @param userManager for the user
     * @param gameManager for games
     * @param logoutController to logout
     */
    public void registerManagers(FormManager formManager, UserManager userManager, GameManager gameManager, LogoutController logoutController) {
        this.formManager =formManager;
        this.userManager = userManager;
        this.gameManager = gameManager;
        this.logoutController = logoutController;
    }


}
