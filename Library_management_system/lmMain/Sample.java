package lmMain;

import javax.swing.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sample {
    public static void main(String[] args) {
        String details = "[{\"id\":1,\"user\":{\"userName\":\"Sricharan\",\"userId\":\"AA02\",\"userPhno\":3456789,\"userMail\":\"charan@gmail.com\"},\"books\":{\"bookName\":\"The Princess of Mars\",\"edition\":\"2\",\"category\":\"Science\",\"imageUrl\":\"https://upload.wikimedia.org/wikipedia/commons/3/3a/Princess_of_Mars_large.jpg\",\"bookId\":\"AA01\",\"deweyDecimal\":919.9,\"quantity\":6,\"summary\":\"The story is set on Mars, imagined as a dying planet with a harsh desert environment. This vision of Mars was based on the work of the astronomer Percival Lowell..\",\"author\":\"Edgar Rice Burroughs\"},\"borrowDate\":\"2023-12-19\",\"returnDate\":\"2023-12-26\",\"returned\":false},{}]";

        Pattern pattern = Pattern.compile(
                "\"id\":(\\d+),\"user\":(\\{[^}]+\\}),\"books\":(\\{[^}]+\\}),\"borrowDate\":\"([^\"]+)\",\"returnDate\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(details);

        while (matcher.find()) {
            String borrowId = matcher.group(1);
            String userJson = matcher.group(2);
            String bookJson = matcher.group(3);
            String borrowDate = matcher.group(4);
            String returnDate = matcher.group(5);

            // Now you can further process or print the values as needed
            System.out.println("Borrow ID: " + borrowId);
            System.out.println("User JSON: " + userJson);
            System.out.println("Book JSON: " + bookJson);
            System.out.println("Borrow Date: " + borrowDate);
            System.out.println("Return Date: " + returnDate);
        }

    }
}
