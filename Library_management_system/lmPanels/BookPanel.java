package lmPanels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmDisplay.Display;
import lmMain.Books;

public class BookPanel extends JPanel {
    Books book;
    CardLayout layout = (CardLayout) Display.mainPanel.getLayout();

    BookPanel(String BookName) {
        book = new Books(BookName);

        ImageIcon icon;
        try {
            URL imageURL = new URL(book.imageUrl);
            icon = new ImageIcon(imageURL);
            icon.setImage(icon.getImage().getScaledInstance(400, 610, Image.SCALE_SMOOTH));
        } catch (MalformedURLException e) {
            icon = new ImageIcon("C:\\Users\\srich\\Desktop\\rose.png");
        }

        JLabel bookcover = new JLabel(icon);
        bookcover.setBounds(0, 0, 400, 600);

        JLabel bookNameLabel = Home.createBookLabel(BookName, bookcover);

        add(bookcover);
        add(bookNameLabel);

        setBackground(new Color(10, 10, 10));
        setLayout(null);
        setBounds(145, 55, 1350, 2000);
        Display.mainPanel.add(this, "book");
        layout.show(Display.mainPanel, "book");
    }
}