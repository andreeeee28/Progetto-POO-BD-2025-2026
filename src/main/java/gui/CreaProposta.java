package gui;

import controller.Controller;
import model.TipoProposta;

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

    public CreaProposta(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Crea proposta");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        tipoPropostaComboBox.setModel(new DefaultComboBoxModel<>(TipoProposta.values()));


        //Tasto invia proposta
        inviaPropostaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoProposta tipoSelezionato = (TipoProposta) tipoPropostaComboBox.getSelectedItem();
                String titoloInserito = titoloTextField.getText();
                String descrizioneInserita = descrzioneTextField.getText();

            }
        });
    }
}
