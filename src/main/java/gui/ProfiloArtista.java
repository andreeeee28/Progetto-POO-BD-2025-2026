package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Profilo artista.
 */
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

    /**
     * Istanzia e inizializza la finestra grafica per il Profilo Artista.
     *
     * @param controller     L'istanza del Controller per gestire l'interazione con il database.
     * @param frameChiamante La finestra precedente da cui si è effettuato l'accesso.
     * @param artista        L'artista di cui mostrare le informazioni.
     * @param utenteAttuale  L'utente attualmente connesso al sistema.
     */
    public ProfiloArtista(Controller controller, JFrame frameChiamante, Artista artista, Utente utenteAttuale) {
        frame = new JFrame("Profilo Artista - " + artista.getNomeArte());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(visualizzaButton);

        ArrayList<MembroBand> partecipazioniMembri;
        ArrayList<Album> discografia;

        if (artista.getClass() == Band.class) {         //Band

            configuraElementiBand(artista);
            partecipazioniMembri = riempiListaMembriBand(artista);
            discografia = riempiListaDiscografia(artista);

        } else if (artista.getClass() == Musicista.class) {         //Musicista

            configuraElementiMusicista(artista);
            partecipazioniMembri = riempiListaMembriMusicista(artista);
            discografia = riempiListaDiscografia(artista);

        } else {
            partecipazioniMembri = new ArrayList<>();
            discografia = new ArrayList<>();
        }

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
                visualizza(controller, partecipazioniMembri, discografia, artista, utenteAttuale);
            }
        });
    }

    //Configurazione

    /**
     * Adatta la visibilità dei componenti grafici della form in base alle informazioni della Band.
     *
     * @param artista La Band di cui mostrare le informazioni.
     */
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
        nomeArteLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
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

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Adatta la visibilità dei componenti grafici della form in base alle informazioni del Musicista.
     *
     * @param artista Il Musicista di cui mostrare le informazioni.
     */
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
        nomeArteLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        idLabel.setText(artista.getIdArtista());
        annoInizioAttivitaLabel.setText(String.valueOf(artista.getAnnoInizioAttivita()));
        numeroAlbumLabel.setText(String.valueOf(artista.getAlbumPubblicati().toArray().length));


        nomeVeroLabel.setText(((Musicista) artista).getNomeVero());
        cognomeVeroLabel.setText(((Musicista) artista).getCognonomeVero());
        dataDiNascitaLabel.setText(((Musicista) artista).getDataDiNascita().toString());

        tipoLabel.setText("Musicista");
        tabbedPane.setTitleAt(1, "Partecipazioni");

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    //Riempimento liste

    /**
     * Riempe la JList di Membri appartenenti alla Band.
     *
     * @param artista La Band di cui mostrare le informazioni.
     * @return ArrayList di MembroBand appartenenti alla Band.
     */
    private ArrayList<MembroBand> riempiListaMembriBand(Artista artista) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();
        ArrayList<MembroBand> partecipazioniMembri = ((Band) artista).getMembriBand();

        for (MembroBand membroBand : partecipazioniMembri) {
            modelloLista.addElement(membroBand.getMusicista().getNomeArte());
        }
        partecipazioniMembriList.setModel(modelloLista);

        return partecipazioniMembri;
    }

    /**
     * Riempe la JList di Band a cui appartiene il Musicista.
     *
     * @param artista Il Musicista di cui mostrare le informazioni.
     * @return ArrayList di Band a cui appartiene alla Band.
     */
    private ArrayList<MembroBand> riempiListaMembriMusicista(Artista artista) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();
        ArrayList<MembroBand> partecipazioniMembri = ((Musicista) artista).getPartecipazioniBand();

        for (MembroBand membroBand : partecipazioniMembri) {
            modelloLista.addElement(membroBand.getBand().getNomeArte());
        }
        partecipazioniMembriList.setModel(modelloLista);

        return partecipazioniMembri;
    }

    /**
     * Riempe la JList di Album pubblicati dall'Artista.
     *
     * @param artista L'artista di cui mostrare le informazioni.
     * @return ArrayList di Album appartenenti alla Band.
     */
    private ArrayList<Album> riempiListaDiscografia(Artista artista) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();
        ArrayList<Album> discografia = artista.getAlbumPubblicati();

        for (Album album : discografia) {
            modelloLista.addElement(album.getTitolo());
        }
        discografiaList.setModel(modelloLista);

        return discografia;
    }


    //Funzioni Listeners

    /**
     * Gestisce la chiusura della finestra attuale e il ripristino della visibilità della finestra chiamante.
     *
     * @param frameChiamante La finestra chiamante da mostrare nuovamente.
     * @param frame          La finestra corrente da chiudere (dispose).
     */
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     * Gestisce le operazioni legate al tasto visualizza, scegliendole in base alla tab selezionata.
     *
     * @param controller           L'istanza del Controller per gestire l'interazione con il database.
     * @param partecipazioniMembri ArrayList di MembroBand dal quale prelevare l'Artista da visualizzare
     * @param discografia          ArrayList di Album dal quale prelevare l'Album da visualizzare
     * @param artista              L'artista di cui mostrare le informazioni.
     * @param utenteAttuale        L'utente attualmente connesso al sistema.
     */
    private void visualizza(Controller controller, ArrayList<MembroBand> partecipazioniMembri, ArrayList<Album> discografia, Artista artista, Utente utenteAttuale) {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:                                 //Discografia
                try {
                    new ProfiloAlbum(controller, frame, discografia.get(discografiaList.getSelectedIndex()), utenteAttuale);
                    frame.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Errore nella selezione dell'album");
                }

                break;
            case 1:                                 //Crediti
                try {
                    if (artista.getClass() == Band.class) {
                        new ProfiloArtista(controller, frame, partecipazioniMembri.get(partecipazioniMembriList.getSelectedIndex()).getMusicista(), utenteAttuale);
                        frame.setVisible(false);
                    } else if (artista.getClass() == Musicista.class) {
                        new ProfiloArtista(controller, frame, partecipazioniMembri.get(partecipazioniMembriList.getSelectedIndex()).getBand(), utenteAttuale);
                        frame.setVisible(false);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Errore nella selezione dell'artista");
                }


                break;
        }
    }
}
