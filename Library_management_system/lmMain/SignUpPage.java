package lmMain;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SignUpPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton signUpButton;

    private JLabel captchaLabel;
    private JTextField captchaField;

    public SignUpPage() {

        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setPreferredSize(new Dimension(700, 500));
        setUndecorated(true);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon(
                "C:\\Users\\srich\\Desktop\\project_newday\\year2\\Library_management_system\\MarsLibLogo.jpeg");
        setContentPane(new JLabel(backgroundImage));

        // Ensure the frame is set to fullscreen
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Increase the size of signUpPanel
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(255, 255, 255, 195)); // Semi-transparent white background
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setPreferredSize(new Dimension(400, 400)); // Adjust the size as needed

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 33));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the labels for Username and Password
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JLabel emailLabel = new JLabel("Email ID");
        JLabel phoneLabel = new JLabel("Phone No.");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        emailField = new JTextField();
        phoneField = new JTextField();

        signUpButton = new JButton("Sign Up");

        usernameField.setMaximumSize(new Dimension(400, 30));
        passwordField.setMaximumSize(new Dimension(400, 30));
        emailField.setMaximumSize(new Dimension(400, 30));
        phoneField.setMaximumSize(new Dimension(400, 30));
        signUpButton.setMaximumSize(new Dimension(400, 40));

        signUpButton.setBackground(new Color(76, 175, 80));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput() && validateCaptcha()) {

                    openLoginPage();
                }
            }
        });

        signUpPanel.add(Box.createVerticalStrut(40));
        signUpPanel.add(titleLabel);
        signUpPanel.add(Box.createVerticalStrut(20));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        usernamePanel.add(Box.createHorizontalStrut(20));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createHorizontalStrut(20));
        usernamePanel.add(usernameField);
        signUpPanel.add(usernamePanel);
        usernamePanel.setBackground(new Color(0, 0, 0, 0));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.add(Box.createHorizontalStrut(20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createHorizontalStrut(20));
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(new Color(0, 0, 0, 0));
        signUpPanel.add(passwordPanel);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.add(Box.createHorizontalStrut(20));
        emailPanel.add(emailLabel);
        emailPanel.add(Box.createHorizontalStrut(20));
        emailPanel.add(emailField);
        emailPanel.setBackground(new Color(0, 0, 0, 0));
        signUpPanel.add(emailPanel);

        // Create the phone number panel
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.X_AXIS));
        phonePanel.add(Box.createHorizontalStrut(20));
        phonePanel.add(phoneLabel);
        phonePanel.add(Box.createHorizontalStrut(20));
        phonePanel.add(phoneField);
        phonePanel.setBackground(new Color(0, 0, 0, 0));
        signUpPanel.add(phonePanel);

        signUpPanel.add(Box.createVerticalStrut(20));
        signUpPanel.add(signUpButton);
        signUpPanel.add(Box.createVerticalStrut(10));

        // Create a left-pointing arrow icon
        Icon leftArrowIcon = createLeftArrowIcon();
        JLabel leftArrowLabel = new JLabel(leftArrowIcon);
        leftArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                goBackToPreviousPage();
            }

            public void mouseEntered(MouseEvent e) {
                leftArrowLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        // Create the captcha label and field
        captchaLabel = new JLabel(generateCaptcha());
        captchaField = new JTextField();
        captchaField.setMaximumSize(new Dimension(100, 30));

        // Add the captcha panel
        JPanel captchaPanel = new JPanel();
        captchaPanel.setLayout(new BoxLayout(captchaPanel, BoxLayout.X_AXIS));
        captchaPanel.setBackground(new Color(255, 255, 255, 0));
        captchaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        captchaPanel.add(captchaLabel);
        captchaPanel.add(Box.createHorizontalStrut(10)); // Add some spacing
        captchaPanel.add(captchaField);
        captchaPanel.add(Box.createHorizontalStrut(10));

        signUpPanel.add(captchaPanel);

        // Add the left-pointing arrow to the leftmost corner of the content pane
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(leftArrowLabel, gbc);

        // Add the login panel to the content pane with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        getContentPane().add(signUpPanel, gbc);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen

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

    private void goBackToPreviousPage() {
        SignUpPage.this.setVisible(false);

        // Assuming the previous page is an instance of the ResponsiveNavbar class
        new First();
    }

    private boolean isValidPhoneNumber(String phone) {
        // Simple phone number validation using a regular expression
        // This example assumes a 10-digit phone number without any formatting
        String phoneRegex = "\\d{10}";
        return phone.matches(phoneRegex);
    }

    private boolean isValidEmail(String email) {
        // Complex email validation using a regular expression based on RFC 5322
        String emailRegex = "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)$";
        return email.matches(emailRegex);
    }

    private boolean validateInput() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        String email = emailField.getText();
        String phone = phoneField.getText();

        // Validation logic (as before)
        // Check if username is empty
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username.");
            return false;
        }

        // Check if password is empty
        if (password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter a password.");
            return false;
        }

        // Check if the username contains only alphanumeric characters
        if (!username.matches("^[a-zA-Z0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Username can only contain letters and numbers.");
            return false;
        }

        // Check if the password is strong (at least 8 characters, with a mix of letters
        // and numbers)
        if (password.length < 8 || !containsLettersAndNumbers(password)) {
            JOptionPane.showMessageDialog(this,
                    "Password must be at least 8 characters and include letters and numbers.");
            return false;
        }
        if (username.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return false;
        }

        // Check if email is valid (you can use a more advanced email validation)
        if (!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return false;
        }

        // Check if phone number is valid (you can use a more advanced phone validation)
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number.");
            return false;
        }
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return false;
        }

        // Check if phone number is valid
        if (!isValidPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number.");
            return false;
        }

        // Additional validations can be added here

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

    private void openLoginPage() {
        SignUpPage.this.setVisible(false);
        System.out.println(new String(passwordField.getPassword()));
        String temp = new String(passwordField.getPassword());

        Users.addUser(usernameField.getText(), phoneField.getText(), emailField.getText(), "USER",
                temp);
        First loginPage = new First();
        loginPage.setVisible(true);
    }

    private String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }

    private boolean validateCaptcha() {
        String enteredCaptcha = captchaField.getText();
        String actualCaptcha = captchaLabel.getText();
        boolean isCaptchaValid = enteredCaptcha.equalsIgnoreCase(actualCaptcha);

        if (!isCaptchaValid) {
            JOptionPane.showMessageDialog(this, "Incorrect captcha. Please try again.");
        }

        return isCaptchaValid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignUpPage().setVisible(true);
            }
        });
    }
}
