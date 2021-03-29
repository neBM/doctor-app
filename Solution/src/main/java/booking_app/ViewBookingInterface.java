package booking_app;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.util.Set;
import java.awt.*;

public class ViewBookingInterface {
    // JFrame Object
    private JFrame frame = new JFrame();

    // JPanels Object
    private JPanel panelMain = new JPanel();
    private JPanel panelTop = new JPanel();

    // JLabels
    private JLabel label = new JLabel();

    // JButtons
    private JButton buttonReturn = new JButton();

    private User loggedInUser;

    ViewBookingInterface(User loggedInUser){
        // Frame Information
        this.loggedInUser = loggedInUser;
        frame.setLayout(null);
        frame.setTitle("Past Bookings");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.add(panelTop, BorderLayout.PAGE_START);
        frame.add(panelMain, BorderLayout.CENTER);
        frame.setVisible(true);

        // Panel Information
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBounds(0, 40, 600, 400);

        panelTop.setBounds(0, 0, 600, 40);
        panelTop.setBackground(Color.decode("#709ED6"));

        panelMain.setVisible(true);
        panelTop.setVisible(true);

        // Button Information
        
        buttonReturn.setText("Return to welcome Screen");
        

        int numOfVisits = 0;
        panelTop.add(label);
        label.setText("Total number of past Visits: 0");
        try {
            Set<Model.Visit> setVisits = Model.getVisitDetails(loggedInUser.getEmail());
            for(Model.Visit visits: setVisits){
                numOfVisits++; 
                label.setText("Total number of past Visits: " + numOfVisits);
                JLabel msg = new JLabel();
                msg.setText(numOfVisits + ". Patient: " + visits.getPatient() + ", " + visits.getVisitNotes() + ". " + visits.getPrescriptionName() + ": " + visits.getPrescriptionQuantity() + " at " + visits.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
                panelMain.add(msg);
            }
            panelTop.add(buttonReturn);
            buttonReturn.addActionListener(e -> {
                frame.setVisible(false);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //ViewBookingInterface main = new ViewBookingInterface();
    }
}
