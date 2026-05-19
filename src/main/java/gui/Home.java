package gui;

import controller.Controller;

import javax.swing.*;

public class Home {
    private JPanel mainPanel;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton accediButton;
    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new Home().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Home() {
        controller = new Controller();
        // Add action listeners or other initialization code here

    }


}
