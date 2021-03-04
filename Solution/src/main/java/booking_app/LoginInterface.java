package booking_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Set;

public class LoginInterface extends JFrame {

    private JFrame frame;
    private JButton button;
    private JPanel panelFooter;
    private JPanel panelCenter;
    private JPanel panelUsername;
    private JPanel panelPassword;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelMessage;
    private JTextField textUsername;
    private JPasswordField textPassword;

    public LoginInterface() {
        // Creating objects for Interface
        frame = new JFrame();
        button = new JButton();
        panelFooter = new JPanel();
        panelCenter = new JPanel();
        panelUsername = new JPanel();
        panelPassword = new JPanel();
        labelUsername = new JLabel();
        labelPassword = new JLabel();
        labelMessage = new JLabel();
        textUsername = new JTextField();
        textPassword = new JPasswordField();

        // Frame Information
        frame.setTitle("NHS Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 150);
        frame.setResizable(false);
        frame.add(panelFooter, BorderLayout.SOUTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.setVisible(true);

        // Label Information
        labelUsername.setText("Username");
        labelPassword.setText("Password");

        // Text Information
        textUsername.setPreferredSize(new Dimension(150, 20));
        textPassword.setPreferredSize(new Dimension(150, 20));

        // Panel Information
        panelFooter.add(button);
        panelFooter.add(labelMessage);

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

        // Confirmation Message Information
        labelMessage.setLocation(150, 550);

        // Button Action listener
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //if (e.getActionCommand().equals("button")) {
                    String usernameField = textUsername.getText();
                    String passwordField = String.valueOf(textPassword.getPassword());
                    try {
                        Set<User> users = Model.getUsers();
                        //checking if the username exists
                        for (User user : users) {
                            if(usernameField.contains(user.toString())){    
                                try{
                                    //checking they entered the correct password
                                    if(user.testPassword(passwordField)){
                                        labelMessage.setText("Successful log in attempt c:");
                                        //Takes user to Interface
                        new Interface(user);
                                        new Interface();
                                        frame.setVisible(false);
                                    }
                                    else{
                                        labelMessage.setText("The password was incorrect, please try again");
                                        textPassword.setText("");
                                    }
                                }
                                catch(NoSuchAlgorithmException e2){
                                    e2.printStackTrace();
                                }
                            }
                            else{
                                labelMessage.setText("This username does not exist");
                                textUsername.setText("");
                                textPassword.setText("");
                            }
                        }
                        }
                     catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    
                //}
            }
        });
    }

    public static void main(String[] args) {
        LoginInterface logininterface = new LoginInterface();
        
    }
}
