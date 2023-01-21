package Persistance.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A class used to obtain the information from the DB configuration file in a JSON format
 *
 * @author ICE 9
 */

public class ConfigJSONDAO implements ConfigDAO{

    @Override
    public ConfigObject read(String path) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(gson.newJsonReader((new FileReader(path))), ConfigObject.class);
    }
}
