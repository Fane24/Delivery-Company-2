package main;

import gui.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SC DELIVERY RO - Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            LoginFrame loginPanel = new LoginFrame(frame);
            frame.setContentPane(loginPanel);

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Centrează fereastra pe ecran
            frame.setVisible(true);
        });
    }
}
