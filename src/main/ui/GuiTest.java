package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class GuiTest implements ActionListener {
    private int integer = 0;

    public GuiTest() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
        panel.setLayout(null);
        JLabel label = new JLabel("meow");
        label.setBounds(100, 10, 250, 25);
        frame.setSize(1080, 720);

        JButton button = new JButton(":__:");
        ActionListener integerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                integer++;
                label.setText("click: " + integer);
            }
        };
        button.addActionListener(integerListener);
        button.setBounds(10, 10, 90, 90);

        JButton button2 = new JButton("el troll");
        ActionListener trollListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                integer--;
                label.setText("el troll click: " + integer);
            }
        };
        button2.addActionListener(trollListener);
        button2.setBounds(100, 100, 50, 50);

        JTextField textField = new JFormattedTextField(NumberFormat.getNumberInstance());
        textField.setBounds(300, 300, 150, 25);
        panel.add(textField);

        panel.add(button);
        panel.add(button2);
        panel.add(label);

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
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new GuiTest();
            }
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 275, 160, 50);
        panel.add(button2);
    }

    private void inputButton(JPanel panel, JTextField textField, int index) {
        JButton button2 = new JButton("Load Game");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new GuiTest();
            }
        };
        button2.addActionListener(listener);
        button2.setBounds(460, 275, 160, 50);
        panel.add(button2);
    }



}
