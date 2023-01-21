package Business.Entities;


//import Business.MyClock;

import Business.Entities.MyClock;
import Presentation.Controller.Listeners.GameListener;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class used to run the logic behind simulating an actual game
 *
 * @author ICE 9
 */

public class Game {


    //most important attributes
    private int gameID;
    private String gameName;
    private int numOfAI; //the same as number of players, eah player has a board
    private int attackTime;
    private int numAttacks;
    private boolean stop = false;
    private boolean allSunk = false;

    private Player player;
    private ArrayList<AI> ais;
    private GameListener listener;
    private final UserShotTimer userTimer;


    private final MyClock duration;

    /**
     * used to load a game
     * @param gameID the number associated
     * @param gameName the name of the game
     * @param player the player information
     * @param numberAI the number of AI
     * @param ais the AI information
     * @param duration the length of the game
     */
    public Game(int gameID,String gameName ,Player player, int numberAI, ArrayList<AI> ais,String duration) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.player = player;
        this.numOfAI = numberAI;
        this.ais = new ArrayList<>();
        for (int i = 0; i < numOfAI; i++) {
            AI newAi = new AI(ais.get(0).getAttackTime(),ais.get(i).getID());
            newAi.setGame(this);
            newAi.setMyBoard(ais.get(i).getBoard());
            this.ais.add(newAi);
        }
        userTimer = new UserShotTimer(ais.get(0).getAttackTime(),this);
        this.duration = new MyClock(duration);
    }

    /**
     * Used to make a new game from the new game menu
     * @param attackTime the wait time between attacks
     * @param listener listener to be used for the new game
     */
    public Game(int attackTime, GameListener listener) {
        this.listener=listener;
        gameID = new Random().nextInt(Integer.MAX_VALUE);
        userTimer = new UserShotTimer(attackTime,this);
        ais = new ArrayList<>();
        for(int i = 0; i < 10; i++) ais.add(new AI(attackTime, -1));
        this.attackTime = attackTime;
        this.player = new Player(new User("tmp","tmp"),new Board_());
        this.duration = new MyClock();
    }

    /**
     * Used to determine that the game is not over
     * @return a boolean that is true if the game is not over
     */
    public boolean notOver() {
        if (stop){
            //controller.showGameOver();
            return false;
        }

        int count_alive = 0;
        if (!player.allSunk()) count_alive++;
        for (AI ai : ais) {
            if (!ai.allSunk()) count_alive++;
        }
        if (count_alive <= 1) allSunk = true;
        //if (count_alive <= 1) controller.showGameOver();
        return count_alive >= 2;
    }

    /**
     * The method used to attack during the game
     * @param player_shooting an int of the number of the player shooting
     * @param point the location to be attacked
     */
    synchronized public void attack(int player_shooting,Point point){
        if (notOver()){
            numAttacks++;
            if (player_shooting != numOfAI){
                if (!player.allSunk()){
                    player.attackBoard(point.getX(), point.getY(),player_shooting);
                    if (player.getBoard().isSunk(point.getX(), point.getY())){
                        listener.updateSunkShip(numOfAI);
                    }
                }
            }

            for (int i = 0; i < numOfAI;i++){
                if (i == player_shooting) continue;
                if(!ais.get(i).allSunk()){
                    ais.get(i).markCell(point,player_shooting);
                    if (ais.get(i).getBoard().isSunk(point.getX(), point.getY())){
                        listener.updateSunkShip(i);
                    }
                }
            }
            if (listener!= null){
                listener.updateCell(point.getX(), point.getY());
                listener.updateTable();
            }
        }
    }

    /**
     * A getter for the board
     * @return an array of boards
     */
    public Board_[] getBoards(){
        Board_[] boards = new Board_[ais.size()+1];
        boards[ais.size()] = player.getBoard();
        for (int i = 0; i < numOfAI;i++){
            boards[i] = ais.get(i).getBoard();
        }
        return boards;
    }


    /**
     * Used to have the ai's create their boards
     */
    public void generateAiBoards() {
        for (AI ai: ais){
            ai.createRandomBoard(1,1,1,2);
        }
    }



    /**********Used to save a game************/
    /**
     * a getter to save a game
     * @return the number of AI
     */
     public int getNumOfAI(){
         return ais.size();
     }

    /**
     * a getter to save a game
     * @return the player
     */
     public Player getPlayerForSaving(){
         return player;
     }

    /**
     * a getter to save a game
     * @return the arraylist of ai's
     */
     public ArrayList<AI> getAIsForSaving(){
         return (ArrayList<AI>) ais.clone();
     }

    /**
     * a getter for the game ID
     * @return an int of the game ID
     */
    public int getGameID() {
        return gameID;
    }



    /**
     * A setter to load a game
     * @param gameID the id of the game
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * A setter to load a game
     * @param player the player to be used
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * A setter to load a game
     * @param numOfAI the number of AI involved
     */
    public void setAis(int numOfAI) {
        this.numOfAI = numOfAI;
        this.ais = new ArrayList<>();
        for (int i = 0; i < numOfAI; i++){
            ais.add(i,new AI(attackTime,i));
            ais.get(i).setGame(this);
        }
    }

    /**
     * Used to start the time in the game
     */
    public void startUserTimer(){
        userTimer.start();
    }



    /**
     * A getter for the AI board in the param
     * @param i the index of the AI to be obtained
     * @return the board of the AI
     */
    public Board_ getAIBoard(int i) {
        return ais.get(i).getBoard();
    }

    /**
     * Used to obtain the players board
     * @return the board of the player
     */
    public Board_ getPlayerBoard() {
        return player.getBoard();
    }

    /**
     * A setter of the attribute stop
     * @param bool boolean to be equivalated
     */
    public void setStop(boolean bool) {
        stop = bool;
        duration.setStop(stop);
    }

    /**
     * Used in the game manager to initiate the AI
     */
    public void startAi() {
        for (int i = 0; i < numOfAI;i++){
            ais.get(i).start();
        }

    }

    /**
     * Used to determine a player can attack a cell
     * @param x coordinate x
     * @param y coordinate y
     * @param user_id user attacking
     * @return boolean that is true if the user can attack
     */
    public boolean canAttack(int x, int y, int user_id) {
        if (user_id != numOfAI) if (!player.getBoard().isHit(x,y)) return true;
        for (int i = 0; i < ais.size();i++){
            if (i == user_id) continue;
            if(!ais.get(i).getBoard().isHit(x,y)) return true;
        }
        return false;
    }

    /**
     * Used to determine how a cell is
     * @param x coordinate x
     * @param y coordinate y
     * @param board_id the id of the board
     * @return a string with the status of the cell
     */
    public String getCellState(int x, int y, int board_id) {
        if (board_id == numOfAI) {
            if (player.getBoard().isSunk(x,y)) return "sunk";
            if (player.getBoard().isHit(x,y)) {
                if (player.getBoard().isShip(x,y)) return "hit_Ship";
                return "miss";
            }else {
                if (player.getBoard().isShip(x,y)) return "our_Ship";
                return "water";
            }
        }

        if(ais.get(board_id).getBoard().isHit(x,y)){
            if (ais.get(board_id).getBoard().isSunk(x,y)) return "sunk";
            if (ais.get(board_id).getBoard().isShip(x,y)) return "hit_Ship";
            return "miss";
        }else{
            return "water";
        }

    }

    /**
     * A getter for the name of the game
     * @return a string of the name of the game
     */
    public String getGameName() {
        return  gameName;
    }

    /**
     * A setter for the name of the game
     * @param gameName String of the name
     */
    public void setGameName(String gameName){
        this.gameName = gameName;
    }

    /**
     * Given a listener, adds to the game's listener
     * @param listener the GameListener to be added
     */
    public void registerListener(GameListener listener){
        this.listener = listener;
        duration.registerListener(listener);
    }

    /**
     * Get the name of the player involved in the game
     * @return string of player name
     */
    public String getPlayerName() {
        return player.getUser().getUsername();
    }


    /**
     * Used to obtain the number of attacks made in a game
     * @return the int
     */
    public int getAttacksMade() {

        return numAttacks;
    }

    /**
     * Used to get all ships have been sunk
     * @return true if all ships are sunk
     */
    public boolean getAllSunk() {
        return allSunk;
    }

    /**
     * Get the number of ships the player has left
     * @return an int of the ships
     */
    public int getPlayerShipsRemaining() {
        return player.getBoard().getShipsRemaining();
    }

    /**
     * Used to determine if the player won or lost
     * @return a boolean true if player won
     */
    public boolean playerWon() {
        if (player.getBoard().allSunk()) return false;
        else {
            return allSunk;
        }
    }

    /**
     * A getter for the duration of the game
     * @return the time of the game recorded
     */
    public Time getDuration() {
        return Time.valueOf(duration.getHour()+":"+duration.getMinute()+":"+duration.getSecond());
    }

    /**
     * Restricts the player from shooting
     */
    public void playerCantShoot() {
        listener.updatePlayerShotPermission(false);
    }

    /**
     * Allows player to shoot
     */
    public void playerCanShoot() {
        listener.updatePlayerShotPermission(true);
    }

    /**
     * Resets the shot time
     */
    public void resetPlayerShotTimer() {
        userTimer.resetShootTimer();
    }

    /**
     * Used with the time to determine if a player can shoot
     * @return true if player can shoot
     */
    public boolean canPlayerShoot() {
        return userTimer.canShoot();
    }

    /**
     * A recursive function used to determine who shot that turn
     * @param i x coordinate
     * @param j y coordinate
     * @param board number of the board
     * @return number of player who shot
     */
    public int whoShot(int i, int j, int board) {
        if (board == numOfAI) {
            return (player.getBoard().whoShot(i,j));
        }

        return ais.get(board).getBoard().whoShot(i,j);
    }

    /**
     * A getter for the color of the player
     * @return the color
     */
    public Color getPlayerColor() {
        return player.getUser().getColor();
    }

    /**
     * Gets the amount of time in the game
     * @return a string of the time
     */
    public String getGameTime() {
        return duration.toString();
    }

    /**
     * starts the timer of the game
     */
    public void startTimer() {
        duration.setStop(false);
        duration.start();
    }
}
