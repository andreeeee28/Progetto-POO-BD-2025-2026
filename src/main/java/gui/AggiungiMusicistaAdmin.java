package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * The type Aggiungi musicista admin.
 */
public class AggiungiMusicistaAdmin {
    private JPanel mainPanel;
    private JTextField textFieldNomeArte;
    private JLabel labelNomeArte;
    private JComboBox textFieldAnnoInizioAttivita;
    private JTextField textFieldIdArtista;
    private JTextField textFieldNomeVero;
    private JTextField textFieldCognomeVero;
    private JButton creaButton;
    private JList listaBand;
    private JLabel labelBand;
    private JComboBox comboBoxAnno;
    private JComboBox comboBoxMese;
    private JComboBox comboBoxGiorno;
    private JLabel labelInizioAttivita;
    private JLabel labelIdArtista;
    private JLabel labelCognome;
    private JLabel labelNome;
    private JLabel labelAnnoNascita;
    private JLabel labelMeseNascita;
    private JLabel labelGiornoNascita;
    private JButton creaMembroBandMusicistaButton;
    private JFrame frame;
    private Musicista nuovoMusicista;
    private ArrayList<Band> bandSelezionate;
    private int indiceBandAttuale = 0;

    /**
     * Instantiates a new Aggiungi musicista admin.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utente         the utente
     */
    public AggiungiMusicistaAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Musicista");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centriamo la finestra (come avevamo detto prima!)
        frame.setVisible(true);

        creaMembroBandMusicistaButton.setVisible(false);

        // --- INIZIALIZZAZIONE AUTOMATICA ---
        // Avviene subito appena si apre la form (prima era nel RadioButton)
        ArrayList<Band> bandPresenti = controller.getBandPresenti();
        DefaultListModel<Band> modelBand = new DefaultListModel<>();
        for (Band band : bandPresenti) {
            modelBand.addElement(band);
        }
        listaBand.setModel(modelBand);
        listaBand.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        for (int i = 1900; i < Year.now().getValue() + 1; i++) {
            comboBoxAnno.addItem(i);
        }
        for (int i = 1; i < 13; i++) {
            comboBoxMese.addItem(i);
        }
        for (int i = 1; i < 32; i++) {
            comboBoxGiorno.addItem(i);
        }

        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaButton(controller,frameChiamante,utente);

                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
                }
            }
        });

        creaMembroBandMusicistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaMembroBandMusicistaButton(controller,frameChiamante,utente);

                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore: controlla di aver inserito numeri validi per gli anni.");
                }
            }
        });


    }

    /**
     * Prepara interfaccia.
     */
    private void preparaInterfaccia() {
        comboBoxGiorno.setVisible(false);
        comboBoxMese.setVisible(false);
        textFieldNomeVero.setVisible(false);
        textFieldCognomeVero.setVisible(false);
        textFieldIdArtista.setVisible(false);
        comboBoxAnno.removeAllItems();
        comboBoxAnno.setModel(new DefaultComboBoxModel<>(Strumento.values()));
        textFieldNomeArte.setText("");
        textFieldAnnoInizioAttivita.setText("");
        labelAnnoNascita.setText("Selezionare lo strumento suonato");
        labelGiornoNascita.setVisible(false);
        labelMeseNascita.setVisible(false);
        labelIdArtista.setVisible(false);
        labelCognome.setVisible(false);
        labelNome.setVisible(false);
        labelInizioAttivita.setText("Inserire anno Ingresso");
        labelNomeArte.setText("Inserire anno Uscita, se è ancora nella band lasciare vuoto");
        labelScelta.setVisible(false);
        listaBand.setVisible(false);
        labelBand.setVisible(false);
        creaButton.setVisible(false);
        creaMembroBandMusicistaButton.setVisible(true);
        creaButton.setVisible(false);
    }

    /**
     *
     * @param controller
     * @param frameChiamante
     * @param utente
     * @throws CampoNonValido
     */
    private void cliccatoCreaButton(Controller controller, JFrame frameChiamante, Utente utente) throws CampoNonValido{
        String nomeArte = textFieldNomeArte.getText();
        int annoInizioAttivita = Integer.parseInt(textFieldAnnoInizioAttivita.getText());
        String idArtista = textFieldIdArtista.getText();
        String nomeVero = textFieldNomeVero.getText();
        String cognomeVero = textFieldCognomeVero.getText();
        bandSelezionate = new ArrayList<>(listaBand.getSelectedValuesList());
        indiceBandAttuale = 0;
        int giorno = (Integer) comboBoxGiorno.getSelectedItem();
        int mese = (Integer) comboBoxMese.getSelectedItem();
        int anno = (Integer) comboBoxAnno.getSelectedItem();
        LocalDate dataNascita = LocalDate.of(anno, mese, giorno);
        if (anno + 14 > annoInizioAttivita) {
            JOptionPane.showMessageDialog(null, "Errore! dissonanza tra l' anno di nascita del musicista e quello di inizio attività. In questo programma in particolare un musicista può iniziare la attività musicale minimo a 14 anni");
            return;
        }
        nuovoMusicista = new Musicista(nomeArte, annoInizioAttivita, idArtista, nomeVero, cognomeVero, dataNascita);
        controller.verificaMusicista(nuovoMusicista);
        controller.scriviMusicistaDataBase(nuovoMusicista);
        javax.swing.JOptionPane.showMessageDialog(null, "Musicista creato con successo");

        if (!bandSelezionate.isEmpty()) {
            preparaInterfaccia();
            labelScelta.setText("Aggiungi ruolo per: " + bandSelezionate.get(indiceBandAttuale).getNomeArte());
            labelScelta.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Operazione avvenuta con successo");
            new Home(controller, frame, utente);
            frame.dispose();

        }
    }

    /**
     *
     * @param controller
     * @param frameChiamante
     * @param utente
     * @throws CampoNonValido
     */
    private void cliccatoCreaMembroBandMusicistaButton(Controller controller, JFrame frameChiamante, Utente utente) throws CampoNonValido{
        Band bandAttuale = bandSelezionate.get(indiceBandAttuale);

        int annoIngresso = Integer.parseInt(textFieldAnnoInizioAttivita.getText());

        // Correzione: usiamo textFieldNomeArte per l'uscita, come da te impostato in preparaInterfaccia
        Integer annoUscita = null;
        String testoUscita = textFieldNomeArte.getText();
        if (!testoUscita.trim().isEmpty()) {
            annoUscita = Integer.parseInt(testoUscita);
        }

        Strumento strumentoMusicista = (Strumento) comboBoxAnno.getSelectedItem();

        MembroBand nuovoMembroBand = new MembroBand(strumentoMusicista, annoIngresso, annoUscita, nuovoMusicista);
        nuovoMembroBand.setBand(bandAttuale);
        controller.scriviMembroBandDataBase(nuovoMembroBand);
        indiceBandAttuale++;

        if (indiceBandAttuale < bandSelezionate.size()) {
            textFieldAnnoInizioAttivita.setText("");
            textFieldNomeArte.setText("");
            labelScelta.setText("Aggiungi ruolo per: " + bandSelezionate.get(indiceBandAttuale).getNomeArte());
            labelScelta.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Tutti i ruoli nelle band sono stati salvati con successo!");
            frame.dispose();
        }

    }

}
