package Persistance.Config;

/**
 * A class used to directly represent the information from the file /Files/config.json
 * that obtains the information about our DB
 *
 * @author ICE 9
 */

public class ConfigObject {

    private String host;
    private int port;
    private String username;
    private String nameDB;
    private String passDB;
    private int time;

    /**
     * Constructor when reading from file
     * @param host host used
     * @param port port utilized
     * @param username username for db
     * @param nameDB name of db
     * @param passDB the password to db
     * @param time time
     */
    public ConfigObject(String host, int port, String username, String nameDB, String passDB,int time) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.nameDB = nameDB;
        this.passDB = passDB;
        this.time=time;
    }

    @Override
    public String toString() {
        return "ConfigObject{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", nameDB='" + nameDB + '\'' +
                ", passDB='" + passDB + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    /**
     * A getter for the host of the DB
     * @return a string of the host
     */
    public String getHost() {
        return host;
    }

    /**
     * A getter for the port of the Db
     * @return an int of the port
     */
    public int getPort() {
        return port;
    }

    /**
     * A getter for the username of the DB
     * @return a String of the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * A getter for the name of the DB
     * @return a String of the name
     */
    public String getNameDB() {
        return nameDB;
    }

    /**
     * A getter for the password
     * @return a string of the DB password
     */
    public String getPassDB() {
        return passDB;
    }

    /**
     * A getter for the time
     * @return the time as an int
     */
    public int getTime() {
        return time;
    }
}