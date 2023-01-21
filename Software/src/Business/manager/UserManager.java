package Business.manager;

import Business.Entities.User;
import Persistance.Config.ConfigJSONDAO;
import Persistance.Config.ConfigObject;
import Persistance.Database.SQLUserDAO;
import Persistance.Database.UserDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A class used to handle the process's related to user
 *
 * @author ICE 9
 */

public class UserManager {

    //sign up
    /**
     * Used to say the  signup was successful
     */
    public static final int ALL_GOOD = 19;
    /**
     * Used to say the signup was unsuccessful and the username is taken
     */
    public static final int USERNAME_TAKEN = 1;
    /**
     * Used to say the signup was unsuccessful and the email was wrongly entered
     */
    public static final int INCORRECT_EMAIL_FORMAT = 2;
    /**
     * Used to say the signup was unsuccessful and the email wasnt provided
     */
    public static final int NO_EMAIL_PROVIDED = 3;
    /**
     * Used to say the signup was unsuccessful and the username wasnt provided
     */
    public static final int NO_USERNAME_PROVIDED = 4;
    /**
     * Used to say the signup was unsuccessful and the password wasnt provided
     */
    public static final int NO_PW_PROVIDED = 5;

    /**
     * Used to say the signup was unsuccessful and the error was from SQL
     */
    public static final int SQL_EXCEPTION = 999;


    private UserDao userDAO;

    //sign in
    /**
     * The user entered username is wrong
     */
    public static final int INCORRECT_USERNAME = 101;

    /**
     * The user entered password is wrong
     */
    public static final int INCORRECT_PASSWORD = 102;



    private User user;
    //delete user

    /**
     * Used to delete a user
     * @param user User to be deleted
     * @return an int of a constant indicating if everything is okay
     */
    public int deleteUser(User user){
        try {
            if(this.userDAO.usernameExists(user.getUsername()));
                this.userDAO.deletePlayer(user.getUsername());
        }catch (SQLException e) {
            return SQL_EXCEPTION;
        }
        return ALL_GOOD;
    }


    /**
     * Used to communicate with persistence when logging a player in
     * @param usernameGiven the username of user
     * @param password the pw of user
     * @return an constant int depending on error or not
     */
    public int logIn(String usernameGiven, String password){
        if(usernameGiven.contains("@")){
            try{
                usernameGiven = userDAO.getUsernameFromEmail(usernameGiven);
                //System.out.println(usernameGiven);
            }catch(SQLException e){
                //e.printStackTrace();
            }
        }
        try {
            if (!this.userDAO.usernameExists(usernameGiven))
                    return INCORRECT_USERNAME;
            if (!this.userDAO.checkPassword(usernameGiven, password))
                return INCORRECT_PASSWORD;

            user = new User(usernameGiven,password);
            return ALL_GOOD;
        }catch (SQLException e) {
            return SQL_EXCEPTION;
        }
    }

    /**
     * This function returns an empty user with default values, so when you log out the username on the screen disappears
     * @return an empty user
     */
    public User logout(){
        String default_password = "NotSigned";
        return new User("NotSigned", default_password, "NotSigned",0);
    }

    /**
     * A function that can be called to check the information recieved from the user
    * @param newUser the newly entered user
     * @return an int thats value will depend on the error that arrises from the recieved parameters
     *          -If there is no error the return value will be 19 (Spencer's favorite number)
     */
    public int signUp(User newUser){
        //persistance check username exists
        try {
            if(this.userDAO.usernameExists(newUser.getUsername())) {
                return USERNAME_TAKEN;
            }
            //an externally discovered Class/method's to be used for determing if the email is correct
            String format = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(format);
            if(!pattern.matcher(newUser.getEmail()).matches())
                return INCORRECT_EMAIL_FORMAT;
            if(newUser.getEmail().equals(""))
                return NO_EMAIL_PROVIDED;
            if(newUser.getUsername().equals(""))
                return NO_USERNAME_PROVIDED;
            if(newUser.getPassword().equals(""))
                return NO_PW_PROVIDED;
            //persistance add user to database
            this.userDAO.signUp(newUser);
            return ALL_GOOD;
        } catch (SQLException e) {
            //e.printStackTrace();
            return SQL_EXCEPTION;
        }

    }

    /**
     * A getter for the user
     * @return User
     */
    public User getUser(){
        return user;
    }


    /**
     * A getter for the user involved with a game
     * @return User
     */
    public User getUserPlaying(){
        return user;
    }

    /**
     * Constructor used in the main
     */
    public UserManager(){
        this.user = new User("NotSigned", "notSigned", "NotSigned",0);
        this.userDAO = new SQLUserDAO();
        try {
            ConfigObject a = new ConfigJSONDAO().read("Files/config.json");
            this.userDAO.registerConnection(a);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Used to connect to persistence and notify if the user could be registered to the system
     * @param user the user's information
     * @return a constant int indicating the error if any
     */
    public int setUser(User user){
        int flag = signUp(user);
        if(flag == 19){
            //good
            return 0;
        }else{
            //not
            return flag;
        }

    }

    /**
     * Used to delete a User
     */
    public void deleteCurrentUser() {
        deleteUser(user);
    }
}