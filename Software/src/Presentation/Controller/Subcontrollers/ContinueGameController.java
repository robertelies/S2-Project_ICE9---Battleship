package Presentation.Controller.Subcontrollers;

import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.View.MainView.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to determine what the next step is based on the input receved in the corresponding view
 *
 * @author ICE 9
 */

public class ContinueGameController implements ActionListener {
    private MainView view;
    private GameManager gameManager;
    private UserManager userManager;
    private ConfigBoardController configBoardController;

    /**
     * Constructor used in the main
     */
    public ContinueGameController() {

    }

    /**
     * Used to inform that the user interacted
     * @param e the action done to interact
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SUBMIT GAME SELECTED":
                gameManager.loadGame(view.getContGameView().getCombo().getSelectedItem().toString());
                if (gameManager.gameOver()) {
                    JOptionPane.showMessageDialog(this.view, "This game has already been completed.");
                } else if (gameManager.samePlayer(userManager.getUserPlaying())) {
                    view.getNewGame().getController().removeColor(gameManager.getGame().getPlayerColor());
                    gameManager.startGame();
                    configBoardController.setMode(false);
                    switch (gameManager.getNumPlayers()) {
                        case 2 -> showGame("7");
                        case 3 -> showGame("8");
                        case 4 -> showGame("9");
                    }

                } else {
                    JOptionPane.showMessageDialog(this.view, "This game does not belong to you.");

                }
                break;
        }
    }

    /**
     * Used to show the visuals
     * @param caseShow to determine what to show
     */
    public void showGame(String caseShow) {
        view.getCl().show(view.getContentPane(), caseShow);
    }

    /**
     * Used to update the panel that contains all the available games to chose from
     * @param panel the panel to be updated
     * @return JPanel updated
     */
    public JPanel updatePanel(JPanel panel) {
        panel.removeAll();
        JLabel available = new JLabel("Available games: ");
        available.setOpaque(false);
        available.setFont(new Font("Verdana",Font.BOLD,15));
        panel.add(available);
        for (String fileName : gameManager.getUsersGamesAttributes()) {
            panel.add(new JLabel(fileName));
        }
        return panel;
    }

    /**
     * Used to update the box with available games to continue
     * @param combo box to be modified
     * @return JComboBox
     */
    public JComboBox updateComboBox(JComboBox combo) {
        combo.removeAllItems();
        for (String fileName : gameManager.getUsersGames()) {
            combo.addItem(fileName);
        }
        return combo;
    }

    /**
     * Sets the MainView
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view =view;
    }

    /**
     * A setter for the managers
     * @param gameManager for the game
     * @param userManager for the user
     * @param configBoardController for configuring a board
     */
    public void registerManagers(GameManager gameManager, UserManager userManager, ConfigBoardController configBoardController) {
        this.gameManager = gameManager;
        this.userManager = userManager;
        this.configBoardController = configBoardController;
    }
}
