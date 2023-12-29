package lmPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NonAdmin extends JPanel {

    private TwinklingStarsEffect twinklingStarsEffect;
    private Moon moon;

    public NonAdmin() {
        setLayout(null);
        setBounds(145, 55, 1350, 2000);

        twinklingStarsEffect = new TwinklingStarsEffect();
        twinklingStarsEffect.setBounds(0, 0, 1350, 1600);
        add(twinklingStarsEffect);

        moon = new Moon(100);
        moon.setBounds(1350, 200, 100, 100);
        add(moon);

        setBackground(new Color(0, 0, 0));
        setVisible(true);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveMoon();
                repaint();
            }
        });
        timer.start();
    }

    private void moveMoon() {
        if (moon.getX() + moon.getWidth() > 0) {
            moon.setLocation(moon.getX() - 2, moon.getY() + 2);
        } else {
            moon.setLocation(1350, 200);
        }
    }

}
