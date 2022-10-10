package ui;
import java.util.Arrays;
import java.util.Scanner;
import model.Game;

public class GameApp {
    public int resolutionX;
    public int resolutionY; //will use these later for gui
    private Scanner input;
    private char[] playField;
    //make zombie W
    //make plant  W
    //have zombie get hit by plant
    //have zombie die
    //have plant get hit by zombie

    public GameApp(){
        runGame();
    }

    public void runGame() {
        mainMenuDisplay();
        String answer = getUserInput();
        if (answer.equals("p")){
            // run game
            Game game = new Game();
            while (game.gameState){
                printPlayField(game.returnPlayField());
                gameMenuDisplay(game.returnPlayField(), game);
                String input = getUserInput();
                if (processNumericalInput(input, game.returnPlayField())){
                    processGameInput(Integer.parseInt(input), game);
                } else if (input.equals("e")){
                    game.gameState = false;
                    System.out.println("Quitting game");
                } // does the processing of commands and stuff
                game.damagePlant();
                game.damageZombie(game.calculateDamage());
                game.moveZombie();
                game.gameOver();
            }

        } else if (answer.equals("e")) {
            // quit game
        }
    }

    public void printPlayField(String[] playField) {
        System.out.println(Arrays.toString(playField));
    }

    public boolean processNumericalInput(String input, String[] playField){
        int numericalValue;
        int length = playField.length;
        try {
            numericalValue = Integer.parseInt(input);
            if (numericalValue >= length){
                System.out.println("Input an integer from 0 to " + (length - 1));
                return false;
            }
            return true;
        } catch (NumberFormatException exception){
            if (!input.equals("e")) {
                System.out.println("Please input an integer from 0 to " + (length - 1));
            }
        }
        return false;
    }

    public void mainMenuDisplay(){
        System.out.println("Pvz idk");
        System.out.println("=============================");
        System.out.println("p -> play");
        System.out.println("e -> exit");
        System.out.println("=============================");
    }

    public void gameMenuDisplay(String[] playField, Game game){
        System.out.println("=============================");
        System.out.println("what index do you want to plant a flower: ");
        System.out.println("=============================");
    }

    public void processGameInput(int input, Game game){
        game.placePlant(input);
    }

    public String getUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Input: ");
        return input.nextLine();
    }
}


