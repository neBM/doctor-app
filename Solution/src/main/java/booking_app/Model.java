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
        Set<User> patients = new HashSet<>();
        patients = getPatients("ben@sample.co.uk");
        for (User patient : patients) {
            System.out.println(patient.getEmail());
        }
       
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
            try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("UPDATE `messages` SET `read` = ? WHERE `id` = ?;")) {
                stmt.setBoolean(1, true);
                stmt.setInt(2, id);
                stmt.executeUpdate();

            }
            read = true;
        }

    }
    public static Set<User> getPatients(String doctor)  throws SQLException {
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("SELECT `users`.`email`,`users`.`firstName`,`users`.`lastname`,`users`.`phoneNumber`,`users`.`address`,`users`.`gender`,`users`.`dateOfbirth`, `patientDetails`.`assignedDoctor` FROM `users` LEFT JOIN `patientDetails` ON `users`.`email` = `patientDetails`.`patientEmail` WHERE `patientDetails`.`assignedDoctor` = ?")) {
            stmt.setString(1, doctor);
            ResultSet result = stmt.executeQuery();
            Set<User> patients = new HashSet<>();
            while (result.next()) {
                patients.add(new User(result.getString(USER_KEY), result.getString("firstName"), result.getString("lastName"), result.getString("phoneNumber"), result.getString("address"), result.getString("gender"), result.getTimestamp("dateOfBirth"), result.getString("assignedDoctor")));
               
               
            }
            return patients;
        }
    }
    
    public static Set<Visit> getVisits()  throws SQLException {
        try (Connection conn = getConn(); Statement stmt = conn.createStatement()){
            ResultSet result = stmt.executeQuery("SELECT * FROM `visitDetails`");
            HashSet<Visit> visits = new HashSet<>();
            while (result.next()) {
                visits.add(new Visit(getUser(result.getString("doctor")), result.getString("visitNotes"), result.getTimestamp("timestamp"), getUser(result.getString("patientEmail")), result.getString("prescriptionName"), result.getInt("prescriptionQuantity")));
            }
            return visits;
        }

    }

    public static User getUser(String email) throws SQLException, IllegalArgumentException {
        if (userCache.keySet().contains(email)) {
            return userCache.get(email);
        }
        //try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("SELECT `email` FROM `users` WHERE `email` = ?")) {
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("SELECT `users`.`email`,`users`.`firstName`,`users`.`lastname`,`users`.`phoneNumber`,`users`.`address`,`users`.`gender`,`users`.`dateOfbirth`, `patientDetails`.`assignedDoctor` FROM `users` LEFT JOIN `patientDetails` ON `users`.`email` = `patientDetails`.`patientEmail` WHERE `users`.`email` = ?")) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            HashSet<User> users = new HashSet<>();
            if (result.next()) {
                User user =  new User(result.getString(USER_KEY), result.getString("firstName"), result.getString("lastName"), result.getString("phoneNumber"), result.getString("address"), result.getString("gender"), result.getTimestamp("dateOfBirth"), result.getString("assignedDoctor"));
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
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO `accessRecords` (`email`, `event`) VALUES (?, ?)")) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, event.name());
            stmt.executeUpdate();
        }
    }

    public static Set<User> getUsers() throws SQLException {
        return getUsers(null);
    }

    public static Set<User> getUsers(User.Type type) throws SQLException {
       // String query = "SELECT `email` FROM `users`";
        String query = "SELECT `users`.`email`,`users`.`firstName`,`users`.`lastname`,`users`.`phoneNumber`,`users`.`address`,`users`.`gender`,`users`.`dateOfbirth`, `patientDetails`.`assignedDoctor` FROM `users`LEFT JOIN `patientDetails` ON `users`.`email` = `patientDetails`.`patientEmail`";
        if (type != null) {
            query += " WHERE `type` = ?";
        }
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            if (type != null) {
                stmt.setString(1, type.name());
            }
            ResultSet result = stmt.executeQuery();
            HashSet<User> users = new HashSet<>();
            while (result.next()) {
                User user = new User(result.getString(USER_KEY), result.getString("firstName"), result.getString("lastName"), result.getString("phoneNumber"), result.getString("address"), result.getString("gender"), result.getTimestamp("dateOfBirth"), result.getString("assignedDoctor"));
                userCache.put(result.getString(USER_KEY), user);
                users.add(user);
            }
            return users;
        }
    }

    public static Set<Booking> getBookings() throws SQLException {
        try (Connection conn = getConn(); Statement stmt = conn.createStatement()){
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
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            HashSet<Message> messages = new HashSet<>();
            while (result.next()){
                messages.add(new Message(result.getInt("id"), getUser(result.getString("to")), getUser(result.getString("from")), result.getString("message")));
            }
            return messages;
        }

    }

    public static void addVisitDetails(User doctor, String visitNotes, LocalDateTime timestamp, User patient, String prescriptionName, int prescriptionQunatity) throws SQLException {
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO `visitDetails` (`prescriptionQuantity`, `prescriptionName`, `patientEmail`, `visitNotes`, `doctor`, `timestamp`) VALUES ( ?, ?, ?, ?, ?, ?);")) {
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
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO `messages` (`to`, `from`, `message`) VALUES (?, ?, ?);"))  {
            stmt.setString(1, to.getEmail());
            stmt.setString(2, from.getEmail());
            stmt.setString(3, message);
            stmt.executeUpdate();
        }
    }
}
