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

/**
 * Rappresenta la finestra iniziale di autenticazione (Login) dell'applicazione.
 * Consente l'accesso agli utenti standard e agli admin, gestendo dinamicamente
 * i campi visibili e permettendo di navigare verso la schermata di registrazione.
 */
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

    /**
     * Punto di ingresso principale dell'applicazione grafica.
     *
     * @param args Gli argomenti passati da riga di comando.
     * @throws UnsupportedLookAndFeelException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        frame = new JFrame("Login");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Istanzia e inizializza i componenti grafici della schermata di login, definendo le azioni dei pulsanti e dei radio button.
     */
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
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore imprevisto provare a rinserire i dati");
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