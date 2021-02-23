package booking_app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class Model {

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

        public Message(int id, User to, User from, String message, Boolean read){
            this.id = id;
            this.to = to;
            this.from = from;
            this.message = message; 
            this.read = read;
        }
        public void markRead(){
            read = true;
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
        try (PreparedStatement stmt = getConn().prepareStatement("SELECT `email` FROM `users` WHERE `email` = ?")) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            HashSet<User> users = new HashSet<>();
            if (result.next()) {
                return new User(result.getString("email"));
            }
            throw new IllegalArgumentException("Username doesn't exist");
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
    public static void addVisitDetails(User doctor, String visitNotes, int timestamp, User patient, String prescriptionName, int prescriptionQunatity) throws SQLException {
        try (PreparedStatement stmt = getConn().prepareStatement("INSERT INTO visitDetails VALUES ( '?', '?', '?', '?', '?', '?');")) {
            stmt.setInt(1, prescriptionQunatity);
            stmt.setString(2, prescriptionName);
            stmt.setString(3, patient.getEmail());
            stmt.setString(4, visitNotes);
            stmt.setString(5, doctor.getEmail());
            stmt.setInt(6, timestamp);
            stmt.executeUpdate();
        }
    }
    public static void addMessage(User to, User from, String message) throws SQLException {
        try (PreparedStatement stmt = getConn().prepareStatement("INSERT INTO `messages` (to, from, message) VALUES ('?', '?', '?');"))  {
            stmt.setString(1, to.getEmail());
            stmt.setString(2, from.getEmail());
            stmt.setString(3, message);
            stmt.executeUpdate();
        }
    }

    

}
