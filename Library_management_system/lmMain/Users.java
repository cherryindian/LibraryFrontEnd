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
    public String userPhno;
    public String userMail;
    public String userRole;
    public String password;

    public static int usercount;

    static String userUrl = "http://localhost:8080/library_test/users";
    static String adduseString = "http://localhost:8080/register";

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
            if (isLong(user)) {
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
            // System.out.println(details);
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
                this.userPhno = (String) i.get("userPhno");
                this.password = (String) i.get("password");
                this.userRole = (String) i.get("userRole");

            }

        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    private boolean isLong(String user) {
        try {
            Long.parseLong(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static Map<String, Object> parseJsonToMap(String jsonString) {
        Pattern pattern = Pattern.compile(
                "\\{\"userName\":\"([^\"]+)\",\"userMail\":\"([^\"]+)\",\"password\":\"([^\"]+)\",\"userRole\":\"([^\"]+)\",\"isenabled\":(true|false),\"userPhno\":(\\d+),\"userId\":(\\d+)\\}");
        Matcher matcher = pattern.matcher(jsonString);

        Map<String, Object> resultMap = new java.util.HashMap<>();

        while (matcher.find()) {
            String userName = matcher.group(1);
            String userMail = matcher.group(2);
            String password = matcher.group(3);
            String userRole = matcher.group(4);
            String userPhno = matcher.group(6);
            String userId = matcher.group(7);

            resultMap.put("userName", userName);
            resultMap.put("userMail", userMail);
            resultMap.put("password", password);
            resultMap.put("userRole", userRole);
            resultMap.put("userPhno", userPhno);
            resultMap.put("userId", userId);
        }

        return resultMap;
    }

    public static void addUser(String userName, String userPhno, String userMail, String userRole,
            String password) {

        if (!isValidEmail(userMail)) {
            System.out.println("Invalid email address.");
            return;
        }

        HttpClient httpClient = HttpClient.newHttpClient();

        String jsonBody = "{"
                + "\"UserName\":\"" + userName + "\","
                + "\"userMail\":\"" + userMail + "\","
                + "\"password\":\"" + password + "\","
                + "\"UserRole\":\"" + userRole + "\","
                + "\"UserPhno\":" + userPhno
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

    public static void verifyUser(String userName, String Password) {
    }

}
