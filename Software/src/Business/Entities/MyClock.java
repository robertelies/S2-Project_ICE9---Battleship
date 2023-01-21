package Business.Entities;

import Presentation.Controller.Listeners.GameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class designed to simulate like a clock when needing to keep track of tiem
 *
 * @author ICE 9
 */

public class MyClock extends Thread {

    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private GameListener gameListener;
    private boolean stop;

    /**
     * Constructor used to set a new clock at 0
     */
    public MyClock() {
        setTime(0,0,0);
    }

    /**
     * Used to load a clock given a previous time
     * @param duration the previous time of game
     */
    public MyClock(String duration) {
        String[] split = duration.split(":");
        hour = Integer.parseInt(split[0]);
        minute = Integer.parseInt(split[1]);
        second = Integer.parseInt(split[2]);
    }

    /**
     * Used to designate that the clock should function
     */
    @Override
    public void run(){
        while (!stop){
            try {
                Thread.sleep(1000);
                second++;
                if (second >= 60){
                    second = 0;
                    minute++;
                    if (minute >= 60){
                        hour++;
                        minute = 0;
                    }
                }
                if (gameListener != null) gameListener.timeAdvanced();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }

    }

    /**
     * Set the time to the param
     * @param newHours hours
     * @param newMinute minutes
     * @param newSecond seconds
     */
    public void setTime(int newHours, int newMinute, int newSecond){
        hour = newHours;
        minute = newMinute;
        second = newSecond;
    }

    /**
     * getter for the hour
     * @return int
     */
    public int getHour() {
        return hour;
    }

    /**
     * getter for the minute
     * @return int
     */
    public int getMinute() {
        return minute;
    }

    /**
     * getter for the second
     * @return int
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set the clock to stop
     * @param stop boolean if we should stop
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * a setter for the listener
     * @param listener GameListener
     */
    public void registerListener(GameListener listener){
        this.gameListener = listener;
    }

    /**
     * Used to convert the time to a string
     * @return string
     */
    public String toString(){
        return String.format("%02d" , hour)+":"+String.format("%02d" , minute)+":"+String.format("%02d" , second);
    }


}
