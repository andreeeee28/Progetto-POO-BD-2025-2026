package gui;

import controller.Controller;
import model.Artista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CatalogoArtisti {
    private JPanel mainPanel;
    private JList listaArtisti;
    private JTextField campoCerca;
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
        frame.getRootPane().setDefaultButton(visualizzaButton);


        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();
        ArrayList<Artista> artistiFiltrati = new ArrayList<>();

        for (Artista artistaNelDataBase : artistiNelDataBase) {
            modelloLista.addElement(artistaNelDataBase.getNomeArte());
            artistiFiltrati.add(artistaNelDataBase);
        }

        listaArtisti.setModel(modelloLista);


        //Filtro textbox
        campoCerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                filtraLista(campoCerca.getText(), modelloLista, artistiNelDataBase, artistiFiltrati);
            }
        });


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
                new ProfiloArtista(controller, frame, artistiFiltrati.get(listaArtisti.getSelectedIndex()));
                frame.setVisible(false);
            }
        });
    }

    private void filtraLista(String testoCercato,DefaultListModel<String> modelloDestinazione, ArrayList<Artista> artistiNelDataBase, ArrayList<Artista> artistiFiltrati)
    {
        modelloDestinazione.clear();
        artistiFiltrati.clear();

        for(Artista artista : artistiNelDataBase) {
            String nome = artista.getNomeArte().toLowerCase();

            if (nome.contains(testoCercato.toLowerCase())) {
                modelloDestinazione.addElement(artista.getNomeArte());
                artistiFiltrati.add(artista);
            }
        }
        listaArtisti.setModel(modelloDestinazione);
    }

}
