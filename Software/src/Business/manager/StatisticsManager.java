package Business.manager;

import Business.Entities.User;
import Business.manager.UserManager;
import Persistance.Config.ConfigJSONDAO;
import Persistance.Config.ConfigObject;
import Persistance.Database.SQLGameDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

/**
 * A class used to get the information related with statistics from the database,
 * so the controller can get the data from it
 *
 * @author ICE9
 */

public class StatisticsManager {
    private SQLGameDao gameDao;
    private UserManager userManager;

    /**Constructor used by statistics controller
     * @param userManager for data about a user
     */
    public StatisticsManager(UserManager userManager) {
        this.userManager = userManager;
        gameDao = new SQLGameDao();
        try {
            ConfigObject a = new ConfigJSONDAO().read("Files/config.json");
            gameDao.registerConnection(a);
        } catch (FileNotFoundException e) {
           // e.printStackTrace();
        }

    }

    /**
     * we get the number of attacks per game from the database from the user who is playing
     * @return a Hashmap of number of attacks made (key) and number of games played (value)
     */
    public LinkedHashMap<Integer, Integer> getNumAttacks(){

        try {
            return gameDao.getAttacksPerGame(userManager.getUser().getUsername());
        } catch (SQLException e) {
          //e.printStackTrace();
        }
        return null;
    }
    /**
     * we get the number of total games played from the database
     * @return integer containing the number of games played
     */
    public int getTotalGames() {
        try {
            return gameDao.getGamesPlayed(userManager.getUser().getUsername());
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return 0;
    }
    /**
     * we get the number of wins from the database from the user who is playing
     * @return integer containing the number of wins
     */
    public int getNumWins() {
        try {
            return gameDao.getGamesWon(userManager.getUser().getUsername());
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return 0;
    }
}
