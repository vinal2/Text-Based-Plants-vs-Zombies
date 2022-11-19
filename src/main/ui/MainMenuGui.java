package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// main menu class for the gui. Creates a jFrame/panel for the main menu and has a background added to it
// has buttons for loading, exiting the game and to play without loading, which will load the base level into the game
public class MainMenuGui {
    JLabel mainLabel;
    JPanel panel;
    GameToGui gameInstance;

    // MODIFIES: this
    // EFFECTS: initializes the gui, adds the buttons and puts everything on the frame to display
    public MainMenuGui() {
        JFrame frame = new JFrame();
        panel = new PanelBackground();
        panel.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
        panel.setLayout(null);
        gameInstance = new GameToGui();

        mainLabel = new JLabel("bottom text");
        mainLabel.setBounds(340, 500, 400, 100);
        fillLabelWithText(mainLabel);
        panel.add(mainLabel);

        JLabel title = new JLabel("put title image here");
        title.setBounds(340, 30, 400, 100);
        fillLabelWithText(title);
        panel.add(title);

        buttons();

        frame.setSize(1080, 720);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

    // MODIFIES: this
    // EFFECTS: adds all 3 buttons, with play, load and exit buttons with their own functions
    private void buttons() {
        JButton play = new JButton("Play");
        ActionListener playListener = e -> new GameGui(new GameToGui());
        play.setBounds(460, 200, 160, 50);
        play.addActionListener(playListener);
        panel.add(play);

        JButton load = new JButton("Load Game");
        ActionListener loadListener = e -> {
            mainLabel.setText("meow1"); // load all the game components into a new game Gui
            gameInstance.readGame();
            new GameGui(gameInstance);
        };
        load.addActionListener(loadListener);
        load.setBounds(460, 275, 160, 50);
        panel.add(load);

        JButton exit = new JButton("Exit Game");
        ActionListener exitListener = e -> System.exit(0);
        exit.addActionListener(exitListener);
        exit.setBounds(460, 350, 160, 50);
        panel.add(exit);
    }
}
