package Business.Entities;


import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class used to perform logic based functions in a game with AI opponents
 *
 * @author ICE9
 */

public class AI extends Thread {
    private Game game;
    @Expose
    private Board_ myBoard;
    @Expose
    private int time;
    @Expose
    private int player_id;

    /**
     * Constructor used when AI's are needed for a game
     * @param time the time to wait between shots
     * @param id the id associated
     */
    public AI(int time, int id) {
        this.time = time;
        player_id = id;
        myBoard = new Board_();
    }

    /**
     * Sets the board for the AI
     * @param board playing field for AI
     */
    public void setMyBoard(Board_ board){
        myBoard = board;
    }

    /**
     * A getter for the time
     * @return an int
     */
    public int getAttackTime() {return time;}
    @Override
    public void run() {
        while (game.notOver()) {

            while(!myBoard.allSunk() && game.notOver()){
                try {
                    Thread.sleep(time);
                    game.attack(player_id,nextShot(game.getBoards()));
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    /**
     * generates the AIs random game board
     * @param numDestroyer the number of destroyers to be placed on the board (4 cells wide)
     * @param numAircraft the number of aircrafts to be placed on the board (5 cells wide)
     * @param numSpeedboat the number of speedboats to be placed on the board (2 cells wide)
     * @param numSubmarine the number of submarines to be placed on the board (3 cells wide)
     */
    public void createRandomBoard(int numDestroyer, int numAircraft, int numSpeedboat, int numSubmarine) {
        //int[] ships = new int[]{2,3,3,4,5};
        Board_ newBoard = new Board_();
        Random r = new Random();
        for (int i = 0; i < numDestroyer; i++) {
            boolean vertical = r.nextBoolean();
            Point point = placeShip(newBoard,4,vertical);
            newBoard.addDestroyer(point.getX(),point.getY(),(vertical)?1:0);
        }
        for (int i = 0; i < numAircraft; i++) {
            boolean vertical = r.nextBoolean();
            Point point = placeShip(newBoard,5,vertical);
            newBoard.addAircraft(point.getX(),point.getY(),(vertical)?1:0);
        }
        for (int i = 0; i < numSpeedboat; i++) {
            boolean vertical = r.nextBoolean();
            Point point = placeShip(newBoard,2,vertical);
            newBoard.addSpeedBoat(point.getX(),point.getY(),(vertical)?1:0);
        }
        for (int i = 0; i < numSubmarine; i++) {
            boolean vertical = r.nextBoolean();
            Point point = placeShip(newBoard,3,vertical);
            newBoard.addSubmarine(point.getX(),point.getY(),(vertical)?1:0);
        }
        myBoard = newBoard;
    }

    /**
     * Places ships around the board before a game
     * @param newBoard the board to place the ship
     * @param shipSize size of ship
     * @param vertical a boolean indicating the oreintation of the ship
     * @return the coordinates of the ships start point
     */
    private Point placeShip(Board_ newBoard,int shipSize, boolean vertical){
        boolean canPlace;
        Random r = new Random();
        int x;
        int y;
        do {
            // generates random coordinate
            x = r.nextInt(1, Board_.NUMBER_OF_CELLS);
            y = r.nextInt(1, Board_.NUMBER_OF_CELLS);

            // checks if the ship can fit in that direction and if not adjusts the x and y position to fit inside the board
            if (vertical) {
                if ((y + shipSize) >= Board_.NUMBER_OF_CELLS) {
                    y -= shipSize;
                }
            } else if (x + shipSize >= Board_.NUMBER_OF_CELLS) {
                x -= shipSize;

            }
            canPlace = true;

            // checks the board for the direction to see if the ship can be placed without encroaching on the space of another ship
            // if there isnt enough space, we go back to the beginning and get new coordinates
            if (vertical) {
                for (int i = y; i < y + shipSize; i++) {
                    if (!freeToPlace(newBoard, x, i)) {
                        canPlace = false;
                        break;
                    }
                }
            } else {
                for (int i = x; i < x + shipSize; i++) {
                    if (!freeToPlace(newBoard, i, y)) {
                        canPlace = false;
                        break;
                    }
                }
            }
        } while (!canPlace);

        // returns the coordinates of the ships start point
        return new Point(x,y);
    }

    /**
     *  Used to determine if the location to place the ship is possible or not
     * @param board
     * @param x the location on the x axis
     * @param y the locaiton on the y axis
     * @return a boolean indicating true if it is possible to place the ship
     */
    private boolean freeToPlace(Board_ board, int x, int y) {
        // goes through all of the board to see if the coordinate is part of another ships territory
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x+i < 0 || y+j < 0 || x+i >= Board_.NUMBER_OF_CELLS || y+j >= Board_.NUMBER_OF_CELLS) {
                    continue;
                }
                if (board.isShip(x + i, y + j)) return false;

            }
        }
        return true;
    }



    /**
     * gnerates the best possible shot across several boards
     * @param boards an array of boards we want to attack
     * @return the best possible shot across several boards
     */
    public Point nextShot(Board_[] boards) {
        ArrayList<Point> best = new ArrayList<>();
        ArrayList<Point> ok = new ArrayList<>();
        ArrayList<Point> random = new ArrayList<>();
        // will return the best shot chosen between several boards
        // process is the same as for 1 board but shots are added to best, ok, and random arrays

        for (Board_ board: boards){
            if (board.allSunk()) continue;
            ArrayList<Point> possibleShots = new ArrayList<>();
            ArrayList<Point> optimisedRandomShots = new ArrayList<>();
            ArrayList<Point> hitNotSunk = new ArrayList<>();
            for (int x = 0; x < Board_.NUMBER_OF_CELLS; x++) {
                for (int y = 0; y < Board_.NUMBER_OF_CELLS; y++) {
                    if (!board.isHit(x, y) && !nearSunk(board, x, y)) possibleShots.add(new Point(x, y));

                    if (board.isHit(x, y) && board.isShip(x, y) && !board.isSunk(x, y)) hitNotSunk.add(new Point(x, y));
                }
            }

            for (int y = 0; y < Board_.NUMBER_OF_CELLS; y++) {
                for (int x = y%2; x < Board_.NUMBER_OF_CELLS; x = x+2) {
                    if (!board.isHit(x, y) && !nearSunk(board, x, y)) optimisedRandomShots.add(new Point(x, y));


                }
            }

            ArrayList<Point> betterShots = new ArrayList<>();
            for (Point point : possibleShots) {
                if (adjacentToHitShip(board, point.getX(), point.getY())) betterShots.add(point);
            }

            ArrayList<ArrayList<Point>> hitNotSunkShips = groupHits(hitNotSunk);
            ArrayList<Point> bestShots = new ArrayList<>();
            if (betterShots.size() != 0) {
                for (Point point : betterShots) {
                    for (ArrayList<Point> ship : hitNotSunkShips) {

                        if (probableHit(ship, point)){
                            bestShots.add(point);
                        }
                    }
                }
                if (bestShots.size() == 0) {
                    ok.add(betterShots.get(new Random().nextInt(0, betterShots.size())));
                } else {
                    best.add(bestShots.get(new Random().nextInt(0, bestShots.size())));
                }
            } else {
                if (optimisedRandomShots.size() != 0) random.add(optimisedRandomShots.get(new Random().nextInt(0, optimisedRandomShots.size())));
            }
        }
        if (best.size() != 0) {
            return best.get(new Random().nextInt(0, best.size()));
        } else if (ok.size() != 0) {
            return ok.get(new Random().nextInt(0, ok.size()));
        } else return random.get(new Random().nextInt(0, random.size()));


    }

    /**
     * Used to determine if a shot is beneficial or not
     * @param ship the array list of the ships points
     * @param point the point determining if beneficial hit
     * @return a boolean that is true if the shot will be good
     */
    private boolean probableHit(ArrayList<Point> ship, Point point) {
        int samex = 0;
        int samey = 0;
        for (Point shipPoint: ship){
            if (shipPoint.getY() == point.getY()){
                samey++;
            }
            if (shipPoint.getX() == point.getX()){
                samex++;
            }
        }
        // if the point is on the same axis as the points of all of our ships points it means we have no L shape
        if (samex == ship.size()){
            int maxY = -1;
            int minY = Integer.MAX_VALUE;
            for (Point shipPoint:ship){
                maxY = Math.max(maxY, shipPoint.getY());
                minY = Math.min(minY, shipPoint.getY());
            }
            // if the shot is 1 point away from the top or bottom of our ship it means it is probably a hit
            if (point.getY() - 1 == maxY || point.getY() + 1 == minY){
                return true;
            }
        } else if (samey == ship.size()){
            int maxX = -1;
            int minX = Integer.MAX_VALUE;
            for (Point shipPoint:ship){
                maxX = Math.max(maxX, shipPoint.getX());
                minX = Math.min(minX, shipPoint.getX());
            }
            // if the shot is 1 point away from the left or right of our ship it means it is probably a hit
            if (point.getX() - 1 == maxX || point.getX() + 1 == minX){
                return true;
            }
        } else return false;
        return false;
    }

    /**
     *  groups adjacent hit and not sunk ships together to create unsunk ships
     * @param hitNotSunk an arraylist of points of ships that are hit but not sunk
     * @return an arrayList of arraylists that contain the points of unsunk ships
     */
    private ArrayList<ArrayList<Point>> groupHits(ArrayList<Point> hitNotSunk) {
        ArrayList<ArrayList<Point>> unsunk = new ArrayList<>();
        // groups adjacent hit and not sunk ships together to create unsunk ships
        for (Point point : hitNotSunk) {
            boolean added = false;
            int addPos = -1;
            for (ArrayList<Point> ship : unsunk) {
                if (!added) {
                    for (Point shipPoint : ship) {
                        if (!added) {
                            if (point.getY() == shipPoint.getY()) {
                                if (point.getX() + 1 == shipPoint.getX() || point.getX() - 1 == shipPoint.getX()) {
                                    addPos = unsunk.indexOf(ship);
                                    added = true;
                                }
                            } else if (point.getX() == shipPoint.getX()) {
                                if (point.getY() + 1 == shipPoint.getY() || point.getY() - 1 == shipPoint.getY()) {
                                    addPos = unsunk.indexOf(ship);
                                    added = true;
                                }
                            }
                        }

                    }
                }
            }
            if (!added) {
                ArrayList<Point> newShip = new ArrayList<>();
                newShip.add(point);
                unsunk.add(newShip);
            } else {
                ArrayList<Point> overWrite = unsunk.get(addPos);
                overWrite.add(point);
                unsunk.remove(addPos);
                unsunk.add(overWrite);
            }
        }
        return unsunk;
    }

    /**
     * Used to determine if a shot is near a sunk ship
     * @param board the board being used
     * @param x the location on x axis
     * @param y the location on the y axis
     * @return a boolean that is true if the shot is near a ship
     */
    private boolean nearSunk(Board_ board, int x, int y) {
        // checks if the coordinate is in the territory of a sunk ship
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i >= 0 && x + i < Board_.NUMBER_OF_CELLS && y + j >= 0 && y + j < Board_.NUMBER_OF_CELLS) {
                    if (board.isSunk(x + i, y + j)) return true;
                }

            }
        }
        return false;
    }

    /**
     * Used to determine if a ship is near a shot
     * @param board the board being used
     * @param x the location on x axis
     * @param y the location on the y axis
     * @return a boolean that is true if the shot is near a ship
     */
    private boolean adjacentToHitShip(Board_ board, int x, int y) {
        // checks if the coordinate is adjacent to a cell that is both a hit, and a ship
        if (x + 1 < Board_.NUMBER_OF_CELLS) if (board.isHit(x + 1, y)) if (board.isShip(x + 1, y)) return true;
        if (x - 1 >= 0) if (board.isHit(x - 1, y)) if (board.isShip(x - 1, y)) return true;
        if (y + 1 < Board_.NUMBER_OF_CELLS) if (board.isHit(x, y + 1)) if (board.isShip(x, y + 1)) return true;
        if (y - 1 >= 0) if (board.isHit(x, y - 1)) if (board.isShip(x, y - 1)) return true;
        return false;
    }

    /**
     * getter for the AIs board
     * @return the AIs board
     */
    public Board_ getBoard() {
        return myBoard;
    }



    /**
     * Used when it is needed to determine if there are a players ships left
     * @return a boolean that is true if there are no ships left
     */
    public boolean allSunk() {
        return myBoard.allSunk();
    }

    /**
     * Used to annotate that a cell in the board has been adjusted
     * @param point the locaiton (x,y) of the cell
     * @param player_shooting the person who shot their board
     */
    public void markCell(Point point, int player_shooting) {
        myBoard.markCell(point.getX(),point.getY(),player_shooting);
    }

    /**
     * a setter to give the AI the current game
     * @param game the game we are playing
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * A getter for the ID
     * @return an int of the ID
     */
    public int getID() {
        return player_id;
    }
}