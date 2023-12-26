package lmPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwinklingStarsEffect extends JPanel {

    private static final int WIDTH = 1350;
    private static final int HEIGHT = 2000;
    private static final int STAR_SIZE = 5;

    private List<Star> stars;

    public TwinklingStarsEffect() {
        setSize(WIDTH, HEIGHT);
        setOpaque(false);

        stars = new ArrayList<>();

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twinkleStars();
                repaint();
            }
        });

        createStars();

        timer.start();
    }

    private void createStars() {
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            stars.add(new Star(x, y));
        }
    }

    private void twinkleStars() {
        Random random = new Random();

        for (Star star : stars) {
            if (random.nextDouble() < 0.02) {
                star.setTwinkling(!star.isTwinkling());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Star star : stars) {
            g.setColor(star.isTwinkling() ? Color.WHITE : Color.BLACK);
            g.fillOval(star.getX(), star.getY(), STAR_SIZE, STAR_SIZE);
        }
    }

    private static class Star {
        private int x;
        private int y;
        private boolean twinkling;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
            this.twinkling = false;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isTwinkling() {
            return twinkling;
        }

        public void setTwinkling(boolean twinkling) {
            this.twinkling = twinkling;
        }
    }

}
