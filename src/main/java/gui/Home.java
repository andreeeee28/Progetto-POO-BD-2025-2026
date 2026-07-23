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
    private JButton creaPropostaAdminButton;
    private JLabel labelCreaPropostaAdmin;

    /**
     * Istanzia e inizializza la schermata principale, configurando le azioni dei pulsanti di navigazione.
     *
     * @param controller Il Controller per la gestione della logica di business.
     * @param frameChiamante La finestra precedente (solitamente il Login) da cui si è effettuato l'accesso.
     * @param utenteAttuale L'utente (standard o admin) attualmente connesso al sistema.
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
                new CatalogoGeneri(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Catalogo Artisti
        esploraArtistiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CatalogoArtisti(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });


        //Tasto crea Proposta
        creaPropostaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreaProposta(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });


        //Tasto aggiungi Musicista
        aggiungiMusicistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AggiungiMusicistaAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Tasto aggiungi Band
        aggiungiBandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AggiungiBandAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Tasto aggiungi Album
        aggiungiAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AggiungiAlbumAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

        //Tasto aggiungi Genere
        aggiungiGenereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AggiungiGeneriAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });


        //Tasto valuta Proposte
        valutaProposteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ValutaProposteAdmin(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });
        creaPropostaAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreaProposta(controller,frame,utenteAttuale);
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
            creaPropostaAdminButton.setVisible(false);
            labelCreaPropostaAdmin.setVisible(false);
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
}
