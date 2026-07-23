package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Proposta;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta l'interfaccia grafica che permette a un admin di visualizzare i dettagli di una specifica proposta
 * e di prendere una decisione finale (accettarla o rifiutarla), aggiornando così lo stato del database.
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
     * Istanzia e inizializza la finestra per la verifica, popolando i campi testuali con i dettagli della proposta selezionata.
     *
     * @param controller L'istanza del Controller per aggiornare lo stato della proposta nel database.
     * @param frameChiamante La finestra precedente (solitamente la lista delle proposte da valutare).
     * @param propostaDaValutare L'oggetto Proposta contenente le informazioni che l'admin deve esaminare.
     * @param utente L'admin attualmente connesso al sistema, passato alla Home in caso di ritorno.
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

    /**
     * Gestisce l'accettazione della proposta, aggiorna il database tramite il controller e riporta l'admin alla schermata Home.
     *
     * @param controller L'istanza del Controller per avviare il salvataggio del nuovo stato accettato.
     * @param frameChiamante La finestra precedente di riferimento.
     * @param utente L'admin loggato per ripristinare correttamente la sua sessione nella Home.
     * @param propostaDaValutare La proposta specifica che è stata approvata.
     * @throws CampoNonValido Se si verifica un errore durante l'aggiornamento dello stato o l'interazione col database.
     */
    public void cliccatoAccettaButton(Controller controller, JFrame frameChiamante, Utente utente,Proposta propostaDaValutare) throws CampoNonValido {
        controller.setPropostaAccettaDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");
        frame.dispose();
        new Home(controller, frameChiamante, utente);

    }

    /**
     * Gestisce il rifiuto della proposta, aggiorna il database tramite il controller e riporta l'admin alla schermata Home.
     *
     * @param controller L'istanza del Controller per avviare il salvataggio del nuovo stato rifiutato.
     * @param frameChiamante La finestra precedente di riferimento.
     * @param utente L'admin loggato per ripristinare correttamente la sua sessione nella Home.
     * @param propostaDaValutare La proposta specifica che è stata respinta.
     * @throws CampoNonValido Se si verifica un errore durante l'aggiornamento dello stato o l'interazione col database.
     */
    public void cliccatoRifiutaButton(Controller controller,JFrame frameChiamante,Utente utente,Proposta propostaDaValutare) throws CampoNonValido{
        controller.setPropostaRifiutataDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");
        frame.dispose();
        new Home(controller, frameChiamante, utente);
    }
}
