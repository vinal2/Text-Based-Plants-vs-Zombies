package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGui {
    JLabel mainLabel;

    public MainMenuGui() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        mainLabel = new JLabel("el trole");
        mainLabel.setBounds(340, 500, 400, 100);
        fillLabelWithText(mainLabel);
        panel.add(mainLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
        panel.setLayout(null);

        JLabel title = new JLabel("pant vus cog-3");
        title.setBounds(340, 30, 400, 100);
        fillLabelWithText(title);
        panel.add(title);

        JButton button2 = new JButton("el troll");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiTest();
            }
        };
        button2.setBounds(460, 200, 160, 50);
        button2.addActionListener(listener);
        panel.add(button2);

        loadButton(panel);
        exitButton(panel);

        frame.setSize(1080, 720);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");

        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenuGui();
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

    private void loadButton(JPanel panel) {
        JButton button2 = new JButton("Load Game");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new GuiTest();
                mainLabel.setText("meow1"); // load all the game components into a new game Gui

            }
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 275, 160, 50);
        panel.add(button2);
    }

    private void exitButton(JPanel panel) {
        JButton button2 = new JButton("Exit Game");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new GuiTest();
                System.exit(0);
            }
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 350, 160, 50);
        panel.add(button2);
    }

}
