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
 * The type Valuta proposte admin.
 */
public class ValutaProposteAdmin {
    private JFrame frame;
    private JPanel mainPanel;
    private JTable valutateTable;
    private JList proposteList;
    private JButton visualizzaButton;
    private JButton indietroButton;


    /**
     * Instantiates a new Valuta proposte admin.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utenteAttuale  the utenteAttuale
     */
    public ValutaProposteAdmin(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Proposte");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                cliccatoVisualizza(controller, utenteAttuale);
            }
        });
    }

    //Riempimento Liste e Tabelle
    //Valutate
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
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    //Visualizza
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
