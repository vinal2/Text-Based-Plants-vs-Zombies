package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final String filePath = "./data/gameData.json";
    Game game = new Game();

    @BeforeEach
    void instantiateGame() {
        game.summonBaseLevel();
    }

    @Test
    void testConstructors() { //test that zombie/plant list are not empty
        assertEquals(game.getZombieList().size(), 2);
        assertEquals(game.getPlantList().size(), 0);

        game.placeZombie(4);
        game.placePlant(0);

        assertEquals(game.getZombieList().size(), 3);
        assertEquals(game.getPlantList().size(), 1);
        // test if the objects are really initializing properly
        assertEquals(game.getZombieList().get(2).getIndex(), 4);
        assertEquals(game.getPlantList().get(0).getIndex(), 0);
    }

    @Test
    void testMoveZombies() { // test cases:
        // test case of having no plant in front of zombie -> all zombies move forward
        game.moveZombies();
        assertEquals(game.getZombieList().get(0).getIndex(), 6);
        assertEquals(game.getZombieList().get(1).getIndex(), 8);

        // test case of having plant in front of first zombie -> all zombies that aren't the first must move
        game.placePlant(5);
        game.moveZombies();
        assertEquals(game.getZombieList().get(0).getIndex(), 6);
        assertEquals(game.getZombieList().get(1).getIndex(), 7);

        // test case of having plant in front of first zombie, and second zombie is behind first -> no movement
        game.moveZombies();
        assertEquals(game.getZombieList().get(0).getIndex(), 6);
        assertEquals(game.getZombieList().get(1).getIndex(), 7);
    }

    @Test
    void testDamageZombies() {
        // test damaging only the first zombie, and the first zombie is the only zombie that has reduced hp
        game.placePlant(0);
        // test that the plant damage is calculated correctly
        assertEquals(game.calculateDamage(), game.getPlantList().get(0).getDamage());
        game.damageZombie(game.calculateDamage());
        //test that only the first zombie takes damage
        assertEquals(game.getZombieList().get(0).getHealth(), 490);
        assertEquals(game.getZombieList().get(1).getHealth(), 500);
        //test when one zombie is lower than 0 health that it gets removed
        for (int i = 0; i < 49; i++) {
            game.damageZombie(game.calculateDamage());
        }

        assertEquals(game.getZombieList().get(0).getHealth(), 500);
        assertEquals(game.getZombieList().size(), 1);
    }

    @Test
    void testDamagePlants () {
        // place plant right in front of first zombie
        game.placePlant(6);
        assertEquals(game.getPlantList().get(0).getHealth(), 100);
        // test case where plant is in front of zombie
        game.damagePlant();
        assertEquals(game.getPlantList().get(0).getHealth(), 80);

        // test when plant gets lower than 0 hp
        for (int i = 0; i < 4; i++) {
            game.damagePlant();
        }
        assertTrue(game.getPlantList().isEmpty()); // empty string when the plant is removed

        // test when plant is not in front of a zombie
        game.placePlant(0);
        game.damagePlant();
        assertEquals(game.getPlantList().get(0).getHealth(), 100);

        // test that plants behind a plant before a zombie do not get hit
        game.placePlant(6);
        game.placePlant(5);
        game.damagePlant();
        assertEquals(game.getPlantList().get(1).getHealth(), 80);
        assertEquals(game.getPlantList().get(2).getHealth(), 100);
    }

    @Test
    void testGameOver() {
        // test if the game is still going (within the game instance)
        game.gameOver();
        assertTrue(game.getGameState());
        // move zombie to index 0 from 7
        for (int i = 0; i < 7; i++) {
            game.moveZombies();
        }
        // checks if the game is over afterwards
        game.gameOver();
        assertFalse(game.getGameState());
    }

    @Test
    void readFileTest() {
        JsonReader reader = new JsonReader(filePath);
        try {
            reader.readJson(game);
            game.loadedGame();
            game.setLoadedField();
            System.out.println("loaded game");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + filePath);
        }

        assertEquals(game.getZombieList().size(), 1);
        assertEquals(game.getPlantList().size(), 3);

        Game game2 = new Game();
        game2.summonBaseLevel();
        JsonWriter writer = new JsonWriter(filePath);

        try {
            writer.open();
            writer.writeToJson(game);
            writer.close();
            System.out.println("saved game");
            game.stopGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file at " + filePath);
        }

        assertEquals(game2.getZombieList().size(), 2);
        assertEquals(game2.getPlantList().size(), 0);
    }

}

