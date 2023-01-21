package Persistance.Game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * A class containing the logic for saving and loading a game
 *
 * @author ICE 9
 */

public class GameJSONDAO implements BoardDAO {



    @Override
    public GameObject read(String path) throws FileNotFoundException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        FileReader f = new FileReader(path);
        GameObject g = gson.fromJson(gson.newJsonReader((f)), GameObject.class);
        try {
            f.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return g;
    }

    @Override
    public void write(String path, GameObject toBeSaved) throws IOException {
        Writer writer = new FileWriter(path+toBeSaved.getGameName()+".json");
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        //gson.toJson(toBeSaved.getGameName(),writer);
        gson.toJson(toBeSaved,writer);
        writer.close();
    }

    @Override
    public String[] getFileNames(String path){
        File f = new File(path);
        return f.list();
    }
    @Override
    public String[][] getFileInfo(String path) throws IOException {
        String[] filenames = getFileNames(path);
        String[][] files = new String[filenames.length][2];
        for (int i = 0; i < files.length; i++){
            files[i][0] = filenames[i];
            files[i][1] =  Files.readAttributes(Path.of(path + files[i][0]), BasicFileAttributes.class).lastModifiedTime().toString().split("T")[0];
        }
        return files;
    }
    @Override
    public boolean filenameExists(String path,String name){
        String[] files = getFileNames(path);
        for (String file : files) {
            if (file.equals(name + ".json")) return true;
        }
        return false;
    }

    @Override
    public boolean deleteGame(String path) {
        File f = new File(path);
        return f.delete();
    }
}
