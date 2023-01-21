package Business.manager;

import Business.Entities.*;
import Persistance.Config.ConfigJSONDAO;
import Persistance.Config.ConfigObject;
import Persistance.Database.GameDao;
import Persistance.Database.SQLGameDao;
import Persistance.Game.BoardDAO;
import Persistance.Game.GameJSONDAO;
import Persistance.Game.GameObject;
import Presentation.Controller.Listeners.GameListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * A class to handle outside interactions for the class Game.java
 *
 * @author ICE 9
 */

public class GameManager {


    private Game game;
    private String gameName;
    private User currentUser;

    private BoardDAO boardDAO;
    private GameDao gameDao;
    private GameListener listener;
    private int attackTime = 1000;

    /**
     * Constructor called in the main
     */
    public GameManager() {
        boardDAO = new GameJSONDAO();
        //game = new Game(1000,controller); // NEEDS TO BE SET THROUGH THE CONFIG OBJECT THAT IS READ
        try {
            ConfigObject a = new ConfigJSONDAO().read("Files/config.json");
            gameDao = new SQLGameDao();
            gameDao.registerConnection(a);
            attackTime = a.getTime();
        } catch (FileNotFoundException e) {
           // e.printStackTrace();
        }
    }

    /**
     * Used to create a new game
     */
    public void newGame(){
        game = new Game(attackTime,listener);
        game.setGameName(gameName);
        try {
            
            game.setGameID(gameDao.getNextGameId());
        } catch (SQLException e) {
            //e.printStackTrace();
            game.setGameID(new Random().nextInt(-100000,0));
        }
    }

    /**
     * Used to prepare everything for a new game
     *      includes:
     *          -boards
     *          -timer
     *          -AI
     *
     */
    public void startGame(){
        //controller.correctBoards(getNumPlayers());
        listener.updateMyBoard();
        listener.updateAllBoards();
        //if (game.playerNotRegistered()) return; // player should have their board registered before playing the game
        //game.generateAiBoards();
        game.setStop(false);
        game.startTimer();
        game.startAi();
        game.startUserTimer();


        //ask user to place ships

        //wait for user input saying all is good

        //display good to go

        //--------GAME START ----------

        //one player attacks
            //check the board

        //for each player at the end of the turn we have to check the number of remaining ships and
        //determine if they are still elligible to play the game


    }

    /**
     * Used to provide a temporary stop to the game
     */
    public void stopGame(){
        game.setStop(true);
    }

    /**
     * Used to interact with the persistence to save the information about the game
     * @return a int indicating if the process succeeded
     *          1 -> error
     *          0 -> no error
     */
    public int saveGame(){
        GameObject newSaveFile = new GameObject(game.getGameID(), game.getGameName(),game.getPlayerForSaving(), game.getNumOfAI(), game.getAIsForSaving(),game.getGameTime());
        try{
            boardDAO.write("Files/Games/", newSaveFile);
            gameDao.saveGame(game.getGameID(),game.getPlayerName(),game.getNumOfAI(),game.getAttacksMade(),game.getDuration(),game.playerWon(),game.getPlayerShipsRemaining(),game.getAllSunk(),"Files/Games/"+game.getGameName());
        }catch(IOException | SQLException e){
           // System.out.println("The game was unable to be saved");
            //to indicate error
            return 1;
        }
        //to indicate no error
        return 0;
    }

    /**
     * saves the game data only to the database and not to a file
     */
    public void saveDatabaseOnly() {
        try {
            gameDao.saveGame(game.getGameID(), game.getPlayerName(), game.getNumOfAI(), game.getAttacksMade(), game.getDuration(), game.playerWon(), game.getPlayerShipsRemaining(), game.getAllSunk(), "Files/Games/" + game.getGameName());

        } catch (SQLException throwables) {
            //throwables.printStackTrace();

        }
    }
    /**
     * Used to link a Game to the persistence layer to load a game
     * @param path a string of the path of the file
     */
    public void loadGame(String path){
        try {
            GameObject savedGame = boardDAO.read("Files/Games/"+path);
            game = new Game(savedGame.getGameID(),savedGame.getGameName(),savedGame.getPlayer(),savedGame.getNumAI(),savedGame.getInGameAI(), savedGame.getTime());
            game.registerListener(listener);
            game.setStop(false);
        }catch(FileNotFoundException e){
            //System.out.println("Unable to locate file");
            //return null;
        }

    }


    /**
     * Used to set the AI's and their boards
     * @param numPlayers the number of players in the current game
     */
    public void setNumPlayers(int numPlayers){
        game.setAis(numPlayers-1);
        game.generateAiBoards();
    }

    /**
     * Used to set the information about a player for a game
     * @param user the user's information
     * @param board the user's board
     */
    public void setPlayer(User user, Board_ board){
        game.setPlayer(new Player(user, board));
    }

    /**
     * A getter for the board of the AI
     * @param i the number of AI
     * @return the desired board
     */
    public Board_ getAiBoard(int i) {
        return game.getAIBoard(i);
    }

    /**
     * A getter for the player board
     * @return the board of the player
     */
    public Board_ getPlayerBoard() {
        return game.getPlayerBoard();
    }

    /**
     * Used to determine if the desired cell can be attacked
     * @param x the location on the x axis
     * @param y the location on the y axis
     * @return a boolean that is true if the attack is possible
     */
    public boolean canAttackCell(int x, int y) {
        return game.canAttack(x,y,getNumPlayers()-1);
    }

    /**
     * Used to attack a cell
     * @param i x coordinate
     * @param j y coordinate
     */
    public void attackCell(int i, int j) {
        game.attack(getNumPlayers()-1,new Point(i,j));
        game.resetPlayerShotTimer();
    }

    /**
     * Used to obtain the state of any given cell on the board
     * @param x the location on the x axis
     * @param y the location on the y axis
     * @param board_id the board we are playing the game
     * @return a String indicating the status
     */
    public String getCellState(int x, int y, int board_id) {
        return game.getCellState(x,y,board_id);
    }

    /**
     * A setter for the listener
     * @param listener GameListener
     */
    public void registerListener(GameListener listener) {
        this.listener =listener;
    }

    /**
     * A getter for the number of players
     * @return the int of the number of players
     */
    public int getNumPlayers() {
        return game.getNumOfAI()+1;
    }

    /**
     * Utilizes the game class' logic to determine if the game is over
     * @return a boolean saying if the game is over
     */
    public boolean gameOver() {
        if (game == null) return true;
        return !game.notOver();
    }



    /**
     * Used to determine if a player can shoot depending on the timer
     * @return a boolean that is true if the player can shoot
     */
    public boolean playerCanShoot(){
        return game.canPlayerShoot();
    }



    /**
     * Obtains the length of the ships
     * @return an array of ints containing the lengths of the ships
     */
    public int[] getShipLengths() {
        return new int[]{2,3,4,5};
    }

    /**
     * Used to set the name of the current game
     * @param gameName the desired name as a string
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Used to determine if two players are the same
     * @param userPlaying the user playing
     * @return a boolean
     */
    public boolean samePlayer(User userPlaying) {
        return (Objects.equals(userPlaying.getUsername(), game.getPlayerName()));
    }



    /**
     * Used to obtain the games a user has
     * @return an arrayList of strings
     */
    public ArrayList<String> getUsersGames() {
        try {
            ArrayList<String> files = new ArrayList<>();
            for (String string: boardDAO.getFileNames("Files/Games/")){
                if (currentUser == null) continue;
                if (boardDAO.read("Files/Games/"+string).getPlayer().sameUser(currentUser)) files.add(string);
            }
            return files;
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Used to obtain the attributes of the game associated with a player
     * @return ArrayList of strings of names of game
     */
    public ArrayList<String> getUsersGamesAttributes() {
        try {
            ArrayList<String> files = new ArrayList<>();
            for (String[] string: boardDAO.getFileInfo("Files/Games/")){
                if (currentUser == null) continue;
                if (boardDAO.read("Files/Games/"+string[0]).getPlayer().sameUser(currentUser)) files.add(string[0]+" - "+string[1]);
            }
            return files;
        } catch (IOException e) {
            //e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Used to reset the game
     */
    public void resetGame(){
        game = null;
        gameName = null;
    }

    /**
     * Used to set the user
     * @param userPlaying user playing the game
     */
    public void setUser(User userPlaying) {
        currentUser = userPlaying;
    }

    /**
     * Checks if there is a game with name provided
     * @param text name of the desired game
     * @return boolean
     */
    public boolean gameNameExists(String text) {
        return boardDAO.filenameExists("Files/Games/",text);
    }

    /**
     * a getter for the game
     * @return a Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Used to obtain who shot what cell
     * @param i x coordinate
     * @param j y coordinate
     * @param board board in the game
     * @return the number of the player
     */
    public int getCellShotBy(int i, int j, int board) {
        return game.whoShot(i,j,board);
    }

    /**
     * Used to delete a game
     * @return returns true if the file has been deleted, false otherwise
     */
    public boolean deleteGameFile() {

        return boardDAO.deleteGame("Files/Games/"+game.getGameName()+".json");
    }

    /**
     * a getter for the game time
     * @return time of game as a string
     */
    public String getTime() {
        return game.getGameTime();
    }
}


