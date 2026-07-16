package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
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
    private JFrame frame;

    public AggiungiAlbumAdmin(Controller controller, JFrame frameChiamante){
        frame = new JFrame("AggiungiElementoAdmin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // qui setto i valori delle ComboBox
        DefaultComboBoxModel<String> modelTipoElemento = new DefaultComboBoxModel<>();
        modelTipoElemento.addElement("ALBUM");
        modelTipoElemento.addElement("GENERE");
        modelTipoElemento.addElement("ARTISTA");
        tipoElemento.setModel(modelTipoElemento);
        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();

        for(Artista artistaNelDataBase : artistiNelDataBase){
            comboBoxArtista.addItem(artistaNelDataBase.getNomeArte());
        }

        for (int i = 1900; i< Year.now().getValue() + 1; i++){
            comboBoxAnno.addItem(i);
        }
        for (int i = 1; i< 13; i++){
            comboBoxMese.addItem(i);
        }
        for (int i = 1; i< 32; i++){
            comboBoxGiorno.addItem(i);
        }
        ArrayList<Genere> generiPresenti = controller.getGeneriPresenti();
        DefaultListModel<Genere> modelGeneri = new DefaultListModel<>();
        for(Genere genere : generiPresenti){
            modelGeneri.addElement(genere);
        }
        listaGeneri.setModel(modelGeneri);
        listaGeneri.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeArtista = (String) comboBoxArtista.getSelectedItem();
                    int numeroCanzoni =Integer.parseInt( fieldCanzoni.getText());
                    String titolo = fieldTitolo.getText();
                    int giorno = (Integer)comboBoxGiorno.getSelectedItem();
                    int mese = (Integer) comboBoxMese.getSelectedItem();
                    int anno = (Integer) comboBoxAnno.getSelectedItem();
                    LocalDate dataPubblicazione = LocalDate.of(anno, mese, giorno);
                    ArrayList<Canzone> canzoniAlbum = controller.inserisciCanzoni(numeroCanzoni,frame);
                    Artista artista = controller.trovaArtista(nomeArtista);
                    ArrayList<Genere> generiSelezionati = new ArrayList<>(listaGeneri.getSelectedValuesList());
                    controller.creaAlbum(titolo,dataPubblicazione,artista,generiSelezionati,canzoniAlbum);
                    javax.swing.JOptionPane.showMessageDialog(null, "album creato con successo");

                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                catch (NumberFormatException ex) {
                    // QUESTA È LA RETE CHE MANCAVA! Cattura il campo vuoto o testuale
                    javax.swing.JOptionPane.showMessageDialog(null, "Attenzione: Inserisci un numero valido nel campo Canzoni!");

                } catch (Exception ex) {
                    // Questa è una "super rete" che cattura qualsiasi altro crash imprevisto
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore imprevisto: " + ex.getMessage());
                }

            }
        });
    }
}

