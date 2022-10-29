package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String filePath = "./data/gameData.json";
    Game game = new Game();
    JsonReader reader = new JsonReader(filePath);
    JsonWriter writer = new JsonWriter(filePath);

    @Test
    void writeInvalidFileTest() {
        try {
            writer = new JsonWriter("./data/wy\0si.json");
            writer.open();
            writer.writeToJson(game);
            writer.close();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void writeEmptyGameTest() {
        try {
            writer = new JsonWriter("./data/emptyGame.json");
            Game emptyGame = new Game();
            writer.open();
            writer.writeToJson(emptyGame);
            writer.close();

            reader = new JsonReader("./data/emptyGame.json");
            reader.readJson(emptyGame);
            assertEquals(emptyGame.getZombieList().size(), 0);
            assertEquals(emptyGame.getPlantList().size(), 0);
        } catch (IOException e) {
            fail("file doesn't exist");
        }
    }

    @Test
    void writeGameTest() {
        // update this with every change in gameData.json
        game.summonBaseLevel();
        game.placePlant(0);
        try {
            writer = new JsonWriter(filePath);
            writer.open();
            writer.writeToJson(game);
            writer.close();

            reader = new JsonReader(filePath);
            assertEquals(game.getZombieList().size(), 2);
            assertEquals(game.getPlantList().size(), 1);
        } catch (IOException e) {
            fail("file doesn't exist");
        }
    }
}
