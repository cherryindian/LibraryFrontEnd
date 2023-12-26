package lmMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PillButton extends JButton {
    private static final int ARC_WIDTH = 100;
    private static final int ARC_HEIGHT = 100;

    public PillButton(String label) {
        super(label);

        // Make the button transparent
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click
                System.out.println("Button clicked!");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Draw pill-shaped button
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, ARC_WIDTH, ARC_HEIGHT);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, width, height, ARC_WIDTH, ARC_HEIGHT);

        // Draw label text
        super.paintComponent(g);
    }
}

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pill Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            PillButton pillButton = new PillButton("Click Me");
            pillButton.setPreferredSize(new Dimension(100, 50));

            frame.getContentPane().add(pillButton);
            frame.setVisible(true);
        });
    }
}
