package gui;

import controller.Controller;
import model.Proposta;
import model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

public class ValutaProposteAdmin {
    private JPanel mainPanel;
    private JTable tableProposte;
    private JList listaProposte;
    private JButton vediDettagliButton;
    private JLabel labelProposteVerificate;
    private JLabel labelProposteDaValutare;
    private JLabel labelPulsante;
    JFrame frame;


    public ValutaProposteAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("ValutaProposteAdmin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        String[] nomiColonne = {"Tipo Elemento", "Titolo Elemento", "Stato Proposta", "Utente"};
        DefaultTableModel modelloTabella = new DefaultTableModel(nomiColonne, 0);
        ArrayList<Object[]> listaRighe = controller.creaRicheTabella();
        for(Object[] riga : listaRighe){
            modelloTabella.addRow(riga);
        }
        tableProposte.setModel(modelloTabella);
        DefaultListModel modelloLista = new DefaultListModel<>();
        ArrayList<Proposta> proposteDaValutare = controller.getProposteDaValutare();
        for(Proposta proposta : proposteDaValutare){
            modelloLista.addElement(proposta);
        }
        listaProposte.setModel(modelloLista);


        vediDettagliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Proposta propostaDaValutare = (Proposta) listaProposte.getSelectedValue();
                    new VerificaProposta(controller,frame,propostaDaValutare,utente);
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Errore nella selezione della proposta");
                }
            }
        });
    }
}
