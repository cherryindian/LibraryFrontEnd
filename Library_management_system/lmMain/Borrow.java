package lmMain;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Borrow {
    public int borrowId;
    public Books book;
    public Users user;
    public String returnDate;
    public String borrowDate;
    public boolean returned;
    public static int borrowCount;
    public static String fullUrl = "http://localhost:8080/library_test/borrow";
    static {
        HttpResponse<String> responsecount;
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest requestcount = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .build();

            responsecount = httpClient.send(requestcount, HttpResponse.BodyHandlers.ofString());
            borrowCount = (((String) responsecount.body()).split("\\},\\{").length);

        } catch (Exception e) {
            System.out.println("Error in getting the count of borrows");
        }
    }

    public Borrow(int id) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String details = response.body();
        List<String> jsonStringList = new ArrayList<>();
        String[] del = details.split("\\},\\{");
        jsonStringList.add(del[id - 1] + "}]");
        List<Map<String, Object>> borrow = new ArrayList<>();

        for (String jsonString : jsonStringList) {
            Map<String, Object> borrowMap = parseJsonToMap(jsonString);
            borrow.add(borrowMap);
        }

        for (Map<String, Object> i : borrow) {
            this.user = new Users((String) i.get("userid"));
            this.book = new Books((String) i.get("bookid"));
            this.borrowId = Integer.parseInt((String) i.get("id"));
            this.returnDate = (String) i.get("returnDate");
            this.borrowDate = (String) i.get("borrowDate");
            this.returned = Boolean.parseBoolean((String) i.get("returned"));
        }
    }

    private Map<String, Object> parseJsonToMap(String jsonString) {
        Pattern pattern = Pattern.compile(
                "\"id\":(\\d+),\"user\":(\\{[^}]+\\}),\"books\":(\\{[^}]+\\}),\"borrowDate\":\"([^\"]+)\",\"returnDate\":\"([^\"]+)\",\"returned\":(false|true)");
        Matcher matcher = pattern.matcher(jsonString);
        Map<String, Object> resultMap = new java.util.HashMap<>();
        while (matcher.find()) {
            String borrowId = matcher.group(1);
            String userJson = matcher.group(2);
            String bookJson = matcher.group(3);
            String borrowDate = matcher.group(4);
            String returnDate = matcher.group(5);
            String returned = matcher.group(6);

            List<String> userjsonStringList = new ArrayList<>();
            userjsonStringList.add(userJson);
            List<Map<String, Object>> users = new ArrayList<>();

            for (String userjsonString : userjsonStringList) {
                Map<String, Object> userMap = Users.parseJsonToMap(userjsonString);
                users.add(userMap);
            }
            for (Map<String, Object> i : users) {
                String value = (String) i.get("userId");
                resultMap.put("userid", value);
            }

            List<String> bookjsonStringList = new ArrayList<>();
            bookjsonStringList.add(bookJson);
            List<Map<String, Object>> books = new ArrayList<>();
            for (String bookjsonString : bookjsonStringList) {
                Map<String, Object> bookMap = Books.parseJsonToMap(bookjsonString);
                books.add(bookMap);
            }

            for (Map<String, Object> i : books) {
                String value = (String) i.get("bookId");
                resultMap.put("bookid", value);
            }
            resultMap.put("id", borrowId);
            resultMap.put("returnDate", returnDate);
            resultMap.put("borrowDate", borrowDate);
            resultMap.put("returned", returned);
        }

        return resultMap;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int targetId = 1;
        Borrow b = new Borrow(targetId);

        System.out.println("Borrow ID: " + b.borrowId);
        System.out.println("User ID: " + b.user.userName);
        System.out.println("Book ID: " + b.book.bookName);
        System.out.println("Borrow Date: " + b.borrowDate);
        System.out.println("Return Date: " + b.returned);

    }
}
