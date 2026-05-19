package gui;

import javax.swing.*;

public class Registrazione {
    private JPanel mainPanel;
    private JTextField campoUtente;
    private JTextField textField2;
    private JButton registratiButton;
    private JLabel campoPassword;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Registrazione");
        frame.setContentPane(new Registrazione().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
