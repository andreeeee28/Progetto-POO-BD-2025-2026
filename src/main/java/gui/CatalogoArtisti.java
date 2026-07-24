package gui;

import controller.Controller;
import model.Artista;
import model.Genere;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The type Catalogo artisti.
 */
public class CatalogoArtisti {
    private JPanel mainPanel;
    private JList listaArtisti;
    private JTextField campoCerca;
    private JButton visualizzaButton;
    private JButton tornaAllaHomeButton;
    private ArrayList<Artista> artisti;
    private JFrame frame;


    /**
     * Istanzia e inizializza la finestra grafica per il Catalogo Artisti.
     *
     * @param controller     L'istanza del Controller per gestire l'interazione con il database.
     * @param frameChiamante La finestra precedente da cui è stata aperta questa schermata (per permettere di tornare indietro).
     * @param utenteAttuale  L'oggetto Utente (Admin) attualmente loggato nel sistema.
     */
    public CatalogoArtisti(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Catalogo Artisti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(visualizzaButton);


        artisti = riempiListaArtisti(controller);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Tasto torna alla home
        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Filtro textbox
        campoCerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                artisti = filtraLista(controller, campoCerca.getText());
            }
        });

        //Tasto visualizza artista
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newProfiloArtista(controller, frame, utenteAttuale);
            }
        });
    }

    //Riempimento liste
    //Artisti

    /**
     * Riempe la JList di Artisti, permettendo all'utente di selezionarli per la navigazione del catalogo.
     *
     * @param controller L'istanza del Controller per gestire l'interazione con il database.
     * @return L'ArrayList di Artisti presenti nel DataBase
     */
    private ArrayList<Artista> riempiListaArtisti(Controller controller) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();
        ArrayList<Artista> artistiFiltrati = new ArrayList<>();

        for (Artista artistaNelDataBase : artistiNelDataBase) {
            modelloLista.addElement(artistaNelDataBase.getNomeArte());
            artistiFiltrati.add(artistaNelDataBase);
        }

        listaArtisti.setModel(modelloLista);

        return artistiFiltrati;
    }


    //Funzioni Listeners

    /**
     * Gestisce la chiusura della finestra attuale e il ripristino della visibilità della finestra chiamante.
     *
     * @param frameChiamante La finestra chiamante da mostrare nuovamente.
     * @param frame          La finestra corrente da chiudere (dispose).
     */
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     * Filtra la Lista di Artisti a partire da una stringa di testo.
     *
     * @param controller   L'istanza del Controller per gestire l'interazione con il database.
     * @param testoCercato La stringa di testo necessaria a filtrare gli Artisti
     */
    private ArrayList<Artista> filtraLista(Controller controller, String testoCercato) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Artista> artistiNelDataBase = controller.getArtistiPresenti();
        ArrayList<Artista> artistiFiltrati = new ArrayList<>();

        for (Artista artista : artistiNelDataBase) {
            String nome = artista.getNomeArte().toLowerCase();

            if (nome.contains(testoCercato.toLowerCase())) {
                modelloLista.addElement(artista.getNomeArte());
                artistiFiltrati.add(artista);
            }
        }
        listaArtisti.setModel(modelloLista);

        return artistiFiltrati;
    }

    /**
     * Istanzia e inizializza una nuova finestra grafica per il ProfiloArtista dell'artita selezionato.
     *
     * @param controller    L'istanza del Controller per gestire l'interazione con il database.
     * @param frame         La finestra corrente da nascondere
     * @param utenteAttuale L'utente attualmente loggato nel sistema
     */
    private void newProfiloArtista(Controller controller, JFrame frame, Utente utenteAttuale) {
        try {
            new ProfiloArtista(controller, frame, artisti.get(listaArtisti.getSelectedIndex()), utenteAttuale);
            frame.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore nella selezione dell'artista");
        }
    }
}
