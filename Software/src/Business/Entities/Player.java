package Business.Entities;

import com.google.gson.annotations.Expose;

/**Game is formed by a list of players
 *
 * @author ICE 9
 */
public class Player {

    @Expose
    private User user;
    @Expose
    private Board_ board;

    /**
     * Constructor used to create a player in the game
     * @param user information about user of program
     * @param board the board layout
     */
    public Player(User user, Board_ board) {
        this.user = user;
        this.board = board;

    }

    /**
     * a getter for the board
     * @return a board
     */

    public Board_ getBoard() {
        return board;
    }

    /**
     * A getter for the user
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Used to determine all the players ships are sunk
     * @return a boolean
     */
    public boolean allSunk() {
        return board.allSunk();
    }

    /**
     * Used to notify a cell the player will attack it
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player_shooting the int of the number of the player
     * @return an int if the cell is allowed to be attacked
     */
    public int attackBoard(int x, int y, int player_shooting){
        return this.board.markCell(x, y,player_shooting);
    }

    /**
     * Used to determine if two users are the same
     * @param currentUser the user operating the game
     * @return a boolean that is true if the usernames equal
     */
    public boolean sameUser(User currentUser) {
        return user.getUsername().equals(currentUser.getUsername());
    }
}
