package booking_app;

import javax.swing.*;

public class BookingInterface {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JComboBox textMonth = new JComboBox();
    private JComboBox textDay = new JComboBox();
    private String[] month = new String[13];
    private String[] day = new String[32];
    private JLabel labelMonth = new JLabel();
    private JLabel labelDay = new JLabel();

    public BookingInterface(){
        // Frame Information
        frame.setLayout(null);
        frame.setTitle("Booking Filter");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(375, 180);
        frame.setResizable(false);
        frame.add(panel);

        // Panel Information
        panel.setBounds(0, 0, 500, 200);
        panel.setLayout(null);
        panel.add(textMonth);
        panel.add(textDay);
        panel.add(labelDay);
        panel.add(labelMonth);

        // ComboBox Information
        // Setting String array for months (12)
        for(int i = 1; i <= 12; i++){
            month[i] = String.valueOf(i);
        }

        // Setting String array for days (31)
        for(int i = 1; i <= 31; i++){
            day[i] = String.valueOf(i);
        }

        // Set Size and Location of ComboBox
        textMonth.setBounds(50, 30, 275, 20);
        textDay.setBounds(50, 60, 275,20);
        textDay.setModel(new DefaultComboBoxModel(day));
        textMonth.setModel(new DefaultComboBoxModel(month));

        // Label Information
        labelMonth.setText("Month");
        labelDay.setText("Day");
        labelMonth.setBounds(10,30, 50, 20);
        labelDay.setBounds(10,60, 50, 20); 
    }

    public static void main (String[] args){
        BookingInterface bookingInterface = new BookingInterface();
    }
}




