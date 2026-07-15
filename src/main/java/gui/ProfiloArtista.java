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
    private JList partecipazioniMembriList;
    private JButton indietroButton;
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

    public ProfiloArtista(Controller controller, JFrame frameChiamante, Artista artista) {
        frame = new JFrame("Profilo Artista");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setDefaultButton(visualizzaButton);

        ArrayList<MembroBand> partecipazioniMembri = new ArrayList<>();

        if (artista.getClass() == Band.class) {         //Band

            configuraElementiBand(artista);
            riempiListaMembriBand(artista, partecipazioniMembri);
            riempiListaDiscografia(artista);

        } else if (artista.getClass() == Musicista.class) {         //Musicista

            configuraElementiMusicista(artista);
            riempiListaMembriMusicista(artista, partecipazioniMembri);
            riempiListaDiscografia(artista);

        }

        frame.setVisible(true);


        //Tasto torna alla form precedente
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
                if (tabbedPane.getSelectedIndex() == 0) {           //Discografia

                    /*CREARE FORM PAGINA ALBUM*/

                } else if (tabbedPane.getSelectedIndex() == 1) {            //Membri - Partecipazioni

                    if (artista.getClass() == Band.class) {
                        new ProfiloArtista(controller, frame, partecipazioniMembri.get(partecipazioniMembriList.getSelectedIndex()).getMusicista());
                        frame.setVisible(false);
                    } else if (artista.getClass() == Musicista.class) {
                        new ProfiloArtista(controller, frame, partecipazioniMembri.get(partecipazioniMembriList.getSelectedIndex()).getBand());
                        frame.setVisible(false);
                    }
                }
            }
        });
    }

    //Configurazione
    private void configuraElementiBand(Artista artista) {
        //Nasconde elementi non inerenti
        nomeLabel.setVisible(false);
        nomeVeroLabel.setVisible(false);
        nomeSeparator.setVisible(false);

        cognomeLabel.setVisible(false);
        cognomeVeroLabel.setVisible(false);
        cognomeSeparator.setVisible(false);

        natoAIlLabel.setVisible(false);
        dataDiNascitaLabel.setVisible(false);
        natoAIlSeparator.setVisible(false);

        //Caricamento informazioni
        nomeArteLabel.setText(artista.getNomeArte());
        idLabel.setText(artista.getIdArtista());
        annoInizioAttivitaLabel.setText(String.valueOf(artista.getAnnoInizioAttivita()));
        numeroAlbumLabel.setText(String.valueOf(artista.getAlbumPubblicati().toArray().length));


        numeroMembriLabel.setText(String.valueOf(((Band) artista).getNumeroMembri()));
        if (((Band) artista).getAnnoScioglimento() == null) {
            fineAttivitaLabel.setVisible(false);
            annoFineAttivitaLabel.setVisible(false);
            fineAttivitaSeparator.setVisible(false);
        } else {
            annoFineAttivitaLabel.setText(((Band) artista).getAnnoScioglimento().toString());
        }

        tipoLabel.setText("Band");
        tabbedPane.setTitleAt(1, "Membri");
    }

    private void configuraElementiMusicista(Artista artista) {
        //Nasconde elementi non inerenti
        membriLabel.setVisible(false);
        numeroMembriLabel.setVisible(false);
        membriSeparator.setVisible(false);

        fineAttivitaLabel.setVisible(false);
        annoFineAttivitaLabel.setVisible(false);
        fineAttivitaSeparator.setVisible(false);

        //Caricamento informazioni
        nomeArteLabel.setText(artista.getNomeArte());
        idLabel.setText(artista.getIdArtista());
        annoInizioAttivitaLabel.setText(String.valueOf(artista.getAnnoInizioAttivita()));
        numeroAlbumLabel.setText(String.valueOf(artista.getAlbumPubblicati().toArray().length));


        nomeVeroLabel.setText(((Musicista) artista).getNomeVero());
        cognomeVeroLabel.setText(((Musicista) artista).getCognonomeVero());
        dataDiNascitaLabel.setText(((Musicista) artista).getDataDiNascita().toString());

        tipoLabel.setText("Musicista");
        tabbedPane.setTitleAt(1, "Partecipazioni");
    }

    //Riempimento lista Membri/Partecipazioni
    private void riempiListaMembriBand(Artista artista, ArrayList<MembroBand> partecipazioniMembri) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        partecipazioniMembri = ((Band) artista).getMembriBand();

        for (MembroBand membroBand : partecipazioniMembri) {
            modelloLista.addElement(membroBand.getMusicista().getNomeArte());
        }
        partecipazioniMembriList.setModel(modelloLista);
    }

    private void riempiListaMembriMusicista(Artista artista, ArrayList<MembroBand> partecipazioniMembri) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        partecipazioniMembri = ((Musicista) artista).getPartecipazioniBand();

        for (MembroBand membroBand : partecipazioniMembri) {
            modelloLista.addElement(membroBand.getBand().getNomeArte());
        }
        partecipazioniMembriList.setModel(modelloLista);
    }

    //Riempimento lista Discografia
    private void riempiListaDiscografia(Artista artista) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Album> discografia = artista.getAlbumPubblicati();

        for (Album album : discografia) {
            modelloLista.addElement(album.getTitolo());
        }
        discografiaList.setModel(modelloLista);
    }


    //Funzioni Listeners
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }
}
