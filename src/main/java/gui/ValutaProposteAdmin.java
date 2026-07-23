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
    private JPanel mainPanel;
    private JTable valutateTable;
    private JList proposteList;
    private JButton visualizzaButton;
    private JButton indietroButton;
    JFrame frame;

    /**
     * Istanzia e inizializza la schermata per la valutazione delle proposte, popolando la tabella e la lista.
     *
     * @param controller L'istanza del Controller per recuperare i dati dal database e gestire la logica.
     * @param frameChiamante La finestra precedente (Home) da riattivare premendo il tasto indietro.
     * @param utente L'admin attualmente connesso.
     */
    public ValutaProposteAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("Proposte");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configuraElementi(controller);
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
                try {
                    Proposta propostaDaValutare = (Proposta) proposteList.getSelectedValue();
                    if (propostaDaValutare.getAutoreProposta().getUsername().equals(utente.getUsername())){
                        JOptionPane.showMessageDialog(null,"Non puoi valutare le proposte inviate da te");
                        return;
                    }
                    new VerificaProposta(controller, frame, propostaDaValutare, utente);
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore nella selezione della proposta");
                }
            }
        });
    }

    /**
     * Popola la tabella con lo storico delle proposte già valutate e la lista con quelle in attesa di valutazione.
     *
     * @param controller L'istanza del Controller per ottenere le righe della tabella e l'elenco delle proposte.
     */
    private void configuraElementi(Controller controller) {
        //Configurazione tabella Valutate
        String[] nomiColonne = {"Tipo Elemento", "Titolo Elemento", "Stato Proposta", "Utente"};
        DefaultTableModel modelloTabella = new DefaultTableModel(nomiColonne, 0);

        ArrayList<Object[]> listaRighe = controller.creaRigheTabella();
        for (Object[] riga : listaRighe) {
            modelloTabella.addRow(riga);
        }
        valutateTable.setModel(modelloTabella);

        //configurazione tabella Proposte
        DefaultListModel modelloLista = new DefaultListModel<>();
        ArrayList<Proposta> proposteDaValutare = controller.getProposteDaValutare();
        for (Proposta proposta : proposteDaValutare) {
            modelloLista.addElement(proposta);
        }
        proposteList.setModel(modelloLista);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }
}
