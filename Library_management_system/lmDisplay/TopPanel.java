package lmDisplay;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

    TopPanel() {
        JLabel appName = new JLabel("<html><h1>MARS</h1></html>");
        appName.setBounds(0, 0, 200, 100);
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("Roboto", Font.BOLD, 36));

        JButton closer = new JButton();
        closer.setBounds(1500, 0, 50, 50);
        closer.setBackground(new Color(0, 0, 0));
        closer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(appName);
        add(closer);

        setBounds(0, 0, 1495, 55);
        setBackground(new Color(0, 0, 0));
        setVisible(true);
    }
}
