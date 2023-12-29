package lmDisplay;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lmMain.First;
import lmPanels.AddBooks;
// import lmPanels.BooksBorrowed;
import lmPanels.Home;
import lmPanels.IssueBooks;

public class Display {
    static JFrame mainFrame = new JFrame();
    static CardLayout layout;
    public static JPanel mainPanel = new JPanel();
    static SidePanel sp;
    static TopPanel tp;
    public static Home home;
    public static String Home = "home";
    // public static BooksBorrowed borrowed;
    // static String borrow = "borrow";
    public static IssueBooks issue;
    static String Issue = "issue";
    static AddBooks addBook;
    static String addbook = "addbook";

    public Display(String userId, String userRole) {
        mainPanel.setLayout(new CardLayout());

        sp = new SidePanel(userRole);
        tp = new TopPanel(userId);

        home = new Home();
        // borrowed = new BooksBorrowed(userId);
        issue = new IssueBooks();
        addBook = new AddBooks();

        mainPanel.add(home, Home);
        // mainPanel.add(borrowed, borrow);
        if (userRole.equals("ADMIN")) {
            mainPanel.add(addBook, addbook);
            mainPanel.add(issue, Issue);
        }
        JPanel container = new JPanel(new BorderLayout());
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(tp, BorderLayout.NORTH);
        container.add(sp, BorderLayout.WEST);

        mainFrame.setTitle("Library Management System");
        mainFrame.setSize(2000, 900);
        mainFrame.setBackground(Color.BLACK);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(container);
        mainFrame.setVisible(true);
    }

    public static void logout() {
        mainFrame.dispose();

        new First().setVisible(true);
    }

}
