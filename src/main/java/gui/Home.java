package gui;

import controller.Controller;
import model.Admin;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta l'interfaccia grafica principale (dashboard) del sistema.
 * Gestisce la navigazione verso le varie sezioni (cataloghi, proposte, inserimenti) e adatta
 * dinamicamente i menu e i pulsanti visibili in base ai permessi dell'utente (standard o admin).
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
     * Istanzia e inizializza la schermata principale, configurando le azioni dei pulsanti di navigazione in base all'utente attualmente loggato.
     *
     * @param controller     L'istanza del Controller per gestire l'interazione con il database.
     * @param frameChiamante La finestra precedente (solitamente il Login) da cui si è effettuato l'accesso.
     * @param utenteAttuale  L'utente (standard o admin) attualmente connesso al sistema.
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

    /**
     * Adatta la visibilità dei componenti grafici della dashboard in base al ruolo dell'utente loggato.
     *
     * @param utenteAttuale L'utente di cui verificare il ruolo per nascondere o mostrare i pannelli da admin.
     */
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

    /**
     * Apre la schermata del catalogo dei generi musicali e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'utente attualmente connesso.
     */
    private void newCatalogoGeneri(Controller controller, JFrame frame, Utente utenteAttuale) {
        new CatalogoGeneri(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata del catalogo degli artisti e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'utente attualmente connesso.
     */
    private void newCatalogoArtisti(Controller controller, JFrame frame, Utente utenteAttuale) {
        new CatalogoArtisti(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata per la creazione di una nuova proposta (riservata agli utenti standard) e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'utente attualmente connesso.
     */
    private void newCreaProposta(Controller controller, JFrame frame, Utente utenteAttuale) {
        new CreaProposta(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata per l'inserimento di un nuovo musicista (riservata agli admin) e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'admin attualmente connesso.
     */
    private void newAggiungiMusicistaAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiMusicistaAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata per l'inserimento di una nuova band (riservata agli admin) e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'admin attualmente connesso.
     */
    private void newAggiungiBandAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiBandAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata per l'inserimento di un nuovo album (riservata agli admin) e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'admin attualmente connesso.
     */
    private void newAggiungiAlbumAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiAlbumAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata per l'inserimento di un nuovo genere (riservata agli admin) e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'admin attualmente connesso.
     */
    private void newAggiungiGeneriAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new AggiungiGeneriAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }

    /**
     * Apre la schermata per la valutazione delle proposte in sospeso (riservata agli admin) e nasconde la dashboard corrente.
     *
     * @param controller    Il Controller per gestire la comunicazione con i dati.
     * @param frame         La finestra Home da nascondere.
     * @param utenteAttuale L'admin attualmente connesso.
     */
    private void newValutaProposteAdmin(Controller controller, JFrame frame, Utente utenteAttuale) {
        new ValutaProposteAdmin(controller, frame, utenteAttuale);
        frame.setVisible(false);
    }
}