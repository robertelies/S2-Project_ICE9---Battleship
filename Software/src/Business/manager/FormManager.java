package Business.manager;

import Business.Entities.User;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.LoginView;
import Presentation.View.Subviews.NewGameView;
import Presentation.View.Subviews.RegisterView;

import javax.swing.*;

/**
 * A class used to handle the different Views and their screens with the information
 * the user could submit
 *
 * @author ICE9
 */

public class FormManager {
    private UserManager userManager;
    private LoginView loginView;
    private RegisterView registerView;
    private NewGameView newGameView;
    private MainView mainView;

    /**
     * Constructor used in the views/controllers
     */
    public FormManager(){

    }

    /**
     * Used to check the password when the User is logging in
     * @return a boolean that is true if the pw is correct
     */
    public boolean checkCorrectPassword() {
        /*boolean found=false;
        int index=0;
        for(int i=0;i<userManager.getUsers().size();i++) {
            if(userManager.getUsernameFromUser(i).equals(loginView.getParam("username"))){
                found=true;
                index=i;
            }
        }
        if(!found){
            JOptionPane.showMessageDialog(mainView,"The user entered is not registered");
            return false;
        }else{
            if(userManager.getPasswordFromUser(index).equals(loginView.getParam("password"))){
                userManager.setIndex(index);
                return true;
            }else{
                return false;
            }
        }*/

        if (userManager.logIn(loginView.getParam("username"),loginView.getParam("password")) == UserManager.INCORRECT_USERNAME){
            JOptionPane.showMessageDialog(mainView,"The user entered is not registered");
            return false;
        } else{
            if(userManager.logIn(loginView.getParam("username"), loginView.getParam("password")) != UserManager.INCORRECT_PASSWORD){
                return true;
            }else{
                JOptionPane.showMessageDialog(mainView,"Incorrect credentials. Please try again");
                return false;
            }
        }
    }


    /**
     * Used in most view's to check if the user completed all fields
     * @param mode a string to determine what view we are in
     * @return a boolean that is true if all fields are filled
     */
    public boolean checkAllFieldsFilled(String mode) {
        loginView=mainView.getLogView();
        registerView=mainView.getRegView();
        newGameView= mainView.getNewGame();
        boolean filled = true;
        switch(mode) {
            case "login":
            case "logout":
                if (loginView.getParam("username").isEmpty()) {
                    filled = false;
                }
                if (loginView.getParam("password").isEmpty()) {
                    filled = false;
                }
            break;
            case "register":
                //System.out.println(registerView.getParam("username"));
                if(registerView.getParam("username").isEmpty()){
                    filled=false;
                }
                if(registerView.getParam("email").isEmpty()){
                    filled=false;
                }
                if(registerView.getParam("password").isEmpty()){
                    filled=false;
                }
                if(registerView.getParam("passwordConfirm").isEmpty()){
                    filled=false;
                }
                break;
            case "newGame":
                filled = !newGameView.getTextName().getText().isEmpty();
                break;
        }
        return filled;
    }

    /**
     * When a user is creating an account this will check the password matches the confirmed
     * @return a boolean that is true if theyre equal
     */
    public boolean checkPasswordIsEqual() {
        return registerView.getParam("password").equals(registerView.getParam("passwordConfirm"));
    }

    /**
     * Used when a user registers their information
     * @return a flag with a constant indicating if the user was registered
     */
    public int setUser() {
        return userManager.setUser(new User(registerView.getParam("username"),registerView.getParam("email"), registerView.getParam("password")));

    }

    /**
     * A setter for the view
     * @param view MainView
     */
    public void setView(MainView view) {
        this.mainView = view;
    }

    /**
     * Sets the manager
     * @param userManager manages user data
     */
    public void setManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
