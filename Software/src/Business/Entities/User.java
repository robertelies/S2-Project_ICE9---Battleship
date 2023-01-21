package Business.Entities;


import com.google.gson.annotations.Expose;

import java.awt.*;

/**
 * Class used to represent a user of the game
 */

public class User {
    @Expose
    private String username;
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private int rgb;


    /**
     * Used more for things related to persistence
     * @param username string associated with user
     * @param email string associated with user
     * @param password string associated with user
     * @param rgb the color of the user
     */
    public User (String username, String email, String password,int rgb){
        this.username = username;
        this.email = email;
        this.rgb = rgb;
        this.password = password;
    }
    /**
     * Used more for things related to persistence
     * @param username string associated with user
     * @param email string associated with user
     * @param password string associated with user
     */
    public User (String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Used for making a new user from the new game menu
     * @param username username provided
     * @param password password provided
     */
    public User (String username, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }



    /**
     * getter for the username
     * @return a string
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for the email
     * @return a string
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for the password
     * @return a string
     */
    public String getPassword() {
        return password;
    }

    //setters



    /**
     * setter of the color of the player
     * @param color the color to be set too
     */
    public void setColor(Color color){
        this.rgb=color.getRGB();
    }


    /**
     * getter for the color
     * @return a string
     */
    public Color getColor(){
        return new Color(rgb);
    }

}
