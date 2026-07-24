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
 * Rappresenta l'interfaccia grafica riservata all'admin per l'inserimento di un nuovo musicista.
 * Consente di registrare i dati anagrafici e artistici del musicista e, opzionalmente, di assegnargli
 * in modo sequenziale i ruoli (strumento e periodo di permanenza) per le band precedentemente selezionate.
 */
public class AggiungiMusicistaAdmin {
    private JPanel mainPanel;
    private JTextField textFieldNomeArte;
    private JLabel labelNomeArte;
    private JComboBox annoInizioAttivitaComboBox;
    private JTextField textFieldIdArtista;
    private JTextField textFieldNomeVero;
    private JTextField textFieldCognomeVero;
    private JButton creaMusicistaButton;
    private JList listaBand;
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
    private JButton creaMembroBandButton;
    private JButton indietroButton;
    private JComboBox strumentoComboBox;
    private JLabel strumentoLabel;
    private JComboBox annoIngressoComboBox;
    private JLabel annoIngressoLabel;
    private JComboBox annoUscitaComboBox;
    private JLabel annoUscitaLabel;
    private JScrollPane listaBandScrollPane;
    private JSeparator idArtistaSeparator;
    private JSeparator dataNascitaSeparator;
    private JLabel bandLabel;
    private JSeparator bandSeparator;
    private JFrame frame;
    private Musicista nuovoMusicista;
    private ArrayList<Band> bandSelezionate;
    private int indiceBandAttuale = 0;

    /**
     * Istanzia e inizializza la finestra per la creazione del musicista, popolando i menu a tendina e le liste.
     *
     * @param controller     L'istanza del Controller per gestire la comunicazione con il database e la logica.
     * @param frameChiamante La finestra originaria che ha invocato l'apertura di questa schermata.
     * @param utente         L'admin attualmente loggato che sta eseguendo l'operazione.
     */
    public AggiungiMusicistaAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Musicista");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(creaMusicistaButton);

        configuraElementi();
        riempiLista(controller);

        frame.setVisible(true);


        creaMusicistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaButton(controller, frameChiamante, utente);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
                }
            }
        });

        creaMembroBandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaMembroBandMusicistaButton(controller, frameChiamante, utente);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore: controlla di aver inserito numeri validi per gli anni.");
                }
            }
        });


        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });
    }

    /**
     * Configura lo stato iniziale dei componenti grafici della finestra definendo i range degli anni e la visibilità dei pannelli.
     */
    private void configuraElementi() {
        for (int i = 1900; i < Year.now().getValue() + 1; i++) {
            annoInizioAttivitaComboBox.addItem(i);
            comboBoxAnno.addItem(i);
        }
        for (int i = 1; i < 13; i++) {
            comboBoxMese.addItem(i);
        }
        for (int i = 1; i < 32; i++) {
            comboBoxGiorno.addItem(i);
        }

        strumentoComboBox.setModel(new DefaultComboBoxModel<>(Strumento.values()));

        bandLabel.setVisible(false);
        bandSeparator.setVisible(false);

        strumentoLabel.setVisible(false);
        strumentoComboBox.setVisible(false);
        annoIngressoLabel.setVisible(false);
        annoIngressoComboBox.setVisible(false);
        annoUscitaLabel.setVisible(false);
        annoUscitaComboBox.setVisible(false);

        creaMembroBandButton.setVisible(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Popola la lista visiva con le band già presenti nel sistema per l'eventuale selezione dell'utente.
     *
     * @param controller L'istanza del Controller per recuperare le band dal database.
     */
    private void riempiLista(Controller controller) {
        ArrayList<Band> bandPresenti = controller.getBandPresenti();
        DefaultListModel<Band> modelBand = new DefaultListModel<>();
        for (Band band : bandPresenti) {
            modelBand.addElement(band);
        }
        listaBand.setModel(modelBand);
        listaBand.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    /**
     * Riconfigura e riadatta i componenti dell'interfaccia grafica per trasformare la schermata
     * affinché sia possibile l'inserimento dei dati di ruolo (strumento, anno ingresso, anno uscita) nelle band.
     *
     * @param band La band specifica per la quale si stanno configurando i parametri di ingresso e uscita del musicista.
     */
    private void configuraElementiMembroBand(Band band) {
        labelNomeArte.setVisible(false);
        textFieldNomeArte.setVisible(false);
        labelInizioAttivita.setVisible(false);
        annoInizioAttivitaComboBox.setVisible(false);

        labelNome.setVisible(false);
        textFieldNomeVero.setVisible(false);
        labelCognome.setVisible(false);
        textFieldCognomeVero.setVisible(false);

        labelAnnoNascita.setVisible(false);
        labelMeseNascita.setVisible(false);
        labelGiornoNascita.setVisible(false);
        comboBoxAnno.setVisible(false);
        comboBoxMese.setVisible(false);
        comboBoxGiorno.setVisible(false);
        dataNascitaSeparator.setVisible(false);

        labelIdArtista.setVisible(false);
        textFieldIdArtista.setVisible(false);
        idArtistaSeparator.setVisible(false);

        listaBandScrollPane.setVisible(false);
        listaBand.setVisible(false);

        creaMusicistaButton.setVisible(false);
        indietroButton.setVisible(false);

        annoIngressoComboBox.setModel(new DefaultComboBoxModel());
        annoUscitaComboBox.setModel(new DefaultComboBoxModel());
        if (band.getAnnoScioglimento() == null) {
            for (int i = band.getAnnoInizioAttivita(); i < Year.now().getValue() + 1; i++) {
                annoIngressoComboBox.addItem(i);
                annoUscitaComboBox.addItem(i);
            }
        } else {
            for (int i = band.getAnnoInizioAttivita(); i < band.getAnnoScioglimento() + 1; i++) {
                annoIngressoComboBox.addItem(i);
                annoUscitaComboBox.addItem(i);
            }
        }

        bandLabel.setText("Inserire informazioni in: " + band.getNomeArte());
        bandLabel.setVisible(true);
        bandSeparator.setVisible(true);

        strumentoLabel.setVisible(true);
        strumentoComboBox.setVisible(true);
        annoIngressoLabel.setVisible(true);
        annoIngressoComboBox.setVisible(true);
        annoUscitaLabel.setVisible(true);
        annoUscitaComboBox.setVisible(true);

        creaMembroBandButton.setVisible(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Gestisce la chiusura della finestra attuale e il ripristino della visibilità della finestra chiamante.
     *
     * @param frameChiamante La finestra chiamante da mostrare nuovamente.
     * @param frame          La finestra corrente da chiudere (dispose).
     */
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }


    /**
     * Valida i dati anagrafici e artistici del musicista inseriti dall'utente. Se corretti, salva il musicista
     * e prepara l'interfaccia per l'eventuale assegnazione alle band selezionate; altrimenti conclude l'operazione.
     *
     * @param controller     L'istanza del Controller per effettuare le validazioni e il salvataggio sul database.
     * @param frameChiamante La finestra padre da cui si è originata l'azione.
     * @param utente         L'admin che sta compiendo l'operazione per gestire i ritorni alle schermate precedenti.
     * @throws CampoNonValido Se la validazione del musicista fallisce o se c'è incongruenza tra età e inizio attività.
     */
    private void cliccatoCreaButton(Controller controller, JFrame frameChiamante, Utente utente) throws CampoNonValido {
        //Prelievo informazioni
        String nomeArte = textFieldNomeArte.getText();
        int annoInizioAttivita = (int) annoInizioAttivitaComboBox.getSelectedItem();

        String nomeVero = textFieldNomeVero.getText();
        String cognomeVero = textFieldCognomeVero.getText();

        int giorno = (int) comboBoxGiorno.getSelectedItem();
        int mese = (int) comboBoxMese.getSelectedItem();
        int anno = (int) comboBoxAnno.getSelectedItem();

        if (anno > annoInizioAttivita) {
            JOptionPane.showMessageDialog(null, "Errore! dissonanza tra l' anno di nascita del musicista e quello di inizio attività");
            return;
        }

        LocalDate dataNascita = LocalDate.of(anno, mese, giorno);

        String idArtista = textFieldIdArtista.getText();
        bandSelezionate = new ArrayList<>(listaBand.getSelectedValuesList());
        indiceBandAttuale = 0;


        nuovoMusicista = new Musicista(nomeArte, annoInizioAttivita, idArtista, nomeVero, cognomeVero, dataNascita);
        controller.verificaMusicista(nuovoMusicista);
        controller.scriviMusicistaDataBase(nuovoMusicista);
        javax.swing.JOptionPane.showMessageDialog(null, "Musicista creato con successo");

        if (!bandSelezionate.isEmpty()) {
            frame.getRootPane().setDefaultButton(creaMembroBandButton);
            configuraElementiMembroBand(bandSelezionate.get(indiceBandAttuale));
            frame.setTitle("Aggiungi ruolo per: " + bandSelezionate.get(indiceBandAttuale).getNomeArte());
        } else {
            JOptionPane.showMessageDialog(null, "Operazione avvenuta con successo");
            indietro(frameChiamante, frame);
        }
    }

    /**
     * Raccoglie i dati del ruolo (strumento, date) per la band corrente processata, salva l'associazione nel database
     * e aggiorna l'interfaccia per la band successiva, ripristinando la schermata precedente al termine dell'operazione.
     *
     * @param controller     L'istanza del Controller per avviare la scrittura del ruolo sul file corrispondente e gestire la logica.
     * @param frameChiamante La finestra precedente da riattivare in caso di conclusione.
     * @param utente         L'admin che sta compiendo l'operazione.
     * @throws CampoNonValido Se gli anni inseriti non sono coerenti o se il form temporale è errato.
     */
    private void cliccatoCreaMembroBandMusicistaButton(Controller controller, JFrame frameChiamante, Utente utente) throws CampoNonValido {
        Band bandAttuale = bandSelezionate.get(indiceBandAttuale);

        Strumento strumentoMusicista = (Strumento) strumentoComboBox.getSelectedItem();
        int annoIngresso = (int) annoIngressoComboBox.getSelectedItem();
        Integer annoUscita = (Integer) annoUscitaComboBox.getSelectedItem();

        MembroBand nuovoMembroBand = new MembroBand(strumentoMusicista, annoIngresso, annoUscita, nuovoMusicista);
        nuovoMembroBand.setBand(bandAttuale);
        controller.scriviMembroBandDataBase(nuovoMembroBand);

        indiceBandAttuale++;

        if (indiceBandAttuale < bandSelezionate.size()) {
            configuraElementiMembroBand(bandSelezionate.get(indiceBandAttuale));
            frame.setTitle("Aggiungi ruolo per: " + bandSelezionate.get(indiceBandAttuale).getNomeArte());
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Tutti i ruoli nelle band sono stati salvati con successo!");
            indietro(frameChiamante, frame);
        }
    }
}