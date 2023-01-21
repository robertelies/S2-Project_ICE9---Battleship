package Presentation.Controller.Subcontrollers;


import Business.manager.BoardManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.Controller.Listeners.GameListener;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PlayerScreenElements.BoardFeatures.BoardGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A class to manage user interaction with the view about a Battle
 *
 * @author ICE 9
 */

public class BattleViewController implements ActionListener, GameListener {

    private MainView view;
    private ArrayList<BoardGrid> boards;
    private ArrayList<JTable> tables;
    private ArrayList<JLabel> timers;
    private ArrayList<JLabel> userNames;
    private UserManager userManager;
    private GameManager gameManager;

    /**
     * Constructor used for in the main
     */
    public BattleViewController(){
        tables=new ArrayList<>();
        boards=new ArrayList<>();
        timers=new ArrayList<>();
        userNames=new ArrayList<>();
    }

    /**
     * Used to adda board
     * @param board the board to be added
     */
    public void addBoard(BoardGrid board){
        boards.add(board);
    }

    /**
     * Used to add a time to the view
     * @param timer JLabel of the timer
     */
    public void addTimer(JLabel timer){
        timers.add(timer);
    }

    /**
     * A getter for the timer
     * @param index which timer to obtain
     * @return JLabel
     */
    public JLabel getTimer(int index){
        return timers.get(index);
    }
    /**
     * A getter for the board
     * @param index the desired board number
     * @return the board as a BoardGrid
     */
    public BoardGrid getBoard(int index){
        return boards.get(index);
    }


    /**
     * Used when the user clicks on exit
     * @param e  the event associated
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if ("EXIT".equals(e.getActionCommand())) {
            gameManager.stopGame();
            view.resetGameElements();
            showDialog();
        }
    }



    /**
     * Used to show the visuals
     * @param caseShow the constant that determines what to show
     */
    public void showGame(String caseShow){
       view.getCl().show(view.getContentPane(),caseShow);
    }


    /**
     * Used to change the background color depending of the type of hit
     * @param i the x location of the cell
     * @param j the y locaiton of the cell
     */
    @Override
    synchronized public void updateCell(int i, int j) {
        if (gameManager.gameOver()){
            showGameOver();
            return;
        }
            if(i==0){
                i=i+1;
            }
            if(j==0){
                j=j+1;
            }
            int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
            for(int k= startPoint; k<startPoint+(gameManager.getNumPlayers());k++) {
                if (gameManager.getGame().getBoards()[k-startPoint].getColor() == null){
                    if(k<startPoint+(gameManager.getNumPlayers()-1)) {

                        gameManager.getGame().getBoards()[k-startPoint].setColor(view.getNewGame().getController().getColorsAI().get(k - startPoint));
                    }else if(k==startPoint+(gameManager.getNumPlayers()-1)){
                        gameManager.getGame().getBoards()[k-startPoint].setColor(view.getNewGame().getController().getColor());
                    }
                }
            }
            for(int k= startPoint; k<startPoint+gameManager.getNumPlayers();k++) {
                //if(k-startPoint!=player_shooting) {
                    switch (gameManager.getCellState(i, j, k - startPoint)) {
                        case "sunk":
                            getBoard(k).getCells(i, j).setBackground(Color.BLACK);
                            break;
                        case "hit_Ship":
                            getBoard(k).getCells(i, j).setBackground(Color.RED);
                            break;
                        case "miss":
                            getBoard(k).getCells(i, j).setBackground(gameManager.getGame().getBoards()[gameManager.getCellShotBy(i,j,k - startPoint)].getColor());
                            break;
                        case "our_Ship":
                            getBoard(k).getCells(i, j).setBackground(view.getNewGame().getController().getColor().darker());
                            break;
                        case "water":
                            getBoard(k).getCells(i, j).setBackground(Color.white);
                            break;
                    }
                    getBoard(k).getCells(i, j).setClicked(true);
                //}
            }


        }

    /**
     * Used to show on the screen that the game has ended
     */
    public void showGameOver() {
        JOptionPane.showMessageDialog(view,"The game has ended. "+getWinner()+" won");
        gameManager.saveDatabaseOnly();
        gameManager.deleteGameFile();
        gameManager.stopGame();
        gameManager.resetGame();
        showGame("3");
    }

    private String getWinner() {
        for(int i=0;i<gameManager.getNumPlayers()-1;i++){
            if(!gameManager.getGame().getAIBoard(i).allSunk()){
                return "AI "+(i+1);
            }
        }
        return userManager.getUser().getUsername();
    }


    /**
     * Used to update the board when configuring it
     */
    @Override
    synchronized public void updateMyBoard() {
        if (gameManager.gameOver()) return;
        BoardManager boardManager=view.getBoardManager();
        int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                if(boardManager.getBoard().isShip(i,j)){
                    getBoard(startPoint+gameManager.getNumPlayers()-1).getCells(i,j).setBackground(view.getNewGame().getController().getColor().darker());
                }
            }
        }
    }

    /**
     * Used to reset the boards when desired
     */
    public void resetBoards() {
        for(int i=0;i<boards.size();i++){
            boards.get(i).resetBoard();
        }
    }

    /**
     * a function to let the game know that 1second has passed
     */
    @Override
    public void timeAdvanced() {
        updateTimers();
    }


    /**
     * Used to add a table
     * @param table the JTable to be added
     */
    public void addTable(JTable table) {
        tables.add(table);
       //System.out.println(tables.size());
    }

    /**
     * A getter for the constructed table
     * @param index the number of the location
     * @return JTable
     */
    public JTable getTable(int index){
        return tables.get(index);
    }


    /**
     * When a ship has been sunk this is usedto tell to the board
     * @param board the numbered board where the ship is sunk
     */
    @Override
    synchronized public void updateSunkShip(int board) {
        if (gameManager.gameOver()) return;
        int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                if (board == gameManager.getNumPlayers()-1){
                    if(gameManager.getPlayerBoard().isSunk(i,j)){
                        getBoard(startPoint+gameManager.getNumPlayers()-1).getCells(i,j).setBackground(Color.black);
                    }
                }else {
                    if(gameManager.getAiBoard(board).isSunk(i,j)){
                        getBoard(startPoint+board).getCells(i,j).setBackground(Color.black);
                    }
                }
            }
        }
    }


    /**
     * Used to update the table in the Game class inside method attack
     */
    @Override
    synchronized public void updateTable() {
        if (gameManager.gameOver()) return;
        int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
        for(int k= startPoint; k<startPoint+ gameManager.getNumPlayers();k++) {
            for (int shipSize: gameManager.getShipLengths()){
                switch (shipSize) {
                    case 2:
                        if(k==startPoint+ gameManager.getNumPlayers()-1) {
                            if (!gameManager.getPlayerBoard().getShipState(2)){
                                getTable(k).setValueAt("sunk", 2, 1);
                            }
                        }else{
                            if (!gameManager.getAiBoard(k-startPoint).getShipState(2)) {
                                getTable(k).setValueAt("sunk", 2, 1);
                            }
                        }
                        break;
                    case 3:
                        if(k==startPoint+gameManager.getNumPlayers()-1) {
                            if (!gameManager.getPlayerBoard().getShipState(3) && !gameManager.getPlayerBoard().getShipState(4)) {
                                getTable(k).setValueAt("sunk", 3, 1);
                            }
                        }else{
                            if (!gameManager.getAiBoard(k-startPoint).getShipState(3) && !gameManager.getAiBoard(k-startPoint).getShipState(4)) {
                                getTable(k).setValueAt("sunk", 3, 1);
                            }
                        }
                        break;
                    case 4:
                        if(k==startPoint+gameManager.getNumPlayers()-1) {
                            if (!gameManager.getPlayerBoard().getShipState(1)){
                                getTable(k).setValueAt("sunk", 1, 1);
                            }
                        }else{
                            if (!gameManager.getAiBoard(k-startPoint).getShipState(1)) {
                                getTable(k).setValueAt("sunk", 1, 1);
                            }
                        }
                        break;
                    case 5:
                        if(k==startPoint+gameManager.getNumPlayers()-1) {
                            if (!gameManager.getPlayerBoard().getShipState(0)){
                                getTable(k).setValueAt("sunk", 0, 1);
                            }
                        }else{
                            if (!gameManager.getAiBoard(k-startPoint).getShipState(0)) {
                                getTable(k).setValueAt("sunk", 0, 1);
                            }
                        }
                        break;
                }
            }
        }

    }

    /**
     * updates depending if the player can shoot
     * @param allowed boolean if the player is allowed to shoot
     */
    @Override
    public void updatePlayerShotPermission(boolean allowed) {
        int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
        if (allowed){
            getTimer(startPoint+ gameManager.getNumPlayers()-1).setForeground(Color.green);
        }else{
            getTimer(startPoint+ gameManager.getNumPlayers()-1).setForeground(Color.red);
        }

    }


    /**
     * Used to update all the boards at once
     */
    @Override
    public void updateAllBoards() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                updateCell(i,j);
            }
        }
        for (int i = 0; i < gameManager.getNumPlayers(); i++) {
            updateSunkShip(i);
        }
    }


    /**
     * Used to update the game timers
     */
    public void updateTimers() {
        if (gameManager.gameOver()) return;
        int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
        for (int i = startPoint; i < startPoint+ gameManager.getNumPlayers(); i++) {

            if (i==startPoint+gameManager.getNumPlayers()-1){
               if (!gameManager.getPlayerBoard().allSunk()) {
                   getTimer(i).setText(gameManager.getTime());
               }
            } else {
                if (!gameManager.getAiBoard(i-startPoint).allSunk()){
                    getTimer(i).setText(gameManager.getTime());
                }
            }
        }
    }


    /**
     * Used to set the main view
     * @param view mainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }




    /**
     * Used set the managers
     * @param userManager used to manage a user
     * @param gameManager used to manage games
     */
    public void registerManagers(UserManager userManager, GameManager gameManager) {
        this.userManager = userManager;
        this.gameManager = gameManager;
    }

    /**
     * Used to place the names into window for display
     */
    public void updateNames() {
        int startPoint = (gameManager.getNumPlayers() == 2)? 0: ((gameManager.getNumPlayers() == 3)?2:5);
        for(int k=startPoint; k<startPoint+ gameManager.getNumPlayers();k++) {
            if(k==startPoint+gameManager.getNumPlayers()-1) {
                getUsername(k).setText(userManager.getUserPlaying().getUsername());
            }else {
                getUsername(k).setText("AI" + (k + 1 - startPoint));
            }
        }
    }

    /**
     * Used to set a name in the view of hte game
     * @param username user's alias for the game
     */
    public void addName(JLabel username) {
        userNames.add(username);
    }

    /**
     * a getter for the username
     * @param i the location of the user
     * @return a JLabel
     */
    public JLabel getUsername(int i){
        return userNames.get(i);
    }

    /**
     * Used to show a message to the user
     */
    public void showDialog() {
        int result = JOptionPane.showConfirmDialog(view,"Do you want to save the game?","GAME OVER",JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            gameManager.saveGame();
            showGame("1");
        }else {
            gameManager.saveDatabaseOnly();
            showGame("1");
        }
        gameManager.resetGame();
    }
}
