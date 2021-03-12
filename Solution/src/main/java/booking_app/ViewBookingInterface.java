package booking_app;

import javax.swing.*;

public class ViewBookingInterface {
    // JFrame Object
    private JFrame frame = new JFrame();

    // JPanel Object
    private JPanel panel = new JPanel();

    ViewBookingInterface(){
        // Frame Information
        frame.setLayout(null);
        frame.setTitle("Past Bookings");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.add(panel);

        // Panel Information
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 400);
    }

    public static void main(String[] args){
        ViewBookingInterface main = new ViewBookingInterface();
    }
}
