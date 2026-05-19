package gui;

import controller.Controller;

import javax.swing.*;

public class Login {
    private JPanel mainPanel;
    private JPasswordField campoPassword;
    private JTextField campoNomeUtente;
    private JButton accediButton;
    private JButton registrazioneButton;
    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Login() {
        controller = new Controller();
        // Add action listeners or other initialization code here

    }


}
