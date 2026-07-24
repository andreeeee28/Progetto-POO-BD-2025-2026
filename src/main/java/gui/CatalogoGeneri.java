package gui;

import controller.Controller;
import model.Album;
import model.Genere;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The type Catalogo generi.
 */
public class CatalogoGeneri {
    private JPanel mainPanel;
    private JList listaGeneri;
    private JTextField campoCerca;
    private JButton visualizzaButton;
    private JButton tornaAllaHomeButton;
    private ArrayList<Genere> generi;
    private JFrame frame;


    /**
     * Istanzia e inizializza la finestra grafica per il Catalogo Generi.
     *
     * @param controller     L'istanza del Controller per gestire l'interazione con il database.
     * @param frameChiamante La finestra precedente da cui è stata aperta questa schermata (per permettere di tornare indietro).
     * @param utenteAttuale  L'oggetto Utente (Admin) attualmente loggato nel sistema.
     */
    public CatalogoGeneri(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Catalogo Generi");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(visualizzaButton);


        generi = riempiListaGeneri(controller);

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
                generi = filtraLista(controller, campoCerca.getText());
            }
        });

        //Tasto visualizza genere
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newProfiloGenere(controller, frame, utenteAttuale);
            }
        });
    }

    //Riempimento liste

    /**
     * Riempe la JList di Generi, permettendo all'utente di selezionarli per la navigazione del catalogo.
     *
     * @param controller L'istanza del Controller per gestire l'interazione con il database.
     * @return L'ArrayList di Generi presenti nel DataBase
     */
    private ArrayList<Genere> riempiListaGeneri(Controller controller) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Genere> generiNelDataBase = controller.getGeneriPresenti();
        ArrayList<Genere> generiFiltrati = new ArrayList<>();

        for (Genere genereNelDataBase : generiNelDataBase) {
            modelloLista.addElement(genereNelDataBase.getNome());
            generiFiltrati.add(genereNelDataBase);
        }

        listaGeneri.setModel(modelloLista);

        return generiFiltrati;
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
     * Filtra la Lista di Generi a partire da una stringa di testo.
     *
     * @param controller   L'istanza del Controller per gestire l'interazione con il database.
     * @param testoCercato La stringa di testo necessaria a filtrare i Generi
     */
    private ArrayList<Genere> filtraLista(Controller controller, String testoCercato) {
        DefaultListModel<String> modelloLista = new DefaultListModel<>();

        ArrayList<Genere> generiNelDataBase = controller.getGeneriPresenti();
        ArrayList<Genere> generiFiltrati = new ArrayList<>();

        for (Genere genere : generiNelDataBase) {
            String nome = genere.getNome().toLowerCase();

            if (nome.contains(testoCercato.toLowerCase())) {
                modelloLista.addElement(genere.getNome());
                generiFiltrati.add(genere);
            }
        }
        listaGeneri.setModel(modelloLista);

        return generiFiltrati;
    }

    /**
     * Istanzia e inizializza una nuova finestra grafica per il Profilo del genere selezionato.
     *
     * @param controller    L'istanza del Controller per gestire l'interazione con il database.
     * @param frame         La finestra corrente da nascondere
     * @param utenteAttuale L'utente attualmente loggato nel sistema
     */
    private void newProfiloGenere(Controller controller, JFrame frame, Utente utenteAttuale) {
        try {
            new ProfiloGenere(controller, frame, generi.get(listaGeneri.getSelectedIndex()), utenteAttuale);
            frame.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore nella selezione del genere");
        }
    }
}
