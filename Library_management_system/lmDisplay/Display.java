package lmDisplay;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lmPanels.AddBooks;
import lmPanels.AddUsers;
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
    static IssueBooks issue;
    static String Issue = "issue";
    static AddBooks addBook;
    static String addbook = "addbook";
    static AddUsers addUser;
    static String adduser = "adduser";

    public Display() {
        mainPanel.setLayout(new CardLayout());

        sp = new SidePanel();
        tp = new TopPanel();

        home = new Home();
        issue = new IssueBooks();
        addBook = new AddBooks();
        addUser = new AddUsers();

        mainPanel.add(home, Home);
        mainPanel.add(addBook, addbook);
        mainPanel.add(issue, Issue);
        mainPanel.add(addUser, adduser);

        JPanel container = new JPanel(new BorderLayout());
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(tp, BorderLayout.NORTH);
        container.add(sp, BorderLayout.WEST);

        mainFrame.setTitle("Library Management System");
        mainFrame.setSize(2000, 900);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(container);
        mainFrame.setVisible(true);
    }

}
