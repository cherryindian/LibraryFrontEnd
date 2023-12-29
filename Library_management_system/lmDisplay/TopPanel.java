package lmDisplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import lmMain.First;
import lmMain.Users;

public class TopPanel extends JPanel {

    public TopPanel(String userid) {
        setLayout(new GridLayout(1, 4));

        ImageIcon icon = new ImageIcon(
                "C:\\Users\\srich\\Desktop\\project_newday\\year2\\Library_management_system\\MarsLibLogo.jpeg");
        icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel appLogo = new JLabel(icon);
        appLogo.setForeground(Color.WHITE);
        appLogo.setFont(new Font("Roboto", Font.BOLD, 36));

        JLabel appName = new JLabel("<html><h1>MARS</h1></html>");
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("Roboto", Font.BOLD, 40));

        JPanel logoNamePanel = new JPanel();
        logoNamePanel.setOpaque(false);
        logoNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        logoNamePanel.add(appLogo);
        logoNamePanel.add(appName);

        Users user = new Users(userid);
        JLabel helloThere = new JLabel("Hello " + user.userName);
        helloThere.setForeground(Color.WHITE);
        helloThere.setFont(new Font("Roboto", Font.BOLD, 26));

        JButton logout = new JButton("Logout");
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.BLACK);
        logout.setFont(new Font("Roboto", Font.BOLD, 18));
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Display.mainFrame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new First().setVisible(true);
                    }
                });
            }
        });

        add(logoNamePanel);
        add(helloThere);
        add(logout);

        setBackground(new Color(0, 0, 0));
        setPreferredSize(new Dimension(1495, 80));
        setVisible(true);
    }

}
