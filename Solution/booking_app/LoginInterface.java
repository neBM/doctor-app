package booking_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame implements ActionListener {

    public static void main(String[] args){
        // Creating objects for Interface
        JFrame frame = new JFrame();
        JButton button = new JButton();
        JPanel panelFooter = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelUsername = new JPanel();
        JPanel panelPassword = new JPanel();
        JLabel labelUsername = new JLabel();
        JLabel labelPassword = new JLabel();
        JTextField textUsername = new JTextField();
        JTextField textPassword = new JTextField();

        // Frame Information
        frame.setTitle("NHS Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(550, 150);
        frame.setResizable(false);
        frame.add(panelFooter, BorderLayout.SOUTH);
        frame.add(panelCenter, BorderLayout.CENTER);

        // Label Information
        labelUsername.setText("Username");
        labelPassword.setText("Password");

        // Text Information
        textUsername.setPreferredSize(new Dimension(150, 20));
        textPassword.setPreferredSize(new Dimension(150, 20));

        // Panel Information
        panelFooter.add(button);

        panelUsername.add(labelUsername);
        panelUsername.setPreferredSize(new Dimension(250, 200));
        panelUsername.add(textUsername);

        panelPassword.add(labelPassword);
        panelPassword.setPreferredSize(new Dimension(250, 200));
        panelPassword.add(textPassword);

        panelCenter.add(panelUsername, BorderLayout.WEST);
        panelCenter.add(panelPassword);

        // Login Button Information
        button.setText("Login");
        button.setLocation(125, 500);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("button")){
        }
    }

}
