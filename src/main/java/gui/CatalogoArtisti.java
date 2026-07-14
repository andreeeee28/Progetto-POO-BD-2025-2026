package gui;

import controller.Controller;
import model.Artista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CatalogoArtisti {
    private JPanel mainPanel;
    private JList listaArtisti;
    private JTextField campoCerca;
    private JButton cercaButton;
    private JButton visualizzaButton;
    private JButton tornaAllaHomeButton;
    private JFrame frame;


    public CatalogoArtisti(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Catalogo Artisti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();

        for (Artista artistaNelDataBase : artistiNelDataBase) {
            modelloLista.addElement(artistaNelDataBase.getNomeArte());
        }

        listaArtisti.setModel(modelloLista);


        //Tasti
        //Tasto torna alla home
        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        //Tasto visualizza artista
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfiloArtista(controller, frame, artistiNelDataBase.get(listaArtisti.getSelectedIndex()));
                frame.setVisible(false);
            }
        });
    }


}
