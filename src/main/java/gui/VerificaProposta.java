package gui;

import controller.Controller;
import model.Proposta;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerificaProposta {
    private JPanel mainPanel;
    private JTextArea textAreaDescrizioneProposta;
    private JButton rifiutaButton;
    private JButton accettaButton;
    private JLabel labelTipoProposta;
    private JLabel labelTitoloProposta;
    private JLabel labelDataProposta;
    private JLabel labelUtente;
    private JLabel labelDescrizione;
    private JFrame frame;

    public VerificaProposta(Controller controller, JFrame frameChiamante, Proposta propostaDaValutare, Utente utente) {
        frame = new JFrame("VerificaProposta");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        labelTipoProposta.setText("Tipo Proposta : " + propostaDaValutare.getTipoElemento());
        labelTitoloProposta.setText("Titolo Proposta : " + propostaDaValutare.getTitoloElemento());
        labelDataProposta.setText("Data Proposta : " + propostaDaValutare.getDataRichiesta());
        labelUtente.setText("Utente da cui è stata inviata la proposta : " + propostaDaValutare.getAutoreProposta().getUsername());
        textAreaDescrizioneProposta.setText(propostaDaValutare.getDescrizione()
        );


        accettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setPropostaAccettaDataBase(propostaDaValutare);
                JOptionPane.showMessageDialog(null,"Proposta aggiornata con Successo");
                frame.dispose();
                new Home (controller,frameChiamante,utente);


            }
        });
        rifiutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setPropostaRifiutataDataBase(propostaDaValutare);
                JOptionPane.showMessageDialog(null,"Proposta aggiornata con Successo");
                frame.dispose();
                new Home (controller,frameChiamante,utente);

            }
        });
    }
}
