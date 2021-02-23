package booking_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Model {

    private Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://www.martinilink.co.uk:3306/doctor_app", "doctor_app", "JNpRFmbXk5WB68SW");
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
