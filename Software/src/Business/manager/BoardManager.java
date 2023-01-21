package Business.manager;

import Business.Entities.Board_;
import Presentation.Controller.Subcontrollers.ConfigBoardController;

import java.util.HashMap;

/**
 *  A class used to handle the data related to a board
 */

public class BoardManager {
    private Board_ grid;
    private HashMap<Integer,Integer> numShips;


    /**
     * Constructor used to manage flow around a board
     */
    public BoardManager() {
        this.grid = new Board_();
        numShips = new HashMap<>();
        numShips.put(2,1); // number of speedboats
        numShips.put(3,2); // number of submarines
        numShips.put(4,1); // number of destroyers
        numShips.put(5,1); // number of aircrafts
    }

    /**
     * Checks if two cells are concurrent
     * @param i location x
     * @param j location y
     * @param rotation oreintation
     * @param numSquares number squares
     * @param controller configBoard controller
     * @return int of location
     */
    public int checkContiguous(int i, int j, int rotation, int numSquares, ConfigBoardController controller) {
        int counter = 0;
        boolean contiguous = false;
        boolean out = false;
        if (i > 0 && j > 0 && i < 16 && j < 16) {

            contiguous = checkContiguougsCells(grid,i,j);
            counter++;
            while (counter < numSquares && !contiguous && !out&&!controller.isEditing()) {
                switch (rotation) {
                    case 0:

                        i = i + 1;
                        break;
                    case 1:

                        j = j + 1;

                        break;
                    case 2:
                        i = i - 1;
                        break;
                    case 3:

                        j = j - 1;

                        break;
                }
                if (i < 1 || j < 1 || i > 15 || j > 15){
                    out = true;
                    //System.out.println("out");
                }
                 if (!contiguous && !out) {

                    contiguous = checkContiguougsCells(grid,i, j);
                }
                counter++;


            }
        }
        return (contiguous||out)? ((contiguous)?1:2) : 0;
    }

    private boolean checkContiguougsCells(Board_ grid, int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i < 0 || y + j < 0 || x + i > 15 || y + j > 15) {
                    continue;
                }
                //System.out.println("Checking cell: "+(x + i)+", "+ (y + j));
                if (grid.isShip(x+i,y+j)) {
                    //System.out.println("cell: "+(x + i)+", "+ (y + j)+" failed");
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Used to determine if typs of a ship have bben placed
     * @param shipSize the size of the ship
     * @return a boolean
     */
    public boolean allShipTypePlaced(int shipSize){
        return numShips.get(shipSize) == 0;
    }

    /**
     * Used to place ships
     * @param shipSize size
     * @param x location x
     * @param y location y
     * @param rotation oreintation
     */
    public void shipPlaced(int shipSize,int x, int y, int rotation){
        switch (shipSize) {
            case 2 -> grid.addSpeedBoat(x, y, rotation);
            case 3 -> grid.addSubmarine(x, y, rotation);
            case 4 -> grid.addDestroyer(x, y, rotation);
            case 5 -> grid.addAircraft(x, y, rotation);
        }
        numShips.put(shipSize,numShips.get(shipSize)-1);
    }



    /**
     * Used to determine if all ships have been placed or not
     * @return boolean
     */
    public boolean allShipsPlaced() {
        for (int remaining: numShips.values()){
            if (remaining != 0) return false;
        }
        return true;
    }

    /**
     * A getter for the playing board
     * @return a board
     */
    public Board_ getBoard() {
        return grid;
    }

    /**
     * Used to reset a board
     */
    public void resetBoard() {
        this.grid = new Board_();
        numShips = new HashMap<>();
        numShips.put(2,1); // number of speedboats
        numShips.put(3,2); // number of submarines
        numShips.put(4,1); // number of destroyers
        numShips.put(5,1); // number of aircrafts
    }
}
