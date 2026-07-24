package gui;

import controller.Controller;
import model.Proposta;
import model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Rappresenta l'interfaccia grafica riservata agli admin per visualizzare e valutare le proposte inviate dagli utenti.
 * Mostra uno storico delle proposte già valutate tramite una tabella e una lista di quelle ancora in attesa di giudizio.
 */
public class ValutaProposteAdmin {
    /**
     * The Frame.
     */
    private JFrame frame;
    private JPanel mainPanel;
    private JTable valutateTable;
    private JList proposteList;
    private JButton visualizzaButton;
    private JButton indietroButton;


    /**
     * Istanzia e inizializza la schermata per la valutazione delle proposte, popolando la tabella e la lista.
     *
     * @param controller     L'istanza del Controller per recuperare i dati dal database e gestire la logica.
     * @param frameChiamante La finestra precedente (Home) da riattivare premendo il tasto indietro.
     * @param utente         L'admin attualmente connesso.
     */
    public ValutaProposteAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Proposte");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(visualizzaButton);

        riempiTabellaValutate(controller);
        riempiListaDaValutare(controller);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Tasto indietro
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Tasto visualizza
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliccatoVisualizza(controller, utente);
            }
        });
    }

    //Riempimento Liste e Tabelle
    //Valutate

    /**
     * Popola la tabella visiva con lo storico delle proposte che sono già state valutate in passato.
     *
     * @param controller L'istanza del Controller per ottenere le righe formattate da inserire nella tabella.
     */
    private void riempiTabellaValutate(Controller controller) {
        String[] nomiColonne = {"Tipo Elemento", "Titolo Elemento", "Stato Proposta", "Utente"};
        DefaultTableModel modelloTabella = new DefaultTableModel(nomiColonne, 0);

        ArrayList<Object[]> listaRighe = controller.creaRigheTabella();
        for (Object[] riga : listaRighe) {
            modelloTabella.addRow(riga);
        }

        valutateTable.setModel(modelloTabella);
    }

    //Da Valutare

    /**
     * Popola la lista visiva con le proposte ancora in attesa di valutazione da parte di un admin.
     *
     * @param controller L'istanza del Controller per recuperare dal database l'elenco delle proposte pendenti.
     */
    private void riempiListaDaValutare(Controller controller) {
        DefaultListModel modelloLista = new DefaultListModel<>();
        ArrayList<Proposta> proposteDaValutare = controller.getProposteDaValutare();

        for (Proposta proposta : proposteDaValutare) {
            modelloLista.addElement(proposta);
        }

        proposteList.setModel(modelloLista);
    }


    //Funzioni Listeners
    //Indietro

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

    //Visualizza

    /**
     * Apre la schermata di dettaglio per la proposta selezionata, permettendo all'admin di accettarla o rifiutarla.
     *
     * @param controller    L'istanza del Controller da passare alla finestra successiva.
     * @param utenteAttuale L'admin loggato che sta eseguendo la valutazione e di cui si deve mantenere la sessione.
     */
    private void cliccatoVisualizza(Controller controller, Utente utenteAttuale) {
        try {
            Proposta propostaDaValutare = (Proposta) proposteList.getSelectedValue();
            new VerificaProposta(controller, frame, propostaDaValutare, utenteAttuale);
            frame.setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errore nella selezione della proposta");
        }
    }
}