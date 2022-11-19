package ui;

import model.Game;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

// similar to GameApp class, but without any of the console methods, includes getters to return Game data
public class GameToGui {
    private static final String filePath = "./data/gameData.json";
    private JsonWriter writer;
    private JsonReader reader;
    private Game game;
    private boolean gameLoaded;

    //EFFECTS: runs game, instantiates json reader and writer
    public GameToGui() {
        writer = new JsonWriter(filePath);
        reader = new JsonReader(filePath);
        game = new Game();
        gameLoaded = false;
    }

    // REQUIRES: non-empty string array
    // EFFECTS: prints play field array
    public void printPlayField() {
        System.out.println(Arrays.toString(game.returnPlayField()));
    }

    // REQUIRES: instantiated game
    // EFFECTS: runs an update of the game, moving zombies, checking game state etc.
    public boolean gameUpdate() {
        game.damageZombie(game.calculateDamage());
        if (game.getZombieList().size() == 0) {
            return false;
        }
        game.moveZombies();
        game.gameOver();
        if (game.getGameState()) {
            game.damagePlant();
        } else {
            printPlayField();
            System.out.println("Game over");
            return false;
        }
        return true;
    }

    // REQUIRES: non-empty input string, non-empty string array
    // EFFECTS: processes user input, finds if it is an integer. will exit game if e is entered (like menu)
    public boolean processNumericalInput(String input) {
        int numericalValue;
        int length = game.returnPlayField().length;
        try {
            numericalValue = Integer.parseInt(input);
            if (numericalValue >= length) {
                return false;
            }
            return true;
        } catch (NumberFormatException exception) {
            if (!input.equals("")) {
                return false;
            }
        }
        return false;
    }

    //REQUIRES: input >= 0, game instance instantiated
    //EFFECTS: plants a flower
    public void processGameInput(int input) {
        game.placePlant(input);
    }

    // MODIFIES: this
    // EFFECTS: writes game data to json file
    public void saveGame() {
        try {
            writer.open();
            writer.writeToJson(game);
            writer.close();
            System.out.println("saved game");
            game.stopGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file at " + filePath);
        }
    }

    // MODIFIES: this
    // EFFECTS: reads json file of game data into game instance
    public void readGame() {
        try {
            reader.readJson(game);
            game.loadedGame();
            game.setLoadedField();
            System.out.println("loaded game");
            gameLoaded = true;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + filePath);
        }
    }

    public String returnGamePlayField() {
        return Arrays.toString(game.returnPlayField());
    }

    // MODIFIES: this
    // EFFECTS: if the game isn't loaded, it will start with the base level of 2 zombies
    public void loadBaseLevel() {
        if (!gameLoaded) {
            game.summonBaseLevel();
        }
    }
}
