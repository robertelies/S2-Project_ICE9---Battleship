package Persistance.Game;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An interface developed for the saving and loading of games
 *
 * @author ICE 9
 */

public interface BoardDAO {

    /**
     * Used to read from the file
     * @param path the path to the file in String format
     * @return a constructed GameObject with the information
     * @throws FileNotFoundException for if we cannot properly locate the file
     */
    GameObject read(String path) throws FileNotFoundException;

    /**
     * Obtains the names of the available loading games
     * @param path the path to the file
     * @return an array of Strings with the names
     * @throws FileNotFoundException if we cannot locate the file
     */
    String[] getFileNames(String path) throws FileNotFoundException;

    /**
     * Used when writing to the file
     * @param path a string to the location of the file
     * @param toBeSaved the GameObject we want to save
     * @throws IOException For file related errors
     */
    void write(String path, GameObject toBeSaved) throws IOException;


    /**
     * Used to obtain the information from file
     * @param path path to file
     * @return a matrix of strings
     * @throws IOException if we cannot access the file
     */
    String[][] getFileInfo(String path) throws IOException;

    /**
     * Used to ensure that a file exists
     * @param path the string of the path
     * @param name the name of the file
     * @return boolean if it exists
     */
    public boolean filenameExists(String path,String name);

    /**
     * Used to delete a game from teh saved
     * @param path location of file
     * @return boolean if deleted
     */
    public boolean deleteGame(String path);
}
