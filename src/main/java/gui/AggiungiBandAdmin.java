package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;

/**
 * The type Aggiungi band admin.
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
     * Instantiates a new Aggiungi band admin.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utente         the utente
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

        numeroMembriSpinner.setModel(new SpinnerNumberModel(1, 1, null, 1));

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
     *
     * @param controller
     * @param frameChiamante
     * @param utente
     * @throws CampoNonValido
     */
    private void cliccatoCreaButton(Controller controller, JFrame frameChiamante, Utente utente) throws CampoNonValido {
        ArrayList<Musicista> musicistiSelezionati = new ArrayList<>(listaMusicisti.getSelectedValuesList());
        if (musicistiSelezionati.size() < 2) {
            JOptionPane.showMessageDialog(null, "Errore: Una band deve essere composta da almeno 2 musicisti!");
            return; // Blocca l'esecuzione qui
        }

        // 2. Leggiamo i dati della Band
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

        // 4. Chiudiamo questa finestra (Form 1)
        frame.dispose();

    }
}