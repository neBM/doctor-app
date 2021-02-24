package booking_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;


public class Interface implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panelTop = new JPanel();
    private JPanel panelMessage = new JPanel();
    private JPanel panelPatientList = new JPanel();
    private JPanel panelBookings = new JPanel();
    private JPanel panelVisit = new JPanel();

    private JPanel panelAddVisit = new JPanel();
    private JPanel panelAddPrescription = new JPanel();

    private JButton buttonMessage = new JButton();
    private JButton buttonPatientList = new JButton();
    private JButton buttonBookings = new JButton();
    private JButton buttonVisit = new JButton();

    private JButton buttonAddVisit = new JButton();
    private JButton buttonAddPrescriptions = new JButton();
    private JButton buttonSubmit = new JButton();

    private JLabel labelPatient = new JLabel();
    private JLabel labelDoctor = new JLabel();
    private JLabel labelDate = new JLabel();
    private JLabel labelNotes = new JLabel();
    private JLabel labelPrescriptionName = new JLabel();
    private JLabel labelQuantity = new JLabel();

    private JComboBox textFieldPatient = new JComboBox();
    private JComboBox textDoctor = new JComboBox();

    private JTextField textYear = new JTextField();
    private JTextField textMonth = new JTextField();
    private JTextField textDay = new JTextField();
    private JTextField textHour = new JTextField();
    private JTextField textMinute = new JTextField();

    private JTextField textVisitNotes = new JTextField();
    private JTextField textPrescription = new JTextField();
    private JTextField textQuantity = new JTextField();

    private int year = 0;
    private int month = 0;
    private int dayOfMonth = 0;
    private int hour = 0;
    private int minute = 0;

    public Interface(){
        // Frame Information
        frame.setLayout(null);
        frame.setTitle("Welcome Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 560);
        frame.setResizable(false);
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMessage);
        frame.add(panelPatientList);
        frame.add(panelBookings);
        frame.add(panelVisit);

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
        textFieldPatient.setBounds(20, 30, 250, 25);
        try {
            for (Model.User user : Model.getUsers(Model.User.Type.PATIENT)) {
                textFieldPatient.addItem(user.getEmail());
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        textDoctor.setBounds(20, 80, 250, 25);
        try {
            for (Model.User user : Model.getUsers(Model.User.Type.DOCTOR)) {
                textDoctor.addItem(user.getEmail());
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        textYear.setBounds(20, 130, 50, 25);
        textYear.setText("yyyy");
        textMonth.setBounds(80, 130, 35, 25);
        textMonth.setText("mm");
        textDay.setBounds(130, 130, 35, 25);
        textDay.setText("dd");
        textHour.setBounds(180, 130, 35, 25);
        textHour.setText("hh");
        textMinute.setBounds(230, 130, 35, 25);
        textMinute.setText("mm");
        textVisitNotes.setBounds(20, 180, 250, 25);
        textPrescription.setBounds(20, 230, 250, 25);
        textQuantity.setBounds(20, 280, 250, 25);
        textQuantity.setHorizontalAlignment(SwingConstants.CENTER);


        textQuantity.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                String value = textQuantity.getText();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                }
                else{
                    textQuantity.setText("");
                }
            }
        });

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

        panelPatientList.setVisible(false);
        panelPatientList.setBounds(0, 40, 800, 560);

        panelBookings.setVisible(false);
        panelBookings.setBounds(0, 40, 800, 560);

        panelVisit.setBackground(Color.LIGHT_GRAY);
        panelVisit.setVisible(false);
        panelVisit.setBounds(0, 40, 800, 560);
        panelVisit.add(buttonAddVisit);
        panelVisit.add(buttonAddPrescriptions);
        panelVisit.add(panelAddVisit);
        panelVisit.add(panelAddPrescription);
        panelVisit.setLayout(null);

        panelAddVisit.setBounds(40, 20, 700, 450);
        panelAddVisit.add(labelPatient);
        panelAddVisit.add(textFieldPatient);
        panelAddVisit.add(labelDoctor);
        panelAddVisit.add(textDoctor);
        panelAddVisit.add(labelDate);
        panelAddVisit.add(textYear);
        panelAddVisit.add(textMonth);
        panelAddVisit.add(textDay);
        panelAddVisit.add(textHour);
        panelAddVisit.add(textMinute);
        panelAddVisit.add(labelNotes);
        panelAddVisit.add(textVisitNotes);
        panelAddVisit.add(buttonSubmit);
        panelAddVisit.add(labelPrescriptionName);
        panelAddVisit.add(textPrescription);
        panelAddVisit.add(labelQuantity);
        panelAddVisit.add(textQuantity);
        panelAddVisit.setLayout(null);

        panelAddPrescription.setBounds(40, 40, 700, 450);

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
    }

    public static void main(String[] args){
        Interface mainInterface = new Interface();
    }

    @Override
    public void actionPerformed(ActionEvent e){
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

        if(e.getSource() == buttonVisit){
            panelMessage.setVisible(false);
            panelPatientList.setVisible(false);
            panelBookings.setVisible(false);
            panelVisit.setVisible(true);
        }

        if(e.getSource() == buttonAddVisit) {
            panelAddVisit.setVisible(true);
            panelAddPrescription.setVisible(false);
        }

        if(e.getSource() == buttonAddPrescriptions) {
            panelAddVisit.setVisible(false);
            panelAddPrescription.setVisible(true);
        }

        if(e.getSource() == buttonSubmit){
        String patientName = textFieldPatient.getSelectedItem().toString();
        String doctorName = textDoctor.getSelectedItem().toString();
        int year = Integer.valueOf(textYear.getText());
        int month = Integer.valueOf(textMonth.getText());
        int day = Integer.valueOf(textDay.getText());
        int hour = Integer.valueOf(textHour.getText());
        int minute = Integer.valueOf(textMinute.getText());
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        String visitNote = textVisitNotes.getText();
        String prescription = textPrescription.getText();
        int quantity = Integer.valueOf(textQuantity.getText());

           try {
                Model.addVisitDetails(Model.getUser(doctorName), visitNote, date, Model.getUser(patientName), prescription, quantity);
            }
           catch(java.sql.SQLIntegrityConstraintViolationException err) {
               JFrame windowError = new JFrame();
               windowError.setLayout(new BoxLayout(windowError.getContentPane(), BoxLayout.Y_AXIS));
               windowError.setResizable(false);
               windowError.setTitle("Error Message");
               windowError.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               windowError.setVisible(true);

               windowError.setSize(600, 100);

               JLabel errorLabel = new JLabel();
               errorLabel.setText(err.getMessage());
               windowError.add(errorLabel);

               JButton buttonOk = new JButton();
               buttonOk.setText("Ok");
               windowError.add(buttonOk);
               buttonOk.setAlignmentX(150);

           } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}


