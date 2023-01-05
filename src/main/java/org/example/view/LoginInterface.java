package org.example.view;

import javax.swing.*;

public class LoginInterface extends JFrame{
    public JPanel jPanel1;
    public JLabel usersRegistrationLabel;
    public JLabel userNameLabel;
    public JLabel passwordLabel;
    public JTextField textName;
    public JTextField textPassword;
    public JButton signInButton;
    public JButton signUpButton;

    public LoginInterface (){
        this.setSize(920,500);
        this.setLocationRelativeTo(null);
        this.setContentPane(jPanel1);
        this.setVisible(true);
    }
}
