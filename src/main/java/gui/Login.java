package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Login {
    private JPanel mainPanel;
    private JPasswordField campoPassword;
    private JTextField campoNomeUtente;
    private JButton accediButton;
    private JButton registrazioneButton;
    private JRadioButton standardRadioButton;
    private JRadioButton adminRadioButton;
    private JLabel inserireIdLabel;
    private JTextField campoID;
    private static JFrame frameHome;
    private Controller controller;
    private static JFrame frame;

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        frame = new JFrame("Login");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Login() {
        controller = new Controller();
        frame.getRootPane().setDefaultButton(accediButton);

        //Inizializzazione button group a utente standard
        standardRadioButton.setSelected(true);
        inserireIdLabel.setVisible(false);
        campoID.setVisible(false);

        //Radio button Admin
        adminRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserireIdLabel.setVisible(true);
                campoID.setVisible(true);
                frame.pack();
            }
        });

        //Radio button Standard
        standardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserireIdLabel.setVisible(false);
                campoID.setVisible(false);
                frame.pack();
            }
        });

        //Pulsante accedi
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String StringaCampoUtente = campoNomeUtente.getText();
                String StringaCampoPassword = new String(campoPassword.getPassword());
                try {
                    Utente utenteAttuale = controller.cliccatoAccedi(StringaCampoUtente, StringaCampoPassword);
                    new Home(controller, frame, utenteAttuale);
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
                new Registrazione(controller, frame);
                frame.setVisible(false);
            }
        });
    }
}