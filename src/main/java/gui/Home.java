package gui;

import controller.Controller;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel mainPanel;
    private JButton creaPropostaButton;
    private JButton esploraArtistiButton;
    private JButton esploraGeneriButton;
    private JFrame frame;

    public Home(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Home");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        creaPropostaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreaProposta(controller, frame, utenteAttuale);
                frame.setVisible(false);
            }
        });

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
