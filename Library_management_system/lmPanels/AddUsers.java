// AddUsers.java
package lmPanels;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lmMain.Users;

public class AddUsers extends JPanel {
    JTextField userNameField;
    JTextField userIdField;
    JTextField userPhnoField;
    JTextField userMailField;
    JLabel userNameLabel;
    JLabel userIdLabel;
    JLabel userPhnoLabel;
    JLabel userMailLabel;
    JButton addButton;

    public AddUsers() {
        userNameField = new JTextField();
        userNameField.setBounds(250, 20, 200, 30);
        userIdField = new JTextField();
        userIdField.setBounds(250, 60, 200, 30);
        userPhnoField = new JTextField();
        userPhnoField.setBounds(250, 100, 200, 30);
        userMailField = new JTextField();
        userMailField.setBounds(250, 140, 200, 30);

        userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(50, 20, 200, 30);
        userIdLabel = new JLabel("User ID");
        userIdLabel.setBounds(50, 60, 200, 30);
        userPhnoLabel = new JLabel("Phone Number");
        userPhnoLabel.setBounds(50, 100, 200, 30);
        userMailLabel = new JLabel("Email");
        userMailLabel.setBounds(50, 140, 200, 30);

        addButton = new JButton("Add User");
        addButton.setBounds(250, 180, 200, 30);
        addButton.addActionListener(e -> addUser());

        setLayout(null);

        add(userNameField);
        add(userIdField);
        add(userPhnoField);
        add(userMailField);
        add(userNameLabel);
        add(userIdLabel);
        add(userPhnoLabel);
        add(userMailLabel);
        add(addButton);

        setBounds(155, 55, 2000, 2000);
        setForeground(Color.white);
        setBackground(new Color(0, 0, 0));
        setVisible(true);
    }

    private void addUser() {
        if (userNameField.getText().length() == 0 ||
                userIdField.getText().length() == 0 ||
                userPhnoField.getText().length() == 0 ||
                userMailField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Users.isValidEmail(userMailField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Users.addUser(
                userNameField.getText(),
                userIdField.getText(),
                Integer.parseInt((String) userPhnoField.getText()),
                userMailField.getText());

        JOptionPane.showMessageDialog(this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
