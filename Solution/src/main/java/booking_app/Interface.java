package booking_app;

import javax.swing.*;

//import jdk.tools.jlink.internal.Jlink;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.awt.Font;
import java.util.stream.Collectors;

public class Interface implements ActionListener, KeyListener {
    // Field Variables
    private Font font = new Font("Courier", Font.BOLD,12);
    private User loggedInUser;
    private int numOfMessages = 0;

    // JFrames Objects
    private JFrame frame = new JFrame();
    private JFrame windowError = new JFrame();
    private JFrame newMessages = new JFrame();
    private JFrame summaryInfo = new JFrame();
    private JPanel panelSummary = new JPanel();
    private JPanel panelSummaryTop = new JPanel();
    private JPanel panelTop = new JPanel();
    private JPanel panelMessage = new JPanel();
    private JPanel panelPatientList = new JPanel();
    private JPanel panelBookings = new JPanel();
    private JPanel panelVisit = new JPanel();
    private JPanel panelNewMessage = new JPanel();
    private JPanel panelAddVisit = new JPanel();
    private JPanel panelAddPrescription = new JPanel();
    private JPanel panelMessageTop = new JPanel();
    private JPanel panelViewBooking = new JPanel();

    // JButtons Objects
    private JButton buttonMessage = new JButton();
    private JButton buttonPatientList = new JButton();
    private JButton buttonBookings = new JButton();
    private JButton buttonVisit = new JButton();
    private JButton buttonOk = new JButton();
    private JButton buttonSubmit = new JButton();
    private JButton buttonFilter = new JButton();
    private JButton buttonPreviousBookings = new JButton();
    private JButton buttonAssignDoctor = new JButton();

    // JLabels Objects
    private JLabel labelPatient = new JLabel();
    private JLabel labelDoctor = new JLabel();
    private JLabel labelDate = new JLabel();
    private JLabel labelNotes = new JLabel();
    private JLabel labelPrescriptionName = new JLabel();
    private JLabel labelQuantity = new JLabel();
    private JLabel labelError = new JLabel();
    private JLabel labelExample = new JLabel();
    private JLabel labelNewMessages = new JLabel();
    private JLabel labelNumOfPatients = new JLabel();
    private JLabel labelPatientName = new JLabel();
    private JLabel labelPatientEmail = new JLabel();
    private JLabel labelNumber = new JLabel();
    private JLabel labelAddress = new JLabel();
    private JLabel labelGender = new JLabel();
    private JLabel labelDOB = new JLabel();
    private JLabel labelAssignedDoc = new JLabel();

    // JComboBoxes Objects
    private JComboBox<String> textFieldPatient = new JComboBox<>();
    private JComboBox<String> textDoctor = new JComboBox<>();
    private JComboBox<String> textChangeDoctor = new JComboBox();

    // JTextFields Objects
    private JTextField textFieldYear = new JTextField();
    private JTextField textFieldMonth = new JTextField();
    private JTextField textFieldDay = new JTextField();
    private JTextField textFieldHour = new JTextField();
    private JTextField textFieldMinute = new JTextField();
    private JTextField textFieldVisitNotes = new JTextField();
    private JTextField textFieldPrescription = new JTextField();
    private JTextField textFieldQuantity = new JTextField();

    public Interface(User loggedInuser){
//----------------------------------------------------------------------------------------------------------------------
        // Main Interface
        // Frame Information
        this.loggedInUser = loggedInuser;
        frame.setLayout(null);
        frame.setTitle("Welcome Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 560);
        frame.setResizable(false);
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMessage);
        frame.add(panelPatientList);
        frame.add(panelBookings);
        frame.add(panelVisit);
        frame.setVisible(true);

        // Button Information
        buttonMessage.setText("Messages");
        buttonMessage.setBounds(20, 5, 175, 30);
        buttonMessage.addActionListener(this);
        buttonPatientList.setText("Patient's List");
        buttonPatientList.setBounds(210, 5, 175, 30);
        buttonPatientList.addActionListener(this);
        buttonBookings.setText("Bookings");
        buttonBookings.setBounds(400, 5, 175, 30);
        buttonBookings.addActionListener(this);
        buttonVisit.setText("Add Visit & Prescription");
        buttonVisit.setBounds(590, 5, 175, 30);
        buttonVisit.addActionListener(this);
        buttonSubmit.setText("Submit");
        buttonSubmit.setBounds(290, 400, 150, 30);
        buttonSubmit.addActionListener(this);
//----------------------------------------------------------------------------------------------------------------------
        // Messages
        // New Messages Window Information
        newMessages.setLayout(new BorderLayout());
        newMessages.setTitle("Unread Messages");
        newMessages.setSize(600, 400);
        newMessages.setResizable(false);
        newMessages.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newMessages.add(panelMessageTop, BorderLayout.PAGE_START);
        newMessages.add(panelNewMessage, BorderLayout.CENTER);
        newMessages.setVisible(true);

        panelMessageTop.add(labelNewMessages);
        panelMessageTop.setPreferredSize(new Dimension(600, 40));
        panelMessageTop.setLayout(null);
        panelMessageTop.setBackground(Color.decode("#709ED6"));
        panelMessageTop.setVisible(true);

        //Retrieving number of new messages
        try {
            Set<Model.Message> unread = Model.getMessages(false);
            for(Model.Message message : unread){
                numOfMessages++;
                JLabel msg = new JLabel();
                msg.setText(message.getMessage());
                panelNewMessage.add(msg);
                JButton buttonRead = new JButton();
                buttonRead.setText("Mark as read");
                panelNewMessage.add(buttonRead);
                buttonRead.addActionListener(e -> {
                    try {
                        message.markRead();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                });
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        //New Messages Label
        labelNewMessages.setText(numOfMessages + " Unread Messages");
        labelNewMessages.setBounds(0, 0, 150, 20);

        // TextField Information
        textFieldPatient.setBounds(20, 30, 250, 25);
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

        // Panel Information
        panelTop.add(buttonMessage);
        panelTop.add(buttonPatientList);
        panelTop.add(buttonBookings);
        panelTop.add(buttonVisit);
        panelTop.setBounds(0, 0, 800, 40);
        panelTop.setBackground(Color.decode("#709ED6"));
        panelTop.setLayout(null);
        panelMessage.setVisible(true);
        panelMessage.setBounds(0, 40, 800, 560);
        panelMessage.setLayout(new BoxLayout(panelMessage, BoxLayout.Y_AXIS));
        numOfMessages = 0;
        try {
            Set<Model.Message> unread = Model.getMessages();
            for(Model.Message message: unread){
                numOfMessages++;
                JLabel msg = new JLabel();
                msg.setText(numOfMessages + ". " + message.getMessage());
                panelMessage.add(msg);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        panelPatientList.setVisible(false);
        panelPatientList.setBounds(0, 40, 800, 560);

//----------------------------------------------------------------------------------------------------------------------
        // Add Visit & Prescription
        // Label Information
        labelPatient.setText("Patient's Full-name");
        labelPatient.setBounds(20,10,150,20);
        labelDoctor.setText("Doctor's Full-name");
        labelDoctor.setBounds(20, 60, 150, 20);
        labelDate.setText("Date and Time of Visit");
        labelDate.setBounds(20, 110, 150, 20);
        labelNotes.setText("Visit Notes");
        labelNotes.setBounds(20, 160, 150, 20);
        labelPrescriptionName.setText("Prescription Name");
        labelPrescriptionName.setBounds(20, 210, 150, 20);
        labelQuantity.setText("Prescription Quantity");
        labelQuantity.setBounds(20, 260, 150, 20);

        // TextField Information
        textFieldYear.setBounds(20, 130, 50, 25);
        textFieldYear.setText("yyyy");
        textFieldYear.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMonth.setBounds(80, 130, 35, 25);
        textFieldMonth.setText("mm");
        textFieldMonth.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldDay.setBounds(130, 130, 35, 25);
        textFieldDay.setText("dd");
        textFieldDay.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHour.setBounds(180, 130, 35, 25);
        textFieldHour.setText("hh");
        textFieldHour.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMinute.setBounds(230, 130, 35, 25);
        textFieldMinute.setText("mm");
        textFieldMinute.setHorizontalAlignment(SwingConstants.CENTER);
        labelExample.setBounds(280, 125, 400, 30);
        labelExample.setText("Example: 2003  05  25  17  30");
        labelExample.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        textFieldVisitNotes.setBounds(20, 180, 250, 25);
        textFieldPrescription.setBounds(20, 230, 250, 25);
        textFieldQuantity.setBounds(20, 280, 250, 25);
        textFieldQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldQuantity.addKeyListener(this);

        // Panel Information
        panelAddVisit.setBounds(40, 20, 700, 450);
        panelAddVisit.add(labelPatient);
        panelAddVisit.add(textFieldPatient);
        panelAddVisit.add(labelDoctor);
        panelAddVisit.add(textDoctor);
        panelAddVisit.add(labelDate);
        panelAddVisit.add(textFieldYear);
        panelAddVisit.add(textFieldMonth);
        panelAddVisit.add(textFieldDay);
        panelAddVisit.add(textFieldHour);
        panelAddVisit.add(textFieldMinute);
        panelAddVisit.add(labelNotes);
        panelAddVisit.add(textFieldVisitNotes);
        panelAddVisit.add(buttonSubmit);
        panelAddVisit.add(labelPrescriptionName);
        panelAddVisit.add(textFieldPrescription);
        panelAddVisit.add(labelQuantity);
        panelAddVisit.add(textFieldQuantity);
        panelAddVisit.add(labelExample);
        panelAddVisit.setLayout(null);
//---------------------------------------------------------------------------------------------------------------------
        // Bookings
        // Panels
        panelBookings.setBackground(Color.LIGHT_GRAY);
        panelBookings.setLayout(null);
        panelBookings.setVisible(false);
        panelBookings.setBounds(0, 40, 800, 560);
        panelBookings.add(panelViewBooking);
        panelBookings.add(buttonFilter);
        panelBookings.add(buttonPreviousBookings);
        panelViewBooking.setBounds(40, 20, 700, 400);

        // Labels
        panelVisit.setBackground(Color.LIGHT_GRAY);
        panelVisit.setVisible(false);
        panelVisit.setBounds(0, 40, 800, 560);
        panelVisit.add(panelAddVisit);
        panelVisit.add(panelAddPrescription);
        panelVisit.setLayout(null);

        // Buttons
        buttonPreviousBookings.setText("Past Bookings");
        buttonPreviousBookings.setBounds(50, 430, 150, 35);
        buttonPreviousBookings.addActionListener(this);
        buttonFilter.setText("Filter");
        buttonFilter.setBounds(360, 430, 100, 35);
        buttonFilter.addActionListener(this);
//----------------------------------------------------------------------------------------------------------------------
        // Error Window Information
        // Frame Information
        windowError.setLayout(null);
        windowError.setResizable(false);
        windowError.setTitle("Error Message");
        windowError.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        windowError.setSize(600, 100);

        // Label Information
        labelError.setBounds(30, 10, 600, 20);

        // Button Information
        buttonOk.setText("Ok");
        buttonOk.setBounds(275,32,50, 25);
        buttonOk.addActionListener(this);

        // View Patient Window Information

        panelPatientList.setLayout(new BoxLayout(panelPatientList, BoxLayout.Y_AXIS));
        int numOfPatients = 0;
        panelPatientList.add(labelNumOfPatients);
        try {
            Set<User> setPatients = Model.getPatients(loggedInUser.getEmail());
            for(User patients: setPatients){
                numOfPatients++; 
                labelNumOfPatients.setText("Total number of patients: " + numOfPatients);
                JLabel msg = new JLabel();
                msg.setText(numOfPatients + ". " + patients.getFirstname() + " " + patients.getLastname());
                JButton buttonViewInfo = new JButton();
                buttonViewInfo.setText("View Summary Information");
                buttonAssignDoctor.setText("Assign New Doctor");
                buttonAssignDoctor.addActionListener(this);
                panelPatientList.add(msg);
                panelPatientList.add(buttonViewInfo);
                buttonViewInfo.addActionListener(e -> {
                    summaryInfo.setVisible(true);
                    labelPatientName.setText(patients.getFirstname() + " " + patients.getLastname());
                    labelNumber.setText(patients.getPhoneNumber());
                    labelAddress.setText(patients.getAddress());
                    labelGender.setText(patients.getGender());
                    labelDOB.setText("" + patients.getDateOfBirth());
                    try {
                        labelAssignedDoc.setText(patients.getAssignedDoctor().getEmail());
                    } catch (IllegalArgumentException | SQLException e1) {
                        e1.printStackTrace();
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // View Summary Information 

        summaryInfo.setLayout(new BorderLayout());
        summaryInfo.setTitle("Patients Summary Information");
        summaryInfo.setSize(600, 400);
        summaryInfo.setResizable(false);
        summaryInfo.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        summaryInfo.add(panelSummaryTop, BorderLayout.PAGE_START);
        summaryInfo.add(panelSummary, BorderLayout.CENTER);

        panelSummaryTop.setPreferredSize(new Dimension(600, 40));
        panelSummaryTop.setLayout(null);
        panelSummaryTop.setBackground(Color.decode("#709ED6"));
        panelSummaryTop.setVisible(true);

        
        panelSummary.add(labelPatientName);
        panelSummary.setLayout(new BoxLayout(panelSummary, BoxLayout.Y_AXIS));
        panelSummary.add(labelNumber);
        panelSummary.add(labelAddress);
        panelSummary.add(labelGender);
        panelSummary.add(labelDOB);
        panelSummary.add(labelAssignedDoc);
    }   

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == buttonPreviousBookings){
            new ViewBookingInterface(loggedInUser);
        }
        if(e.getSource() == buttonMessage){
            panelMessage.setVisible(true);
            panelPatientList.setVisible(false);
            panelBookings.setVisible(false);
            panelVisit.setVisible(false);
        }

        if(e.getSource() == buttonPatientList){
            panelMessage.setVisible(false);
            panelPatientList.setVisible(true);
            panelBookings.setVisible(false);
            panelVisit.setVisible(false);
        }

        if(e.getSource() == buttonBookings){
            panelMessage.setVisible(false);
            panelPatientList.setVisible(false);
            panelBookings.setVisible(true);
            panelVisit.setVisible(false);
        }

        if(e.getSource() == buttonFilter){
            BookingInterface bookingInterface = new BookingInterface(this);
        }

        if(e.getSource() == buttonVisit){
            panelMessage.setVisible(false);
            panelPatientList.setVisible(false);
            panelBookings.setVisible(false);
            panelVisit.setVisible(true);
        }

        if(e.getSource() == buttonOk){
            windowError.dispose();
        }
        

        if(e.getSource() == buttonSubmit){
            String patientName = textFieldPatient.getSelectedItem().toString();
            String doctorName = textDoctor.getSelectedItem().toString();
            int year = Integer.parseInt(textFieldYear.getText());
            int month = Integer.parseInt(textFieldMonth.getText());
            int day = Integer.parseInt(textFieldDay.getText());
            int hour = Integer.parseInt(textFieldHour.getText());
            int minute = Integer.parseInt(textFieldMinute.getText());
            LocalDateTime date;

            String visitNote = textFieldVisitNotes.getText();
            String prescription = textFieldPrescription.getText();
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            try {
                Model.addMessage(Model.getUser(patientName), Model.getUser(doctorName), String.format("Visit confirmation on %d %d %d at %d %d", year, month, day, hour, minute));
                date = LocalDateTime.of(year, month, day, hour, minute);
                Model.addVisitDetails(Model.getUser(doctorName), visitNote, date, Model.getUser(patientName), prescription, quantity);
            }

            catch(java.time.DateTimeException | java.sql.DataTruncation | java.sql.SQLIntegrityConstraintViolationException err){
                windowError.setVisible(true);
                labelError.setText(err.getMessage());
                labelError.setBounds(30, 10, 600, 20);
                windowError.add(buttonOk);
                windowError.add(labelError);
                err.printStackTrace();
            }

           catch (SQLException throwables) {
                throwables.printStackTrace();
           }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (!(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
            textFieldQuantity.setText("");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    protected void updateInterface(Set<Booking> bookings){
        panelViewBooking.removeAll();
        if(bookings.size() <= 0){
            JLabel error = new JLabel();
            error.setText("No Bookings Found!");
            panelViewBooking.add(error);
        } else{
            int bookingNumber = 0;
            for(Booking booking : bookings.stream().sorted((booking1, booking2) -> booking1.getTimestamp().compareTo(booking2.getTimestamp())).collect(Collectors.toList())){
                if(booking.getDoctor().equals(loggedInUser)){
                    bookingNumber++;
                    JLabel labelViewBooking = new JLabel();
                    labelViewBooking.setText(String.format("%d. doctor: %s patient: %s date/time: %s", bookingNumber, booking.getDoctor().getEmail(), booking.getPatient().getEmail(), booking.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME)));
                    panelViewBooking.add(labelViewBooking);
                }
            }
        }
        panelViewBooking.revalidate();
        panelViewBooking.repaint();
    }
}


