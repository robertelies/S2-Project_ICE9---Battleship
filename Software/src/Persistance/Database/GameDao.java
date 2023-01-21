package Persistance.Database;

import Persistance.Config.ConfigObject;

import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedHashMap;

/**
 * Used to communicate to the database and JSONDAO
 *
 * @author ICE 9
 */

public interface GameDao {

    /**
     * to connect the DAO to a database
     * @param config the config object of containing the information for the database
     */
    public void registerConnection(ConfigObject config);

    /**
     * a function to get the number of games played by a certain user
     * @param username the username of the player we want to search for
     * @return the number of games played by the searched user
     * @throws SQLException if there has been any error with communicating with the database
     */
    public int getGamesPlayed(String username) throws SQLException;

    /**
     * a function to get the number of games won by a certain user
     * @param username the username of the player we want to search for
     * @return the number of games won by the searched user
     * @throws SQLException if there has been any error with communicating with the database
     */
    public int getGamesWon(String username) throws SQLException;

    /**
     * a function to get the number of attacks per game, and the number of games with that number of attacks for a given user
     * @param username the username of the player we want to search for
     * @return the number of attacks, and how many games with that number of attacks
     * @throws SQLException if there has been any error with communicating with the database
     */
    public LinkedHashMap<Integer,Integer> getAttacksPerGame (String username) throws SQLException;

    /**
     * saving a game to the database
     * @param username the username of the player who played the game
     * @param no_opponents the number of opponents in the game
     * @param attacks_made the number of attacks made during the game
     * @param duration the duration of the game
     * @param game_id the id of the game
     * @param game_over to indicate if game has completed
     * @param won true if the user won the game, false otherwise
     * @param ships_remaining the users number of ships remaining at the end of the game
     * @throws SQLException if there has been any error with communicating with the database
     */
    public void saveGame(int game_id,String username, int no_opponents, int attacks_made, Time duration, boolean won, int ships_remaining, boolean game_over) throws SQLException;

    /**
     * saving a game to the database
     * @param username the username of the player who played the game
     * @param no_opponents the number of opponents in the game
     * @param attacks_made the number of attacks made during the game
     * @param duration the duration of the game
     * @param game_path the location of the game
     * @param game_over if the game is over
     * @param game_id id of the game
     * @param won true if the user won the game, false otherwise
     * @param ships_remaining the users number of ships remaining at the end of the game
     * @throws SQLException if there has been any error with communicating with the database
     */
    public void saveGame(int game_id,String username, int no_opponents, int attacks_made, Time duration, boolean won, int ships_remaining, boolean game_over, String game_path) throws SQLException;

    /**
     * gets the next available game id
     * @return an available game id
     * @throws SQLException for errors with database
     */
    public int getNextGameId() throws SQLException;
}

