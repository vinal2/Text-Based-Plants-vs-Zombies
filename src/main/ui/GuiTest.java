package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class GuiTest implements ActionListener {
    private int integer = 0;
    private GameToGui game;
    private JLabel playField;

    public GuiTest() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1080, 720);
        panel.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
        panel.setLayout(null);
        game = new GameToGui();

        playField = new JLabel();
        playField.setBounds(140,450, 800, 100);
        playField.setText(game.returnGamePlayField());
        fillLabelWithText(playField);

        JLabel label = new JLabel();
        label.setBounds(100, 10, 250, 25);
        label.setText("el troll");

        JTextField textField = new JTextField();
        textField.setBounds(460, 240, 160, 25);

        JLabel errorLabel = new JLabel();
        errorLabel.setBounds(440, 200, 200, 30);
        inputButton(panel, textField, errorLabel);

        panel.add(errorLabel);
        panel.add(textField);
        panel.add(label);
        panel.add(playField);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");

        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    private void nextTurnButton(JPanel panel) {
        JButton button2 = new JButton("Next Turn");
        ActionListener listener = e -> {
            //new GuiTest();
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 275, 160, 50);
        panel.add(button2);
    }

    private void inputButton(JPanel panel, JTextField textField, JLabel label) {
        JButton button2 = new JButton("Place Flower");
        ActionListener listener = e -> {
            String input = textField.getText();
            System.out.println(input);
            if (game.processNumericalInput(input)) {
                label.setBounds(495, 200, 200, 30);
                label.setText("input received");
                game.processGameInput(Integer.parseInt(input));
                playField.setText(game.returnGamePlayField());
                fillLabelWithText(playField);
            } else {
                label.setBounds(440, 200, 200, 30);
                label.setText("Please input an integer from 0 - 9");
            }
            textField.setText("");
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 275, 160, 50);
        panel.add(button2);
    }

    private void theDump(JLabel label) {
        JButton button = new JButton(":__:");
        ActionListener integerListener = e -> {
            integer++;
            label.setText("click: " + integer);
            game.printPlayField();
        };
        button.addActionListener(integerListener);
        button.setBounds(10, 10, 90, 90);

        JButton button2 = new JButton("el troll");
        ActionListener trollListener = e -> {
            integer--;
            label.setText("el troll click: " + integer);
        };
        button2.addActionListener(trollListener);
        button2.setBounds(100, 100, 50, 50);
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
}
