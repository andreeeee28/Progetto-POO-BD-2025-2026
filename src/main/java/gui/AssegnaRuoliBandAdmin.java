package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The type Assegna ruoli band admin.
 */
public class AssegnaRuoliBandAdmin {
    private JPanel mainPanel;
    private JLabel labelAnnoIngresso;
    private JLabel labelAnnoUscita;
    private JComboBox comboBox1;
    private JTextField textFieldAnnoIngresso;
    private JTextField textFieldAnnoUscita;
    private JButton CreaMembroBandbutton;
    private JLabel labelStrumento;
    private JLabel labelIdentificazioneMusicista;
    private JFrame frame;

    // Aggiungiamo solo queste due variabili per gestire il flusso dei click
    private int contatoreMusicisti = 0;
    private ArrayList<MembroBand> membriBandDaCreare = new ArrayList<>();

    /**
     * Instantiates a new Assegna ruoli band admin.
     *
     * @param controller           the controller
     * @param frameChiamante       the frame chiamante
     * @param nomeBand             the nome band
     * @param annoInizioAttivita   the anno inizio attivita
     * @param idArtista            the id artista
     * @param numeroMembri         the numero membri
     * @param annoScioglimento     the anno scioglimento
     * @param musicistiSelezionati the musicisti selezionati
     * @param utente               the utente
     */
    public AssegnaRuoliBandAdmin (Controller controller, JFrame frameChiamante, String nomeBand, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento, ArrayList<Musicista> musicistiSelezionati, Utente utente) {

        frame = new JFrame("Ruolo per: " + musicistiSelezionati.get(contatoreMusicisti).getNomeArte()); // Mostra il nome del primo musicista
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centra la finestra
        frame.setVisible(true);

        comboBox1.setModel(new DefaultComboBoxModel<>(Strumento.values()));
        labelIdentificazioneMusicista.setText("il musicista attuale di cui inserire i dati è " +musicistiSelezionati.get(contatoreMusicisti));

        CreaMembroBandbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaMembroBandButtonController( controller,  frameChiamante, nomeBand, annoInizioAttivita, idArtista, numeroMembri, annoScioglimento, musicistiSelezionati, utente);
                } catch (CampoNonValido ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Errore controllare di aver inserito in campi informazioni valide");
                }
            }
        });

    }

    /**
     *
     * @param controller
     * @param frameChiamante
     * @param nomeBand
     * @param annoInizioAttivita
     * @param idArtista
     * @param numeroMembri
     * @param annoScioglimento
     * @param musicistiSelezionati
     * @param utente
     * @throws CampoNonValido
     */
    private void cliccatoCreaMembroBandButtonController(Controller controller, JFrame frameChiamante, String nomeBand, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento, ArrayList<Musicista> musicistiSelezionati, Utente utente) throws CampoNonValido{
        // 1. Peschiamo il musicista attuale usando il contatore
        Musicista musicistaAttuale = musicistiSelezionati.get(contatoreMusicisti);

        // 2. Leggiamo i dati dai campi di QUESTA form
        int annoIngresso = Integer.parseInt(textFieldAnnoIngresso.getText());

        Integer annoUscita = null;
        String testoUscita = textFieldAnnoUscita.getText();
        if (!testoUscita.trim().isEmpty()) {
            annoUscita = Integer.parseInt(testoUscita);
        }

        Strumento strumentoScelto = (Strumento) comboBox1.getSelectedItem();

        // 3. Creiamo il ruolo (senza assegnare la band per ora)
        MembroBand nuovoMembro = new MembroBand(strumentoScelto, annoIngresso, annoUscita, musicistaAttuale);
        membriBandDaCreare.add(nuovoMembro);

        // Andiamo avanti al prossimo musicista
        contatoreMusicisti++;

        // 4. Controlliamo se abbiamo finito
        if (contatoreMusicisti < musicistiSelezionati.size()) {
            // Puliamo i campi per il prossimo musicista
            textFieldAnnoIngresso.setText("");
            textFieldAnnoUscita.setText("");
            // Aggiorniamo il titolo della finestra col nuovo nome
            labelIdentificazioneMusicista.setText("il musicista attuale di cui inserire i dati è " +musicistiSelezionati.get(contatoreMusicisti));

        } else {

            Band nuovaBand = new Band(nomeBand, annoInizioAttivita, idArtista, numeroMembri, annoScioglimento, membriBandDaCreare);

            // Salviamo la Band nel Database
            controller.scriviBandDataBase(nuovaBand);

            // Ora colleghiamo la band ai membri e salviamo anche loro
            for(MembroBand mb : membriBandDaCreare) {
                mb.setBand(nuovaBand);
                controller.scriviMembroBandDataBase(mb);
            }

            JOptionPane.showMessageDialog(null, "Band e ruoli creati con successo!");

            // Torniamo al menu principale
            new Home(controller,frame,utente);
            frame.dispose();
        }

    }
}