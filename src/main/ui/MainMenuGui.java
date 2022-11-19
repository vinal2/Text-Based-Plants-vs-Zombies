package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenuGui {
    JLabel mainLabel;
    JPanel panel;
    GameToGui gameInstance;

    public MainMenuGui() {
        JFrame frame = new JFrame();
        /*try {
            JLabel backgroundLabel = new JLabel(new ImageIcon(ImageIO.read(new File("resources/background.png"))));
            frame.setContentPane(backgroundLabel);
        } catch (IOException e) {
            System.out.println("unable to find background");
        }*/

        //panel = new JPanel();
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
