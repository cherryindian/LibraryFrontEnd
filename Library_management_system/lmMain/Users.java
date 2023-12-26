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

public class Users {
    public String userID;
    public String userName;
    public int userPhno;
    public String userMail;

    public static int usercount;

    static String userUrl = "http://localhost:8080/library_test/users";
    static String adduseString = "http://localhost:8080/library_test/adduser";

    static {
        HttpResponse<String> responsecount;
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest requestcount = HttpRequest.newBuilder()
                    .uri(URI.create(userUrl))
                    .build();

            responsecount = httpClient.send(requestcount, HttpResponse.BodyHandlers.ofString());
            usercount = (((String) responsecount.body()).split(",").length) / 4;
        } catch (Exception e) {
            System.out.println("Error in getting the count of users");
        }
    }

    public Users(String user) {
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

            String details = response.body();
            List<String> jsonStringList = new ArrayList<>();
            jsonStringList.add(details);
            List<Map<String, Object>> users = new ArrayList<>();

            for (String jsonString : jsonStringList) {
                Map<String, Object> userMap = parseJsonToMap(jsonString);
                users.add(userMap);
            }

            for (Map<String, Object> i : users) {
                this.userID = (String) i.get("userId");
                this.userMail = (String) i.get("userMail");
                this.userName = (String) i.get("userName");
                this.userPhno = (int) Math.round((double) i.get("userPhno"));
            }

        } catch (Exception e) {
            System.out.println("ERROR");
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

    public static void addUser(String userName, String userID, int userPhno, String userMail) {

        Pattern id = Pattern.compile("[A-Z]{2}\\d{2}");
        if (!(id.matcher(userID).matches())) {
            System.out.println("Invalid user id");
            return;
        }

        if (!isValidEmail(userMail)) {
            System.out.println("Invalid email address.");
            return;
        }

        HttpClient httpClient = HttpClient.newHttpClient();

        String jsonBody = "{"
                + "\"userName\":\"" + userName + "\","
                + "\"userId\":\"" + userID + "\","
                + "\"userPhno\":" + Integer.toString(userPhno) + ","
                + "\"userMail\":\"" + userMail + "\""
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(adduseString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
