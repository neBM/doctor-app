package booking_app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingInterface implements ActionListener {
    // Field Variables
    private Interface context;

    // JFrame Object
    private JFrame frame = new JFrame();

    // JPanel Object
    private JPanel panel = new JPanel();

    // JComboBox Object
    private JComboBox textMonth = new JComboBox();
    private JComboBox textYear = new JComboBox();

    // Integer Array Objects
    private Integer[] month = new Integer[13];
    private Integer[] year = new Integer[31];

    // JLabel Objects
    private JLabel labelMonth = new JLabel();
    private JLabel labelYear = new JLabel();

    // JButton Objects
    private JButton buttonCancel = new JButton();
    private JButton buttonSearch = new JButton();

    public BookingInterface(Interface context){
        this.context = context;
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
        panel.add(textYear);
        panel.add(labelYear);
        panel.add(labelMonth);
        panel.add(buttonCancel);
        panel.add(buttonSearch);

        // ComboBox Information
        // Setting String array for months (12)
        for(int i = 1; i <= 12; i++){
            month[i] = i;
        }

        // Setting String array for years (31)
        for(int i = 0; i <= 30; i++){
            year[i] = 2000+i;
        }

        // ComboBox Information
        // Set Size and Location of ComboBox
        textYear.setBounds(50, 60, 275, 20);
        textMonth.setBounds(50, 30, 275,20);
        textYear.setModel(new DefaultComboBoxModel(year));
        textMonth.setModel(new DefaultComboBoxModel(month));
        textMonth.setSelectedItem("1");

        // Creating a list cell renderer to center align the ComboBox
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        textMonth.setRenderer(listRenderer);
        textYear.setRenderer(listRenderer);
        textMonth.removeItemAt(0);

        // Label Information
        labelMonth.setText("Month");
        labelYear.setText("Year");
        labelYear.setBounds(10,60, 50, 20);
        labelMonth.setBounds(10,30, 50, 20);

        // Button Information
        buttonCancel.setText("Cancel");
        buttonSearch.setText("Search");
        buttonCancel.setBounds(50, 100, 120, 20);
        buttonSearch.setBounds(200, 100, 120, 20);
        buttonCancel.addActionListener(this);
        buttonSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonCancel){
            frame.dispose();
        }

        if(e.getSource() == buttonSearch){
            Integer month = (Integer) textMonth.getSelectedItem();
            Integer year = (Integer) textYear.getSelectedItem();
            try {
                Set<Booking> bookings = Model.getBookings().stream().filter((booking) -> booking.getTimestamp().getYear() == year && booking.getTimestamp().getMonthValue() == month).collect(Collectors.toSet());
                context.updateInterface(bookings);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}




