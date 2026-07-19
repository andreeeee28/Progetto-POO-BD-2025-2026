package gui;

import controller.Controller;
import model.Admin;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel mainPanel;
    private JButton creaPropostaButton;
    private JButton esploraArtistiButton;
    private JButton esploraGeneriButton;
    private JLabel creaPropostaLabel;
    private JButton aggiungiArtistaButton;
    private JButton aggiungiGenereButton;
    private JButton aggiungiBandButton;
    private JButton valutaProposteButton;
    private JFrame frame;

    public Home(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Home");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        aggiungiArtistaButton.setVisible(false);
        aggiungiGenereButton.setVisible(false);
        valutaProposteButton.setVisible(false);
        
        if (utenteAttuale instanceof Admin adminLoggato){
            creaPropostaLabel.setText("Clicca qui per aggiungere un album o un artista o un genere al sito");
            creaPropostaButton.setText("Aggiungi Album");
            aggiungiArtistaButton.setVisible(true);
            aggiungiGenereButton.setVisible(true);
            valutaProposteButton.setVisible(true);
            creaPropostaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AggiungiAlbumAdmin(controller,frame,utenteAttuale);
                    frame.setVisible(false);
                }
            });
            aggiungiArtistaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AggiungiMusicistaAdmin(controller,frame,utenteAttuale);
                    frame.setVisible(false);

                }
            });
            aggiungiGenereButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

        } else{
                creaPropostaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CreaProposta(controller, frame, utenteAttuale);
                    frame.setVisible(false);
                }
            });}

        esploraArtistiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CatalogoArtisti(controller, frame,utenteAttuale);
                frame.setVisible(false);
            }
        });

        esploraGeneriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CatalogoGeneri(controller, frame,utenteAttuale);
                frame.setVisible(false);
            }
        });


        aggiungiGenereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AggiungiGeneriAdmin(controller,frame,utenteAttuale);
            }
        });
        aggiungiBandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new AggiungiBandAdmin(controller,frame,utenteAttuale);
            }
        });
        valutaProposteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new ValutaProposteAdmin(controller,frame,utenteAttuale);}
        });
    }
}
