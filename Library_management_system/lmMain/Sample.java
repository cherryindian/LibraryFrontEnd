package lmMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sample extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int METEOR_SIZE = 5;
    private static final int METEOR_SPEED = 3;

    private List<Meteor> meteors;

    public Sample() {
        setTitle("Meteor Shower Animation");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        meteors = new ArrayList<>();

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveMeteors();
                repaint();
            }
        });

        // Create meteors
        createMeteors();

        // Start the timer
        timer.start();
    }

    private void createMeteors() {
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int speed = METEOR_SPEED + random.nextInt(3);
            meteors.add(new Meteor(x, y, speed));
        }
    }

    private void moveMeteors() {
        for (Meteor meteor : meteors) {
            meteor.move();
            if (meteor.getY() > HEIGHT) {
                meteor.resetPosition();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Meteor meteor : meteors) {
            g.setColor(Color.WHITE);
            g.fillOval(meteor.getX(), meteor.getY(), METEOR_SIZE, METEOR_SIZE);
        }
    }

    private static class Meteor {
        private int x;
        private int y;
        private int speed;

        public Meteor(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void move() {
            x += speed;
            y += speed;
        }

        public void resetPosition() {
            y = 0;
            x = new Random().nextInt(WIDTH);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sample().setVisible(true);
            }
        });
    }
}
