package lmPanels;

import javax.swing.*;
import javax.swing.border.Border;

import lmDisplay.Display;
import lmMain.Books;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Home extends JPanel implements Category {

    private JScrollBar scrollBar;
    private JTextField searchBar;
    private TwinklingStarsEffect twinklingStarsEffect;
    private Moon moon;
    private static int bookCount = Books.bookCount;
    private static int columns = 5;
    private static ArrayList<String> category = new ArrayList<>();;

    public Home() {
        setLayout(null);
        setBounds(145, 55, 1350, 2000);

        twinklingStarsEffect = new TwinklingStarsEffect();
        twinklingStarsEffect.setBounds(0, 0, 1350, 1600);
        add(twinklingStarsEffect);

        scrollBar = new JScrollBar();
        scrollBar.setBounds(1325, 0, 20, 1800);
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                setBounds(0, -scrollBar.getValue() * 10, 1350, 2000);
            }
        });

        searchBar = new JTextField("Search Here");
        searchBar.setBounds(50, 20, 200, 30);
        searchBar.setForeground(Color.GRAY);

        searchBar.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Search Here")) {
                    searchBar.setText("");
                    searchBar.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("Search Here");
                    searchBar.setForeground(Color.GRAY);
                }
            }
        });

        searchBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterSubPanels(searchBar.getText());
            }
        });

        add(scrollBar);
        add(searchBar);

        JButton homecate = createStyledSubPanel("home", 1);
        homecate.setBounds(0, 60, 60, 20);
        homecate.setBorder((Border) new RoundedBorder(10));
        homecate.addActionListener(e -> CategoryBasedFilter("home", Display.home));
        add(homecate);
        category.add("home");

        for (int i = 0; i < bookCount; i++) {
            int row = i / columns;
            int col = i % columns;

            Books book = new Books("AA" + (i + 1 < 10 ? ("0" + (i + 1)) : i + 1));

            if (category.indexOf(book.category) == -1) {
                category.add(book.category);
                JButton catebutton = createStyledSubPanel(book.category, i + 1);
                catebutton.setBounds((i + 8) * 10, 60, 60, 20);
                catebutton.setBorder((Border) new RoundedBorder(10));
                catebutton.addActionListener(e -> CategoryBasedFilter(book.category, Display.home));
                add(catebutton);
            }

            ImageIcon icon;
            try {
                URL imageURL = new URL(book.imageUrl);
                icon = new ImageIcon(imageURL);
                icon.setImage(icon.getImage().getScaledInstance(200, 310, Image.SCALE_SMOOTH));
            } catch (MalformedURLException e) {
                icon = new ImageIcon("C:\\Users\\srich\\Desktop\\rose.png");
            }

            JButton subPanel = createStyledSubPanel(book.bookName, i + 1, icon.getImage());
            subPanel.setBounds(col * 230 + 100, row * 380 + 100, 200, 300);
            subPanel.addActionListener(e -> new BookPanel(book.bookName));

            JLabel numberLabel = new JLabel(String.valueOf(i + 1));
            numberLabel.setBounds(col * 230 + 85, row * 380 + 100, 20, 30);
            numberLabel.setForeground(Color.white);

            JLabel bookName = createBookLabel(book.bookName, subPanel);

            add(numberLabel);
            add(subPanel);
            add(bookName);
        }

        moon = new Moon(100);
        moon.setBounds(1350, 200, 100, 100);
        add(moon);

        setBackground(new Color(0, 0, 0));
        setVisible(true);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveMoon();
                repaint();
            }
        });
        timer.start();
    }

    public static JButton createStyledSubPanel(String panelName, int number, Image iconImage) {
        JButton subPanel = new JButton(panelName);
        subPanel.setLayout(new BorderLayout());
        subPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0),
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red)));

        subPanel.setIcon(new ImageIcon(iconImage));

        subPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                subPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                subPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            }
        });

        return subPanel;
    }

    public static JButton createStyledSubPanel(String panelName, int number) {
        JButton subPanel = new JButton(panelName);
        subPanel.setLayout(new BorderLayout());
        subPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0),
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red)));

        return subPanel;
    }

    public static JLabel createBookLabel(String bookName, JButton subPanel) {
        JLabel bookLabel = new JLabel("<html><body style='width: 150px'>" + bookName + "</body></html>");

        bookLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        bookLabel.setForeground(Color.WHITE);
        bookLabel.setBounds(subPanel.getX(), subPanel.getY() + subPanel.getHeight(), 150, 100);

        bookLabel.setToolTipText(bookName);

        bookLabel.setPreferredSize(new Dimension(150, 50));
        bookLabel.setHorizontalAlignment(SwingConstants.CENTER);

        return bookLabel;
    }

    public static JLabel createBookLabel(String bookName, JLabel subPanel) {
        JLabel bookLabel = new JLabel("<html><body style='width: 10000px'>" + bookName + "</body></html>");

        bookLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        bookLabel.setForeground(Color.WHITE);
        bookLabel.setBounds(subPanel.getX(), subPanel.getY() + subPanel.getHeight(), 150, 100);

        bookLabel.setToolTipText(bookName);

        bookLabel.setPreferredSize(new Dimension(150, 50));
        bookLabel.setHorizontalAlignment(SwingConstants.CENTER);

        return bookLabel;
    }

    private void filterSubPanels(String searchTerm) {
        Component[] components = getComponents();
        int i = 0, j = 0, count = 1;
        JButton subPanel = null;
        for (Component component : components) {
            if (component instanceof JButton) {

                subPanel = (JButton) component;
                String buttonText = subPanel.getText();
                if (category.indexOf(buttonText) != -1) {
                    continue;
                }
                boolean containsText = buttonText.toLowerCase().contains(searchTerm.toLowerCase());
                subPanel.setLocation(i * 230 + 100, j * 380 + 100);
                subPanel.setVisible(containsText);
                if (containsText) {
                    if (i == 4) {
                        i = 0;
                        j++;
                    } else {
                        i++;
                    }
                }
            }

            if (subPanel == null) {
                continue;
            }

            else if (component instanceof JLabel) {
                JLabel bookName = (JLabel) component;
                String nameLabel = subPanel.getText();
                if (!isInteger(nameLabel)) {
                    boolean conText = nameLabel.toLowerCase().contains(searchTerm.toLowerCase());
                    bookName.setLocation(subPanel.getX(), subPanel.getY() + subPanel.getHeight());
                    bookName.setVisible(conText);
                }
                JLabel label = (JLabel) component;
                if (isInteger((String) label.getText())) {
                    if (Integer.parseInt(label.getText()) == count) {
                        label.setLocation(i * 230 + 85, j * 380 + 100);
                        label.setVisible(true);
                        ++count;
                    } else {
                        label.setVisible(false);
                    }
                }
            }
        }
    }

    private void moveMoon() {
        if (moon.getX() + moon.getWidth() > 0) {
            moon.setLocation(moon.getX() - 2, moon.getY() + 2);
        } else {
            moon.setLocation(1350, 200);
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
