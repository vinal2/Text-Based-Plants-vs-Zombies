package ui;

import java.util.Arrays;
import java.util.Scanner;

import model.Game;

public class GameApp {

    //EFFECTS: runs game
    public GameApp() {
        runGame();
    }

    // EFFECTS:  if the user enters p, instantiate the game, holds the game loop
    // cont. displays the play field, gets user input from game menu, runs all the game updates
    public void runGame() {
        mainMenuDisplay();
        String answer = getUserInput();
        if (answer.equals("p")) {
            // run game
            Game game = new Game();
            while (game.getGameState()) {
                printPlayField(game.returnPlayField());
                gameMenuDisplay();
                String input = getUserInput();
                if (processNumericalInput(input, game.returnPlayField())) {
                    processGameInput(Integer.parseInt(input), game);
                } else if (input.equals("e")) {
                    game.stopGame();
                    System.out.println("Quitting game");
                } // does the processing of commands and stuff
                gameUpdate(game);
            }

        } else if (answer.equals("e")) {
            System.out.println("Quitting game");
        }
    }

    // REQUIRES: non-empty string array
    // EFFECTS: prints play field array
    public void printPlayField(String[] playField) {
        System.out.println(Arrays.toString(playField));
    }

    // REQUIRES: instantiated game
    // EFFECTS: runs an update of the game, moving zombies, checking game state etc.
    public void gameUpdate(Game game) {
        game.damageZombie(game.calculateDamage());
        game.moveZombies();
        game.gameOver();
        if (game.getGameState()) {
            game.damagePlant();
        } else {
            printPlayField(game.returnPlayField());
            System.out.println("Game over");
        }
    }

    // REQUIRES: non-empty input string, non-empty string array
    // EFFECTS: processes user input, finds if it is an integer. will exit game if e is entered (like menu)
    public boolean processNumericalInput(String input, String[] playField) {
        int numericalValue;
        int length = playField.length;
        try {
            numericalValue = Integer.parseInt(input);
            if (numericalValue >= length) {
                System.out.println("Input an integer from 0 to " + (length - 1));
                return false;
            }
            return true;
        } catch (NumberFormatException exception) {
            if (!input.equals("e") && !input.isBlank()) {
                System.out.println("Please input an integer from 0 to " + (length - 1));
            }
        }
        return false;
    }

    //EFFECTS: prints the main menu
    public void mainMenuDisplay() {
        System.out.println("Pvz idk");
        System.out.println("=============================");
        System.out.println("p -> play");
        System.out.println("e -> exit");
        System.out.println("=============================");
    }

    // EFFECTS: prints the game menu
    public void gameMenuDisplay() {
        System.out.println("=============================");
        System.out.println("what index do you want to plant a flower: ");
        System.out.println("=============================");
    }

    //REQUIRES: input >= 0, game instance instantiated
    //EFFECTS: plants a flower
    public void processGameInput(int input, Game game) {
        game.placePlant(input);
    }

    //EFFECTS: gets the user input
    public String getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Input: ");
        return input.nextLine();
    }
}


