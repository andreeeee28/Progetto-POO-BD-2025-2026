package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Proposta;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Verifica proposta.
 */
public class VerificaProposta {
    private JPanel mainPanel;
    private JTextArea textAreaDescrizioneProposta;
    private JButton rifiutaButton;
    private JButton accettaButton;
    private JLabel tipoPropostaLabel;
    private JLabel titoloPropostaLabel;
    private JLabel dataPropostaLabel;
    private JLabel utenteAutoreLabel;
    private JButton indietroButton;
    private JLabel tipoLabel;
    private JLabel titoloLabel;
    private JLabel dataLabel;
    private JLabel utenteLabel;
    private JFrame frame;

    /**
     * Instantiates a new Verifica proposta.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param proposta       the proposta da valutare
     * @param utente         the utente
     */
    public VerificaProposta(Controller controller, JFrame frameChiamante, Proposta proposta, Utente utente) {
        frame = new JFrame("Dettagli Proposta");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configuraElementi(proposta);
        frame.setVisible(true);

        //Tasto indietro
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        //Tasto rifiuta
        rifiutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoRifiutaButton(controller, frameChiamante, utente, proposta);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore inatteso, riprovare");
                }

            }
        });

        //Tasto accetta
        accettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoAccettaButton(controller, frameChiamante, utente, proposta);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Errore imprevisto, riprovare");
                }
            }
        });
    }

    //Configurazione
    private void configuraElementi(Proposta proposta) {
        tipoLabel.setText(proposta.getTipoElemento().toString());
        titoloLabel.setText(proposta.getTitoloElemento());
        dataLabel.setText(proposta.getDataRichiesta().toString());
        utenteLabel.setText(proposta.getAutoreProposta().getUsername());
        textAreaDescrizioneProposta.setText(proposta.getDescrizione());

        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    //Funzioni Listeners
    //Indietro
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    //Accetta
    public void cliccatoAccettaButton(Controller controller, JFrame frameChiamante, Utente utente, Proposta propostaDaValutare) throws CampoNonValido {
        controller.setPropostaAccettaDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");

        indietro(frameChiamante, frame);
    }

    //Rifiuta
    public void cliccatoRifiutaButton(Controller controller, JFrame frameChiamante, Utente utente, Proposta propostaDaValutare) throws CampoNonValido {
        controller.setPropostaRifiutataDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");

        indietro(frameChiamante, frame);
    }
}
