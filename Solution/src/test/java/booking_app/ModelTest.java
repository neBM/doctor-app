package booking_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;

import org.junit.Test;

public class ModelTest {

    @Test
    public void testGetUser() throws IllegalArgumentException, SQLException {
        assertEquals("ben@sample.co.uk", Model.getUser("ben@sample.co.uk").getEmail());
    }

    @Test
    public void testGetMissingUser() {
        assertThrows(IllegalArgumentException.class, () -> {Model.getUser("");});
    }

    @Test
    public void testGetBookings() throws SQLException {
        assertNotNull(Model.getBookings());
    }

    @Test
    public void testGetMessages() throws SQLException {
        assertNotNull(Model.getMessages());
    }

    @Test
    public void testGetReadMessages() throws SQLException {
        assertNotNull(Model.getMessages(true));
    }

    @Test
    public void testGetPatients() throws SQLException {
        assertNotNull(Model.getPatients("ben@sample.co.uk"));
    }

    @Test
    public void testGetVists() throws SQLException {
        assertNotNull(Model.getVisits());
    }

}
