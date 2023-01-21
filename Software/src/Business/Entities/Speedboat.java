package Business.Entities;

/**
 * Class used to represent a type of ship
 *
 * position -> Example: {(1,2), (1,3), (1,4), (1,5), (1,6)}
 * maxSquares -> Always 5
 * SquaresLeft -> Indicates the number of Squares Alive, it always starts with 2
 *
 * @author ICE 9
 */
public class Speedboat extends Ship{

    //Aclaration: Direction can only be RIGHT(0) or DOWN(1)
    //we store the exact coordinates in order to fast check in the future

    /**
     * Used when placing ships on the board
     * @param initial_x starting x coordinate
     * @param initial_y starting y coordinate
     * @param direction the orientation of the ship
     */
    public Speedboat(int initial_x, int initial_y, int direction) {
        super(initial_x, initial_y, direction, 2);
    }


}
