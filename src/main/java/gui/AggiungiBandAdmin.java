package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;

/**
 * Rappresenta l'interfaccia grafica (GUI) riservata agli admin per l'inserimento dei dati base di una nuova Band.
 * Questa è la prima di due schermate: permette di definire le informazioni anagrafiche del gruppo e di selezionare
 * i musicisti partecipanti prima di passare alla finestra successiva per l'assegnazione dei ruoli e degli strumenti.
 */
public class AggiungiBandAdmin {
    private JPanel labellNumeroMembri;
    private JTextField textFieldNomeArte;
    private JComboBox annoInizioComboBox;
    private JTextField textFieldIdArtista;
    private JSpinner numeroMembriSpinner;
    private JComboBox annoScioglimentoComboBox;
    private JList listaMusicisti;
    private JLabel labelScioglimento;
    private JLabel labelIdArtista;
    private JLabel labelAnnoInizioAttivita;
    private JLabel labelNomeArte;
    private JButton creaButton;
    private JButton indietroButton;
    private JFrame frame;

    /**
     * Istanzia e inizializza la prima schermata per la creazione di una nuova band, popolando la lista dei musicisti disponibili.
     *
     * @param controller     L'istanza del Controller per interrogare il database fittizio e gestire la logica.
     * @param frameChiamante La finestra genitore da cui è stata aperta questa schermata.
     * @param utente         L'oggetto Utente (Admin) attualmente loggato nel sistema.
     */
    public AggiungiBandAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Band - Dati Base");
        frame.setContentPane(labellNumeroMembri);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(creaButton);


        configuraElementi(controller);

        frame.setVisible(true);

        //Tasto indietro
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Tasto crea
        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaButton(controller, frameChiamante, utente);
                } catch (CampoNonValido ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore nell'inserimento dati. Controlla che gli anni siano numeri validi.");
                }

            }
        });
    }


    private void configuraElementi(Controller controller) {
        //Caricamento informazioni
        ArrayList<Musicista> musicistiPresenti = controller.getMusicistiPresenti();
        DefaultListModel<Musicista> modelMusicista = new DefaultListModel<>();

        for (Musicista musicista : musicistiPresenti) {
            modelMusicista.addElement(musicista);
        }
        listaMusicisti.setModel(modelMusicista);
        listaMusicisti.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        //Anno
        annoScioglimentoComboBox.addItem("N/A");
        for (int i = 1900; i < Year.now().getValue() + 1; i++) {
            annoInizioComboBox.addItem(i);
            annoScioglimentoComboBox.addItem(i);
        }

        numeroMembriSpinner.setModel(new SpinnerNumberModel(2, 2, null, 1));

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    //Funzioni Listener
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     * Raccoglie i dati inseriti nel form, esegue i controlli di validità sui campi testuali e sul numero di membri selezionati,
     * e in caso di successo avvia la seconda finestra (AssegnaRuoliBandAdmin) chiudendo quella attuale.
     *
     * @param controller     L'istanza del Controller per effettuare le validazioni finali, controllare la presenza di duplicati e gestire la logica
     * @param frameChiamante La finestra principale originaria da passare alla schermata successiva.
     * @param utente         L'amministratore che sta compiendo l'operazione.
     * @throws CampoNonValido Se i campi inseriti non rispettano le regole di validazione del dominio o contengono caratteri illegali.
     */
    private void cliccatoCreaButton(Controller controller, JFrame frameChiamante, Utente utente) throws CampoNonValido {
        ArrayList<Musicista> musicistiSelezionati = new ArrayList<>(listaMusicisti.getSelectedValuesList());
        if (musicistiSelezionati.size() < 2) {
            JOptionPane.showMessageDialog(null, "Errore: Una band deve essere composta da almeno 2 musicisti!");
            return;
        }

        // Leggiamo i dati della Band
        String nomeBand = textFieldNomeArte.getText();
        int annoInizio = (int) annoInizioComboBox.getSelectedItem();
        String idArtista = textFieldIdArtista.getText();
        int numMembri = (int) numeroMembriSpinner.getValue();

        Integer annoScioglimento = null;
        if (annoScioglimentoComboBox.getSelectedIndex() != 0) {
            annoScioglimento = (int) annoScioglimentoComboBox.getSelectedItem();
        }
        if (numMembri != musicistiSelezionati.size()) {
            JOptionPane.showMessageDialog(null, "I membri selezionati devono essere dello stesso numero del parametro numero membri");
            return;
        }
        controller.verificaBand(nomeBand, musicistiSelezionati, idArtista);
        new AssegnaRuoliBandAdmin(controller, frameChiamante, nomeBand, annoInizio, idArtista, numMembri, annoScioglimento, musicistiSelezionati, utente);

        frame.dispose();
    }
}