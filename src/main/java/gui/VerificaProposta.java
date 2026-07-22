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
    private JLabel labelTipoProposta;
    private JLabel labelTitoloProposta;
    private JLabel labelDataProposta;
    private JLabel labelUtente;
    private JFrame frame;

    /**
     * Instantiates a new Verifica proposta.
     *
     * @param controller         the controller
     * @param frameChiamante     the frame chiamante
     * @param propostaDaValutare the proposta da valutare
     * @param utente             the utente
     */
    public VerificaProposta(Controller controller, JFrame frameChiamante, Proposta propostaDaValutare, Utente utente) {
        frame = new JFrame("Dettagli Proposta");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelTipoProposta.setText("Tipo Proposta : " + propostaDaValutare.getTipoElemento());
        labelTitoloProposta.setText("Titolo Proposta : " + propostaDaValutare.getTitoloElemento());
        labelDataProposta.setText("Data Proposta : " + propostaDaValutare.getDataRichiesta());
        labelUtente.setText("Utente da cui è stata inviata la proposta : " + propostaDaValutare.getAutoreProposta().getUsername());
        textAreaDescrizioneProposta.setText(propostaDaValutare.getDescrizione());

        frame.pack();
        frame.setVisible(true);

        //Tasto rifiuta
        rifiutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    cliccatoRifiutaButton(controller,frameChiamante,utente,propostaDaValutare);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null,ex.getMessage());
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null,"Errore inatteso, riprovare");
                }

            }
        });

        //Tasto accetta
        accettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    cliccatoAccettaButton(controller,frameChiamante,utente,propostaDaValutare);
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null,ex.getMessage());
                } catch(Exception ex){
                    javax.swing.JOptionPane.showMessageDialog(null,"Errore imprevisto, riprovare");
                }
            }
        });
    }
    public void cliccatoAccettaButton(Controller controller, JFrame frameChiamante, Utente utente,Proposta propostaDaValutare) throws CampoNonValido {
        controller.setPropostaAccettaDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");
        frame.dispose();
        new Home(controller, frameChiamante, utente);

    }
    public void cliccatoRifiutaButton(Controller controller,JFrame frameChiamante,Utente utente,Proposta propostaDaValutare) throws CampoNonValido{
        controller.setPropostaRifiutataDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");
        frame.dispose();
        new Home(controller, frameChiamante, utente);
    }
}
