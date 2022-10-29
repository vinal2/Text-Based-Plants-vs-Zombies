package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    private static final String filePath = "./data/gameData.json";
    Game game = new Game();
    JsonReader reader = new JsonReader(filePath);

    @Test
    void readEmptyFileTest() {
        reader = new JsonReader("./data/wysi.json");
        try {
            reader.readJson(game);
        } catch (IOException e) {
            fail("IOException expected");
        }
    }

    @Test
    void readEmptyGameTest() {
        reader = new JsonReader("./data/emptyGame.json");
        try {
            reader.readJson(game);
            assertEquals(game.getZombieList().size(), 0);
            assertEquals(game.getPlantList().size(), 0);
        } catch (IOException e) {
            fail("file doesn't exist");
        }
    }

    @Test
    void readGameTest() {
        // update this with every change in gameData.json
        try {
            reader.readJson(game);
            assertEquals(game.getZombieList().size(), 2);
            assertEquals(game.getPlantList().size(), 1);
        } catch (IOException e) {
            fail("file doesn't exist");
        }
    }

    @Test
    void getPlantAndZombieJsonTest() {
        ArrayList<Plant> plantsList = new ArrayList<>();
        ArrayList<Zombie> zombiesList = new ArrayList<>();
        try {
            String data = reader.readFile(filePath);
            JSONObject dataJson = new JSONObject(data);
            plantsList.add(new Plant(10, 100, 0));
            assertEquals(reader.getPlantJsonList(dataJson).size(), plantsList.size());
            assertEquals(reader.getPlantJsonList(dataJson).get(0).getIndex(), plantsList.get(0).getIndex());

            plantsList.clear();
            String data2 = reader.readFile("./data/emptyGame.json");
            JSONObject dataJson2 = new JSONObject(data2);
            assertEquals(reader.getPlantJsonList(dataJson2).size(), plantsList.size());
        } catch (IOException e) {
            fail("can't read");
        }

        try {
            String data = reader.readFile(filePath);
            JSONObject dataJson = new JSONObject(data);
            zombiesList.add(new Zombie(20, 490, 6));
            zombiesList.add(new Zombie(20, 500, 8));
            assertEquals(reader.getZombieJsonList(dataJson).size(), zombiesList.size());
            assertEquals(reader.getZombieJsonList(dataJson).get(0).getIndex(), zombiesList.get(0).getIndex());

            zombiesList.clear();
            String data2 = reader.readFile("./data/emptyGame.json");
            JSONObject dataJson2 = new JSONObject(data2);
            assertEquals(reader.getZombieJsonList(dataJson2).size(), zombiesList.size());
        } catch (IOException e) {
            fail("can't read");
        }
    }
}
