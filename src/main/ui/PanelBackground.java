package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// creates a "custom" JPanel with an image applied onto the JPanel.
public class PanelBackground extends JPanel {
    private Image background;

    // MODIFIES: this
    // EFFECTS: reads in specified background image
    public PanelBackground() {
        try {
            this.background = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            System.out.println("meow");
        }
    }

    // REQUIRES: instantiated swing component
    // EFFECTS: draws image onto the component
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // image scaled
    }

    // EFFECTS: returns a rectangle of background image width and height
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}
