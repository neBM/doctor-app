package booking_app;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Booking {
    private final int id;
    private final String doctor;
    private final String patient;
    private final LocalDateTime timestamp;

    public Booking(int id, String doctor, String patient, LocalDateTime timestamp) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.timestamp = timestamp;
    }

    public User getDoctor() throws IllegalArgumentException {
        try {
            return Model.getUser(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getPatient() throws IllegalArgumentException {
        try {
            return Model.getUser(patient);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
