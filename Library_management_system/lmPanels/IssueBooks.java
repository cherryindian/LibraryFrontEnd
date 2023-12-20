package lmPanels;

import java.awt.Color;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lmDisplay.Display;
import lmMain.Books;
import lmMain.Users;

public class IssueBooks extends JPanel {
    static JTextField UserID;
    static JTextField BookName;
    static JTextField IssueDate;
    static JTextField ReturnDate;
    JLabel UserIDl;
    JLabel BookNamel;
    JLabel IssueDatel;
    JLabel ReturnDatel;
    JButton borrowBook;

    static String borrowBookString = "http://localhost:8080/library_test/borrow";

    public IssueBooks() {
        UserID = new JTextField();
        UserID.setBounds(150, 20, 200, 30);
        BookName = new JTextField();
        BookName.setBounds(150, 60, 200, 30);
        IssueDate = new JTextField();
        IssueDate.setBounds(150, 100, 200, 30);
        ReturnDate = new JTextField();
        ReturnDate.setBounds(150, 140, 200, 30);

        UserIDl = new JLabel("UserID");
        UserIDl.setBounds(50, 20, 200, 30);
        BookNamel = new JLabel("Book Name");
        BookNamel.setBounds(50, 60, 200, 30);
        IssueDatel = new JLabel("Issue Date");
        IssueDatel.setBounds(50, 100, 200, 30);
        ReturnDatel = new JLabel("Return Date");
        ReturnDatel.setBounds(50, 140, 200, 30);

        borrowBook = new JButton("Borrow Book");
        borrowBook.setBounds(250, 380, 200, 30);
        borrowBook.addActionListener(e -> borrowBook());

        setOpaque(false);
        setLayout(null);
        add(UserID);
        add(BookName);
        add(IssueDate);
        add(ReturnDate);
        add(UserIDl);
        add(BookNamel);
        add(IssueDatel);
        add(ReturnDatel);
        add(borrowBook);

        setBounds(155, 55, 2000, 2000);
        setBackground(new Color(255, 0, 0));
        setVisible(true);
    }

    public static void borrowBook() {
        String userId = UserID.getText();
        String bookName = BookName.getText();
        String issueDate = checkAndPrintFormattedDate(IssueDate.getText());
        System.out.println(issueDate);
        String returnDate = checkAndPrintFormattedDate(ReturnDate.getText());
        System.out.println(returnDate);
        HttpClient httpClient = HttpClient.newHttpClient();

        if (issueDate == "ERROR" || returnDate == "ERROR") {
            JOptionPane.showMessageDialog(Display.mainPanel, "Enter correct date", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Books b = new Books(bookName);
        if (b.bookName == null) {
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
                + "\"borrowDate\":\"" + issueDate + "\","
                + "\"returnDate\":\"" + returnDate + "\""
                + "\"userId\":\"" + userId + "\","
                + "\"bookId\":\"" + bookName + "\","
                + "}";

        borrowBookString = borrowBookString + "/addborrow/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(borrowBookString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
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
}
