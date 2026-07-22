package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Nazione;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Registrazione.
 */
public class Registrazione {
    private JPanel mainPanel;
    private JTextField campoUtente;
    private JButton registratiButton;
    private JFrame frame;
    private JTextField campoPassword;
    private JComboBox <Nazione> campoNazione;
    private JButton tornaAlLoginButton;


    /**
     * Instantiates a new Registrazione.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public Registrazione(Controller controller, JFrame frameChiamante){
        frame = new JFrame("Registrazione");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(registratiButton);

        campoNazione.setModel(new DefaultComboBoxModel<>(Nazione.values()));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        //Tasto indietro
        tornaAlLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Tasto registrati
        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrati(controller);
                } catch (CampoNonValido ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    //Funzioni Listeners
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    private void registrati(Controller controller) throws CampoNonValido {
        //Creazione utente
            String stringaCampoUtente = campoUtente.getText();
            String stringaCampoPassword = campoPassword.getText();
            Nazione enumCampoNazione = (Nazione) campoNazione.getSelectedItem();
            Utente utenteAttuale = controller.cliccatoRegistrati(stringaCampoUtente, stringaCampoPassword, enumCampoNazione);
            //Apertura Home
            JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo");
            new Home(controller, frame, utenteAttuale);
            frame.setVisible(false);
    }
}
