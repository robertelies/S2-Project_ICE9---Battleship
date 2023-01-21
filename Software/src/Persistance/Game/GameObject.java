package Persistance.Game;

import Business.Entities.AI;
import Business.Entities.Player;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * A class used to represent a Game before it is stored
 *
 * When developing this class it was tempting to utilize the 'real' game class
 * but that class contains methods and variables not important for the persistence process
 * for this reason this class was created to simply code
 *
 * @author ICE 9
 */

public class GameObject {

    @Expose
    private int gameID;
    @Expose
    private String gameName;
    @Expose
    private Player player;
    @Expose
    private int numAI;
    @Expose
    private ArrayList<AI> inGameAI;
    @Expose
    private String duration;
    /**
     * Used for writing to a file
     * @param newID id of game
     * @param gameName name of game
     * @param player player in game
     * @param numAI number of AI
     * @param inGameAI Ai in the game
     * @param duration the length of the game
     */
    public GameObject(int newID, String gameName ,Player player, int numAI, ArrayList<AI> inGameAI,String duration) {
        this.gameID = newID;
        this.gameName = gameName;
        this.player = player;
        this.numAI = numAI;
        this.inGameAI = inGameAI;
        this.duration = duration;
    }

    /**
     * A getter for the player
     * @return the player of type Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * A getter for the number of AI
     * @return the number of AI as an int
     */
    public int getNumAI() {
        return numAI;
    }

    /**
     * obtains the AI that are in the game
     * @return an arraylist containing the AI
     */
    public ArrayList<AI> getInGameAI() {
        return inGameAI;
    }

    /**
     * A getter for the id of the game
     * @return an int of the game id
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * a getter for the name of the game
     * @return a string of the name
     */
    public String getGameName() {
        return  gameName;
    }

    /**
     * Used to obtain the time of a game
     * @return a string of the time
     */
    public String getTime() {
        return duration;
    }
}
