package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class AggiungiElementoAdmin {
    private JComboBox tipoElemento;
    private JTextField fieldTitolo;
    private JTextField fieldGeneri;
    private JTextField fieldCanzoni;
    private JButton creaButton;
    private JPanel mainPanel;
    private JLabel laberlArtista;
    private JLabel labelGeneri;
    private JLabel labelCanzoni;
    private JLabel labelAnno;
    private JLabel labelTitolo;
    private JLabel labelGiorno;
    private JComboBox comboBoxGiorno;
    private JComboBox comboBoxMese;
    private JComboBox comboBoxAnno;
    private JComboBox comboBoxArtista;
    private JLabel labelMese;
    private JFrame frame;

    public AggiungiElementoAdmin(Controller controller, JFrame frameChiamante){
        frame = new JFrame("AggiungiElementoAdmin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // qui setto i valori delle ComboBox
        tipoElemento.setModel(new DefaultComboBoxModel<>(TipoProposta.values()));

        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();

        for(Artista artistaNelDataBase : artistiNelDataBase){
            comboBoxArtista.addItem(artistaNelDataBase.getNomeArte());
        }

        for (int i = 1900; i< Year.now().getValue() + 1; i++){
            comboBoxAnno.addItem(String.valueOf(i));
        }
        for (int i = 1; i< 13; i++){
            comboBoxMese.addItem(String.valueOf(i));
        }
        for (int i = 1; i< 32; i++){
            comboBoxGiorno.addItem(String.valueOf(i));
        }
        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeArtista = (String) comboBoxArtista.getSelectedItem();
                int numeroGeneri = Integer.parseInt(fieldGeneri.getText());
                int numeroCanzoni =Integer.parseInt( fieldCanzoni.getText());
                String titolo = fieldTitolo.getText();
                int giorno = (int) comboBoxGiorno.getSelectedItem();
                int mese = (int) comboBoxMese.getSelectedItem();
                int anno = (int) comboBoxAnno.getSelectedItem();
                LocalDate dataPubblicazione = LocalDate.of(anno, mese, giorno);
                try {
                    ArrayList<Canzone> canzoniAlbum = controller.inserisciCanzoni(numeroCanzoni,frame);
                    ArrayList<Genere> generiAlbum = controller.inserisciGeneri(numeroGeneri,frame);
                    Artista artista = controller.trovaArtista(nomeArtista);
                    controller.creaAlbum(titolo,dataPubblicazione,artista,generiAlbum,canzoniAlbum);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }
}

