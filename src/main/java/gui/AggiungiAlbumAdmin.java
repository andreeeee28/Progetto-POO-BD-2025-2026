package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * Rappresenta l'interfaccia grafica  dedicata agli admin per l'aggiunta diretta di un nuovo album al sistema.
 * Gestisce l'inserimento dei dati dell'album, la selezione dell'artista e dei generi,
 * e avvia la procedura guidata per l'aggiunta delle tracce musicali (tracklist).
 */
public class AggiungiAlbumAdmin {
    private JComboBox tipoElemento;
    private JTextField fieldTitolo;
    private JTextField fieldGeneri;
    private JSpinner canzoniSpinner;
    private JButton creaButton;
    private JPanel mainPanel;
    private JLabel laberlArtista;
    private JLabel labelCanzoni;
    private JLabel labelAnno;
    private JLabel labelTitolo;
    private JLabel labelGiorno;
    private JComboBox comboBoxGiorno;
    private JComboBox comboBoxMese;
    private JComboBox comboBoxAnno;
    private JComboBox comboBoxArtista;
    private JLabel labelMese;
    private JList listaGeneri;
    private JButton indietroButton;
    private JFrame frame;

    /**
     * Istanzia e inizializza la finestra grafica per la creazione di un nuovo album.
     *
     * @param controller L'istanza del Controller per gestire la logica di business e l'interazione con il database.
     * @param frameChiamante La finestra precedente da cui è stata aperta questa schermata (per permettere di tornare indietro).
     * @param utente L'oggetto Utente (Admin) attualmente loggato nel sistema.
     */
    public AggiungiAlbumAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Album");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(creaButton);


        configuraElementi(artistiNelDataBase, generiPresenti);

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
    }

    /**
     * Popola gli elementi grafici della finestra (ComboBox e liste) con i dati attualmente presenti a sistema.
     *
     * @param artistiNelDataBase La lista degli artisti caricati in memoria da mostrare nel menu a tendina.
     * @param generiPresenti La lista dei generi musicali caricati in memoria da mostrare nella lista a selezione multipla.
     */
    private void configuraElementi(ArrayList<Artista> artistiNelDataBase, ArrayList<Genere> generiPresenti) {
    private void configuraElementi(Controller controller) {
        //Caricamento informazioni
        //Artisti
        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();
        ArrayList<Genere> generiPresenti = controller.getGeneriPresenti();

        for (Artista artistaNelDataBase : artistiNelDataBase) {
            comboBoxArtista.addItem(artistaNelDataBase.getNomeArte());
        }

        //Data
        for (int i = 1900; i < Year.now().getValue() + 1; i++) {
            comboBoxAnno.addItem(i);
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
    }

    /**
     * Gestisce la chiusura della finestra attuale e il ripristino della visibilità della finestra chiamante.
     *
     * @param frameChiamante La finestra chiamante da mostrare nuovamente.
     * @param frame La finestra corrente da chiudere (dispose).
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
     * @param controller L'istanza del Controller per l'esecuzione dei controlli e l'aggiunta al DB fittizio.
     * @param frameChiamante La finestra precedente a cui tornare in caso di operazione conclusa con successo.
     * @param frame La finestra corrente utilizzata come genitore per i popup di input.
     * @throws CampoNonValido Se la validazione dei campi fallisce (es. album duplicato).
     */
    private void crea(Controller controller, JFrame frameChiamante, JFrame frame) throws CampoNonValido {
        //Prelevamento informazioni
        String nomeArtista = (String) comboBoxArtista.getSelectedItem();
        int numeroCanzoni = (int) canzoniSpinner.getValue();
        String titolo = fieldTitolo.getText();
        LocalDate dataPubblicazione = LocalDate.of((Integer) comboBoxAnno.getSelectedItem(), (Integer) comboBoxMese.getSelectedItem(), (Integer) comboBoxGiorno.getSelectedItem());
        ArrayList<Genere> generiSelezionati = new ArrayList<>(listaGeneri.getSelectedValuesList());

        //Inserimento canzoni
        Artista artista = controller.trovaArtista(nomeArtista);
        controller.verificaAlbum(titolo, artista);
        Artista artista = controller.trovaArtistaDaNomeArte(nomeArtista);
        controller.verificaAlbum(titolo,artista);
        ArrayList<Canzone> canzoniAlbum = controller.inserisciCanzoni(numeroCanzoni, frame);
        Album albumDaAggiungere = controller.creaAlbum(titolo, dataPubblicazione, artista, generiSelezionati, canzoniAlbum);
        controller.scriviAlbumDataBase(albumDaAggiungere);
        JOptionPane.showMessageDialog(null, "Album creato con successo!");
        indietro(frameChiamante, frame);
    }
}

