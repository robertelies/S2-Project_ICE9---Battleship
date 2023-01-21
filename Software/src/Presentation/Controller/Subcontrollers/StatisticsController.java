package Presentation.Controller.Subcontrollers;



import Business.manager.UserManager;
import Business.manager.StatisticsManager;
import Presentation.View.MainView.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Used to deal with user input from the statistics view
 *
 * @author ICE 9
 */

public class StatisticsController implements ActionListener {
    private MainView view;
    private StatisticsManager stats;

    /**
     * Constructor called from the main
     */
    public StatisticsController(){


    }

    /**
     * The user has interacted
     * @param e the action done
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "GO BACK PIE":
                showGame("10");
                break;
            case "GO BACK BAR":
                showGame("10");
                break;
        }

    }

    /**
     * Used for visualization
     * @param caseShow a String containing what visuals will be run in the switch
     */
    public void showGame(String caseShow){
        view.getCl().show(view.getContentPane(),caseShow);
    }

    /**
     * A getter for the pie stats
     * @return an arraylist where each position is a section of the pie chart
     */
    public ArrayList<Integer> getStatsPiechart() {
        ArrayList<Integer> piechartArray = new ArrayList<>();

        int statWins = stats.getNumWins();
        int statGames = stats.getTotalGames() - statWins;

        piechartArray.add(statWins);  //position 0
        piechartArray.add(statGames);  //position 1

        return piechartArray;
    }

    /**
     * A getter for the stats to be used for visuals
     * @return a list with the (number of attacks, number of games)
     *
     **/
    public LinkedHashMap<Integer, Integer> setStatsBarchart() {
        return stats.getNumAttacks();
    }


    /**
     * a setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /**
     * A setter for the manager
     * @param userManager for user data
     */
    public void registerManager(UserManager userManager) {
        stats = new StatisticsManager(userManager);
    }
}
