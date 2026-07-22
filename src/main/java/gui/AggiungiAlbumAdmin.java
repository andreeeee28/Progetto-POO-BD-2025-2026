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
 * The type Aggiungi album admin.
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
     * Instantiates a new Aggiungi album admin.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utente         the utente
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
    }

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

        canzoniSpinner.setModel(new SpinnerNumberModel(1, 1, null, 1));

        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    //Funzioni Listener
    /**
     *
     * @param frameChiamante
     * @param frame
     */
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     *
     *
     * @param controller
     * @param frameChiamante
     * @param frame
     * @throws CampoNonValido
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
        ArrayList<Canzone> canzoniAlbum = controller.inserisciCanzoni(numeroCanzoni, frame);
        Album albumDaAggiungere = controller.creaAlbum(titolo, dataPubblicazione, artista, generiSelezionati, canzoniAlbum);
        controller.scriviAlbumDataBase(albumDaAggiungere);

        JOptionPane.showMessageDialog(null, "Album creato con successo!");
        indietro(frameChiamante, frame);
    }
}

