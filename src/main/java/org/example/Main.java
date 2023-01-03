package org.example;

import org.example.controller.ControllerLoginInterface;
import org.example.view.LoginInterface;


public class Main {
    public static void main(String[] args) {
        new ControllerLoginInterface(new LoginInterface());
    }
}