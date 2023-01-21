package Business.Entities;

import com.google.gson.annotations.Expose;

/**
 * Class used to represent ships
 *
 * position -> Example: {(1,2), (1,3), (1,4), (1,5), (1,6)}
 * maxSquares -> Always 5
 * SquaresLeft -> Indicates the number of Squares Alive, it always starts with 5
 *
 * @author ICE 9
 */
public class Ship  {

    @Expose
    private int[][] positions;
    @Expose
    private int cellsLeft;




    /**
     *
     *     //Aclaration: Direction can only be RIGHT(0) or DOWN(1)
     *     //we store the exact coordinates in order to fast check in the future
     * @param initial_x the x coordinate
     * @param initial_y the y coordinate
     * @param direction oreintation
     * @param MAXCELLS max number of cells required
     */
    public Ship(int initial_x, int initial_y, int direction, int MAXCELLS) {
        this.cellsLeft = MAXCELLS;


        //lets fill the position string using the first cell and the direction
        positions = new int[MAXCELLS][2];
        if (direction == 0) { //RIGHT direction, only changes the 'x' direction
            for (int i = 0; i < MAXCELLS; i++) {
                positions[i][0] = initial_x + i;
                positions[i][1] = initial_y;
            }
        } else if (direction == 1) { //DOWN direction, only changes the 'y' direction
            for (int i = 0; i < MAXCELLS; i++) {
                positions[i][0] = initial_x;
                positions[i][1] = initial_y + i;
            }
        }else if (direction == 2) { //LEFT direction, only changes the 'y' direction
            for (int i = 0; i < MAXCELLS; i++) {
                positions[i][0] = initial_x - i;
                positions[i][1] = initial_y;
            }
        }else if (direction == 3) { //UP direction, only changes the 'y' direction
            for (int i = 0; i < MAXCELLS; i++) {
                positions[i][0] = initial_x;
                positions[i][1] = initial_y - i;
            }
        }
    }

    //GETTER------------------------------------

    /**
     * getter for the positions of the ships
     * @return a matrix of ints
     */
    public int[][] getPositions() {
        return positions;
    }

    /**
     * the cells remaining
     * @return an int
     */
    public int getCellsLeft() {
        return cellsLeft;
    }

    /**
     * used to decrease the cells left
     */
    public void minusCell() {
        cellsLeft--;
    }


    //SETTER------------------------------------





}
