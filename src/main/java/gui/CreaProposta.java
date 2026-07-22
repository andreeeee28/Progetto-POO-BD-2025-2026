package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Proposta;
import model.TipoProposta;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Crea proposta.
 */
public class CreaProposta {
    private JTextField titoloTextField;
    private JTextArea descrzioneTextArea;
    private JComboBox<TipoProposta> tipoPropostaComboBox;
    private JButton inviaPropostaButton;
    private JPanel mainPanel;
    private JButton tornaAllaHomeButton;
    private JFrame frame;

    /**
     * Instantiates a new Crea proposta.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utenteAttuale  the utente attuale
     */
    public CreaProposta(Controller controller, JFrame frameChiamante, Utente utenteAttuale) {
        frame = new JFrame("Crea proposta");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(inviaPropostaButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tipoPropostaComboBox.setModel(new DefaultComboBoxModel<>(TipoProposta.values()));


        //Tasto torna alla home
        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Tasto invia proposta
        inviaPropostaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoInviaPropostaButton(controller, frameChiamante, utenteAttuale);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
                }
            }
        });

    }

    //Funzioni Listeners
    //Indietro
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    //Invia proposta
    private void cliccatoInviaPropostaButton(Controller controller, JFrame frameChiamante, Utente utenteAttuale) throws CampoNonValido {
        try {
            //Prelievo Dati
            TipoProposta tipoSelezionato = (TipoProposta) tipoPropostaComboBox.getSelectedItem();
            String titoloInserito = titoloTextField.getText();
            String descrizioneInserita = descrzioneTextArea.getText();


            //Creazione Proposta + verifica
            Proposta propostaDaCreare = new Proposta(tipoSelezionato, descrizioneInserita, titoloInserito, utenteAttuale);
            controller.verificaProposta(propostaDaCreare);
            controller.scriviPropostaNelDataBase(propostaDaCreare);

            JOptionPane.showMessageDialog(null, "Proposta inviata con successo!");
        } catch (CampoNonValido ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
        }


        indietro(frameChiamante, frame);
    }
}
