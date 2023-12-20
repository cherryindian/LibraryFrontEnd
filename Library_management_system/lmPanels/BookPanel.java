package lmPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BookPanel implements ActionListener {
    public static void main(String[] args) {
        // Bordered layout
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton button = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JLabel label2 = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.black, 3);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon("shopping_cart.jpg");
        ImageIcon icon2 = new ImageIcon("back_arrow.jpeg");
        ImageIcon icon3 = new ImageIcon("Add_to_library.jpg");
        ImageIcon icon4 = new ImageIcon("cross_emoji.jpg");
        ImageIcon image_pnl = new ImageIcon("book_front_page.jpeg");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel1.setLayout(new BorderLayout());
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.blue);

        panel1.setPreferredSize(new Dimension(100, 100));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(100, 100));
        panel4.setPreferredSize(new Dimension(100, 100));
        panel5.setPreferredSize(new Dimension(100, 100));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

        button.addActionListener(e -> System.out.println("Added"));
        button.setBounds(2000, 0, 50, 50);
        button.setText("Add to cart");
        button.setFocusable(false);
        button.setIcon(icon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        // button.setFont(new Font("Comic Sans", Font.BOLD,25));
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEtchedBorder());
        // button.setEnabled(false); This disables a button
        // label.setOpaque(true);

        button.addActionListener(e -> System.out.println("Added"));
        button.setText("Back");
        button.setFocusable(false);
        button.setIcon(icon2);
        button.setHorizontalTextPosition(JButton.LEFT);
        // button.setVerticalTextPosition(JButton.BOTTOM);
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEtchedBorder());
        // button.setEnabled(false); This disables a button

        button.addActionListener(e -> System.out.println("Added"));
        // button.setText("Add to cart");
        button.setFocusable(false);
        button.setIcon(icon3);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        // button.setFont(new Font("Comic Sans", Font.BOLD,25));
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEtchedBorder());

        button.addActionListener(e -> System.out.println("Added"));
        // button.setText("Add to cart");
        button.setFocusable(false);
        button.setIcon(icon4);
        // button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.TOP);
        // button.setFont(new Font("Comic Sans", Font.BOLD,25));
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEtchedBorder());

        panel1.add(button);
        panel1.add(button2);
        panel3.add(button4);

        label.setText("Bro, do you even code.");
        label.setIcon(image_pnl);
        label.setHorizontalTextPosition(JLabel.CENTER);// Changing position of text
        label.setVerticalTextPosition(JLabel.TOP);// Changing position of text
        label.setForeground(new Color(0x00FF00));// Changing word color
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));// Setting Font
        label.setIconTextGap(10);// Set gap of text to image
        label.setBackground(Color.black);// set background color
        label.setOpaque(true);// display background color
        label.setBorder(border);// initiate border
        label.setVerticalAlignment(JLabel.CENTER);// sets the vertical postion of the image and text
        label.setHorizontalAlignment(JLabel.CENTER);// sets the horizontal position of the image and text
        // label.setBounds(0,0,450, 450);//set x,y positon within frame as well as
        // dimensions

        label2.setText(
                "This is a summary sample. The Great Expedition” is a thrilling adventure novel set in the early 20th century");
        label2.setHorizontalTextPosition(JLabel.CENTER);// Changing position of text
        label2.setVerticalTextPosition(JLabel.TOP);// Changing position of text
        label2.setForeground(new Color(0x00FF00));// Changing word color
        label2.setIconTextGap(10);// Set gap of text to image
        label2.setBackground(Color.black);// set background color
        label2.setOpaque(true);// display background color
        label2.setBorder(border);// initiate border
        label2.setVerticalAlignment(JLabel.CENTER);// sets the vertical postion of the image and text
        label2.setHorizontalAlignment(JLabel.RIGHT);// sets the horizontal position of the image and text
        label2.setBounds(1500, 500, 0, 0);// set x,y positon within frame as well as dimensions

        panel5.add(label);
        panel5.add(label2);

    }

    private static LayoutManager set() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}