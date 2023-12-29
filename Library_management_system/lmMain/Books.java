package lmMain;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Books {
    public String bookName;
    public String bookId;
    public String edition;
    public int quantity;
    public String summary;
    public String category;
    public double deweyDecimal;
    public String author;
    public String imageUrl;
    public HttpResponse<String> response;

    public static int bookCount;

    static String bookUrl = "http://localhost:8080/library_test/books";
    static String addBookString = "http://localhost:8080/library_test/addbook";

    static {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest requestcount = HttpRequest.newBuilder()
                    .uri(URI.create(bookUrl + "/countbooks"))
                    .build();

            HttpResponse<String> responsecount = httpClient.send(requestcount, HttpResponse.BodyHandlers.ofString());
            bookCount = Integer.parseInt(responsecount.body());
        } catch (Exception e) {
            System.out.println("Error in getting the count of books");
        }
    }

    public Books(String book) {
        try {
            String fullUrl;
            Pattern id = Pattern.compile("[A]{2}\\d{2}");
            if (id.matcher(book).matches()) {
                fullUrl = bookUrl + "/" + book;
            } else {
                book = book.replaceAll(" ", "%20");
                fullUrl = bookUrl + "/bookname/" + book;
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String details = response.body();

            List<String> jsonStringList = new ArrayList<>();
            jsonStringList.add(details);
            List<Map<String, Object>> books = new ArrayList<>();
            for (String jsonString : jsonStringList) {
                Map<String, Object> bookMap = parseJsonToMap(jsonString);
                books.add(bookMap);
            }

            for (Map<String, Object> i : books) {
                this.bookName = (String) i.get("bookName");
                this.bookId = (String) i.get("bookId");
                this.summary = (String) i.get("summary");
                this.author = (String) i.get("author");
                this.deweyDecimal = (double) i.get("deweyDecimal");
                this.imageUrl = (String) i.get("imageUrl");
                this.category = (String) i.get("category");
                this.edition = (String) i.get("edition");
                this.quantity = (int) Math.round((double) i.get("quantity"));
            }

        } catch (Exception e) {
            return;
        }
    }

    static Map<String, Object> parseJsonToMap(String jsonString) {
        Pattern pattern = Pattern.compile("\"(\\w+)\":\"([^\"]+)\"|\"(\\w+)\":(\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(jsonString);

        Map<String, Object> resultMap = new java.util.HashMap<>();

        while (matcher.find()) {
            if (matcher.group(2) != null) {
                String key = matcher.group(1);
                String value = matcher.group(2);
                resultMap.put(key, value);
            } else if (matcher.group(4) != null) {
                String key = matcher.group(3);
                double value = Double.parseDouble(matcher.group(4));
                resultMap.put(key, value);
            }
        }

        return resultMap;
    }

    public static void addBook(String bookName, String bookId, String edition, int quantity, String summary,
            String category, double deweyDecimal, String author, String Image) {

        HttpClient httpClient = HttpClient.newHttpClient();

        String jsonBody = "{"
                + "\"bookName\":\"" + bookName + "\","
                + "\"bookId\":\"" + bookId + "\","
                + "\"edition\":\"" + edition + "\","
                + "\"quantity\":" + Integer.toString(quantity) + ","
                + "\"summary\":\"" + summary + "\","
                + "\"imageUrl\":\"" + Image + "\","
                + "\"category\":\"" + category + "\","
                + "\"deweyDecimal\":" + deweyDecimal + ","
                + "\"author\":\"" + author + "\""
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(addBookString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ++bookCount;
        } catch (Exception e) {
            System.out.println("Error in add");
        }

    }

    public static void updateBook(String bookName, String bookId, String edition, int quantity, String summary,
            String category, double deweyDecimal, String author, String Image) {

        HttpClient httpClient = HttpClient.newHttpClient();

        String jsonBody = "{"
                + "\"bookName\":\"" + bookName + "\","
                + "\"bookId\":\"" + bookId + "\","
                + "\"edition\":\"" + edition + "\","
                + "\"quantity\":" + Integer.toString(quantity) + ","
                + "\"summary\":\"" + summary + "\","
                + "\"imageUrl\":\"" + Image + "\","
                + "\"category\":\"" + category + "\","
                + "\"deweyDecimal\":" + deweyDecimal + ","
                + "\"author\":\"" + author + "\""
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(addBookString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.out.println("Error in update");
        }

    }
}
