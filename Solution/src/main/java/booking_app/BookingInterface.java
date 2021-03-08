package booking_app;

import javax.swing.*;

public class BookingInterface {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

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
    }

    public static void main (String[] args){
        BookingInterface bookingInterface = new BookingInterface();
    }
}




