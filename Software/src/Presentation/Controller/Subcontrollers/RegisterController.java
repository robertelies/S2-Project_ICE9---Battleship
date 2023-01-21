package Presentation.Controller.Subcontrollers;

import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class used to obtain the choice from the user in the RegisterView and then do something based on the input
 *
 * @author ICE 9
 */

public class RegisterController implements ActionListener {
    private MainView view;
    private RegisterView regView;
    private Boolean submitted;
    private UserManager userManager;
    private FormManager formManager;
    private GameManager gameManager;
    private LogoutController logOutController;

    /**
     * Constructor used in the main
     */
    public RegisterController(){

        this.submitted=false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.regView = view.getRegView();
        switch (e.getActionCommand()){
            case "SUBMIT REGISTER":
                if(formManager.checkAllFieldsFilled("register")) {
                    if(formManager.checkPasswordIsEqual()){
                        JOptionPane.showMessageDialog(view, "User registered correctly");
                        //new adjustments
                        int flag = formManager.setUser();
                        gameManager.setUser(userManager.getUserPlaying());
                        if(flag == 0) {
                            showGame("2");
                            submitted = true;
                        }else{
                            //for cascading messages, what format do you want to follow
                            if(flag == UserManager.USERNAME_TAKEN) {
                                JOptionPane.showMessageDialog(view, "This username has been taken");
                                submitted = false;
                            }
                            if(flag == UserManager.SQL_EXCEPTION) {
                                JOptionPane.showMessageDialog(view, "There was an error. Please try again");
                                submitted = false;
                            }
                            if(flag == UserManager.INCORRECT_EMAIL_FORMAT) {
                                JOptionPane.showMessageDialog(view, "The email format was found to be incorrect");
                                submitted = false;
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(view, "Password and Password Confirmation have to be the same");
                        submitted=false;
                    }
                }else{
                    JOptionPane.showMessageDialog(view, "You have to fill all fields before continuing!");
                    submitted=false;
                }
                break;
            case "RESET REGISTER":
                regView.clearFields();
                showGame("2");
                break;
            case "NEW GAME REGISTER":
                if(submitted) {
                    showGame("4");
                }else{
                    JOptionPane.showMessageDialog(view, "You have to fill all fields before continuing!");
                }
                break;
            case "CONTINUE GAME REGISTER":
                if(submitted) {
                    view.getContGameView().getController().updateComboBox(view.getContGameView().getCombo());
                    view.getContGameView().getController().updatePanel(view.getContGameView().getLeft());
                    showGame("5");
                }else{
                    JOptionPane.showMessageDialog(view, "You have to fill all fields before continuing!");
                }
                break;
            case "SHOW STATISTICS REGISTER":
                if(submitted) {
                    showGame("10");
                }else{
                    JOptionPane.showMessageDialog(view, "You have to fill all fields before continuing!");
                }
                break;
            case "LOGOUT":
                logOutController.setPrevView("2");
                if(submitted){
                    showGame("13");
                }else{
                    JOptionPane.showMessageDialog(view,"You have to fill all fields before continuing!");
                }
                break;
        }
    }

    /**
     * Used to obtain visuals for the game
     * @param caseShow a constant to define what to show
     */
    public void showGame(String caseShow){
        view.getCl().show(view.getContentPane(),caseShow);
    }



    /**
     * A setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /**
     * Used to set the managers / logout controller
     * @param userManager for the user
     * @param formManager to check user input in the screen
     * @param gameManager for the game
     * @param logOutController to logout
     */
    public void registerManager(UserManager userManager, FormManager formManager, GameManager gameManager,LogoutController logOutController) {
        this.userManager = userManager;
        this.formManager = formManager;
        this.gameManager = gameManager;
        this.logOutController = logOutController;
    }
}
