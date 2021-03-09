package booking_app;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Model {

    private static final String USER_KEY = "email";
    private static Map<String, User> userCache = new HashMap<>();

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://www.martinilink.co.uk:3306/doctor_app", "doctor_app", "JNpRFmbXk5WB68SW");
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        // Testing
        System.out.println(Model.getUser("ben@sample.co.uk").testPassword("pass"));
    }


    private static class Visit {
        private User doctor;
        private String visitNotes;
        private Timestamp timestamp;
        private User patient;
        private String prescriptionName;
        private int prescriptionQuantity;

        public Visit(User doctor, String visitNotes, Timestamp timestamp, User patient, String prescriptionName, int prescriptionQuantity) {

            this.doctor = doctor;
            this.visitNotes = visitNotes;
            this.timestamp = timestamp;
            this.patient = patient;
            this.prescriptionName = prescriptionName;
            this.prescriptionQuantity = prescriptionQuantity;
        }



    }
    public static class Message {
        private int id;
        private User to;
        private User from;
        private String message;
        private Boolean read;

        public Message(int id, User to, User from, String message){
            this.id = id;
            this.to = to;
            this.from = from;
            this.message = message;
            this.read = false;
        }
        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return message;
        }
        public void markRead() throws SQLException {
            try (PreparedStatement stmt = getConn().prepareStatement("UPDATE `messages` SET `read` = ? WHERE `id` = ?;")) {
                stmt.setBoolean(1, true);
                stmt.setInt(2, id);
                stmt.executeUpdate();

            }
            read = true;
        }

    }
    
    public static Set<Visit> getVisits()  throws SQLException {
        try (Statement stmt = getConn().createStatement()){
            ResultSet result = stmt.executeQuery("SELECT * FROM `visitDetails`");
            HashSet<Visit> visits = new HashSet<>();
            while (result.next()) {
                visits.add(new Visit(new User(result.getString("doctor")), result.getString("visitNotes"), result.getTimestamp("timestamp"), new User(result.getString("patientEmail")), result.getString("prescriptionName"), result.getInt("prescriptionQuantity")));
            }
            return visits;
        }

    }

    public static User getUser(String email) throws SQLException, IllegalArgumentException {
        if (userCache.keySet().contains(email)) {
            return userCache.get(email);
        }
        try (PreparedStatement stmt = getConn().prepareStatement("SELECT `email` FROM `users` WHERE `email` = ?")) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            HashSet<User> users = new HashSet<>();
            if (result.next()) {
                User user = new User(result.getString(USER_KEY));
                userCache.put(result.getString(USER_KEY), user);
                return user;
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
        return getUsers(null);
    }

    public static Set<User> getUsers(User.Type type) throws SQLException {
        String query = "SELECT `email` FROM `users`";
        if (type != null) {
            query += " WHERE `type` = ?";
        }
        try (PreparedStatement stmt = getConn().prepareStatement(query)) {
            if (type != null) {
                stmt.setString(1, type.name());
            }
            ResultSet result = stmt.executeQuery();
            HashSet<User> users = new HashSet<>();
            while (result.next()) {
                User user = new User(result.getString(USER_KEY));
                userCache.put(result.getString(USER_KEY), user);
                users.add(user);
            }
            return users;
        }
    }

    public static Set<Booking> getBookings() throws SQLException {
        try (Statement stmt = getConn().createStatement()){
            ResultSet result = stmt.executeQuery("SELECT * FROM `bookings`");
            HashSet<Booking> bookings = new HashSet<>();
            while (result.next()) {
                bookings.add(new Booking(result.getInt("id"), result.getString("doctor"), result.getString("patient"), result.getTimestamp("timestamp").toLocalDateTime()));
            }
            return bookings;
        }
    }

    public static Set<Message> getMessages() throws SQLException{
        return getMessages(null);
    }

    public static Set<Message> getMessages(Boolean read) throws SQLException {
        String query = "SELECT * FROM `messages`";
        if(read != null){
            if(read == true){
                query += " WHERE `read` = 1";
            }
            else{
                query += " WHERE `read` = 0";
            }
        }
        try (PreparedStatement stmt = getConn().prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            HashSet<Message> messages = new HashSet<>();
            while (result.next()){
                messages.add(new Message(result.getInt("id"), new User(result.getString("to")), new User(result.getString("from")), result.getString("message")));
            }
            return messages;
        }

    }

    public static void addVisitDetails(User doctor, String visitNotes, LocalDateTime timestamp, User patient, String prescriptionName, int prescriptionQunatity) throws SQLException {
        try (PreparedStatement stmt = getConn().prepareStatement("INSERT INTO `visitDetails` (`prescriptionnQuantity`, `prescriptionName`, `patientEmail`, `visitNotes`, `doctor`, `visitDate`) VALUES ( ?, ?, ?, ?, ?, ?);")) {
            stmt.setInt(1, prescriptionQunatity);
            stmt.setString(2, prescriptionName);
            stmt.setString(3, patient.getEmail());
            stmt.setString(4, visitNotes);
            stmt.setString(5, doctor.getEmail());
            stmt.setTimestamp(6, Timestamp.valueOf(timestamp));
            stmt.executeUpdate();
        }
    }
    public static void addMessage(User to, User from, String message) throws SQLException {
        try (PreparedStatement stmt = getConn().prepareStatement("INSERT INTO `messages` (`to`, `from`, `message`) VALUES (?, ?, ?);"))  {
            stmt.setString(1, to.getEmail());
            stmt.setString(2, from.getEmail());
            stmt.setString(3, message);
            stmt.executeUpdate();
        }
    }
}
