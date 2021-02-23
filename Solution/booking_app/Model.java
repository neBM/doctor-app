package booking_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class Model {

    public static void main(String[] args) throws SQLException {
        // Testing
        Set<User> users = Model.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://www.martinilink.co.uk:3306/doctor_app", "doctor_app", "JNpRFmbXk5WB68SW");
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

    }

}
