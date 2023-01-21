package Presentation.Controller.Board;

import Business.Entities.Cell;
import Business.Entities.Ship;
import Business.manager.GameManager;
import Presentation.Controller.Subcontrollers.ConfigBoardController;
import Presentation.Controller.Subcontrollers.NewGameController;
import Presentation.View.Subviews.PlayerScreenElements.BoardFeatures.BoardGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * A class used to dictate logic regarding a cell \
 *
 * @author ICe 9
 */

public class CellController implements MouseListener {
    private Integer times;
    private final Cell cell;
    private final ConfigBoardController configBoardController;
    private final NewGameController newController;
    private final BoardGrid grid;
    private GameManager gameManager;
    private boolean contiguous;
    private boolean out;
    private int numSquares;

    /**
     * Constructor used when controlling the logic for a cell
     * @param cell cell we are worried
     * @param configBoardView the controller when configuring a board
     * @param newController the controller for a new game
     * @param grid a grid for the board
     * @param gameManager the manager for the game
     */
    public CellController(Cell cell, ConfigBoardController configBoardView, NewGameController newController, BoardGrid grid, GameManager gameManager) {
        times = 0;
        this.cell = cell;
        this.configBoardController = configBoardView;
        this.newController = newController;
        this.grid = grid;
        this.contiguous = false;
        this.out = false;
        this.gameManager = gameManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        times = times + 1;
        if (configBoardController.getMode()) {
            numSquares = getNumSquares();
            //System.out.println(numSquares);
            if (configBoardController.allShipTypePlaced(numSquares)) {
                JOptionPane.showMessageDialog(configBoardController.getView(), "ERROR! All Ships of this type have been placed");
            } else {

                int cont = configBoardController.checkContiguous(cell.getI(), cell.getJ(), configBoardController.getRotate(), numSquares);
                out = cont == 2;
                contiguous = cont == 1;

                if (!out && !contiguous) {
                    if (times % 2 != 0) {
                        if (!(cell.getI() == 0 || cell.getJ() == 0)) {

                                cell.setClicked(true);
                                cell.setBackground(newController.getColor());
                                doPaint(numSquares, configBoardController.getRotate(),newController.getColor(), "click");
                                configBoardController.shipPlaced(numSquares, cell.getI(), cell.getJ(), configBoardController.getRotate());


                        }
                    } else {
                        if (!(cell.getI() == 0 || cell.getJ() == 0)) {
                            cell.setClicked(false);
                            cell.setBackground(Color.white);
                            doPaint(numSquares,configBoardController.getRotate(), Color.white, "click");
                        }
                    }
                } else {
                    if (contiguous) {
                        JOptionPane.showMessageDialog(configBoardController.getView(), "ERROR! Ships cannot touch or overlap!");
                    }
                }
            }
        } else {

            if (!(cell.getI() == 0 || cell.getJ() == 0)) {
                if (!gameManager.getPlayerBoard().allSunk()){
                    if (gameManager.playerCanShoot()){
                        cell.setClicked(true);
                        if (gameManager.canAttackCell(cell.getI(), cell.getJ())) {
                            //cell.setBackground(newController.getColor());
                            gameManager.attackCell(cell.getI(), cell.getJ());


                        } else {
                            JOptionPane.showMessageDialog(configBoardController.getView(), "ERROR! Cell has already been attacked");

                        }
                    } else {
                        JOptionPane.showMessageDialog(configBoardController.getView(), "ERROR! You must wait");
                    }
                }else {
                    JOptionPane.showMessageDialog(configBoardController.getView(), "ERROR! All your ships have been sunk. You can no longer shoot.");
                }


            }

        }
    }

    private int getNumSquares() {

            return switch (configBoardController.getConfView().getTable().getSelectedRow()) {
                case 0 -> 5;
                case 1 -> 4;
                case 2 -> 2;
                //case 3 -> 3;
                default -> 3;
            };

    }





    private void doPaint(int numSquares,int rotation ,Color color, String action) {
        int counter = 1;
        int i = cell.getI();
        int j = cell.getJ();
        while (counter < numSquares) {
            switch (rotation) {
                case 0:
                    if (i >= 0 && i < 15) {
                        i = i + 1;
                    }
                    break;
                case 1:
                    if (j >= 0 && j < 15) {
                        j = j + 1;
                    }
                    break;
                case 2:
                    if (i > 1 && i <= 16) {
                        i = i - 1;
                    }
                    break;
                case 3:
                    if (j > 1 && j <= 16) {
                        j = j - 1;
                    }
                    break;
            }
            if (!grid.getCells(i, j).getClicked()) {
                grid.getCells(i, j).setBackground(color);
            }
            switch (action) {
                case "click" -> grid.getCells(i, j).setClicked(cell.getClicked());
                case "enter" -> grid.getCells(i, j).setEntered(cell.getEntered());
                case "exit" -> grid.getCells(i, j).setExited(cell.getExited());
            }
            counter++;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (configBoardController.getMode()) {
            int numSquares = getNumSquares();
            if (!cell.getClicked() && !(cell.getI() == 0 || cell.getJ() == 0)) {
                cell.setEntered(true);
                cell.setBackground(newController.getColor());
                doPaint(numSquares, configBoardController.getRotate(),newController.getColor(), "enter");
            } else if (cell.getEntered()) {
                cell.setEntered(false);
            }
        } else {
            if (!cell.getClicked() && !(cell.getI() == 0 || cell.getJ() == 0)) {
                cell.setEntered(true);
                //cell.setBackground(newController.getColor());

                //attack cell
            } else if (cell.getEntered()) {
                cell.setEntered(false);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (configBoardController.getMode()) {
            int numSquares = getNumSquares();
            if (!cell.getClicked() && !(cell.getI() == 0 || cell.getJ() == 0)) {
                cell.setExited(true);
                cell.setBackground(Color.white);
                doPaint(numSquares,configBoardController.getRotate() ,Color.white, "exit");
            } else if (cell.getExited()) {
                cell.setExited(false);
            }
        } else {
            if (!cell.getClicked() && !(cell.getI() == 0 || cell.getJ() == 0)) {
                cell.setExited(true);
                //cell.setBackground(Color.white);
            } else if (cell.getEntered()) {
                cell.setExited(false);
            }
        }
    }
}

