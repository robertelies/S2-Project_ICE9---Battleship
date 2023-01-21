package Business.Entities;


import javax.swing.*;
import java.awt.*;

/**This object with this entity is to create an object that simulates a CELL with some properties
 *
 * @author ICE 9
 */
public class Cell extends JPanel {
    private int i;
    private int j;
    private boolean clicked;
    private boolean entered;
    private boolean exited;


    /**WHEN CREATED, state and inship are false, but when the game continues they will be turned to true
     *
     * @param i coordinate x
     * @param j coordinate y
     * @param clicked if the cell is clicked
     * @param entered if the cursor is over the cell
     * @param exited if the cursor leave the cell
     */
    public Cell(int i, int j,boolean clicked,boolean entered, boolean exited){
        this.i=i;
        this.j=j;
        this.clicked=clicked;
        this.entered=entered;
        this.exited=exited;
    }






    /**
     * A setter for the clicked
     * @param pressed true the cell was clicked
     */
    public void setClicked(boolean pressed){
        this.clicked=pressed;
    }

    /**
     * A getter for the clicked attribute
     * @return boolean
     */
    public boolean getClicked() {
        return clicked;
    }


    /**
     * Used to set that a cell has been entered by the mouse
     * @param b true if its entered
     */
    public void setEntered(boolean b) {
        entered=b;
    }

    /**
     * A getter
     * @return a boolean
     */
    public boolean getEntered(){
        return entered;
    }

    /**
     * A setter to show a cell has been exited
     * @param b true if the cell has been exited
     */
    public void setExited(boolean b) {
        exited=b;
    }

    /**
     * getter for exited
     * @return boolean
     */
    public boolean getExited(){
        return exited;
    }

    /**
     * A getter for the i location
     * @return int
     */
    public int getI(){
        return i;
    }

    /**
     * A getter for the j location
     * @return int
     */
    public int getJ(){
        return j;
    }



}
