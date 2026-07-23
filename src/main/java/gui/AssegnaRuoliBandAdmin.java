package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;

/**
 * Rappresenta la seconda schermata dell'interfaccia grafica per la creazione di una band da parte dell'admin.
 * Gestisce l'assegnazione sequenziale dei ruoli (strumento, anno di ingresso, anno di uscita)
 * per ogni singolo musicista selezionato nella finestra precedente, finalizzando poi il salvataggio nel database.
 */
public class AssegnaRuoliBandAdmin {
    private JPanel mainPanel;
    private JLabel labelAnnoIngresso;
    private JLabel labelAnnoUscita;
    private JComboBox strumentoComboBox;
    private JComboBox annoIngressoComboBox;
    private JComboBox annoUscitaComboBox;
    private JButton CreaMembroBandbutton;
    private JLabel labelStrumento;
    private JLabel labelIdentificazioneMusicista;
    private JFrame frame;
    private int contatoreMusicisti = 0;
    private ArrayList<MembroBand> membriBandDaCreare = new ArrayList<>();

    /**
     * Istanzia e inizializza la finestra per l'assegnazione dei ruoli, ricevendo in input i dati base della band e la lista dei musicisti.
     *
     * @param controller L'istanza del Controller per gestire la logica e il salvataggio finale su file.
     * @param frameChiamante La finestra precedente da cui si proviene (AggiungiBandAdmin).
     * @param nomeBand Il nome d'arte della band in fase di creazione.
     * @param annoInizioAttivita L'anno di fondazione della band.
     * @param idArtista L'identificativo alfanumerico univoco della band.
     * @param numeroMembri Il numero totale dei componenti del gruppo.
     * @param annoScioglimento L'eventuale anno di scioglimento della band (null se ancora attiva).
     * @param musicistiSelezionati La lista dei musicisti scelti a cui bisogna assegnare strumento e date.
     * @param utente L'utente admin attualmente loggato nel sistema.
     */
    public AssegnaRuoliBandAdmin(Controller controller, JFrame frameChiamante, String nomeBand, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento, ArrayList<Musicista> musicistiSelezionati, Utente utente) {

        frame = new JFrame("Assegna Ruolo - " + musicistiSelezionati.get(contatoreMusicisti).getNomeArte()); // Mostra il nome del primo musicista
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centra la finestra
        frame.setVisible(true);

        configuraElementi(musicistiSelezionati, annoInizioAttivita, annoScioglimento);

        CreaMembroBandbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaMembroBandButtonController(controller, frameChiamante, nomeBand, annoInizioAttivita, idArtista, numeroMembri, annoScioglimento, musicistiSelezionati, utente);
                } catch (CampoNonValido ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Errore controllare di aver inserito in campi informazioni valide");
                }
            }
        });

    }

    /**
     * Configura i componenti grafici iniziali e i range degli anni nei menu a tendina in base alle date della band.
     *
     * @param musicistiSelezionati La lista dei musicisti per impostare l'etichetta col nome di quello attuale.
     * @param annoInizioAttivita L'anno in cui la band ha iniziato l'attività, per impostare il limite inferiore dell'ingresso.
     * @param annoScioglimento L'anno di scioglimento (o null), per impostare il limite superiore dell'uscita.
     */
    private void configuraElementi(ArrayList<Musicista> musicistiSelezionati, int annoInizioAttivita, Integer annoScioglimento) {
        labelIdentificazioneMusicista.setText("Inserire informazioni su: " + musicistiSelezionati.get(contatoreMusicisti));
        strumentoComboBox.setModel(new DefaultComboBoxModel<>(Strumento.values()));

        annoUscitaComboBox.addItem("N/A");
        if (annoScioglimento == null) {
            for (int i = annoInizioAttivita; i < Year.now().getValue() + 1; i++) {
                annoIngressoComboBox.addItem(i);
                annoUscitaComboBox.addItem(i);
            }
        } else {
            for (int i = annoInizioAttivita; i < annoScioglimento + 1; i++) {
                annoIngressoComboBox.addItem(i);
                annoUscitaComboBox.addItem(i);
            }
        }
    }

    /**
     * Gestisce la chiusura della finestra attuale e il ripristino della visibilità della finestra chiamante.
     *
     * @param frameChiamante La finestra precedente da mostrare nuovamente.
     * @param frame La finestra corrente da chiudere (dispose).
     */
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     * Elabora i dati inseriti per il musicista corrente, crea il ruolo temporaneo e, se tutti i musicisti sono stati processati, finalizza la creazione della band collegando i membri e salvando tutto nel database.
     *
     * @param controller L'istanza del Controller per avviare la scrittura dei dati finali nel DB.
     * @param frameChiamante La finestra originaria passata nel costruttore.
     * @param nomeBand Il nome della band da creare alla fine del ciclo.
     * @param annoInizioAttivita L'anno di inizio attività della band.
     * @param idArtista L'ID univoco della band.
     * @param numeroMembri Il numero di membri totali.
     * @param annoScioglimento L'anno di scioglimento della band (o null).
     * @param musicistiSelezionati La lista dei musicisti per estrarre quello corrispondente al contatore attuale.
     * @param utente L'admin loggato per il ritorno alla schermata Home.
     * @throws CampoNonValido Se l'anno di ingresso o di uscita non sono validi o violano i controlli temporali.
     */
    private void cliccatoCreaMembroBandButtonController(Controller controller, JFrame frameChiamante, String nomeBand, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento, ArrayList<Musicista> musicistiSelezionati, Utente utente) throws CampoNonValido {
        // Peschiamo il musicista attuale usando il contatore
        Musicista musicistaAttuale = musicistiSelezionati.get(contatoreMusicisti);

        // Prelievo informazioni
        Strumento strumentoScelto = (Strumento) strumentoComboBox.getSelectedItem();
        int annoIngresso = (int) annoIngressoComboBox.getSelectedItem();
        Integer annoUscita = null;

        if (annoUscitaComboBox.getSelectedItem() != "N/A") {
            annoUscita = (Integer) annoUscitaComboBox.getSelectedItem();
        }


        // Creiamo il ruolo (senza assegnare la band per ora)
        MembroBand nuovoMembro = new MembroBand(strumentoScelto, annoIngresso, annoUscita, musicistaAttuale);
        membriBandDaCreare.add(nuovoMembro);

        // Andiamo avanti al prossimo musicista
        contatoreMusicisti++;

        //  Controlliamo se abbiamo finito
        if (contatoreMusicisti < musicistiSelezionati.size()) {
            annoIngressoComboBox.setSelectedIndex(0);
            annoUscitaComboBox.setSelectedIndex(0);
            labelIdentificazioneMusicista.setText("Inserire informazioni su: " + musicistiSelezionati.get(contatoreMusicisti));
        } else {

            Band nuovaBand = new Band(nomeBand, annoInizioAttivita, idArtista, numeroMembri, annoScioglimento, membriBandDaCreare);

            // Se abbiamo finito salviamo la Band nel Database
            controller.scriviBandDataBase(nuovaBand);

            // Ora colleghiamo la band ai membri e salviamo anche loro
            for (MembroBand mb : membriBandDaCreare) {
                mb.setBand(nuovaBand);
                controller.scriviMembroBandDataBase(mb);
            }

            JOptionPane.showMessageDialog(null, "Band e ruoli creati con successo!");

            new Home(controller,frame,utente);
            frame.dispose();
        }
    }
}