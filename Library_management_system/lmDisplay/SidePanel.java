package lmDisplay;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
    CardLayout layout = (CardLayout) Display.mainPanel.getLayout();

    SidePanel(String userRole) {
        setLayout(new BorderLayout());

        JButton Home = new JButton("Home Page");
        setButtonProperties(Home, Display.Home);

        // JButton Borrow = new JButton("Borrowed");
        // setButtonProperties(Borrow, Display.borrow);

        JButton Issue = new JButton("Issue Book");
        setButtonProperties(Issue, Display.Issue);

        JButton AddBooksButton = new JButton("Add books");
        setButtonProperties(AddBooksButton, Display.addbook);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(145, 65);
        Home.setPreferredSize(buttonSize);
        // Borrow.setPreferredSize(buttonSize);
        Issue.setPreferredSize(buttonSize);
        AddBooksButton.setPreferredSize(buttonSize);

        buttonPanel.add(Home);
        // buttonPanel.add(Borrow);
        if (userRole.equals("ADMIN")) {
            buttonPanel.add(Issue);
            buttonPanel.add(AddBooksButton);
        }
        buttonPanel.setBackground(new Color(0, 0, 0));

        add(buttonPanel, BorderLayout.CENTER);
        setBackground(new Color(0, 0, 0));
        setPreferredSize(new Dimension(155, 2000));
        setVisible(true);
    }

    private void setButtonProperties(JButton button, String panel) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);

        Font originalFont = button.getFont();
        int fontSize = originalFont.getSize();

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setFont(new Font(originalFont.getName(), Font.BOLD, fontSize + 2));
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setFont(originalFont);
                button.setForeground(Color.WHITE);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layout.show(Display.mainPanel, panel);
            }
        });
    }
}
