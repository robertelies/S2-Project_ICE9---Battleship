package Persistance.Database;

import Persistance.Config.ConfigObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedHashMap;

/**
 * Used to be able to run commands on the database for a game
 *
 * @author ICE 9
 */

public class SQLGameDao implements GameDao{

    private SQLConnector connection;

    /**
     * to connect the DAO to a database
     * @param config the config object of containing the information for the database
     */
    @Override
    public void registerConnection(ConfigObject config){
        this.connection = new SQLConnector(config);
    }


    /**
     * a function to get the number of games played by a certain user
     * @param username the username of the player we want to search for
     * @return the number of games played by the searched user
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public int getGamesPlayed(String username) throws SQLException{
        if (connection != null){
            ResultSet resultSet = connection.selectQuery("SELECT COUNT(Game_ID) AS Games_Played FROM Game WHERE  Player = '"+username+"';");
            if (resultSet != null) {
                resultSet.next();
                return resultSet.getInt("Games_Played");
            }
            throw new SQLException("No results retrieved");
        }else {
            throw new SQLException("Connection not established");
        }
    }

    /**
     * a function to get the number of games won by a certain user
     * @param username the username of the player we want to search for
     * @return the number of games won by the searched user
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public int getGamesWon(String username) throws SQLException{
        if (connection != null){
            ResultSet resultSet = connection.selectQuery("SELECT SUM(case when winner = 1 then 1 else 0 end) AS Games_Won FROM Game WHERE Player = '"+username+"';");
            if (resultSet != null) {
                if(resultSet.next()) {
                    return resultSet.getInt("Games_Won");
                }
            }
            return 0;
        }else {
            throw new SQLException("Connection not established");
        }
    }

    /**
     * a function to get the number of attacks per game, and the number of games with that number of attacks for a given user
     * @param username the username of the player we want to search for
     * @return the number of attacks, and how many games with that number of attacks
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public LinkedHashMap<Integer,Integer> getAttacksPerGame (String username) throws SQLException{
        if (connection != null){
            ResultSet resultSet = connection.selectQuery("SELECT Attacks_Made, COUNT(Game_ID) AS Games FROM Game WHERE Player = '"+username+"' GROUP BY Attacks_Made ORDER BY Attacks_Made;");
            if (resultSet != null) {
                LinkedHashMap<Integer,Integer> results = new LinkedHashMap<>();
                while(resultSet.next()){
                    results.put(resultSet.getInt("Attacks_Made"),resultSet.getInt("Games"));
                }
                if(results.isEmpty()){
                    results.put(0,0);
                }
                return results;
            }
            throw new SQLException("No results retrieved");
        }else {
            throw new SQLException("Connection not established");
        }
    }



    /**
     * saving a game to the database
     * @param username the username of the player who played the game
     * @param no_opponents the number of opponents in the game
     * @param attacks_made the number of attacks made during the game
     * @param duration the duration of the game
     * @param won true if the user won the game, false otherwise
     * @param ships_remaining the users number of ships remaining at the end of the game
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public void saveGame(int game_id,String username, int no_opponents, int attacks_made, Time duration, boolean won, int ships_remaining, boolean game_over) throws SQLException{
        if (connection != null){
            connection.insertQuery("INSERT INTO Game (Player, No_Opponents, Attacks_Made, Duration,Winner,Number_of_ships_remaining) VALUES ("+game_id+",'"+username+"',"+no_opponents+","+attacks_made+",'"+duration+"',"+won+","+ships_remaining+","+game_over+") ON DUPLICATE KEY " +
                    "UPDATE  Attacks_Made = "+attacks_made+" , Duration = '"+duration+"' , Winner = "+won+" , Number_of_ships_remaining = "+ships_remaining+", Game_Over = "+game_over+";");

        }else {
            throw new SQLException("Connection not established");
        }
    }

    /**
     * saving a game to the database
     * @param username the username of the player who played the game
     * @param no_opponents the number of opponents in the game
     * @param attacks_made the number of attacks made during the game
     * @param duration the duration of the game
     * @param won true if the user won the game, false otherwise
     * @param ships_remaining the users number of ships remaining at the end of the game
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public void saveGame(int game_id,String username, int no_opponents, int attacks_made, Time duration, boolean won, int ships_remaining, boolean game_over, String game_path) throws SQLException{
        if (connection != null){
            connection.insertQuery("INSERT INTO Game (Game_id,Player, No_Opponents, Attacks_Made, Duration,Winner,Number_of_ships_remaining, Game_Over, Game_Path) VALUES ("+game_id+",'"+username+"',"+no_opponents+","+attacks_made+",'"+duration+"',"+won+","+ships_remaining+","+game_over+",'"+game_path+"') ON DUPLICATE KEY " +
                    "UPDATE  Attacks_Made = "+attacks_made+" , Duration = '"+duration+"' , Winner = "+won+" , Number_of_ships_remaining = "+ships_remaining+", Game_Over = "+game_over+";");

        }else {
            throw new SQLException("Connection not established");
        }
    }

    @Override
    public int getNextGameId() throws SQLException {
        if (connection != null){
            ResultSet resultSet = connection.selectQuery("SELECT MAX(game_id) AS next_game_id FROM Game;");
            if (resultSet != null){
                if (resultSet.next()) return resultSet.getInt("next_game_id")+1;
            }
            return 0;
        }else {
            throw new SQLException("Connection not established");
        }
    }
}