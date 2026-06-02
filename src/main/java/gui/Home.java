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
    private JFrame frame;

    public Home(Controller controller, JFrame frameChiamante, Utente utenteAttuale ){
        frame = new JFrame("Home");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        if (utenteAttuale instanceof Admin adminLoggato){
            creaPropostaLabel.setText("Clicca qui per aggiungere un album o un artista al sito");
            creaPropostaButton.setText("aggiungi elemento");
            creaPropostaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AggiungiElementoAdmin(controller,frame);
                    frame.setVisible(false);
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
                new CatalogoArtisti(controller, frame);
                frame.setVisible(false);
            }
        });

        esploraGeneriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CatalogoGeneri(controller, frame);
                frame.setVisible(false);
            }
        });

    }
}
