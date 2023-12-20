package lmPanels;

import javax.swing.*;
import lmMain.Books;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Home extends JPanel {

    private JScrollBar scrollBar;
    private JTextField searchBar;
    private TwinklingStarsEffect twinklingStarsEffect;
    private Moon moon;

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

        int bookCount = Books.bookCount;
        int columns = 5;

        for (int i = 0; i < bookCount; i++) {
            int row = i / columns;
            int col = i % columns;

            Books book = new Books("AA" + (i + 1 < 10 ? ("0" + (i + 1)) : i + 1));
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

            JLabel numberLabel = new JLabel(String.valueOf(i + 1));
            numberLabel.setBounds(col * 230 + 85, row * 380 + 100, 20, 30);
            numberLabel.setForeground(Color.white);

            JLabel bookName = new JLabel("<html><body style='width: 150px'>" + book.bookName + "</body></html>");

            bookName.setFont(new Font("Times New Roman", Font.BOLD, 16));
            bookName.setForeground(Color.WHITE);
            bookName.setBounds(subPanel.getX(), subPanel.getY() + subPanel.getHeight(), 150, 100);

            bookName.setToolTipText(book.bookName);

            bookName.setPreferredSize(new Dimension(150, 50));
            bookName.setHorizontalAlignment(SwingConstants.CENTER);

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
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
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

    private void filterSubPanels(String searchTerm) {
        Component[] components = getComponents();
        int i = 0, j = 0;
        JButton subPanel = null;
        for (Component component : components) {
            if (component instanceof JButton) {

                subPanel = (JButton) component;
                String buttonText = subPanel.getText();
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
                String nameLabel = bookName.getText();
                if (!isInteger(nameLabel)) {
                    boolean conText = nameLabel.toLowerCase().contains(searchTerm.toLowerCase());
                    bookName.setLocation(subPanel.getX(), subPanel.getY() + subPanel.getHeight());
                    bookName.setVisible(conText);
                }
                JLabel label = (JLabel) component;
                if (isInteger((String) label.getText())) {
                    label.setVisible(true);
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

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Home Panel with Twinkling Stars and Moon");
            frame.setSize(1350, 1600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Home home = new Home();
            frame.add(home);

            frame.setVisible(true);
        });
    }
}
