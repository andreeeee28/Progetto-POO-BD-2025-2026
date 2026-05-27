package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Proposta;
import model.TipoProposta;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaProposta {
    private JTextField titoloTextField;
    private JTextField descrzioneTextField;
    private JComboBox<TipoProposta> tipoPropostaComboBox;
    private JButton inviaPropostaButton;
    private JPanel mainPanel;
    private JFrame frame;

    public CreaProposta(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Crea proposta");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tipoPropostaComboBox.setModel(new DefaultComboBoxModel<>(TipoProposta.values()));


        //Tasto invia proposta
        inviaPropostaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoProposta tipoSelezionato = (TipoProposta) tipoPropostaComboBox.getSelectedItem();
                String titoloInserito = titoloTextField.getText();
                String descrizioneInserita = descrzioneTextField.getText();

                try {
                    controller.CreaProposta(tipoSelezionato, descrizioneInserita, titoloInserito, utenteAttuale);
                    javax.swing.JOptionPane.showMessageDialog(null, "Proposta inviata con successo!");
                    frameChiamante.setVisible(true);
                    frame.dispose();
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }
}
