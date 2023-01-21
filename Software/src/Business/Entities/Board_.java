package Business.Entities;

import com.google.gson.annotations.Expose;

import java.awt.*;
import java.util.ArrayList;

/**
 * This object with this entity is to create an object that simulates a Board with some properties
 */

public class Board_ {

    /**
     * The number of cells in a board
     */
    public static final int NUMBER_OF_CELLS = 16;

    //errors
    /**
     * Used to show everything is okay
     */
    public static final int ALL_GOOD = 19;

    /**
     * Used to show a cell has already been pressed
     */
    public static final int CELL_ALREADY_PRESSED = 65;


    //ATTRIBUTES
    @Expose
    private final Cell_[][] board;//literally the board made of Cells
    @Expose
    private ArrayList<Ship> ships;

    //counters
    @Expose
    private int shipsAlive;

    private Color color;

    /**
     * Constructor used when creating a new board to be given to a Player(User, AI) and also for visualization
     */
    public Board_() {
        this.board = new Cell_[NUMBER_OF_CELLS][NUMBER_OF_CELLS];
        for (int i = 0; i < NUMBER_OF_CELLS; i++){
            for (int j = 0; j < NUMBER_OF_CELLS; j++){
                board[i][j] = new Cell_();
            }
        }
        this.ships = new ArrayList<>();
        for (int i = 0; i <5; i++) ships.add(null);
        this.shipsAlive = 0;

    }

    //SETTERS-------------------------------------
    // METHODS TO ADD ALL TYPES OF SHIPS(This will happen on the "set up" Stage of the game
    /**
     * Used to add a ship
     * @param initial_x coordinate x
     * @param initial_y coordinate y
     * @param direction orientation of ship
     * @return an int that is constant indicating the status
     */
    public int addAircraft(int initial_x, int initial_y, int direction){
        int mark;
        this.ships.set(0,new Aircraft(initial_x, initial_y, direction));
        mark = placeShipOnTheBoard(initial_x, initial_y, direction, 5);
        this.shipsAlive++;
        return mark;
    }





    /**
     * Used to add a ship
     * @param initial_x coordinate x
     * @param initial_y coordinate y
     * @param direction orientation of ship
     * @return an int that is constant indicating the status
     */
    public int addDestroyer(int initial_x, int initial_y, int direction){
        int mark;
        this.ships.set(1,new Destroyer(initial_x, initial_y, direction));
        mark = placeShipOnTheBoard(initial_x, initial_y, direction, 4);
        this.shipsAlive++;
        return mark;
    }

    /**
     * Used to add a ship
     * @param initial_x coordinate x
     * @param initial_y coordinate y
     * @param direction orientation of ship
     * @return an int that is constant indicating the status
     */
    public int addSpeedBoat(int initial_x, int initial_y, int direction){
        int mark;
        this.ships.set(2,new Speedboat(initial_x, initial_y, direction));
        mark = placeShipOnTheBoard(initial_x, initial_y, direction, 2);
        this.shipsAlive++;
        return mark;
    }

    /**
     * Used to add a ship
     * @param initial_x coordinate x
     * @param initial_y coordinate y
     * @param direction orientation of ship
     * @return an int that is constant indicating the status
     */
    public int addSubmarine(int initial_x, int initial_y, int direction){
        int mark;
        if (this.ships.get(3) == null) this.ships.set(3,new Submarine(initial_x, initial_y, direction));
        else this.ships.set(4,new Submarine(initial_x, initial_y, direction));

        mark = placeShipOnTheBoard(initial_x, initial_y, direction, 3);
        this.shipsAlive++;
        return mark;
    }

    /** METHOD TO MARK A CELL(this will happen as the game advances, with the opponent choices)
     *
     * @param x coordinate x
     * @param y coordinate y
     * @param player_shooting the user id of the person who shot this tile
     * @return an int of the constant of the status of the cell
     */
    public int markCell(int x, int y,int player_shooting){
        //if the cell that we want to mark is already marked there has been an error so we dont run the code
        if(!board[x][y].isHit()) {
            board[x][y].setWhoShot(player_shooting);
            board[x][y].setState(true);
            //we check for all the coordinates where there is a ship placed
            for (Ship single_ship : ships) {
                if (single_ship != null){
                    for (int[] single_position : single_ship.getPositions()) {
                        if (single_position[0] == x && single_position[1] == y) {
                            single_ship.minusCell(); //we subtract '1' to the number of untouched cells on the ship
                        }
                    }
                }

            }
            updateShipsAlive();
            return ALL_GOOD;
        }else{
            return CELL_ALREADY_PRESSED;
        }
    }

    //METHOD TO UPDATE THE SHIPS ALIVE
    private void updateShipsAlive(){
        int counter = 0;
        for(Ship single_ship : ships){
            if (single_ship != null){
                if(single_ship.getCellsLeft() <= 0){
                    counter++;
                }
            }

        }
        this.shipsAlive = 5 - counter;
    }

    //METHOD TO MARK A SHIP PLACEMENT IN THE BOARD
    //this method is used only in this class
    //Aclaration: Direction can only be RIGHT(0) or DOWN(1)
    private int placeShipOnTheBoard(int initial_x, int inital_y, int direction, int length){

        //LEST FIRST CHECK IF THERE IS A BOARD ALREADY WHERE WE WANT TO PLACE A SHIP
        for (int i = 0; i < length; i++) {
            if (direction == 0) {
                this.board[initial_x + i][inital_y].setInShip(true);
            }else if (direction == 1) {
                this.board[initial_x][inital_y + i].setInShip(true);
            }else if (direction == 2) {
                this.board[initial_x - i][inital_y].setInShip(true);
            } else {
                this.board[initial_x][inital_y - i].setInShip(true);
            }
        }
        return ALL_GOOD;
    }



    //GETTERS-------------------------------------
    // METHODS TO GET THE STATUS OF THE ALL THE SHIPS

    /** ACLARATION: from game we input the parameter we want to check from the
     *
     * @param index index of the ship
     * @return a boolean if the ships is not sunk
     */
    public boolean getShipState(int index){
        return this.ships.get(index).getCellsLeft() != 0;
    }


    /**
     * Used to determine if a space on the board has been shot
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a boolean
     */
    public boolean isHit(int x, int y) {
        return board[x][y].isHit();
    }


    /**
     * Used to determine if a ship has been sunk
     * @param x location of x coordinate
     * @param y location of y coordinate
     * @return a boolean that is true if the ship is sunk
     */
    public boolean isSunk(int x, int y) {
        if (board[x][y].getInShip()){
            for (Ship single_ship : ships) {
                if (single_ship != null){
                    for (int[] single_position : single_ship.getPositions()) {
                        if (single_position[0] == x && single_position[1] == y) {
                            return single_ship.getCellsLeft() == 0;
                        }
                    }
                }

            }
        }
        return false;
    }


    /**
     * Used to determine if a point on the board is a ship or not
     * @param x the location on the x axis
     * @param y location on the y axis
     * @return a boolean if the cell contains a ship
     */
    public boolean isShip(int x, int y) {
        return board[x][y].getInShip();
    }



    /**
     * Used to determing if all the ships have been sunk
     * @return a boolean true if all sunk
     */
    public boolean allSunk(){
        for (Ship ship: ships){
            if (ship != null){
                if (ship.getCellsLeft() != 0) return false;
            }

        }
        return true;
    }



    /**
     * Used to get the number of remaining ships
     * @return an int of the number of ships
     */
    public int getShipsRemaining() {
        int counter = 0;
        for (Ship single_ship : ships) {
            if (single_ship != null){
                if (single_ship.getCellsLeft() != 0) counter++;
            }

        }
        return counter;
    }


    /**
     * A getter for the color
     * @return Color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Used to obtain who shot
     * @param i x coordinate
     * @param j y coordinate
     * @return the player number as an int
     */
    public int whoShot(int i, int j){
        return board[i][j].getWhoShot();
    }

    /**
     * a setter for the color
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}

