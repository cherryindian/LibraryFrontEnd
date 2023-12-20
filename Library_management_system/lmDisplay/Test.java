package lmDisplay;

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

import lmMain.Books;
import lmMain.Users;

public class Test {
    static String userUrl = "http://localhost:8080/library_test/users";
    static String adduseString = "http://localhost:8080/library_test/adduser";
    static String user = "Sricharan";
    static String details = "";

    public static void main(String[] a) {
        try {
            String fullUrl;
            Pattern id = Pattern.compile("[A-Z]{2}\\d{2}");
            if (id.matcher(user).matches()) {
                fullUrl = userUrl + "/" + user;
            } else {
                fullUrl = userUrl + "/username/" + user;
            }

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            details = response.body();
            List<String> jsonStringList = new ArrayList<>();
            jsonStringList.add(details);
            List<Map<String, Object>> users = new ArrayList<>();

            for (String jsonString : jsonStringList) {
                Map<String, Object> userMap = parseJsonToMap(jsonString);
                users.add(userMap);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        System.out.println(details);

    }

    private static Map<String, Object> parseJsonToMap(String jsonString) {
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
}
