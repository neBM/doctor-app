package booking_app;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.*;
import java.util.Set;
import java.awt.*;

public class ViewBookingInterface {
    // JFrame Object
    private JFrame frame = new JFrame();

    // JPanels Object
    private JPanel panelMain = new JPanel();
    private JPanel panelTop = new JPanel();
    private JPanel panelEdit = new JPanel();

    // JLabels
    private JLabel label = new JLabel();
    private JLabel labelPatientName = new JLabel();
    private JLabel labelDocName = new JLabel();
    private JLabel labelDateTime = new JLabel();
    private JLabel labelVisitNotes = new JLabel();
    private JLabel labelPreName = new JLabel();
    private JLabel labelPreQuant = new JLabel();
    private JLabel labelAssignedPat = new JLabel();
    private JLabel labelAssignedDoc = new JLabel();
    private JLabel labelExampleFormat = new JLabel();

    // JButtons
    private JButton buttonReturn = new JButton();

    // JTextFields
    private JTextField textYear = new JTextField();
    private JTextField textMonth = new JTextField();
    private JTextField textDay = new JTextField();
    private JTextField textHour = new JTextField();
    private JTextField textMin = new JTextField();
    private JTextField textVisitNotes = new JTextField();
    private JTextField textPreName = new JTextField();
    private JTextField textPreQuant = new JTextField();

    // JCheckboxes
    private JComboBox<String> textFieldPatient = new JComboBox<>();
    private JComboBox<String> textDoctor = new JComboBox<>();

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
        frame.add(panelEdit, BorderLayout.CENTER);
        frame.setVisible(true);

        // Panel Information
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBounds(0, 40, 600, 400);

        panelEdit.setBounds(0, 40, 600, 400);
        panelEdit.add(labelPatientName);
        //panelEdit.add(labelAssignedPat);
        panelEdit.add(textFieldPatient);
        panelEdit.add(labelDocName);
        //panelEdit.add(labelAssignedDoc);
        panelEdit.add(textDoctor);
        panelEdit.add(labelDateTime);
        panelEdit.add(textYear);
        panelEdit.add(textMonth);
        panelEdit.add(textDay);
        panelEdit.add(textHour);
        panelEdit.add(textMin);
        panelEdit.add(labelExampleFormat);
        panelEdit.add(labelVisitNotes);
        panelEdit.add(textVisitNotes);
        panelEdit.add(labelPreName);
        panelEdit.add(textPreName);
        panelEdit.add(labelPreQuant);
        panelEdit.add(textPreQuant);
        

        panelTop.setBounds(0, 0, 600, 40);
        panelTop.setBackground(Color.decode("#709ED6"));

        panelMain.setVisible(true);
        panelTop.setVisible(true);
        panelEdit.setVisible(false);

        // ComboBox information

        try {
            for (User user : Model.getUsers(User.Type.PATIENT)) {
                textFieldPatient.addItem(user.getEmail());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        textDoctor.setBounds(20, 80, 250, 25);
        try {
            for (User user : Model.getUsers(User.Type.DOCTOR)) {
                textDoctor.addItem(user.getEmail());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        // Label Information

        labelPatientName.setText("Patients full-name: ");
        labelDocName.setText("Doctors full-name: ");
        labelDateTime.setText("Date and Time of Visit: ");
        labelVisitNotes.setText("Visit Notes: ");
        labelPreName.setText("Prescription Name: ");
        labelPreQuant.setText("Prescription Quantity: ");
        labelExampleFormat.setText("Please Use the Format 'yyyy mm dd hh mm'");

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
                JButton butt = new JButton();
                butt.setText("Edit");
                panelMain.add(msg);
                panelMain.add(butt);
                JButton buttonSubmit = new JButton();
                butt.addActionListener(e -> {
                    panelMain.setVisible(false);
                    panelEdit.setVisible(true);
                    textFieldPatient.setSelectedItem(visits.getPatient().getEmail());
                    textDoctor.setSelectedItem(visits.getDoctor().getEmail());
                    textVisitNotes.setText(visits.getVisitNotes());
                    textPreName.setText(visits.getPrescriptionName());
                    textPreQuant.setText("" + visits.getPrescriptionQuantity());
                    String time = visits.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME);
                    String[] parts = time.split("-");
                    textYear.setText(parts[0]);
                    textMonth.setText(parts[1]);
                    String[] parts2 = parts[2].split("T");
                    textDay.setText(parts2[0]);
                    String[] parts3 = parts2[1].split(":");
                    textHour.setText(parts3[0]);
                    textMin.setText(parts3[1]);
                    buttonSubmit.setText("Submit Changes");
                    panelEdit.add(buttonSubmit);
                });
                
                buttonSubmit.addActionListener(e -> {
                    panelEdit.remove(buttonSubmit);
                    panelMain.setVisible(true);
                    panelEdit.setVisible(false);
                    try {
                        visits.setVisitNotes(textVisitNotes.getText());
                        visits.setPrescriptionName(textPreName.getText());
                        visits.setPrescriptionQuantity(Integer.parseInt(textPreQuant.getText()));
                        visits.setDoctor(Model.getUser(textDoctor.getSelectedItem().toString()));
                        visits.setPatient(Model.getUser(textFieldPatient.getSelectedItem().toString()));
                        int year = Integer.parseInt(textYear.getText());
                        int month = Integer.parseInt(textMonth.getText());
                        int day = Integer.parseInt(textDay.getText());
                        int hour = Integer.parseInt(textHour.getText());
                        int minute = Integer.parseInt(textMin.getText());
                        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
                        visits.setTimestamp(date);
                        Model.addMessage(Model.getUser(visits.getPatient().getEmail()), Model.getUser(visits.getDoctor().getEmail()), String.format("Visit details Changed for visit on %d %d %d at %d %d", year, month, day, hour, minute));
                    } catch (Exception e1) {
                        
                    }
                });
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
