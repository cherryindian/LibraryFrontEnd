package lmPanels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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

        // Bordered layout
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JLabel label7 = new JLabel();
        JLabel label8 = new JLabel();
        JLabel label9 = new JLabel();
        JLabel label10 = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.black, 3);

        // panel1.setLayout(null);
        label.setIcon(icon);
        label.setBackground(Color.black);// set background color
        label.setOpaque(true);// display background color
        label.setBorder(border);// initiate border
        label.setBounds(0, 0, 450, 610);// set x,y position within frame as well as dimensions

        label2.setText("<html>" + book.summary + "</html>");
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        label2.setForeground(new Color(0x00FF00));// Changing word color
        label2.setIconTextGap(10);// Set gap of text to image
        label2.setBackground(Color.blue);// set background color
        label2.setOpaque(true);// display background color
        label2.setBorder(border);// initiate border
        label2.setBounds(450, 0, 400, 450);// set x,y positon within frame as well as dimensions
        label2.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        label.setMaximumSize(new Dimension(100, 50));
        label.setMinimumSize(new Dimension(100, 50));
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.LEFT);

        String bookName = book.bookName;
        String bookId = book.bookId;
        String edition = book.edition;
        String quantity = Integer.toString(book.quantity);
        String category = book.category;
        String deweyDecimal = Double.toString(book.deweyDecimal);
        String author = book.author;

        label3.setText(bookName);
        Font largerFont = new Font(label.getFont().getName(), Font.PLAIN, 50);
        label3.setFont(largerFont);
        label3.setOpaque(true);
        label3.setSize(10, 10);
        label3.setBounds(0, 610, 275, 50);

        label4.setText("Book ID " + bookId);
        label4.setOpaque(true);
        label4.setBounds(450, 450, 100, 50);
        // label4.setBackground(Color.black);

        label5.setText("Edition " + edition);
        label5.setOpaque(true);
        label5.setBounds(550, 450, 100, 50);

        label6.setText("Quantity " + quantity);
        label6.setOpaque(true);
        label6.setBounds(650, 450, 100, 50);

        label8.setText("Category " + category);
        label8.setOpaque(true);
        label8.setBounds(750, 450, 100, 50);

        label9.setText("dewyDecimal " + deweyDecimal);
        label9.setOpaque(true);
        label9.setBounds(850, 450, 150, 50);

        label10.setText("Author " + author);
        label10.setOpaque(true);
        label10.setBounds(1000, 450, 200, 50);

        add(label);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        add(label10);

        setBackground(new Color(10, 10, 10));
        setLayout(null);
        setBounds(145, 55, 1350, 2000);
        Display.mainPanel.add(this, "book");
        layout.show(Display.mainPanel, "book");
    }
}
