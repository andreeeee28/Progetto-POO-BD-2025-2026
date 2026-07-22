package gui;

import controller.Controller;
import model.Admin;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Home.
 */
public class Home {
    private JPanel mainPanel;
    private JFrame frame;
    private JButton creaPropostaButton;
    private JButton esploraArtistiButton;
    private JButton esploraGeneriButton;
    private JLabel creaPropostaLabel;
    private JButton aggiungiMusicistaButton;
    private JButton aggiungiGenereButton;
    private JButton aggiungiBandButton;
    private JButton valutaProposteButton;
    private JLabel aggiungiElementoLabel;
    private JLabel valutaProposteLabel;
    private JSeparator valutaProposteSeparator;
    private JSeparator catalogoSeparator;
    private JButton aggiungiAlbumButton;

    /**
     * Instantiates a new Home.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utenteAttuale  the utente attuale
     */
    public Home(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Home");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configuraElementi(utenteAttuale);
        frame.setVisible(true);

        //Catalogo Generi
        esploraGeneriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCatalogoGeneri(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Catalogo Artisti
        esploraArtistiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCatalogoArtisti(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });


        //Tasto crea Proposta
        creaPropostaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCreaProposta(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });


        //Tasto aggiungi Musicista
        aggiungiMusicistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAggiungiMusicistaAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Tasto aggiungi Band
        aggiungiBandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAggiungiBandAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Tasto aggiungi Album
        aggiungiAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAggiungiAlbumAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Tasto aggiungi Genere
        aggiungiGenereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAggiungiGeneriAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });


        //Tasto valuta Proposte
        valutaProposteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newValutaProposteAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });
    }

    //Configurazione
    private void configuraElementi(Utente utenteAttuale) {
        if (utenteAttuale instanceof Admin) {
            //Nascondi elementi non inerenti
            creaPropostaLabel.setVisible(false);
            creaPropostaButton.setVisible(false);
        } else {
            //Nascondi elementi non inerenti
            aggiungiElementoLabel.setVisible(false);
            valutaProposteLabel.setVisible(false);
            aggiungiMusicistaButton.setVisible(false);
            aggiungiBandButton.setVisible(false);
            aggiungiAlbumButton.setVisible(false);
            aggiungiGenereButton.setVisible(false);
            valutaProposteButton.setVisible(false);
            valutaProposteSeparator.setVisible(false);
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    //Funzioni Listeners
    //Catalogo Generi
    private void newCatalogoGeneri(Controller controller, JFrame frame, Utente utenteAttuale) {
        new CatalogoGeneri(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Catalogo Artisti
    private void newCatalogoArtisti(Controller controller, JFrame frame, Utente utenteAttuale) {
        new CatalogoArtisti(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Crea Proposta
    private void newCreaProposta(Controller controller, JFrame frame, Utente utenteAttuale) {
        new CreaProposta(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Aggiungi Musicista
    private void newAggiungiMusicistaAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiMusicistaAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Aggiungi Band
    private void newAggiungiBandAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiBandAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Aggiungi Album
    private void newAggiungiAlbumAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiAlbumAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Aggiungi Generi
    private void newAggiungiGeneriAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiGeneriAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    //Valuta Proposte
    private void newValutaProposteAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new ValutaProposteAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }


}
