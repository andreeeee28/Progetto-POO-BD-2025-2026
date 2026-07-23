package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Nazione;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta l'interfaccia grafica per la registrazione di un nuovo utente standard nel sistema.
 * Permette all'utente di inserire username, password e selezionare la propria nazione,
 * gestendo sia la creazione del profilo sia il ritorno alla schermata di accesso.
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
     * Istanzia e inizializza i componenti grafici della schermata di registrazione, popolando il menu a tendina delle nazioni.
     *
     * @param controller L'istanza del Controller per gestire la logica di registrazione e il salvataggio dei dati.
     * @param frameChiamante La finestra di Login da cui si è aperta questa schermata, usata per poter tornare indietro.
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
