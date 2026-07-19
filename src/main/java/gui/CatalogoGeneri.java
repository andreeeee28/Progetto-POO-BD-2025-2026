package gui;

import controller.Controller;
import model.Genere;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CatalogoGeneri {
    private JPanel mainPanel;
    private JList listaGeneri;
    private JTextField campoCerca;
    private JButton visualizzaButton;
    private JButton tornaAllaHomeButton;
    private JFrame frame;


    public CatalogoGeneri(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Catalogo Artisti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(visualizzaButton);


        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Genere> generiNelDataBase = controller.getGeneriPresenti();
        ArrayList<Genere> generiFiltrati = new ArrayList<>();

        for (Genere genereNelDataBase : generiNelDataBase) {
            modelloLista.addElement(genereNelDataBase.getNome());
            generiFiltrati.add(genereNelDataBase);
        }

        listaGeneri.setModel(modelloLista);


        //Filtro textbox
        campoCerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                filtraLista(campoCerca.getText(), modelloLista, generiNelDataBase, generiFiltrati);
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
                new ProfiloGenere(controller, frame, generiFiltrati.get(listaGeneri.getSelectedIndex()), utenteAttuale);
                frame.setVisible(false);
            }
        });
    }

    private void filtraLista(String testoCercato,DefaultListModel<String> modelloDestinazione, ArrayList<Genere> artistiNelDataBase, ArrayList<Genere> generiFiltrati)
    {
        modelloDestinazione.clear();
        generiFiltrati.clear();

        for(Genere genere : artistiNelDataBase) {
            String nome = genere.getNome().toLowerCase();

            if (nome.contains(testoCercato.toLowerCase())) {
                modelloDestinazione.addElement(genere.getNome());
                generiFiltrati.add(genere);
            }
        }
        listaGeneri.setModel(modelloDestinazione);
    }

}
