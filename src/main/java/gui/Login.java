package gui;

import controller.Controller;
import model.CampoNonValido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login {
    private JPanel mainPanel;
    private JPasswordField campoPassword;
    private JTextField campoNomeUtente;
    private JButton accediButton;
    private JButton registrazioneButton;
    private static JFrame frameHome;
    private Controller controller;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Login() {
        controller = new Controller();

        //Pulsante Accedi

        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String StringaCampoUtente = campoNomeUtente.getText();
                String StringaCampoPassword = Arrays.toString(campoPassword.getPassword());
                try {
                    controller.cliccatoAccedi(StringaCampoUtente, StringaCampoPassword);
                    new Home(controller,frame);
                    frame.setVisible(false);

                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });

        //Pulsante registrati

        registrazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Registrazione(controller,frame);
                frame.setVisible(false);


            }
        });
    }


}
