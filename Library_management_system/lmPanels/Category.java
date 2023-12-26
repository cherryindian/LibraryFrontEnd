package lmPanels;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmMain.Books;

public interface Category {
    default void CategoryBasedFilter(String cate, JPanel panel) {
        Component[] components = panel.getComponents();
        int i = 0, j = 0;
        JButton subPanel = null;
        if (cate == "home") {
            cate = "";
        }
        for (Component component : components) {
            if (component instanceof JButton) {

                subPanel = (JButton) component;
                String buttonText = subPanel.getText();
                Books temp = new Books(buttonText);
                String tempcate = temp.category;
                if (tempcate == null) {
                    continue;
                }
                boolean containsText = tempcate.toLowerCase().contains(cate.toLowerCase());
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
                    Books temp = new Books(subPanel.getText());
                    String tempcate = temp.category;
                    if (tempcate == null) {
                        continue;
                    }
                    boolean conText = tempcate.toLowerCase().contains(cate.toLowerCase());
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

    boolean isInteger(String nameLabel);

}
