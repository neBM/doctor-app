package booking_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {

    private Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "app", "AZveHRfl5bXKN5sE");
    }

    public ResultSet getUsers() throws SQLException {
        
        try (Statement stmt = getConn().createStatement()) {
            return stmt.executeQuery("SELECT * FROM `users`");
        }
    }

}
