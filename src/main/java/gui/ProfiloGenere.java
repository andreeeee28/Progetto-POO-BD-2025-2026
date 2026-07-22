package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The type Profilo genere.
 */
public class ProfiloGenere {
    private JFrame frame;
    private JPanel mainPanel;
    private JList generiPadreList;
    private JButton indietroButton;
    private JTextPane descrizioneTextArea;
    private JLabel descrizoneLabel;
    private JTabbedPane tabbedPane;
    private JScrollPane generiPadreScrollPane;
    private JLabel nomeLabel;
    private JSeparator descrizioneSeparator;
    private JButton visualizzaButton;
    private JScrollPane sottogeneriScrollPane;
    private JList sottogeneriList;
    private JScrollPane albumScrollPane;
    private JList albumList;

    /**
     * Instantiates a new Profilo genere.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param genere         the genere
     * @param utenteAttuale  the utente attuale
     */
    public ProfiloGenere(Controller controller, JFrame frameChiamante, Genere genere, Utente utenteAttuale) {
        frame = new JFrame("Profilo Genere - " + genere.getNome());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(visualizzaButton);

        //Caricamento ArrayList
        ArrayList<Genere> generiPadre = genere.getGeneriPadre();
        ArrayList<Genere> sottogeneri = genere.getSottogeneri();
        ArrayList<Album> albums = genere.getListaAlbum();

        configuraElementi(genere);
        riempiListaGeneriPadre(generiPadre);
        riempiListaSottogeneri(sottogeneri);
        riempiListaAlbum(albums);

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
                visualizza(controller, generiPadre, sottogeneri, albums, utenteAttuale);
            }
        });
    }

    //Configurazione
    private void configuraElementi(Genere genere) {
        //Caricamento informazioni
        nomeLabel.setText(genere.getNome());
        nomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        descrizioneTextArea.setText(genere.getDescrizione());

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    //Riempimento liste
    private void riempiListaGeneriPadre(ArrayList<Genere> generiPadre) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        for (Genere g : generiPadre) {
            modelloLista.addElement(g.getNome());
        }
        generiPadreList.setModel(modelloLista);
    }

    private void riempiListaSottogeneri(ArrayList<Genere> sottogeneri) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        for (Genere g : sottogeneri) {
            modelloLista.addElement(g.getNome());
        }
        sottogeneriList.setModel(modelloLista);
    }

    private void riempiListaAlbum(ArrayList<Album> albums) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        for (Album a : albums) {
            modelloLista.addElement(a.getTitolo());
        }
        albumList.setModel(modelloLista);
    }

    //Funzioni Listeners
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    private void visualizza(Controller controller, ArrayList<Genere> generiPadre, ArrayList<Genere> sottogeneri, ArrayList<Album> albums, Utente utenteAttuale) {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:                                 //Generi Padre
                new ProfiloGenere(controller, frame, generiPadre.get(generiPadreList.getSelectedIndex()), utenteAttuale);
                frame.setVisible(false);
                break;
            case 1:                                 //Sottogeneri
                new ProfiloGenere(controller, frame, sottogeneri.get(sottogeneriList.getSelectedIndex()), utenteAttuale);
                frame.setVisible(false);
                break;
            case 2:                                 //Album
                new ProfiloAlbum(controller, frame, albums.get(albumList.getSelectedIndex()), utenteAttuale);
                frame.setVisible(false);
                break;
        }
    }
}
