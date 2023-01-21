package Presentation.Controller.Subcontrollers;

import Business.Entities.Board_;
import Business.Entities.Point;
import Business.manager.BoardManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.ConfigBoardView;
import Presentation.View.Subviews.PlayerScreenElements.BoardFeatures.BoardGrid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to determine logic based on ConfigBoardView input
 *
 * @author ICE 9
 */

public class ConfigBoardController implements ActionListener {

    private MainView view;
    private Integer rotate;
    private boolean mode;
    private BoardManager boardManager;
    private GameManager gameManager;
    private UserManager userManager;
    private boolean editing;


    /**
     * Constructor used in the main
     */
    public ConfigBoardController(){

        this.rotate=0;
        mode = true;
        editing=false;
    }

    /**
     * Used to inform that the user interacted with the board
     * @param e the action done to interact
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "SUBMIT BOARD CONFIGURATION":
                if (boardManager.allShipsPlaced()) {
                    gameManager.newGame();
                    gameManager.setNumPlayers(view.getNewGame().getController().getNumPlayers());
                    gameManager.setPlayer(userManager.getUserPlaying(),boardManager.getBoard());
                    setRotate(-1);
                    view.getBattleViewController().updateNames();
                    view.getBattleViewController().updateMyBoard();
                    view.getBattleView().setUserBoard(setBoardWithBackground((BoardGrid) view.getConfView().getBoard()));

                    view.getBattleView().getUsername().setText(userManager.getUserPlaying().getUsername());
                    mode = false;

                    switch (view.getNewGame().getController().getNumPlayers()) {
                        case 2:
                            showGame("7");
                            break;
                        case 3:
                            showGame("8");
                            break;
                        case 4:
                            showGame("9");
                            break;
                    }
                    JOptionPane.showMessageDialog(view, "You can start attacking now");
                    gameManager.startGame();
                } else {
                    JOptionPane.showMessageDialog(view, "Not all ships have been placed.");
                }

                break;
            case "ROTATE":
                rotate=rotate+1;
                if(rotate>3){
                    rotate=0;
                }
                break;

        }
    }

    private JPanel setBoardWithBackground(BoardGrid board) {
        for(int i=1;i<15;i++){
            for(int j=1;j<15;j++){
                if(board.getCells(i,j).getClicked()){
                    board.getCells(i,j).setBackground(getView().getNewGame().getController().getColor());
                }
            }
        }
        return board;
    }

    /**
     * Used to show the visuals for the game
     * @param caseShow the constant to indicate what case to show
     */
    public void showGame(String caseShow){
        view.getCl().show(view.getContentPane(),caseShow);
    }

    /**
     * A getter for the MainView
     * @return MainView
     */
    public MainView getView(){
        return view;
    }

    /**
     * A getter for the variable
     * @return the Integer associated with the variable
     */
    public Integer getRotate(){
        return rotate;
    }

    /**
     * A setter for the variable
     * @param rotate the orientation
     */
    public void setRotate(int rotate){
        this.rotate=rotate;
    }

    /**
     * A getter for the view ConfigBoard
     * @return Config BoardView
     */
    public ConfigBoardView getConfView(){
        return view.getConfView();
    }

    /**
     * Used to obtain the boolean associated with the Mode
     * @return a boolean
     */
    public boolean getMode(){
        return mode;
    }

    /**
     * Used to determine if all the ships are placed
     * @param numSquares the total number of squares
     * @return a boolean that is true if all ships are placed
     */
    public boolean allShipTypePlaced(int numSquares) {
        return boardManager.allShipTypePlaced(numSquares);
    }

    /**
     * Used to determine if a ship will conflict with another location
     * @param i location on x axis
     * @param j location on y axis
     * @param rotate the orientation of the ship
     * @param numSquares the number of squares for the ship
     * @return an int recieved from the boardManager class
     **/
    public int checkContiguous(int i, int j, int rotate, int numSquares) {
        return boardManager.checkContiguous(i,j,rotate,numSquares,this);
    }


    /**
     * Used to tell the board a ship has been placed
     * @param numSquares the size of ship
     * @param i location on x axis
     * @param j location on y axis
     * @param rotate the orientation of the ship
     */
    public void shipPlaced(int numSquares, int i, int j, int rotate) {
        boardManager.shipPlaced(numSquares,i,j,rotate);
    }



    /**
     * Used to set the mode to the value of the parameter
     * @param b a boolean to pass the value
     */
    public void setMode(boolean b) {
        mode = b;
    }

    /**
     * a getter saying ifthe user is editing
     * @return a boolean
     */
    public boolean isEditing() {
        return editing;
    }




    /**
     * Used to reset the game
     */
    public void resetGame() {
        boardManager.resetBoard();
        mode = true;
        rotate = 0;
    }

    /**
     * A setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }

    /**
     * Setters for the managers
     * @param userManager for users
     * @param boardManager for boards
     * @param gameManager for games
     */
    public void registerManagers(UserManager userManager, BoardManager boardManager, GameManager gameManager) {
        this.userManager = userManager;
        this.boardManager= boardManager;
        this.gameManager = gameManager;
    }





}
