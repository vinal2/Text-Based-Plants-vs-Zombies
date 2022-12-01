package persistence;

import model.Game;
import model.Zombie;
import model.Plant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// reads game data from json into game instance
public class JsonReader {
    private final String file;

    // REQUIRES: non-empty string -> should be a path to a file
    // MODIFIES: this
    // EFFECTS: sets class field to parameter
    public JsonReader(String source) {
        file = source;
    }

    // REQUIRES: instantiated game instance
    // EFFECTS: gets json data from file path, stores it in a json object and parses it to get zombie and plant lists
    // and replaces the game instance's zombie/plant lists
    public void readJson(Game game) throws IOException {
        String data = readFile(file);
        JSONObject dataJson = new JSONObject(data);
        game.replacePlantList(getPlantJsonList(dataJson));
        game.replaceZombieList(getZombieJsonList(dataJson));
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    } // note: from JsonSerialization Demo

    // REQUIRES: non-empty json object
    // EFFECTS: returns zombie list with data read from json
    public ArrayList<Zombie> getZombieJsonList(JSONObject dataJson) {
        JSONArray array = dataJson.getJSONArray("zombies");
        ArrayList<Zombie> zombieList = new ArrayList<>();
        for (Object json : array) {
            JSONObject next = (JSONObject) json;
            int index = next.getInt("index");
            int damage = next.getInt("damage");
            int health = next.getInt("health");
            Zombie zombie = new Zombie(health, damage, index);
            zombieList.add(zombie);
        }
        return zombieList;
    }

    // REQUIRES: non-empty json object
    // EFFECTS: returns plant list of data read from json
    public ArrayList<Plant> getPlantJsonList(JSONObject dataJson) {
        JSONArray array = dataJson.getJSONArray("plants");
        ArrayList<Plant> plantList = new ArrayList<>();
        for (Object json : array) {
            JSONObject next = (JSONObject) json;
            int index = next.getInt("index");
            int damage = next.getInt("damage");
            int health = next.getInt("health");
            Plant plant = new Plant(health, damage, index);
            plantList.add(plant);
        }
        return plantList;
    }

}
