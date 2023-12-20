package lmDisplay;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
// import javax.swing.JFrame;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

    TopPanel() {

        JButton closer = new JButton("X");
        closer.setBounds(1500, 0, 50, 50);
        closer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(closer);

        setBounds(0, 0, 1495, 55);
        setBackground(new Color(0, 0, 0));
        setVisible(true);
    }
}
