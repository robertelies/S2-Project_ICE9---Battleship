package Persistance.Config;

import java.io.FileNotFoundException;

/**
 * An interface used for the DB configuration file
 */

public interface ConfigDAO {

    /**
     * The method that reads from the config file
     * @param path the path to the file as a string
     * @return a constructed ConfigObject with the information from the file
     * @throws FileNotFoundException for when there was an error reading from the file
     */
    ConfigObject read(String path) throws FileNotFoundException;
}
