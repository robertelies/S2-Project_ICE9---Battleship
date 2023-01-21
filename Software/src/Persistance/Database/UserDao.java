package Persistance.Database;

import Business.Entities.User;
import Persistance.Config.ConfigObject;

import java.sql.SQLException;

/**
 * Used to connect the Db to to the program
 *
 * @author ICE 9
 */

public interface UserDao {

    /**
     * to connect the DAO to a database
     * @param config the config object of containing the information for the database
     */
    public void registerConnection(ConfigObject config);

    /**
     * Will insert the user to the database
     * No exception handling has been applied to this, simply runs the query to insert the user.
     * if necessary check if user exists or not.
     * @param user user to sign up
     * @throws SQLException if there has been any error with communicating with the database
     */
    public void signUp(User user) throws SQLException;

    /**
     * Will insert the user to the database
     * No exception handling has been applied to this, simply runs the query to insert the user.
     * if necessary check if user exists or not.
     * @param username username of the new user
     * @param email email of the new user
     * @param password password of the new user
     * @throws SQLException if there has been any error with communicating with the database
     */
    public void signUp(String username, String email, String password) throws SQLException;

    /**
     * a function to see if a given username already exists in our database
     * @param username the username we are searching for
     * @return true if the username already exists, false if otherwise
     * @throws SQLException if there has been any error with communicating with the database
     */
    public boolean usernameExists(String username) throws SQLException;

    /**
     * a function to see whether an email is already in use for a player
     * @param email the email we are searching for
     * @return true if the email already exists, false if otherwise
     * @throws SQLException if there has been any error with communicating with the database
     */
    public boolean emailExists(String email) throws SQLException;

    /**
     * a function to see if the username and password match those stored in the database
     * @param username the username of the player
     * @param password the password entered for that username
     * @return true if the password is correct, false otherwise
     * @throws SQLException if there has been any error with communicating with the database
     */
    public boolean checkPassword(String username, String password) throws SQLException;

    /**
     * a function to delete a player and all of their games from the database
     * @param username the username of the player we want to delete
     * @throws SQLException if there has been any error with communicating with the database
     */
    public void deletePlayer (String username) throws SQLException;

    /**
     * a function that returns the username of a corresponding email
     * @param email the email belonging to the username we want to retrieve
     * @return the username of the user whose email we entered
     * @throws SQLException if there has been any error with communicating with the database
     */
    public String getUsernameFromEmail(String email) throws SQLException;

}
