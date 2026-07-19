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

    public ProfiloAlbum(Controller controller, JFrame frameChiamante, Album album, Utente utenteAttuale) {
        frame = new JFrame("Profilo Album - " + album.getTitolo());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
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
                recensioneVoto.setText(String.valueOf((float) recensioneSlider.getValue() / 10));
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
        mediaVotiLabel.setText(String.valueOf(album.getRating()));
        numeroGeneriLabel.setText(String.valueOf(album.getGeneri().size()));
        visualizzaButton.setVisible(false);
        recensioneVoto.setText(String.valueOf((float) recensioneSlider.getValue() / 10));
    }

    //Riempimento liste
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

    private ArrayList<Genere> riempiListaGeneri(Album album) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();
        ArrayList<Genere> generi = album.getGeneri();

        for (Genere g : generi) {
            modelloLista.addElement(g.getNome());
        }
        generiList.setModel(modelloLista);

        return generi;
    }

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
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    private void visualizza(Controller controller, ArrayList<Genere> generi, ArrayList<Artista> crediti, Album album, Utente utenteAttuale) throws CampoNonValido {
        switch (tabbedPane.getSelectedIndex()) {
            case 1:                                 //Generi
                new ProfiloGenere(controller, frame, generi.get(generiList.getSelectedIndex()), utenteAttuale);
                frame.setVisible(false);
                break;
            case 2:                                 //Crediti
                new ProfiloArtista(controller, frame, crediti.get(creditiList.getSelectedIndex()), utenteAttuale);
                frame.setVisible(false);
                break;
            case 3:
                new Recensione(album, utenteAttuale, (float) recensioneSlider.getValue() / 10, LocalDate.now());
                JOptionPane.showMessageDialog(null, "Recensione inviata con successo!");
                break;

        }
    }

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
}
