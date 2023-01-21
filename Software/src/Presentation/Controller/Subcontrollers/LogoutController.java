package Presentation.Controller.Subcontrollers;

import Business.manager.FormManager;
import Business.manager.UserManager;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.LogoutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to driver logic behind the logoutView
 *
 * @author ICE 9
 */

public class LogoutController implements ActionListener {
        private MainView view;
        private LogoutView logout;
        private FormManager formManager;
        private UserManager userManager;
        private String prevView;

    /**
     * Constructor used by the main and logout button
     */
    public LogoutController(){

            this.prevView="1";
        }

    /**
     * Used to determine when a user has clicked on a button
     * @param e the action performed
     */
    public void actionPerformed(ActionEvent e) {
            logout=view.getLogoutView();
            switch(e.getActionCommand()){
                case "LOGOUT":
                        showGame("13");
                    break;
                case "SUBMIT LOGOUT":
                    if(formManager.checkAllFieldsFilled("logout")) {
                        JOptionPane.showMessageDialog(view, "You have logged out correctly.");
                        userManager.logout();
                    }else{
                        JOptionPane.showMessageDialog(view, "You have to fill all fields before continuing!");
                    }
                    break;
                case "RESET LOGOUT":
                    logout.clearFields();
                    showGame("13");
                    break;
                case "DELETE ACCOUNT":
                    if(formManager.checkAllFieldsFilled("logout")) {
                        JOptionPane.showMessageDialog(view, "Your account was deleted successfully.");
                        userManager.deleteCurrentUser();
                    }else{
                        JOptionPane.showMessageDialog(view, "You have to enter your correct credentials before deleting your account");
                    }
                    break;
                case "LOGOUT ACCOUNT":
                    if(formManager.checkAllFieldsFilled("logout")){
                        showGame("1");
                    }else{
                        JOptionPane.showMessageDialog(view, "You have to enter your correct credentials before exiting the game");
                    }
                    break;
                case "GO BACK":
                    //System.out.println(getPrevView());
                    showGame(getPrevView());
                    break;
            }
        }

    /**
     * Used to determine what to show next
     * @param caseShow what to show
     */
    public void showGame(String caseShow){
            view.getCl().show(view.getContentPane(),caseShow);
        }

    /**
     * a getter to get the previous view
     * @return a string
     */
    public String getPrevView(){
        return prevView;
    }

    /**
     * A setter for the previous view
     * @param prevView the string of the view
     */
    public void setPrevView(String prevView){
            this.prevView=prevView;
    }

    /**
     * a setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /**
     * A setter for the managers
     * @param formManager user input
     * @param userManager user data
     */
    public void registerManagers(FormManager formManager, UserManager userManager) {
        this.formManager = formManager;
        this.userManager =userManager;
    }
}
