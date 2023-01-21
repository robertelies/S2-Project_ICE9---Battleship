package Business.Entities;


import com.google.gson.annotations.Expose;

/**This object with this entity is to create an object that simulates a CELL with some properties
 *
 */
public class Cell_ {
    @Expose
    private Boolean hit; //TRUE = the user marked the cell during the game
    @Expose
    private Boolean inShip; //TRUE = there is a ship in the cell
    @Expose
    private int who_shot;
    /**WHEN CREATED, state and inship are false, but when the game continues they will be turned to true
     */
    public Cell_() {
        this.hit = false;
        this.inShip = false;
    }



    //GETTERS-----------------------------------------------------------

    /**if the cell has a ship return TRUE
     *
     * @return a boolean
     */
    public Boolean getInShip() { return inShip; }

    /**if the cell is marked it returns TRUE
     *
     * @return boolean
     */
    public Boolean isHit() { return hit; }

    //GETTERS-----------------------------------------------------------

    /**
     * Used to set a cell and a ship
     * @param inShip true if the ship is in the cell
     */
    public void setInShip(Boolean inShip) { this.inShip = inShip; }

    /**
     * A setter for the state of the ship
     * @param state a boolean that determine the state of the ship
     */
    public void setState(Boolean state) { this.hit = state;}

    /**
     * a function to get who shot this tile
     * @return the id of the person who shot this tile
     */
    public int getWhoShot(){
        return who_shot;
    }

    /**
     * Used to set who shot the cell
     * @param player_shooting the player corresponding to the shot
     */
    public void setWhoShot(int player_shooting) {
        who_shot = player_shooting;
    }
}
