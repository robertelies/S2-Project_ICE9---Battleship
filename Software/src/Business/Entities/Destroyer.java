package Business.Entities;

/**
 * Used to represent a type of ship
 *
 *
 * position -> Example: {(1,2), (1,3), (1,4), (1,5), (1,6)}
 * maxSquares -> Always 5
 * SquaresLeft -> Indicates the number of Squares Alive, it always starts with 4
 *
 * @author ICE 9
 */
public class Destroyer extends Ship{

    /**
     * Direction can only be RIGHT(0) or DOWN(1)
     *     we store the exact coordinates in order to fast check in the future
     * @param initial_x the x start
     * @param initial_y the y start
     * @param direction the oreintation
     */
    public Destroyer(int initial_x, int initial_y, int direction) {
        super(initial_x, initial_y, direction, 4);
    }


}
