package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// creates the game gui, displays the playField from Game class, has ability to place plants, go to the next turn
// and can save the game as well.
public class GameGui implements ActionListener {
    private final GameToGui game;
    private JLabel playField;
    private JLabel gameOverMessage;
    private final JPanel panel;
    private boolean gameState;

    // REQUIRES: instantiated GameToGui class
    // MODIFIES: this
    // EFFECTS: initializes the gameGui with an instantiated GameToGui class (which runs the game)
    //          places everything onto the frame and displays it
    public GameGui(GameToGui gameApp) {
        JFrame frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1080, 720);
        panel.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
        panel.setLayout(null);
        game = gameApp;
        gameState = true;
        game.loadBaseLevel();

        layout();

        panel.add(playField);
        panel.add(gameOverMessage);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    // MODIFIES: this
    // EFFECTS: places all the labels and text onto the screen, including the buttons
    private void layout() {
        playField = new JLabel();
        playField.setBounds(120,450, 840, 100);
        playField.setText(game.returnGamePlayField());
        fillLabelWithText(playField);

        JLabel label = new JLabel();
        label.setBounds(100, 10, 250, 25);
        label.setText("el troll");

        JTextField textField = new JTextField();
        textField.setBounds(460, 240, 160, 25);

        JLabel errorLabel = new JLabel();
        errorLabel.setBounds(440, 200, 200, 30);

        gameOverMessage = new JLabel();
        gameOverMessage.setBounds(320, 350, 400, 80);
        gameOverMessage.setText("");
        fillLabelWithText(gameOverMessage);

        inputButton(textField, errorLabel);
        nextTurnButton();
        saveButton();

        panel.add(errorLabel);
        panel.add(textField);
        panel.add(label);
    }

    // MODIFIES: this
    // EFFECTS: places the next turn button, which calls onto the GameToGui instance
    private void nextTurnButton() {
        JButton button2 = new JButton("Next Turn");
        ActionListener listener = e -> updateGameGui();
        button2.addActionListener(listener);
        button2.setBounds(280, 275, 160, 50);
        panel.add(button2);
    }

    // MODIFIES: this
    // EFFECTS: places button that saves the game when pressed
    private void saveButton() {
        JButton button2 = new JButton("Save Game");
        ActionListener listener = e -> game.saveGame();
        button2.addActionListener(listener);
        button2.setBounds(640, 275, 160, 50);
        panel.add(button2);
    }

    // REQUIRES: instantiated text field and label
    // MODIFIES: this
    // EFFECTS: places a button that when pressed, takes the input from text field and depending on what the input is,
    //          will change the text of the label saying whether the input was taken.
    private void inputButton(JTextField textField, JLabel label) {
        JButton button2 = new JButton("Place Flower");
        ActionListener listener = e -> {
            String input = textField.getText();
            if (gameState) {
                if (game.processNumericalInput(input)) {
                    label.setBounds(495, 200, 200, 30);
                    label.setText("input received");
                    game.processGameInput(Integer.parseInt(input));
                    updateGameGui();
                } else {
                    label.setBounds(440, 200, 200, 30);
                    label.setText("Please input an integer from 0 - 9");
                }
            }
            textField.setText("");
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 275, 160, 50);
        panel.add(button2);
    }

    // REQUIRES: instantiated JLabel
    // EFFECTS: fills a label with its text (I found this online I don't have the link however)
    private void fillLabelWithText(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();
        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();
        double widthRatio = (double)componentWidth / (double)stringWidth;
        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();
        int fontSizeToUse = Math.min(newFontSize, componentHeight);
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse - 2));
    }

    // MODIFIES: this
    // EFFECTS: runs the game updates -> updating the playField every turn and checks for the game over
    //          will display game over whether the user wins or not
    private void updateGameGui() {
        if (gameState && game.gameUpdate()) {
            playField.setText(game.returnGamePlayField());
            fillLabelWithText(playField);
        } else {
            gameOverMessage.setText("Game over");
            playField.setText(game.returnGamePlayField());
            fillLabelWithText(playField);
            fillLabelWithText(gameOverMessage);
            gameState = false;
        }
    }
}
