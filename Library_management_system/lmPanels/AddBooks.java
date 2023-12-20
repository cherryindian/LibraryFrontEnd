package lmPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lmDisplay.Display;
import lmMain.Books;

public class AddBooks extends JPanel {
    JPanel addPanel = new JPanel();
    JTextField bookNameField;
    JTextField bookIdField;
    JTextField editionField;
    JTextField quantityField;
    JTextField summaryField;
    JTextField categoryField;
    JTextField deweyDecimalField;
    JTextField authorField;
    JTextField ImageField;
    JLabel bookNameLabel;
    JLabel bookIdLabel;
    JLabel editionLabel;
    JLabel quantityLabel;
    JLabel summaryLabel;
    JLabel categoryLabel;
    JLabel deweyDecimalLabel;
    JLabel authorLabel;
    JLabel ImageLabel;
    JButton addButton;

    private static final int SUMMARY_LIMIT = 215;

    public AddBooks() {
        bookNameField = new JTextField();
        bookNameField.setBounds(250, 20, 200, 30);
        bookIdField = new JTextField();
        bookIdField.setBounds(250, 60, 200, 30);
        editionField = new JTextField();
        editionField.setBounds(250, 100, 200, 30);
        quantityField = new JTextField();
        quantityField.setBounds(250, 140, 200, 30);
        summaryField = new JTextField();
        summaryField.setBounds(250, 180, 200, 30);
        categoryField = new JTextField();
        categoryField.setBounds(250, 220, 200, 30);
        deweyDecimalField = new JTextField();
        deweyDecimalField.setBounds(250, 260, 200, 30);
        authorField = new JTextField();
        authorField.setBounds(250, 300, 200, 30);
        ImageField = new JTextField();
        ImageField.setBounds(250, 340, 200, 30);

        bookNameLabel = new JLabel("Book Name");
        bookNameLabel.setBounds(50, 20, 200, 30);
        bookIdLabel = new JLabel("Book ID");
        bookIdLabel.setBounds(50, 60, 200, 30);
        editionLabel = new JLabel("Edition");
        editionLabel.setBounds(50, 100, 200, 30);
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(50, 140, 200, 30);
        summaryLabel = new JLabel("Summary (max " + SUMMARY_LIMIT + " characters)");
        summaryLabel.setBounds(50, 180, 200, 30);
        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(50, 220, 200, 30);
        deweyDecimalLabel = new JLabel("Dewey Decimal");
        deweyDecimalLabel.setBounds(50, 260, 200, 30);
        authorLabel = new JLabel("Author");
        authorLabel.setBounds(50, 300, 200, 30);
        ImageLabel = new JLabel("Image Link");
        ImageLabel.setBounds(50, 340, 200, 30);

        addButton = new JButton("Add Book");
        addButton.setBounds(250, 380, 200, 30);
        addButton.addActionListener(e -> addBook());

        setLayout(null);

        add(bookNameField);
        add(bookIdField);
        add(editionField);
        add(quantityField);
        add(summaryField);
        add(categoryField);
        add(deweyDecimalField);
        add(authorField);
        add(ImageField);
        add(bookNameLabel);
        add(bookIdLabel);
        add(editionLabel);
        add(quantityLabel);
        add(summaryLabel);
        add(categoryLabel);
        add(deweyDecimalLabel);
        add(authorLabel);
        add(ImageLabel);
        add(addButton);

        setBounds(155, 55, 2000, 2000);
        setForeground(Color.white);
        setBackground(new Color(0, 0, 0));
        setVisible(true);
    }

    private void addBook() {

        if (bookNameField.getText().length() == 0 ||
                bookIdField.getText().length() == 0 ||
                editionField.getText().length() == 0 ||
                quantityField.getText().length() == 0 ||
                summaryField.getText().length() == 0 ||
                categoryField.getText().length() == 0 ||
                deweyDecimalField.getText().length() == 0 ||
                authorField.getText().length() == 0 ||
                ImageField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (summaryField.getText().length() > SUMMARY_LIMIT) {
            JOptionPane.showMessageDialog(this, "Summary exceeds the character limit.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Books.addBook(
                bookNameField.getText(),
                bookIdField.getText(),
                editionField.getText(),
                Integer.parseInt(quantityField.getText()),
                summaryField.getText(),
                categoryField.getText(),
                Double.parseDouble(deweyDecimalField.getText()),
                authorField.getText(),
                ImageField.getText());

        ImageIcon icon;
        try {
            URL imageURL = new URL(ImageField.getText());
            icon = new ImageIcon(imageURL);
            icon.setImage(icon.getImage().getScaledInstance(200, 310, Image.SCALE_SMOOTH));
        } catch (MalformedURLException e) {
            icon = new ImageIcon("C:\\Users\\srich\\Desktop\\rose.png");
        }

        int col = Books.bookCount % 5;
        int row = Books.bookCount / 5;

        JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        JButton subPanel = Home.createStyledSubPanel(bookNameField.getText(), Books.bookCount, icon.getImage());
        subPanel.setBounds(col * 230 + 100, row * 380 + 100, 200, 300);
        JLabel numberLabel = new JLabel(String.valueOf(Books.bookCount));
        numberLabel.setBounds(col * 230 + 85, row * 380 + 100, 20, 30);
        numberLabel.setForeground(Color.white);

        JLabel bookName = new JLabel("<html><body style='width: 150px'>" + bookNameField.getText() + "</body></html>");

        bookName.setFont(new Font("Times New Roman", Font.BOLD, 16));
        bookName.setForeground(Color.WHITE);
        bookName.setBounds(subPanel.getX(), subPanel.getY() + subPanel.getHeight(), 150, 100);

        bookName.setToolTipText(bookNameField.getText());

        bookName.setPreferredSize(new Dimension(150, 50));
        bookName.setHorizontalAlignment(SwingConstants.CENTER);

        Display.home.add(numberLabel);
        Display.home.add(subPanel);
        Display.home.add(bookName);
    }
}
