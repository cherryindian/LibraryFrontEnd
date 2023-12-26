package lmPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import lmDisplay.Display;
import lmMain.Books;
import lmMain.Borrow;
import lmMain.Users;

public class IssueBooks extends JPanel {
    static JTextField UserID;
    static JTextField BookID;
    static JTextField IssueDate;
    static JTextField ReturnDate;
    JLabel UserIDl;
    JLabel BookIDl;
    JLabel IssueDatel;
    JLabel ReturnDatel;
    JButton borrowBook;
    private JTable issueBookTable;
    private JScrollPane issueBookScrollPane;

    static String borrowBookString = "http://localhost:8080/library_test/addborrow";

    public IssueBooks() {
        TwinklingStarsEffect twinklingStarsEffect = new TwinklingStarsEffect();
        twinklingStarsEffect.setBounds(0, 0, 1350, 1600);
        add(twinklingStarsEffect);

        UserID = new JTextField();
        UserID.setBounds(150, 20, 200, 30);
        BookID = new JTextField();
        BookID.setBounds(150, 60, 200, 30);
        IssueDate = new JTextField();
        IssueDate.setBounds(150, 100, 200, 30);
        ReturnDate = new JTextField();
        ReturnDate.setBounds(150, 140, 200, 30);

        UserIDl = new JLabel("UserID");
        UserIDl.setBounds(50, 20, 200, 30);
        BookIDl = new JLabel("Book ID");
        BookIDl.setBounds(50, 60, 200, 30);
        IssueDatel = new JLabel("Issue Date");
        IssueDatel.setBounds(50, 100, 200, 30);
        ReturnDatel = new JLabel("Return Date");
        ReturnDatel.setBounds(50, 140, 200, 30);

        borrowBook = new JButton("Borrow Book");
        borrowBook.setBounds(250, 380, 200, 30);
        borrowBook.addActionListener(e -> borrowBook());

        try {
            issueBookTable();
        } catch (Exception e) {
            System.out.println("Error in issue books table.");
        }

        setOpaque(false);
        setLayout(null);
        add(UserID);
        add(BookID);
        add(IssueDate);
        add(ReturnDate);
        add(UserIDl);
        add(BookIDl);
        add(IssueDatel);
        add(ReturnDatel);
        add(borrowBook);

        setBounds(155, 55, 2000, 2000);
        setBackground(new Color(255, 0, 0));
        setVisible(true);
    }

    public void borrowBook() {
        String userId = UserID.getText();
        String bookID = BookID.getText();
        String issueDate = checkAndPrintFormattedDate(IssueDate.getText());
        String returnDate = checkAndPrintFormattedDate(ReturnDate.getText());
        HttpClient httpClient = HttpClient.newHttpClient();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date parsedReturnDate;
        Date parsedIssueDate;
        try {
            parsedReturnDate = dateFormat.parse(returnDate);
            parsedIssueDate = dateFormat.parse(issueDate);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(Display.mainPanel, "Error parsing return date", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (issueDate == "ERROR" || returnDate == "ERROR" || parsedReturnDate.before(currentDate)
                || parsedIssueDate.before(parsedReturnDate)) {
            JOptionPane.showMessageDialog(Display.mainPanel, "Enter correct date", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Books b = new Books(bookID);
        if (b.bookId == null) {
            JOptionPane.showMessageDialog(Display.mainPanel, "Enter correct book name", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Users u = new Users(userId);
        if (u.userID == null) {
            JOptionPane.showMessageDialog(Display.mainPanel, "Enter correct User ID", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String jsonBody = "{"
                + "\"id\":" + (++Borrow.borrowCount) + ","
                + "\"user\": {"
                + "\"userName\":\"" + u.userName + "\","
                + "\"userId\":\"" + u.userID + "\","
                + "\"userPhno\":" + u.userPhno + ","
                + "\"userMail\":\"" + u.userMail
                + "\"},"
                + "\"books\": {"
                + "\"bookName\":\"" + b.bookName + "\","
                + "\"edition\":\"" + b.edition + "\","
                + "\"category\":\"" + b.category + "\","
                + "\"imageUrl\":\"" + b.imageUrl + "\","
                + "\"bookId\":\"" + b.bookId + "\","
                + "\"deweyDecimal\":" + b.deweyDecimal + ","
                + "\"quantity\":" + b.quantity + ","
                + "\"summary\":\"" + b.summary + "\","
                + "\"author\":\"" + b.author
                + "\"},"
                + "\"borrowDate\":\"" + issueDate + "\","
                + "\"returnDate\":\"" + returnDate + "\","
                + "\"isreturned\": false"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(borrowBookString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            updateIssueBookTable();
            JOptionPane.showMessageDialog(Display.mainPanel, "Borrow successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println("Error in borrow");
        }
    }

    private static String checkAndPrintFormattedDate(String inputDate) {
        try {
            String formattedDate = formatDateString(inputDate);
            return (String) formattedDate;
        } catch (ParseException e) {
            System.out.println("Invalid date");
            return "ERROR";
        }
    }

    private static String formatDateString(String inputDate) throws ParseException {
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat inputFormat2 = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date parsedDate = inputFormat1.parse(inputDate);
            return formatDate(parsedDate);
        } catch (ParseException e1) {
            Date parsedDate = inputFormat2.parse(inputDate);
            return formatDate(parsedDate);
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(date);
    }

    private void issueBookTable() throws IOException, InterruptedException {
        String[] columns = { "Borrow ID", "User ID", "User Name", "Book ID", "Book Name", "Issue Date", "Return Date",
                "Fine" };

        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        for (int i = 1; i <= Borrow.borrowCount; i++) {
            Borrow borrow = new Borrow(i);

            ArrayList<String> row = new ArrayList<>();
            row.add(String.valueOf(borrow.borrowId));
            row.add(borrow.user.userID);
            row.add(borrow.user.userName);
            row.add(borrow.book.bookId);
            row.add(borrow.book.bookName);
            row.add(borrow.borrowDate);
            row.add(borrow.returnDate);

            try {
                Date returnDate = dateFormat.parse(borrow.returnDate);
                if (returnDate.before(currentDate) && !borrow.returned) {
                    long daysOverdue = TimeUnit.DAYS.convert(currentDate.getTime() - returnDate.getTime(),
                            TimeUnit.MILLISECONDS);
                    int fine = (int) (daysOverdue * 5);
                    row.add(String.valueOf(fine));
                } else {
                    row.add("0");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            rows.add(row);
        }

        String[][] rowsArray = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            rowsArray[i] = rows.get(i).toArray(new String[0]);
        }

        issueBookTable = new JTable(rowsArray, columns) {
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
        issueBookScrollPane = new JScrollPane(issueBookTable);

        issueBookTable.setBounds(200, 20, 700, 300);
        issueBookTable.setBackground(Color.WHITE);
        issueBookTable.setForeground(Color.BLACK);
        issueBookTable.setRowHeight(issueBookTable.getRowHeight() + 15);
        for (int i = 0; i < columns.length; i++) {
            issueBookTable.getColumnModel().getColumn(i).setPreferredWidth(200);
        }

        issueBookScrollPane.setBounds(450, 20, 800, 300);
        Font tableFont = new Font("Arial", Font.PLAIN, 18);
        issueBookTable.setFont(tableFont);
        JTableHeader header = issueBookTable.getTableHeader();
        header.setFont(tableFont);
        add(issueBookScrollPane);

    }

    private void updateIssueBookTable() {
        Container parent = issueBookTable.getParent();

        if (parent != null) {
            Component[] components = parent.getComponents();

            if (components.length > 0) {
                parent.remove(components[components.length - 1]);
            }

            try {
                issueBookTable();
            } catch (Exception e) {
                System.out.println("Error in creating issue table");
            }

            parent.add(issueBookScrollPane);

            parent.revalidate();
            parent.repaint();
        }
    }

}
