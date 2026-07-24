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
     * Istanzia e inizializza la finestra per la verifica, popolando i campi testuali con i dettagli della proposta selezionata.
     *
     * @param controller     L'istanza del Controller per aggiornare lo stato della proposta nel database.
     * @param frameChiamante La finestra precedente (solitamente la lista delle proposte da valutare).
     * @param proposta       L'oggetto Proposta contenente le informazioni che l'admin deve esaminare.
     * @param utente         L'admin attualmente connesso al sistema.
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

    /**
     * Configura e popola i campi testuali della finestra con le informazioni estratte dalla proposta selezionata.
     *
     * @param proposta La proposta specifica da cui prelevare i dati (tipo, titolo, data, autore, descrizione).
     */
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

    /**
     * Gestisce la chiusura della finestra attuale e il ripristino della visibilità della finestra chiamante.
     *
     * @param frameChiamante La finestra chiamante da mostrare nuovamente.
     * @param frame          La finestra corrente da chiudere (dispose).
     */
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     * Gestisce l'accettazione della proposta, aggiorna il database tramite il controller e riporta l'admin alla schermata precedente.
     *
     * @param controller         L'istanza del Controller per avviare il salvataggio del nuovo stato accettato.
     * @param frameChiamante     La finestra precedente di riferimento da ripristinare.
     * @param utente             L'admin loggato che compie l'operazione.
     * @param propostaDaValutare La proposta specifica che è stata approvata.
     * @throws CampoNonValido Se si verifica un errore durante l'aggiornamento dello stato o l'interazione col database.
     */
    public void cliccatoAccettaButton(Controller controller, JFrame frameChiamante, Utente utente, Proposta propostaDaValutare) throws CampoNonValido {
        controller.setPropostaAccettaDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");

        new Home(controller,frame,utente);
        frame.setVisible(false);
    }

    /**
     * Gestisce il rifiuto della proposta, aggiorna il database tramite il controller e riporta l'admin alla schermata precedente.
     *
     * @param controller         L'istanza del Controller per avviare il salvataggio del nuovo stato rifiutato.
     * @param frameChiamante     La finestra precedente di riferimento da ripristinare.
     * @param utente             L'admin loggato che compie l'operazione.
     * @param propostaDaValutare La proposta specifica che è stata respinta.
     * @throws CampoNonValido Se si verifica un errore durante l'aggiornamento dello stato o l'interazione col database.
     */
    public void cliccatoRifiutaButton(Controller controller, JFrame frameChiamante, Utente utente, Proposta propostaDaValutare) throws CampoNonValido {
        controller.setPropostaRifiutataDataBase(propostaDaValutare);
        JOptionPane.showMessageDialog(null, "Proposta aggiornata con Successo");

        new Home(controller,frame,utente);
        frame.setVisible(false);
    }
}