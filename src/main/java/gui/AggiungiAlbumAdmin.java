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

public class AggiungiAlbumAdmin {
    private JComboBox tipoElemento;
    private JTextField fieldTitolo;
    private JTextField fieldGeneri;
    private JTextField fieldCanzoni;
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
    private JLabel labelGeneri;
    private JButton indietroButton;
    private JFrame frame;

    public AggiungiAlbumAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Aggiungi Album");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setDefaultButton(creaButton);

        //Caricamento ArrayList dal DB
        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();
        ArrayList<Genere> generiPresenti = controller.getGeneriPresenti();

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

    //Configurazione
    private void configuraElementi(ArrayList<Artista> artistiNelDataBase, ArrayList<Genere> generiPresenti) {
        //Caricamento informazioni
        //Artisti
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

    //Funzioni Listeners
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    private void crea(Controller controller, JFrame frameChiamante, JFrame frame) throws CampoNonValido {
        //Prelevamento informazioni
        String nomeArtista = (String) comboBoxArtista.getSelectedItem();
        int numeroCanzoni = Integer.parseInt(fieldCanzoni.getText());
        String titolo = fieldTitolo.getText();
        LocalDate dataPubblicazione = LocalDate.of((Integer) comboBoxGiorno.getSelectedItem(), (Integer) comboBoxMese.getSelectedItem(), (Integer) comboBoxAnno.getSelectedItem());
        ArrayList<Genere> generiSelezionati = new ArrayList<>(listaGeneri.getSelectedValuesList());

        //Inserimento canzoni
        ArrayList<Canzone> canzoniAlbum = controller.inserisciCanzoni(numeroCanzoni, frame);
        //Ricerca artista dal DB
        Artista artista = controller.trovaArtista(nomeArtista);

        controller.creaAlbum(titolo, dataPubblicazione, artista, generiSelezionati, canzoniAlbum);
        JOptionPane.showMessageDialog(null, "Album creato con successo!");
        indietro(frameChiamante, frame);
    }
}

