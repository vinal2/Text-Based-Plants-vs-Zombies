package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

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
    void gameTest() {
        // update this with every change in gameData.json
        reader = new JsonReader(filePath);
        try {
            reader.readJson(game);
            assertEquals(game.getZombieList().size(), 2);
            assertEquals(game.getPlantList().size(), 1);
        } catch (IOException e) {
            fail("file doesn't exist");
        }
    }

    @Test
    void readPlantJsonTest() {
        ArrayList<Plant> plantsList = new ArrayList<>();
        String source = "./data/readPlantTest.json";
        reader = new JsonReader(source);
        try {
            String data = reader.readFile(source);
            JSONObject dataJson = new JSONObject(data);
            plantsList.add(new Plant(10, 100, 0));
            assertEquals(reader.getPlantJsonList(dataJson).size(), plantsList.size());
            assertEquals(reader.getPlantJsonList(dataJson).get(0).getIndex(), plantsList.get(0).getIndex());

            game.placePlant(0);
            game.placePlant(3);

            JsonWriter writer = new JsonWriter(source);

            writer.open();
            writer.writeToJson(game);
            writer.close();

            String data3 = reader.readFile(source);
            JSONObject dataJson3 = new JSONObject(data3);
            plantsList.add(new Plant(10, 100, 3));
            assertEquals(reader.getPlantJsonList(dataJson3).size(), plantsList.size());
            assertEquals(reader.getPlantJsonList(dataJson3).get(1).getIndex(), plantsList.get(1).getIndex());

            Game onePlantGame = new Game();
            onePlantGame.placePlant(0);
            writer.open();
            writer.writeToJson(onePlantGame);
            writer.close();

        } catch (IOException e) {
            fail("can't read");
        }
    }

    @Test
    void readEmptyPlantJsonTest() {
        try {
            String data2 = reader.readFile("./data/emptyGame.json");
            JSONObject dataJson2 = new JSONObject(data2);
            assertEquals(reader.getPlantJsonList(dataJson2).size(), 0);
        } catch (IOException e) {
            // pass
        }


    }

    @Test
    void readZombieJsonTest() {
        ArrayList<Zombie> zombiesList = new ArrayList<>();
        try {
            String data = reader.readFile(filePath);
            JSONObject dataJson = new JSONObject(data);
            zombiesList.add(new Zombie(20, 490, 7));
            zombiesList.add(new Zombie(20, 500, 9));
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
