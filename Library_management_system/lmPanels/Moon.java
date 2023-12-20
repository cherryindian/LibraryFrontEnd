package lmPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Moon extends JComponent {

    private int diameter;

    Moon(int diameter) {
        this.diameter = diameter;
        setPreferredSize(new Dimension(diameter, diameter));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMoon(g2d);
    }

    private void drawMoon(Graphics2D g2d) {
        int x = 0;
        int y = 0;

        Ellipse2D moon = new Ellipse2D.Double(x, y, diameter, diameter);
        g2d.setColor(Color.WHITE);
        g2d.fill(moon);
    }

}
