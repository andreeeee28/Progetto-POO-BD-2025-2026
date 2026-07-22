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
 * The type Login.
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
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
     * @throws ClassNotFoundException          the class not found exception
     * @throws InstantiationException          the instantiation exception
     * @throws IllegalAccessException          the illegal access exception
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
     * Instantiates a new Login.
     */
    public Login() {
        controller = new Controller();
        frame.getRootPane().setDefaultButton(accediButton);

        configuraElementi();


        //Radio button Admin
        adminRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliccatoAdminRadioButton();
            }
        });

        //Radio button Standard
        standardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliccatoStandardRadioButton();
            }
        });

        //Pulsante accedi
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliccatoAccedi();
            }
        });

        //Pulsante registrati
        registrazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newRegistrazione();
            }
        });
    }

    //Configurazione
    private void configuraElementi() {
        //Inizializzazione button group a utente standard
        standardRadioButton.setSelected(true);
        inserireIdLabel.setVisible(false);
        campoID.setVisible(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    //Funzioni Listeners
    //Radio button Admin
    public void cliccatoAdminRadioButton() {
        inserireIdLabel.setVisible(true);
        campoID.setVisible(true);
        frame.pack();
    }

    //Radio button Standard
    public void cliccatoStandardRadioButton() {
        inserireIdLabel.setVisible(false);
        campoID.setVisible(false);
        frame.pack();
    }

    //Pulsante accedi
    public void cliccatoAccedi() {
        String StringaCampoUtente = campoNomeUtente.getText();
        String StringaCampoPassword = new String(campoPassword.getPassword());
        try {

            Utente utenteAttuale = controller.cliccatoAccedi(StringaCampoUtente, StringaCampoPassword);
            new Home(controller, frame, utenteAttuale);
            frame.setVisible(false);

        } catch (CampoNonValido ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errore imprevisto provare a rinserire i dati");
        }
    }

    //Registrazione
    public void newRegistrazione() {
        new Registrazione(controller, frame);
        frame.setVisible(false);
    }
}