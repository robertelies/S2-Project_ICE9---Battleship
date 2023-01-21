package Business.Entities;

/**
 * Class used to represent the shot time for a user
 *
 * @author ICE 9
 */

public class UserShotTimer extends Thread {
    private int time;
    private int timeRemaining;
    private Game game;

    /**
     * Constructor to be used for counting shots
     * @param attackTime the time between attack
     * @param game the game associated
     */
    public UserShotTimer(int attackTime, Game game) {
        this.time = attackTime;
        this.game = game;
    }

    /**
     * Used to begin the the timer
     */
    @Override
    public void run() {
        while (game.notOver()) {

            try {
                Thread.sleep(100);
                if (timeRemaining > 0){
                    timeRemaining = timeRemaining - 100;
                    game.playerCantShoot();
                }
                else {
                    game.playerCanShoot();
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }


        }
    }

    /**
     * Used to reset the time remaining
     */
    public void resetShootTimer() {
        timeRemaining = time;
    }

    /**
     * to determine if a shot can be made
     * @return a boolean
     */
    public boolean canShoot() {
        return timeRemaining <= 0;
    }


}
