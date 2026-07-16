package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class AggiungiArtistaAdmin {
    private JPanel mainPanel;
    private JRadioButton radioButtonMusicista;
    private JRadioButton radioButtonBand;
    private JLabel labelScelta;
    private JTextField textFieldNomeArte;
    private JLabel labelNomeArte;
    private JTextField textFieldAnnoInizioAttivita;
    private JTextField textFieldIdArtista;
    private JTextField textFieldNomeVero;
    private JTextField textFieldCognomeVero;
    private JTextField textFieldNumeroPartBand;
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
    private JFrame frame;

    public AggiungiArtistaAdmin(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("AggiungiArtistaAdmin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ArrayList<Band> bandPresenti = controller.getBandPresenti();
        DefaultListModel<Band> modelBand = new DefaultListModel<>();
        for(Band band : bandPresenti){
            modelBand.addElement(band);
        }
        listaBand.setModel(modelBand);
        listaBand.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        for (int i = 1900; i< Year.now().getValue() + 1; i++){
            comboBoxAnno.addItem(i);
        }
        for (int i = 1; i< 13; i++){
            comboBoxMese.addItem(i);
        }
        for (int i = 1; i< 32; i++){
            comboBoxGiorno.addItem(i);
        }


        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nomeArte = textFieldNomeArte.getText();
                    int annoInizioAttivita = Integer.parseInt(textFieldAnnoInizioAttivita.getText());
                    String idArtista = textFieldIdArtista.getText();
                    String nomeVero = textFieldNomeVero.getText();
                    String cognomeVero = textFieldCognomeVero.getText();
                    ArrayList<Band> bandSelezionate = new ArrayList<>(listaBand.getSelectedValuesList());
                    int giorno = (Integer)comboBoxGiorno.getSelectedItem();
                    int mese = (Integer) comboBoxMese.getSelectedItem();
                    int anno = (Integer) comboBoxAnno.getSelectedItem();
                    LocalDate dataPubblicazione = LocalDate.of(anno, mese, giorno);
                    Musicista nuovoMusicista = new Musicista(nomeArte,annoInizioAttivita,idArtista,nomeVero,cognomeVero,dataPubblicazione);
                    controller.scriviMusicistaDataBase(nuovoMusicista);
                    javax.swing.JOptionPane.showMessageDialog(null, "Musicista creato con successo");
                    if(!bandSelezionate.isEmpty()) {
                        for (Band bandAttuale : bandSelezionate) {
                            comboBoxGiorno.setVisible(false);
                            comboBoxMese.setVisible(false);
                            textFieldNomeVero.setVisible(false);
                            textFieldCognomeVero.setVisible(false);
                            textFieldIdArtista.setVisible(false);
                            comboBoxAnno.removeAllItems();
                            comboBoxAnno.setModel(new DefaultComboBoxModel<>(Strumento.values()));
                            textFieldNomeArte.setText("");
                            textFieldAnnoInizioAttivita.setText("");
                            labelGiornoNascita.setVisible(false);
                            labelMeseNascita.setVisible(false);
                            labelIdArtista.setVisible(false);
                            labelCognome.setVisible(false);
                            labelNome.setVisible(false);
                            labelInizioAttivita.setText("Inserire anno Ingresso");
                            labelNomeArte.setText("Inserire anno Uscita, se è ancora nella band lasciare vuoto");
                            int annoIngresso = Integer.parseInt(textFieldAnnoInizioAttivita.getText());
                            Integer annoUscita;
                            if (textFieldAnnoInizioAttivita.getText().isEmpty()) {
                                annoUscita = null;
                            } else {
                                annoUscita = Integer.parseInt(textFieldAnnoInizioAttivita.getText());
                            }
                            Strumento strumentoMusicista = (Strumento) comboBoxAnno.getSelectedItem();
                            MembroBand nuovoMembroBand = new MembroBand(strumentoMusicista,annoIngresso,annoUscita,nuovoMusicista);
                            controller.scriviMembroBandDataBase(nuovoMembroBand,bandAttuale);


                        }
                    }


                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
                }

            }
        });
    }
}
