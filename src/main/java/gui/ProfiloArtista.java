package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfiloArtista {
    private JFrame frame;
    private JPanel mainPanel;
    private JList parteciapzioniMembriList;
    private JButton tornaAlCatalogoButton;
    private JTextPane biografiaTextArea;
    private JLabel nomeLabel;
    private JLabel nomeVeroLabel;
    private JLabel cognomeVeroLabel;
    private JLabel numeroMembriLabel;
    private JLabel dataDiNascitaLabel;
    private JLabel annoInizioAttivitaLabel;
    private JLabel annoFineAttivitaLabel;
    private JLabel numeroAlbumLabel;
    private JLabel albumPubblicatiLabel;
    private JLabel fineAttivitaLabel;
    private JLabel inizioAttivitaLabel;
    private JLabel natoAIlLabel;
    private JLabel membriLabel;
    private JLabel cognomeLabel;
    private JTabbedPane tabbedPane;
    private JList discografiaList;
    private JScrollPane discografiaScrollPane;
    private JScrollPane partecipazioniMembriScrollPane;
    private JLabel tipoLabel;
    private JLabel idLabel;
    private JLabel nomeArteLabel;
    private JSeparator nomeSeparator;
    private JSeparator cognomeSeparator;
    private JSeparator membriSeparator;
    private JSeparator natoAIlSeparator;
    private JSeparator inizioAttivitaSeparator;
    private JSeparator fineAttivitaSeparator;
    private JSeparator albumSeparator;
    private JButton visualizzaButton;
    private ArrayList<MembroBand> partecipazioniMembri;

    public ProfiloArtista(Controller controller, JFrame frameChiamante, Artista artista) {
        frame = new JFrame("Profilo Artista");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        //Caricando informazioni Artista
        nomeArteLabel.setText(artista.getNomeArte());
        idLabel.setText(artista.getIdArtista());
        partecipazioniMembri = new ArrayList<>();
        if (artista.getClass() == Band.class) {

            //Band

            nomeLabel.setVisible(false);
            nomeVeroLabel.setVisible(false);
            nomeSeparator.setVisible(false);

            cognomeLabel.setVisible(false);
            cognomeVeroLabel.setVisible(false);
            cognomeSeparator.setVisible(false);

            natoAIlLabel.setVisible(false);
            dataDiNascitaLabel.setVisible(false);
            natoAIlSeparator.setVisible(false);

            String numeroMembriString = new Integer(((Band) artista).getNumeroMembri()).toString();
            numeroMembriLabel.setText(numeroMembriString);
            if (((Band) artista).getAnnoScioglimento() == null) {
                fineAttivitaLabel.setVisible(false);
                annoFineAttivitaLabel.setVisible(false);
            } else {
                annoFineAttivitaLabel.setText(((Band) artista).getAnnoScioglimento().toString());
            }


            tipoLabel.setText("Band");
            tabbedPane.setTitleAt(1, "Membri");

            //Riempimento lista membri band
            DefaultListModel<String> modelloLista = new DefaultListModel<>();

            partecipazioniMembri = ((Band) artista).getMembriBand();

            for (MembroBand membroBand : partecipazioniMembri) {
                modelloLista.addElement(membroBand.getMusicista().getNomeArte());
            }
            parteciapzioniMembriList.setModel(modelloLista);
        } else if (artista.getClass() == Musicista.class) {

            //Musicista

            membriLabel.setVisible(false);
            numeroMembriLabel.setVisible(false);
            membriSeparator.setVisible(false);

            fineAttivitaLabel.setVisible(false);
            annoFineAttivitaLabel.setVisible(false);
            fineAttivitaSeparator.setVisible(false);


            nomeVeroLabel.setText(((Musicista) artista).getNomeVero());
            cognomeVeroLabel.setText(((Musicista) artista).getCognonomeVero());
            dataDiNascitaLabel.setText(((Musicista) artista).getDataDiNascita().toString());

            tipoLabel.setText("Musicista");
            tabbedPane.setTitleAt(1, "Partecipazioni");

            //Riempimento lista partecipazioni
            DefaultListModel<String> modelloLista = new DefaultListModel<>();

            partecipazioniMembri = ((Musicista) artista).getPartecipazioniBand();

            for (MembroBand membroBand : partecipazioniMembri) {
                modelloLista.addElement(membroBand.getBand().getNomeArte());
            }
            parteciapzioniMembriList.setModel(modelloLista);
        }

        //Riempimento lista discografia
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Album> discografia = artista.getAlbumPubblicati();

        for (Album album : discografia) {
            modelloLista.addElement(album.getTitolo());
        }
        discografiaList.setModel(modelloLista);

        frame.setVisible(true);

        //Tasto torna al catalogo
        tornaAlCatalogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        //Tasto visualizza
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    /*CREARE FORM PAGINA ALBUM*/
                } else if (tabbedPane.getSelectedIndex() == 1) {
                    if (artista.getClass() == Band.class) {
                        new ProfiloArtista(controller, frame, partecipazioniMembri.get(parteciapzioniMembriList.getSelectedIndex()).getMusicista());
                        frame.setVisible(false);
                    } else if (artista.getClass() == Musicista.class) {
                        new ProfiloArtista(controller, frame, partecipazioniMembri.get(parteciapzioniMembriList.getSelectedIndex()).getBand());
                        frame.setVisible(false);
                    }
                }
            }
        });
    }
}
