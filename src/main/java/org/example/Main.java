package org.example;

import org.example.controller.Controller;
import org.example.view.UserInterface;

public class Main {
    public static void main(String[] args) {
        new Controller(new UserInterface());
    }
}