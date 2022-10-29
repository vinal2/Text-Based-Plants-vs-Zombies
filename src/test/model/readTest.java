package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class readTest {
    private static final String filePath = "./data/gameData.json";
    Game game = new Game();
    JsonReader reader = new JsonReader(filePath);

    @Test
    void readEmptyFileTest() {
        reader = new JsonReader("./data/wysi.json");
        try {
            reader.readJson(game);
            fail("IOException expected");
        } catch (IOException e) {

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
}
