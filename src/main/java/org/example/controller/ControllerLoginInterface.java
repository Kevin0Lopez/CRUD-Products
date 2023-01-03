package org.example.controller;

import org.example.model.User;
import org.example.model.UserDao;
import org.example.view.LoginInterface;
import org.example.view.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerLoginInterface implements ActionListener {

    LoginInterface loginView;
    UserInterface userView;
    ControllerUserInterface controllerUserInterface;
    User user = new User();
    UserDao userDao = new UserDao();

    public ControllerLoginInterface(LoginInterface loginView) {
        this.loginView = loginView;
        this.loginView.signInButton.addActionListener(this);
        this.loginView.signUpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.loginView.signInButton) {
            this.signIn();
        } else if (e.getSource() == this.loginView.signUpButton) {
            this.signUp();
        }
    }


    private void signIn() {

        if (this.unavailableToContinue()) {
            JOptionPane.showMessageDialog(null, "Fill all the parameters");
        } else if (invalidPassword()) {
            JOptionPane.showMessageDialog(null, "Password must be only 6 digits");
        } else {
            String name = this.loginView.textName.getText();
            int password = Integer.parseInt(this.loginView.textPassword.getText());
            user = new User(name, password);
            User userLoged = userDao.signIn(user);
            if (userLoged != null) {
                JOptionPane.showMessageDialog(null, "Successful Login");
                this.loginView.setVisible(false);
                this.userView = new UserInterface();
                this.controllerUserInterface = new ControllerUserInterface(this.userView);

            } else {
                JOptionPane.showMessageDialog(null, "User not found");
            }
        }
    }

    private void signUp() {

        if (this.unavailableToContinue()) {
            JOptionPane.showMessageDialog(null, "Fill all the parameters");
        } else if (invalidPassword()) {
            JOptionPane.showMessageDialog(null, "Password must be only 6 digits");
        } else {
            String name = this.loginView.textName.getText();
            int password = Integer.parseInt(this.loginView.textPassword.getText());
            user = new User(name, password);
            userDao.signUp(user);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), " Successfully Registered User");
            this.reset();
        }

    }

    private boolean unavailableToContinue() {
        return this.loginView.textName.getText().isBlank() || this.loginView.textPassword.getText().isBlank();
    }

    private boolean invalidPassword() {
        Matcher matcher = Pattern.compile("(\\d{6})").matcher(this.loginView.textPassword.getText());
        return !matcher.matches();
    }

    private void reset() {
        this.loginView.textName.setText("");
        this.loginView.textPassword.setText("");
    }
}
