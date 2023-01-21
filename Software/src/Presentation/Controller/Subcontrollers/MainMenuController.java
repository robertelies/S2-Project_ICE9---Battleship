package Presentation.Controller.Subcontrollers;

import Presentation.View.MainView.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to determine what is required to do based on the user input in the main menu
 *
 * @author ICE 9
 */

public class MainMenuController implements ActionListener {
    private MainView view;

    /**
     * Constructor used in the main
     */
    public MainMenuController() {

    }

    /**
     * Used to inform the user has performed an action
     * @param e the action
     */
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "LOGIN MENU":
                    showGamePlayers("3");
                    break;
                case "REGISTER MENU":
                    showGamePlayers("2");
                    break;
            }
        }

    /**
     * Used to visualize
     * @param caseShow a constant that will determine what is the next menu
     */
    public void showGamePlayers(String caseShow){
            view.getCl().show(view.getContentPane(),caseShow);
        }



    /**
     * A setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }
}
