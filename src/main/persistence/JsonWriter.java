package persistence;

import org.json.JSONObject;
import model.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// writes game data to json
public class JsonWriter {
    private String file;
    private PrintWriter writer;

    // REQUIRES: non-empty file path string
    // MODIFIES: this
    // EFFECTS: sets class field to parameter
    public JsonWriter(String source) {
        file = source;
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of plants and zombies to file
    public void writeToJson(Game game) {
        JSONObject json = game.gameJson();
        saveToFile(json.toString(4));
    }

    // MODIFIES: this
    // EFFECTS: opens writer throws FileNotFoundException if destination file doesn't exist
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(file);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
