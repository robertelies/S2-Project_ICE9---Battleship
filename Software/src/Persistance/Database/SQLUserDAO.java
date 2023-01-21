package Persistance.Database;

import Business.Entities.User;
import Persistance.Config.ConfigObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Used to be able to run commands on the database for a user
 *
 * @author ICE 9
 */

public class SQLUserDAO implements UserDao {

    private SQLConnector connection;

    /**
     * to connect the DAO to a database
     *
     * @param config the config object of containing the information for the database
     */
    @Override
    public void registerConnection(ConfigObject config) {
        this.connection = new SQLConnector(config);
    }


    /**
     * Will insert the user to the database
     * No exception handling has been applied to this, simply runs the query to insert the user.
     * if necessary check if user exists or not.
     *
     * @param user user to sign up
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public void signUp(User user) throws SQLException {
        if (connection != null) {
            connection.insertQuery("INSERT INTO Player (Username, Email, Password) VALUES ('" + user.getUsername() + "','" + user.getEmail() + "','" + user.getPassword() + "');");

        } else {
            throw new SQLException("Connection not established");
        }

    }

    /**
     * Will insert the user to the database
     * No exception handling has been applied to this, simply runs the query to insert the user.
     * if necessary check if user exists or not.
     *
     * @param username username of the new user
     * @param email    email of the new user
     * @param password password of the new user
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public void signUp(String username, String email, String password) throws SQLException {
        if (connection != null) {
            connection.insertQuery("INSERT INTO Player (Username, Email, Password) VALUES ('" + username + "','" + email + "','" + password + "');");

        } else {
            throw new SQLException("Connection not established");
        }

    }

    /**
     * a function to see if a given username already exists in our database
     *
     * @param username the username we are searching for
     * @return true if the username already exists, false if otherwise
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public boolean usernameExists(String username) throws SQLException {
        if (connection != null) {
            ResultSet resultSet = connection.selectQuery("SELECT COUNT(Username) AS num_users FROM Player WHERE Username = '" + username + "';");
            if (resultSet != null) {
                resultSet.next();
                return resultSet.getInt("num_users") != 0;
            }
            throw new SQLException("No results retrieved");
        } else {
            throw new SQLException("Connection not established");
        }

    }

    /**
     * a function to see whether an email is already in use for a player
     *
     * @param email the email we are searching for
     * @return true if the email already exists, false if otherwise
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public boolean emailExists(String email) throws SQLException {
        if (connection != null) {
            ResultSet resultSet = connection.selectQuery("SELECT COUNT(Username) AS num_users FROM Player WHERE Email = '" + email + "';");
            if (resultSet != null) {
                resultSet.next();
                return resultSet.getInt("num_users") != 0;
            }
            throw new SQLException("No results retrieved");
        } else {
            throw new SQLException("Connection not established");
        }

    }

     /**
     * a function to see if the username and password match those stored in the database
     *
     * @param username the username of the player
     * @param password the password entered for that username
     * @return true if the password is correct, false otherwise
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public boolean checkPassword(String username, String password) throws SQLException {
        if (connection != null) {
            ResultSet resultSet = connection.selectQuery("SELECT password FROM Player WHERE username = '" + username + "';");
            if (resultSet != null) {
                resultSet.next();
                return Objects.equals(resultSet.getString("password"), password);
            }
            throw new SQLException("No results retrieved");
        } else {
            throw new SQLException("Connection not established");
        }
    }

    /**
     * a function to delete a player and all of their games from the database
     *
     * @param username the username of the player we want to delete
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public void deletePlayer(String username) throws SQLException {
        if (connection != null) {
            connection.deleteQuery("DELETE FROM Game WHERE Player = '" + username + "';");
            connection.deleteQuery("DELETE FROM Player WHERE username = '" + username + "';");
        } else {
            throw new SQLException("Connection not established");
        }
    }

    /**
     * a function that returns the username of a corresponding email
     * @param email the email belonging to the username we want to retrieve
     * @return the username of the user whose email we entered
     * @throws SQLException if there has been any error with communicating with the database
     */
    @Override
    public String getUsernameFromEmail(String email) throws SQLException{
        if (connection != null) {
            ResultSet resultSet = connection.selectQuery("SELECT username FROM Player WHERE email = '" + email + "';");
            if (resultSet != null) {
                resultSet.next();
                return resultSet.getString("username");
            }
            throw new SQLException("No results retrieved");
        } else {
            throw new SQLException("Connection not established");
        }
    }

}