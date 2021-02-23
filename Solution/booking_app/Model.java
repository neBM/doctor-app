package booking_app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class Model {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        // Testing
        System.out.println(Model.getUser("ben@sample.co.uk").testPassword("pass"));
        }

    private static class User {
        private String email;
    
        public User(String email) {
            this.email = email;
        }
    
        public String getEmail() {
            return email;
        }
    
        @Override
        public String toString() {
            return email;
        }
    
        public boolean testPassword(String password) throws SQLException, NoSuchAlgorithmException {
            try (PreparedStatement stmt = getConn().prepareStatement("SELECT `password` FROM `users` WHERE `email` = ? LIMIT 1")) {
                stmt.setString(1, email);
                ResultSet result = stmt.executeQuery();
                result.next();
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                
                return result.getString("password").equals(Base64.getEncoder().encodeToString(md.digest(password.getBytes())));
            }
        }
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://www.martinilink.co.uk:3306/doctor_app", "doctor_app", "JNpRFmbXk5WB68SW");
    }

    public static User getUser(String email) throws SQLException, IllegalArgumentException {
        try (PreparedStatement stmt = getConn().prepareStatement("SELECT `email` FROM `users` WHERE `email` = ?")) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new User(result.getString("email"));
            }
            throw new IllegalArgumentException("Username doesn't exist");
        }
    }

    public enum Events {
        VIEW_MESSAGES,
        VIEW_PATIENTS,
        VIEW_BOOKINGS,
        VIEW_VISTS,
        VIEW_NEWVIST,
        VIEW_WELCOMESCREEN;
    }

    public static void logEvent(User user, Events event) throws SQLException {
        try (PreparedStatement stmt = getConn().prepareStatement("INSERT INTO `accessRecords` (`email`, `event`) VALUES (?, ?)")) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, event.name());
            stmt.executeUpdate();
        }
    }
    
    public static Set<User> getUsers() throws SQLException {
        try (Statement stmt = getConn().createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT `email` FROM `users`");
            HashSet<User> users = new HashSet<>();
            while (result.next()) {
                users.add(new User(result.getString("email")));
            }
            return users;
        }
    }

    

}
