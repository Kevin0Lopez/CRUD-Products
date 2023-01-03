package org.example.view;

import javax.swing.*;

public class LoginInterface extends JFrame{
    public JLabel usersRegistrationLabel;
    public JLabel userNameLabel;
    public JLabel passwordLabel;
    public JTextField textName;
    public JTextField textPassword;
    public JButton signInButton;
    public JButton signUpButton;
    public JPanel jPanel1;

    public LoginInterface (){
        this.setSize(920,500);
        this.setLocationRelativeTo(null);
        this.setContentPane(jPanel1);
        this.setVisible(true);
    }
}
