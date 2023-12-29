package lmPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import lmDisplay.Display;
import lmMain.Books;

public class AddBooks extends JPanel {
    private JTextField bookNameField;
    private JTextField bookIdField;
    private JTextField editionField;
    private JTextField quantityField;
    private JTextField summaryField;
    private JTextField categoryField;
    private JTextField deweyDecimalField;
    private JTextField authorField;
    private JTextField ImageField;
    private JLabel bookNameLabel;
    private JLabel bookIdLabel;
    private JLabel editionLabel;
    private JLabel quantityLabel;
    private JLabel summaryLabel;
    private JLabel categoryLabel;
    private JLabel deweyDecimalLabel;
    private JLabel authorLabel;
    private JLabel ImageLabel;
    private JButton addButton;
    private JButton updateButton;
    private JTable booksTable;
    private JScrollPane booksScrollPane;

    private static final int SUMMARY_LIMIT = 215;

    public AddBooks() {

        TwinklingStarsEffect twinklingStarsEffect = new TwinklingStarsEffect();
        twinklingStarsEffect.setBounds(0, 0, 1350, 1600);
        add(twinklingStarsEffect);

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

        Font labelFont = new Font("Arial", Font.BOLD, 15);

        bookNameLabel = new JLabel("Book Name");
        bookNameLabel.setBounds(50, 20, 200, 30);
        bookNameLabel.setForeground(Color.WHITE);
        bookNameLabel.setFont(labelFont);

        bookIdLabel = new JLabel("Book ID");
        bookIdLabel.setBounds(50, 60, 200, 30);
        bookIdLabel.setForeground(Color.WHITE);
        bookIdLabel.setFont(labelFont);

        editionLabel = new JLabel("Edition");
        editionLabel.setBounds(50, 100, 200, 30);
        editionLabel.setForeground(Color.WHITE);
        editionLabel.setFont(labelFont);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(50, 140, 200, 30);
        quantityLabel.setForeground(Color.WHITE);
        quantityLabel.setFont(labelFont);

        summaryLabel = new JLabel("Summary (max " + SUMMARY_LIMIT + " characters)");
        summaryLabel.setBounds(50, 180, 300, 30);
        summaryLabel.setForeground(Color.WHITE);
        summaryLabel.setFont(labelFont);

        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(50, 220, 200, 30);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(labelFont);

        deweyDecimalLabel = new JLabel("Dewey Decimal");
        deweyDecimalLabel.setBounds(50, 260, 200, 30);
        deweyDecimalLabel.setForeground(Color.WHITE);
        deweyDecimalLabel.setFont(labelFont);

        authorLabel = new JLabel("Author");
        authorLabel.setBounds(50, 300, 200, 30);
        authorLabel.setForeground(Color.WHITE);
        authorLabel.setFont(labelFont);

        ImageLabel = new JLabel("Image Link");
        ImageLabel.setBounds(50, 340, 200, 30);
        ImageLabel.setForeground(Color.WHITE);
        ImageLabel.setFont(labelFont);

        addButton = new JButton("Add Book");
        addButton.setBounds(250, 380, 200, 30);
        addButton.addActionListener(e -> addBook());

        updateButton = new JButton("Update Book");
        updateButton.setBounds(250, 420, 200, 30);
        updateButton.addActionListener(e -> updateBook());

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
        add(updateButton);

        createBooksTable();

        setBounds(155, 55, 2000, 2000);
        setForeground(Color.white);
        setBackground(new Color(0, 0, 0));
        setVisible(true);
    }

    private boolean errorHandler() {
        String bookid = bookIdField.getText();
        String edition = editionField.getText();
        String quantity = quantityField.getText();
        String summary = summaryField.getText();
        String dewey = deweyDecimalField.getText();

        Pattern id = Pattern.compile("[A]{2}\\d{2}");
        if (!(id.matcher(bookid).matches())) {
            JOptionPane.showMessageDialog(this, "Book id according to standard", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (Integer.parseInt(edition) < 0) {
            JOptionPane.showMessageDialog(this, "Edition needs to be positive", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (Integer.parseInt(quantity) < 0) {
            JOptionPane.showMessageDialog(this, "Quantity needs to be greater than 0", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (summary.length() > SUMMARY_LIMIT) {
            JOptionPane.showMessageDialog(this, "Summary exceeds the character limit.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

        String regex = "\\d{1,3}(\\.\\d{1,3})?";
        if (!(dewey.matches(regex))) {
            return false;
        }

        return true;
    }

    private void updateBook() {

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

        if (!errorHandler()) {
            return;
        }

        Books.updateBook(
                bookNameField.getText(),
                bookIdField.getText(),
                editionField.getText(),
                Integer.parseInt(quantityField.getText()),
                summaryField.getText(),
                categoryField.getText(),
                Double.parseDouble(deweyDecimalField.getText()),
                authorField.getText(),
                ImageField.getText());

        JOptionPane.showMessageDialog(this, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        updateBooksTable();

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
        Books b = new Books(bookIdField.getText());
        if (b.bookId != null) {
            JOptionPane.showMessageDialog(this, "Book already in list", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!errorHandler()) {
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

        updateBooksTable();

        Display.home.add(numberLabel);
        Display.home.add(subPanel);
        Display.home.add(bookName);
    }

    private void createBooksTable() {
        String[] columns = { "Book ID", "Book Name", "Book Edition", "Book Author", "Book Quantity" };
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        int bookCount = Books.bookCount;

        for (int i = 0; i < bookCount; i++) {
            Books book = new Books("AA" + (i + 1 < 10 ? ("0" + (i + 1)) : i + 1));
            ArrayList<String> row = new ArrayList<>();
            row.add(book.bookId);
            row.add(book.bookName);
            row.add(book.edition);
            row.add(book.author);
            row.add(Integer.toString(book.quantity));
            rows.add(row);
        }

        String[][] rowsArray = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            rowsArray[i] = rows.get(i).toArray(new String[0]);
        }

        booksTable = new JTable(rowsArray, columns) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer();
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                return new TableCellEditor() {
                    @Override
                    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                            int row, int column) {
                        return null;
                    }

                    @Override
                    public Object getCellEditorValue() {
                        return null;
                    }

                    @Override
                    public boolean isCellEditable(EventObject anEvent) {
                        return false;
                    }

                    @Override
                    public boolean shouldSelectCell(EventObject anEvent) {
                        return false;
                    }

                    @Override
                    public boolean stopCellEditing() {
                        return true;
                    }

                    @Override
                    public void cancelCellEditing() {
                    }

                    @Override
                    public void addCellEditorListener(CellEditorListener l) {
                    }

                    @Override
                    public void removeCellEditorListener(CellEditorListener l) {
                    }
                };
            }
        };

        booksTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = booksTable.getSelectedRow();
                if (selectedRow != -1) {
                    fillFieldsFromSelectedRow(selectedRow);
                }
            }
        });

        booksScrollPane = new JScrollPane(booksTable);
        booksTable.setBounds(600, 20, 700, 300);
        booksTable.setBackground(Color.BLACK);
        booksTable.setForeground(Color.WHITE);
        booksTable.setRowHeight(booksTable.getRowHeight() + 15);
        for (int i = 0; i < columns.length; i++) {
            booksTable.getColumnModel().getColumn(i).setPreferredWidth(200);
        }

        booksScrollPane.setBounds(650, 20, 600, 300);
        Font tableFont = new Font("Arial", Font.PLAIN, 18); // Adjust the font size as needed
        booksTable.setFont(tableFont);
        JTableHeader header = booksTable.getTableHeader();
        header.setFont(tableFont);

        add(booksScrollPane);
    }

    private void updateBooksTable() {
        Container parent = booksTable.getParent();

        if (parent != null) {
            Component[] components = parent.getComponents();

            if (components.length > 0) {
                parent.remove(components[components.length - 1]);
            }

            createBooksTable();

            parent.add(booksScrollPane);

            parent.revalidate();
            parent.repaint();
        }
    }

    private void fillFieldsFromSelectedRow(int selectedRow) {
        Books temp = new Books(booksTable.getValueAt(selectedRow, 0).toString());
        bookIdField.setText(temp.bookId);
        bookNameField.setText(temp.bookName);
        editionField.setText(temp.edition);
        authorField.setText(temp.author);
        quantityField.setText(Integer.toString(temp.quantity));
        summaryField.setText(temp.summary);
        categoryField.setText(temp.category);
        deweyDecimalField.setText(Double.toString(temp.deweyDecimal));
        ImageField.setText(temp.imageUrl);
    }

}
