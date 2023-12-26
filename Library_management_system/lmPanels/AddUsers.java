// AddUsers.java
package lmPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.EventObject;

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

import lmMain.Users;

public class AddUsers extends JPanel {
    private JTextField userNameField;
    private JTextField userIdField;
    private JTextField userPhnoField;
    private JTextField userMailField;
    private JLabel userNameLabel;
    private JLabel userIdLabel;
    private JLabel userPhnoLabel;
    private JLabel userMailLabel;
    private JButton addButton;
    private JTable userTable;
    private JScrollPane userTabScrollPane;
    private static int userCount = Users.usercount;

    public AddUsers() {
        TwinklingStarsEffect twinklingStarsEffect = new TwinklingStarsEffect();
        twinklingStarsEffect.setBounds(0, 0, 1350, 1600);
        add(twinklingStarsEffect);
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

        createTable();
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
        add(userTabScrollPane);

        setBounds(155, 55, 2000, 2000);
        setForeground(Color.white);
        setBackground(new Color(0, 0, 0));
        setVisible(true);
    }

    private void updateUserTable() {
        Container parent = userTable.getParent();
        Component[] components = parent.getComponents();
        if (parent != null) {
            parent.remove(components[components.length - 1]);
        }

        createTable();
        parent.add(userTabScrollPane);

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

        ++userCount;

        Users.addUser(
                userNameField.getText(),
                userIdField.getText(),
                Integer.parseInt(userPhnoField.getText()),
                userMailField.getText());

        updateUserTable();

        JOptionPane.showMessageDialog(this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createTable() {
        String column[] = { "User Id", "Name", "Email", "Ph no." };
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            Users user = new Users("AA" + (i + 1 < 10 ? ("0" + (i + 1)) : i + 1));
            ArrayList<String> row = new ArrayList<>();
            row.add(user.userID);
            row.add(user.userName);
            row.add(user.userMail);
            row.add(Integer.toString(user.userPhno));
            rows.add(row);
        }

        String[][] rowsArray = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            rowsArray[i] = rows.get(i).toArray(new String[0]);
        }

        userTable = new JTable(rowsArray, column) {
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

        userTabScrollPane = new JScrollPane(userTable);
        userTable.setBounds(600, 20, 700, 300);
        userTable.setBackground(Color.BLACK);
        userTable.setForeground(Color.WHITE);
        userTable.setRowHeight(userTable.getRowHeight() + 15);
        for (int i = 0; i < column.length; i++) {
            userTable.getColumnModel().getColumn(i).setPreferredWidth(200);
        }

        userTabScrollPane.setBounds(650, 20, 600, 300);
        Font tableFont = new Font("Arial", Font.PLAIN, 18); // Adjust the font size as needed
        userTable.setFont(tableFont);
        JTableHeader header = userTable.getTableHeader();
        header.setFont(tableFont);

    }
}
