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
 * Rappresenta l'interfaccia grafica dedicata agli admin per l'aggiunta diretta di un nuovo album al sistema.
 * Gestisce l'inserimento dei dati dell'album, la selezione dell'artista e dei generi,
 * e avvia la procedura guidata per l'aggiunta delle tracce musicali (tracklist).
 */
public class AggiungiAlbumAdmin {
    private JTextField fieldTitolo;
    private JSpinner canzoniSpinner;
    private JButton creaButton;
    private JPanel mainPanel;
    private JLabel labelArtista;
    private JLabel labelCanzoni;
    private JLabel labelTitolo;
    private JLabel labelAnno;
    private JLabel labelGiorno;
    private JLabel labelMese;
    private JComboBox comboBoxGiorno;
    private JComboBox comboBoxMese;
    private JComboBox comboBoxAnno;
    private JComboBox comboBoxArtista;
    private JList listaGeneri;
    private JButton indietroButton;
    private JFrame frame;

    /**
     * Istanzia e inizializza la finestra grafica per la creazione di un nuovo album.
     *
     * @param controller     L'istanza del Controller per gestire la logica di business e l'interazione con il database.
     * @param frameChiamante La finestra precedente da cui è stata aperta questa schermata (per permettere di tornare indietro).
     * @param utente         L'oggetto Utente (Admin) attualmente loggato nel sistema.
     */
    public AggiungiAlbumAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Album");
        frame.setContentPane(mainPanel);
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
                    crea(controller, frameChiamante, frame);
                } catch (CampoNonValido ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    //Eccezione Campo vuoto
                    JOptionPane.showMessageDialog(null, "Attenzione: Inserisci un numero valido nel campo Canzoni!");
                } catch (Exception ex) {
                    //Eccezione crash imprevisti
                    JOptionPane.showMessageDialog(null, "Errore imprevisto: " + ex.getMessage());
                }

            }
        });

        comboBoxArtista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {setComboBoxAnno(controller);}
        });
    }

    /**
     * Popola gli elementi grafici della finestra (ComboBox e liste) interrogando direttamente il controller per ottenere i dati necessari.
     *
     * @param controller L'istanza del Controller utilizzata per recuperare dal database fittizio gli artisti e i generi presenti.
     */
    private void configuraElementi(Controller controller) {
        //Caricamento informazioni
        //Artisti
        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();
        ArrayList<Genere> generiPresenti = controller.getGeneriPresenti();

        for (Artista artistaNelDataBase : artistiNelDataBase) {
            comboBoxArtista.addItem(artistaNelDataBase.getNomeArte());
        }

        //Data
        if (comboBoxArtista.getItemCount() > 0) {
            comboBoxArtista.setSelectedIndex(0);
            setComboBoxAnno(controller);
        }
        for (int i = 1; i < 13; i++) {
            comboBoxMese.addItem(i);
        }
        for (int i = 1; i < 32; i++) {
            comboBoxGiorno.addItem(i);
        }

        //Generi
        DefaultListModel<Genere> modelGeneri = new DefaultListModel<>();
        for (Genere genere : generiPresenti) {
            modelGeneri.addElement(genere);
        }
        listaGeneri.setModel(modelGeneri);
        listaGeneri.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        canzoniSpinner.setModel(new SpinnerNumberModel(1, 1, null, 1));

        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    //Funzioni Listeners
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
     * Raccoglie i dati inseriti nel form, ne verifica la validità e procede con la creazione e il salvataggio del nuovo album.
     * Avvia inoltre i popup per l'inserimento delle singole canzoni della tracklist.
     *
     * @param controller     L'istanza del Controller per l'esecuzione dei controlli e l'aggiunta al DB fittizio.
     * @param frameChiamante La finestra precedente a cui tornare in caso di operazione conclusa con successo.
     * @param frame          La finestra corrente utilizzata come genitore per i popup di input.
     * @throws CampoNonValido Se la validazione dei campi fallisce (es. album duplicato).
     */
    private void crea(Controller controller, JFrame frameChiamante, JFrame frame) throws CampoNonValido {
        //Prelevamento informazioni
        String nomeArtista = (String) comboBoxArtista.getSelectedItem();
        int numeroCanzoni = (int) canzoniSpinner.getValue();
        String titolo = fieldTitolo.getText();
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>30){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 30!");
        }
        LocalDate dataPubblicazione = LocalDate.of((Integer) comboBoxAnno.getSelectedItem(), (Integer) comboBoxMese.getSelectedItem(), (Integer) comboBoxGiorno.getSelectedItem());
        ArrayList<Genere> generiSelezionati = new ArrayList<>(listaGeneri.getSelectedValuesList());
        if(generiSelezionati == null|| generiSelezionati.isEmpty()){
            throw new CampoNonValido("la lista dei generi non può essere vuota ");
        }
        //Inserimento canzoni
        Artista artista = controller.trovaArtistaDaNomeArte(nomeArtista);
        controller.verificaAlbum(titolo, artista);
        ArrayList<Canzone> canzoniAlbum = controller.inserisciCanzoni(numeroCanzoni, frame);
        Album albumDaAggiungere = controller.creaAlbum(titolo, dataPubblicazione, artista, generiSelezionati, canzoniAlbum);
        controller.scriviAlbumDataBase(albumDaAggiungere);

        JOptionPane.showMessageDialog(null, "Album creato con successo!");
        indietro(frameChiamante, frame);
    }

    /**
     * Aggiorna dinamicamente gli anni selezionabili nel menu a tendina in base al periodo di attività dell'artista o della band selezionata.
     *
     * @param controller L'istanza del Controller per recuperare l'oggetto artista e i suoi anni di attività.
     */
    private void setComboBoxAnno(Controller controller){

        Artista artistaSelezionato = controller.trovaArtistaDaNomeArte(((String) comboBoxArtista.getSelectedItem()));

        comboBoxAnno.removeAllItems();

        if (artistaSelezionato != null) {

            if(artistaSelezionato instanceof Musicista || (artistaSelezionato instanceof Band && ((Band) artistaSelezionato).getAnnoScioglimento() == null)) {

                for (int i = artistaSelezionato.getAnnoInizioAttivita(); i <= Year.now().getValue() ; i++) {
                    comboBoxAnno.addItem(i);
                }

            } else {

                for (int i = artistaSelezionato.getAnnoInizioAttivita(); i <= ((Band)artistaSelezionato).getAnnoScioglimento() ; i++) {
                    comboBoxAnno.addItem(i);
                }
            }
        }

    }
}