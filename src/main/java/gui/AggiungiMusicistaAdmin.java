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
    private JLabel labelScelta;
    private JTextField textFieldNomeArte;
    private JLabel labelNomeArte;
    private JTextField textFieldAnnoInizioAttivita;
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
     * Istanzia e inizializza la finestra per la creazione del musicista, popolando i menu a tendina e le liste.
     *
     * @param controller L'istanza del Controller per gestire la comunicazione con il database e la logica.
     * @param frameChiamante La finestra originaria che ha invocato l'apertura di questa schermata.
     * @param utente L'amministratore attualmente loggato che sta eseguendo l'operazione.
     */
    public AggiungiMusicistaAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Musicista");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centriamo la finestra (come avevamo detto prima!)
        frame.setVisible(true);

        creaMembroBandMusicistaButton.setVisible(false);

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
     * Riconfigura e riadatta i componenti dell'interfaccia grafica per trasformare la schermata
     * affinché sia possibile l'inserimento dei dati di ruolo (strumento, anno ingresso, anno uscita) nelle band.
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
     * Valida i dati anagrafici e artistici del musicista inseriti dall'utente. Se corretti, salva il musicista
     * e prepara l'interfaccia per l'eventuale assegnazione alle band selezionate; altrimenti conclude l'operazione.
     *
     * @param controller L'istanza del Controller per effettuare le validazioni e il salvataggio sul database.
     * @param frameChiamante La finestra padre da cui si è originata l'azione.
     * @param utente L'amministratore che sta compiendo l'operazione per gestire i ritorni alle schermate precedenti.
     * @throws CampoNonValido Se la validazione del musicista fallisce o se c'è incongruenza tra età e inizio attività.
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
     * Raccoglie i dati del ruolo (strumento, date) per la band corrente processata, salva l'associazione nel database
     * e aggiorna l'interfaccia per la band successiva, chiudendo la finestra al termine della lista.
     *
     * @param controller L'istanza del Controller per avviare la scrittura del ruolo sul file corrispondente e gestire la logica.
     * @param frameChiamante La finestra precedente da riattivare in caso di conclusione.
     * @param utente L'admin che sta compiendo l'operazione.
     * @throws CampoNonValido Se gli anni inseriti non sono coerenti o se il form temporale è errato.
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
