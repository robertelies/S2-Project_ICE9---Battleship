package Presentation.Controller.Subcontrollers;

import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.NewGameView;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Used to determine the logic required to perform the user choice in the newGame View
 *
 * @author ICE 9
 */

public class NewGameController implements ActionListener {
    private static Color color;
    private MainView view;
    private Integer numPlayers;
    private ColorLibrary colors = new ColorLibrary();
    private FormManager formManager;
    private UserManager userManager;
    private GameManager gameManager;
    private NewGameView newView;
    private ArrayList<Color> colorAI;



    /**
     * Used to controll a new game
     */
    public NewGameController(){
        color = colors.getColor(2);
        setColors();
    }

    /**
     * Used to set different colors to the different AI's
     */
    public void setColors(){
        colorAI=new ArrayList<>();
        colorAI.add(colors.getColor(1));
        colorAI.add(colors.getColor(2));
        colorAI.add(colors.getColor(3));
        colorAI.add(colors.getColor(4));
    }
    /**
     * Used to check the user interacted
     * @param e the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        newView=view.getNewGame();
        switch (e.getActionCommand()) {
            case "SUBMIT NEW GAME" -> {
                if(formManager.checkAllFieldsFilled("newGame")) {

                    if (!newView.isPressed()) {
                        JOptionPane.showMessageDialog(view, "Please select the color of the user");
                    } else {
                        if (!gameManager.gameNameExists(newView.getTextName().getText())){
                            colorAI.remove(color);
                            userManager.getUserPlaying().setColor(color);
                            gameManager.setGameName(newView.getTextName().getText());
                            showGame("6");
                        } else {
                            JOptionPane.showMessageDialog(view, "This game name already exists");
                        }

                    }
                }else{
                    JOptionPane.showMessageDialog(view, "Please fill all fields");
                }
            }
            case "RESET NEW GAME" -> {
                color = Color.white;
                newView.setPressed(false);
                showGame("4");
            }
            case "CYAN" -> {
                color = colors.getColor(2);
                newView.setPressed(true);
                view.getNewGame().getSubmit().setBackground(color);
            }
            case "PURPLE" -> {
                color = colors.getColor(1);
                newView.setPressed(true);
                view.getNewGame().getSubmit().setBackground(color);
            }
            case "GREEN" -> {
                color = colors.getColor(4);
                newView.setPressed(true);
                view.getNewGame().getSubmit().setBackground(color);
            }
            case "ORANGE" -> {
                color = colors.getColor(3);
                newView.setPressed(true);
                view.getNewGame().getSubmit().setBackground(color);
            }
            case "SELECTION" -> {
                numPlayers = Integer.parseInt(Objects.requireNonNull(view.getNewGame().getCombo().getSelectedItem()).toString());
            }
        }
    }

    /**
     * Used to determine what visuals to show the user
     * @param caseShow a constant that will show that case
     */
    public void showGame(String caseShow){
        view.getCl().show(view.getContentPane(),caseShow);
    }

    /**
     * A getter for the number of players
     * @return an int
     */
    public int getNumPlayers(){
        return numPlayers;
    }

    /**
     * A getter for the color
     * @return the Color from the implemented class
     */
    public Color getColor(){
        return color;
    }




    /**
     * A setter for the view
     * @param view MainView
     */
    public void registerview(MainView view) {
        this.view = view;
    }

    /**
     * Setter for the managers
     * @param formManager to manage user input on window
     * @param userManager to manage user data
     * @param gameManager to manage a game
     */
    public void registerManagers(FormManager formManager, UserManager userManager, GameManager gameManager) {
        this.formManager = formManager;
        this.userManager = userManager;
        this.gameManager = gameManager;
    }

    /**
     * Obtains the colors of the AI
     * @return an ArrayList of colors
     */
    public ArrayList<Color> getColorsAI(){
        return colorAI;
    }

    /**
     * Used to take a color away from an assigned player
     * @param color color removing
     */
    public void removeColor(Color color){
        this.color = color;
        for (int i = 0; i < colorAI.size(); i++) {
            if (colorAI.get(i).equals(color)) colorAI.remove(i);
        }
    }
}
