package booking_app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.sql.Timestamp;

public class User {
    private String email;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private String address;
    private String gender;
    private Timestamp dateOfBirth;
    private String assignedDoctor;


    public enum Type {
        DOCTOR, PATIENT;
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://www.martinilink.co.uk:3306/doctor_app", "doctor_app", "JNpRFmbXk5WB68SW");
    }

    public User(String email, String firstName, String lastName, String phoneNumber, String address, String gender, Timestamp dateOfBirth, String assignedDoctor) {
        this.email = email;
        this.firstName = firstName;
        this.lastname = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.assignedDoctor = assignedDoctor;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstname() {
        return firstName;
    }
    public String getLastname() {
        return lastname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return email.equals(((User) obj).getEmail());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}