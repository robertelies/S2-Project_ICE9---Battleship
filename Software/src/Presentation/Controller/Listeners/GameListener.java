package Presentation.Controller.Listeners;

/**
 * A class used to communicate to boards during games
 *
 * @author ICE 9
 */

public interface GameListener {


    /**
     * Used to refresh a certain board
     */
    void updateMyBoard();

    /**
     * Used to refresh all the boards
     */
    void updateAllBoards();

    /**
     * Used to update ship status
     * @param numOfAI AI involved in game
     */
    void updateSunkShip(int numOfAI);

    /**
     * used to refresh a cell
     * @param x coordinate x
     * @param y coordinate y
     */
    void updateCell(int x, int y);

    /**
     * Used to refresh during games
     */
    void updateTable();

    /**
     * Used to determine if the player can shoot
     * @param allowed if the player can shoot
     */
    void updatePlayerShotPermission(boolean allowed);

    /**
     *
     */
    void timeAdvanced();
}
