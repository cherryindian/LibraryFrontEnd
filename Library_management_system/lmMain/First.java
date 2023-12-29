package lmMain;

import javax.swing.*;

import lmDisplay.Display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class First extends JFrame {

    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public First() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon(
                "C:\\Users\\srich\\Desktop\\project_newday\\year2\\Library_management_system\\MarsLibLogo.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a left-pointing arrow icon
        Icon leftArrowIcon = createLeftArrowIcon();
        JLabel leftArrowLabel = new JLabel(leftArrowIcon);
        leftArrowLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openSignUpPage();
            }
        });

        // Increase the size of loginPanel
        loginPanel = new JPanel();
        loginPanel.setBackground(new Color(255, 255, 255, 128)); // Semi-transparent white background
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setPreferredSize(new Dimension(400, 300)); // Adjust the size as needed

        // Add the left-pointing arrow to the leftmost corner of loginPanel
        loginPanel.add(leftArrowLabel);
        loginPanel.add(Box.createVerticalStrut(20));

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(titleLabel);

        // Add the labels for Username and Password
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 21));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        usernameField.setMaximumSize(new Dimension(400, 30));
        passwordField.setMaximumSize(new Dimension(400, 30));
        loginButton.setMaximumSize(new Dimension(400, 40));

        loginButton.setBackground(new Color(76, 175, 80));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateLogin()) {
                    openResponsiveNavbar();
                } else {
                    statusLabel.setText("Invalid username or password.");
                }
            }
        });

        loginPanel.add(Box.createVerticalStrut(30));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        usernamePanel.add(Box.createHorizontalStrut(20));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createHorizontalStrut(20));
        usernamePanel.add(usernameField);
        loginPanel.add(usernamePanel);
        usernamePanel.setBackground(new Color(0, 0, 0, 0));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.add(Box.createHorizontalStrut(20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createHorizontalStrut(20));
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(new Color(0, 0, 0, 0));
        loginPanel.add(passwordPanel);

        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(loginButton);

        // Add the left-pointing arrow to the leftmost corner of the content pane
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(leftArrowLabel, gbc);

        // Add the login panel to the content pane with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        getContentPane().add(loginPanel, gbc);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private boolean validateLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        // Validation logic (as before)
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username.");
            return false;
        }

        if (password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter a password.");
            return false;
        }

        if (!username.matches("^[a-zA-Z0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Username can only contain letters and numbers.");
            return false;
        }

        if (password.length < 8 || !containsLettersAndNumbers(password)) {
            JOptionPane.showMessageDialog(this,
                    "Password must be at least 8 characters and include letters and numbers.");
            return false;
        }

        return true;
    }

    private boolean containsLettersAndNumbers(char[] password) {
        boolean hasLetters = false;
        boolean hasNumbers = false;

        for (char c : password) {
            if (Character.isLetter(c)) {
                hasLetters = true;
            } else if (Character.isDigit(c)) {
                hasNumbers = true;
            }

            if (hasLetters && hasNumbers) {
                return true;
            }
        }

        return false;
    }

    private void openResponsiveNavbar() {
        String userName = usernameField.getText();
        String password = new String(passwordField.getPassword());
        int i = 1;
        boolean match = false;
        do {
            Users user = new Users(Integer.toString(i));
            if ((user.userName).equals(userName) && (user.password).equals(password)) {
                new Display(user.userID, user.userRole);
                this.dispose();
                match = true;
            }
            i++;
        } while (i < Users.usercount);
        if (!match) {
            JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
        }
    }

    private void openSignUpPage() {
        // Assuming the signup page is an instance of the SignUpPage class
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setVisible(true);

        // Close the current login frame
        this.dispose();
    }

    private Icon createLeftArrowIcon() {
        int size = 30;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        int[] xPoints = { 5, size - 5, size - 5 }; // Adjusted the xPoints to make it left-pointing
        int[] yPoints = { size / 2, 5, size - 5 };
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.dispose();

        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new First().setVisible(true);
            }
        });
    }
}