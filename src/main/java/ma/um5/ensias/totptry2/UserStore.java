package ma.um5.ensias.totptry2;

import java.util.HashMap;
import java.util.Map;

public class UserStore {

    private static final Map<String, UserData> users = new HashMap<>();

    static {
        users.put("user1", new UserData("user1", "password123", "SECRET1"));
        users.put("user2", new UserData("user2", "mypassword", "SECRET2"));
    }

    // Validate user credentials
    public static boolean validateUser(String username, String password) {
        if (!users.containsKey(username)) {
            return false;
        }
        return users.get(username).password.equals(password);
    }

    // Retrieve secret key
    public static String getSecretKey(String username) {
        if (!users.containsKey(username)) {
            return null;
        }
        return users.get(username).secretKey;
    }

    // Add a new user
    public static void addUser(String username, String password, String secretKey) {
        users.put(username, new UserData(username, password, secretKey));
    }

    // Generate a default secret key
    public static String generateSecretKey() {
        return "SECRET" + System.currentTimeMillis(); // Replace with a more secure generator
    }

    private static class UserData {
        String username;
        String password;
        String secretKey;

        UserData(String username, String password, String secretKey) {
            this.username = username;
            this.password = password;
            this.secretKey = secretKey;
        }
    }
}