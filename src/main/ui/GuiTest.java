package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiTest implements ActionListener {
    private int integer = 0;

    public GuiTest() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
        JLabel label = new JLabel("meow");

        JButton button = new JButton(":__:");
        ActionListener integerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                integer++;
                label.setText("click: " + integer);
            }
        };
        button.addActionListener(integerListener);

        JButton button2 = new JButton("el troll");
        ActionListener trollListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                integer--;
                label.setText("el troll click: " + integer);
            }
        };
        button2.addActionListener(trollListener);

        panel.add(button);
        panel.add(button2);
        panel.add(label);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.setSize(400, 400);
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

}
