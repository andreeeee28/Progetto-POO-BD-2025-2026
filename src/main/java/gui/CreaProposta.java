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
 * Rappresenta l'interfaccia grafica che permette a un utente di creare e inviare una nuova proposta
 * (ad esempio l'aggiunta di un nuovo album, artista o genere) al sistema, affinché venga successivamente valutata da un amministratore.
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
     * Istanzia e inizializza la finestra per la creazione di una nuova proposta, popolando il menu a tendina delle categorie.
     *
     * @param controller     L'istanza del Controller per gestire l'interazione con il database.
     * @param frameChiamante La finestra precedente (Home) da riattivare una volta chiusa questa schermata o annullata l'operazione.
     * @param utenteAttuale  L'utente che sta attualmente utilizzando il sistema e che figurerà come autore della proposta.
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
     * Raccoglie i dati inseriti nel form, istanzia una nuova Proposta, ne verifica la validità tramite il controller
     * e infine la salva nel database fittizio, chiudendo poi la finestra.
     *
     * @param controller     L'istanza del Controller per validare e registrare la proposta.
     * @param frameChiamante La finestra originaria da rendere nuovamente visibile al termine dell'operazione.
     * @param utenteAttuale  L'utente autore della proposta.
     * @throws CampoNonValido Se la descrizione o il titolo non rispettano i vincoli di lunghezza o se la proposta non è valida.
     */
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
