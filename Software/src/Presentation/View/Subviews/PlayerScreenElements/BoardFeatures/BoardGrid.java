package Presentation.View.Subviews.PlayerScreenElements.BoardFeatures;

import Business.Entities.Cell;
import Business.manager.GameManager;
import Presentation.Controller.Board.CellController;
import Presentation.Controller.Subcontrollers.ConfigBoardController;
import Presentation.Controller.Subcontrollers.NewGameController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A class used to show the board on a JPanel
 *
 * @author ICE 9
 */
public class BoardGrid extends JPanel{
    private NewGameController newController;
    private ConfigBoardController confController;
    private final Cell[][] cell = new Cell[16][16];


    /**
     * USed in Battleview and ConfigBoard view
     * @param controller the controller for the NewGame
     * @param confController the configuring board controller
     * @param gameManager the manger for the game
     */
    public BoardGrid(NewGameController controller, ConfigBoardController confController, GameManager gameManager){
        this.newController=controller;
        this.confController=confController;
        this.add(setBoard(gameManager));
        this.setSize(new Dimension(50,50));
        this.setOpaque(false);
    }

    /**
     * Used to reset every cell within a board
     */
    public void resetBoard(){
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                getCells(i,j).setBackground(Color.white);
                getCells(i,j).setClicked(false);
                getCells(i,j).setEntered(false);
                getCells(i,j).setExited(false);
            }
        }
    }

    /**
     * A setter for the board
     * @param gameManager to be used with the mouse listener
     * @return JPanel
     */
    public JPanel setBoard(GameManager gameManager){
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(16,16));
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++) {
                cell[i][j]=new Cell(i,j,false,false,false);
                cell[i][j].setBackground(Color.white);
                cell[i][j].setSize(grid.getWidth()/16,grid.getWidth()/16);
                cell[i][j].setBorder(new LineBorder(Color.black));
                cell[i][j].addMouseListener(new CellController(cell[i][j], confController,newController,this,gameManager));

                if(i==0||j==0){
                    setContent(i,j);
                }
                grid.add(cell[i][j]);
            }
        }
        return grid;
    }


    /**
     * Used to obtain the cell at a given location
     * @param i the x location
     * @param j the y location
     * @return Cell containing the locaiton
     */
    public Cell getCells(int i,int j){
        return cell[i][j];
    }

    private void setContent(int i, int j) {
        if(i==0&&j==0){
        }else{
            if(j==0){
                setCellRow(i);
            }else if(i==0){
                setCellCol(j);
            }
        }
    }

    private void setCellCol(int j) {
        cell[0][j].add(new JLabel(Character.toString('A'+(j-1)))).setFont(new Font("Verdana",Font.BOLD,6));
    }

    private void setCellRow(int i) {
        if(i<10) {
            cell[i][0].add(new JLabel(Character.toString('1' + (i - 1)))).setFont(new Font("Verdana",Font.BOLD,6));
        }else{
            cell[i][0].add(new JLabel(Integer.toString(i))).setFont(new Font("Verdana",Font.BOLD,6));
        }
    }

}
