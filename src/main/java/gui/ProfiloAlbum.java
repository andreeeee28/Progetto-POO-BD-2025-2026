package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Profilo album.
 */
public class ProfiloAlbum {
    private JFrame frame;
    private JPanel mainPanel;
    private JList generiList;
    private JButton indietroButton;
    private JLabel artistaLabel;
    private JLabel nomeArtistaLabel;
    private JLabel numeroTracceLabel;
    private JLabel dataPubblicazioneLabel;
    private JLabel numeroRecensioniLabel;
    private JLabel mediaVotiLabel;
    private JLabel numeroGeneriLabel;
    private JLabel generiLabel;
    private JLabel votoLabel;
    private JLabel recensioniLabel;
    private JLabel dataLabel;
    private JLabel tracceLabel;
    private JTabbedPane tabbedPane;
    private JScrollPane tracklistScrollPane;
    private JScrollPane generiScrollPane;
    private JLabel titoloLabel;
    private JSeparator nomeSeparator;
    private JSeparator cognomeSeparator;
    private JSeparator membriSeparator;
    private JSeparator natoAIlSeparator;
    private JSeparator inizioAttivitaSeparator;
    private JSeparator fineAttivitaSeparator;
    private JButton visualizzaButton;
    private JTable tracklistTable;
    private JList creditiList;
    private JScrollPane creditiScrollPane;
    private JPanel recensionePanel;
    private JSlider recensioneSlider;
    private JLabel recensioneVoto;

    /**
     * Istanzia e inizializza la finestra grafica per il Catalogo Generi.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param album          the album
     * @param utenteAttuale  the utente attuale
     */
    public ProfiloAlbum(Controller controller, JFrame frameChiamante, Album album, Utente utenteAttuale) {
        frame = new JFrame("Profilo Album - " + album.getTitolo());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(visualizzaButton);

        //Caricamento ArrayList
        ArrayList<Genere> generi;
        ArrayList<Artista> crediti;

        configuraElementi(album);
        riempiListaTracklist(album);
        generi = riempiListaGeneri(album);
        crediti = riempiListaCrediti(album);

        frame.setVisible(true);


        //Tasto indietro
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Tasto visualizza
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    visualizza(controller, generi, crediti, album, utenteAttuale);
                } catch (CampoNonValido ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                configuraVisualizzaButton();
            }
        });

        //Slider recensione
        recensioneSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                moveRecensioneSlider();
            }
        });
    }

    //Configurazione
    private void configuraElementi(Album album) {
        //Caricamento informazioni
        titoloLabel.setText(album.getTitolo());
        titoloLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        nomeArtistaLabel.setText(album.getArtista().getNomeArte());
        numeroTracceLabel.setText(String.valueOf(album.getTracklist().size()));
        dataPubblicazioneLabel.setText(String.valueOf(album.getDataPubblicazione()));
        numeroRecensioniLabel.setText(String.valueOf(album.getRecensioni().size()));
        mediaVotiLabel.setText(String.format("%.2f",album.getRating()));
        numeroGeneriLabel.setText(String.valueOf(album.getGeneri().size()));
        visualizzaButton.setVisible(false);
        recensioneVoto.setText(String.valueOf((float) recensioneSlider.getValue() / 10));

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    //Riempimento liste
    //Tracklist
    private void riempiListaTracklist(Album album) {
        String[] columnNames = {"Titolo", "Durata"};
        ArrayList<Canzone> tracklist = album.getTracklist();
        DefaultTableModel modelloTabella = new DefaultTableModel(columnNames, 0);

        for (Canzone canzone : tracklist) {
            String[] data = {canzone.getTitolo(), canzone.getDurataMinutiSecondi()};
            modelloTabella.addRow(data);
        }
        tracklistTable.setModel(modelloTabella);
    }

    //Generi
    private ArrayList<Genere> riempiListaGeneri(Album album) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();
        ArrayList<Genere> generi = album.getGeneri();

        for (Genere g : generi) {
            modelloLista.addElement(g.getNome());
        }
        generiList.setModel(modelloLista);

        return generi;
    }

    //Crediti
    private ArrayList<Artista> riempiListaCrediti(Album album) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Artista> crediti = new ArrayList<>();
        crediti.add(album.getArtista());
        modelloLista.addElement(album.getArtista().getNomeArte());

        if (album.getArtista().getClass() == Band.class) {

            ArrayList<MembroBand> membriBand = ((Band) album.getArtista()).getMembriBand();

            for (MembroBand membroBand : membriBand) {
                modelloLista.addElement(membroBand.getMusicista().getNomeArte());
                crediti.add(membroBand.getMusicista());
            }
        }
        creditiList.setModel(modelloLista);

        return crediti;
    }


    //Funzioni Listeners
    //Indietro
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    //Visualizza
    private void visualizza(Controller controller, ArrayList<Genere> generi, ArrayList<Artista> crediti, Album album, Utente utenteAttuale) throws CampoNonValido {
        switch (tabbedPane.getSelectedIndex()) {
            case 1:                                 //Generi
                try {
                    new ProfiloGenere(controller, frame, generi.get(generiList.getSelectedIndex()), utenteAttuale);
                    frame.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Errore nella selezione del genere");
                }
                break;
            case 2:                                 //Crediti
                try {
                    new ProfiloArtista(controller, frame, crediti.get(creditiList.getSelectedIndex()), utenteAttuale);
                    frame.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Errore nella selezione dell'artista");
                }
                break;
            case 3:
                try {
                    Recensione nuovaRecensione = new Recensione(album, utenteAttuale, (float) recensioneSlider.getValue() / 10, LocalDate.now());
                    controller.verificaRecensione(nuovaRecensione);
                    controller.scriviRecensioniDataBase(nuovaRecensione);
                    JOptionPane.showMessageDialog(null, "Recensione inviata con successo!");
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
                }
                break;
        }
    }

    //Configura pulsante Visualizza
    private void configuraVisualizzaButton() {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                visualizzaButton.setVisible(false);
                visualizzaButton.setText("");
                break;
            case 1, 2:
                visualizzaButton.setVisible(true);
                visualizzaButton.setText("Visualizza");
                break;
            case 3:
                visualizzaButton.setVisible(true);
                visualizzaButton.setText("Invia Recensione");
        }
    }

    //Configura etichetta Slider
    private void moveRecensioneSlider() {
        recensioneVoto.setText(String.valueOf((float) recensioneSlider.getValue() / 10));
    }
}
