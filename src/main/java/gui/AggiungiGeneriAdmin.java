package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Genere;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Rappresenta l'interfaccia grafica (GUI) riservata agli amministratori per l'aggiunta di un nuovo genere musicale.
 * Permette di definire il nome e la descrizione del genere, e di stabilire eventuali relazioni gerarchiche
 * selezionando i generi padre e i generi figli (sottogeneri) già presenti nel sistema.
 */
public class AggiungiGeneriAdmin {
    private JPanel mainPanel;
    private JTextField textFieldNomeGenere;
    private JTextArea textAreaDescrizione;
    private JList listGeneriPadre;
    private JList listGeneriFigli;
    private JButton creaGenereButton;
    /**
     * The Frame.
     */
    JFrame frame;

    /**
     * Istanzia e inizializza la schermata per la creazione di un nuovo genere, popolando le liste di selezione.
     *
     * @param controller L'istanza del Controller per interrogare il database fittizio e gestire la logica.
     * @param frameChiamante La finestra precedente da cui è stata aperta questa schermata.
     * @param utente L'utente (Admin) attualmente loggato nel sistema.
     */
    public AggiungiGeneriAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("AggiungiGeneriAdmin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ArrayList<Genere> generiPresenti = controller.getGeneriPresenti();
        DefaultListModel<Genere> modelGenere = new DefaultListModel<>();
        for(Genere genere : generiPresenti){
            modelGenere.addElement(genere);
        }
        listGeneriFigli.setModel(modelGenere);
        listGeneriPadre.setModel(modelGenere);
        listGeneriFigli.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listGeneriPadre.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        creaGenereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliccatoCreaGenereButton(controller,frameChiamante,utente);

                } catch (CampoNonValido ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    // Cattura qualsiasi altro errore imprevisto
                    JOptionPane.showMessageDialog(null, "Si è verificato un errore durante la creazione del genere.");
                }
            }
        });
    }

    /**
     * Raccoglie i dati del form, istanzia il nuovo genere, imposta le relazioni bidirezionali con padri e figli e lo salva.
     *
     * @param controller L'istanza del Controller per la validazione, la scrittura sul database e la gestione della logica.
     * @param frameChiamante La finestra originaria passata nel costruttore.
     * @param utente L'amministratore loggato per il ritorno alla schermata Home.
     * @throws CampoNonValido Se i campi di testo non rispettano i vincoli di validazione o se la verifica del dominio fallisce.
     */
    private void  cliccatoCreaGenereButton(Controller controller,JFrame frameChiamante, Utente utente) throws CampoNonValido{
        String nomeGenere = textFieldNomeGenere.getText();
        String descrizioneGenere = textAreaDescrizione.getText();
        Genere nuovoGenere = new Genere(nomeGenere,descrizioneGenere);
        ArrayList<Genere> generiPadriSelezionati = new ArrayList<>(listGeneriPadre.getSelectedValuesList());
        ArrayList<Genere> generiFigliSelezionati = new ArrayList<>(listGeneriFigli.getSelectedValuesList());
        if(!generiFigliSelezionati.isEmpty()){
            for(Genere genere : generiFigliSelezionati){
                genere.addGeneriPadre(nuovoGenere);
                nuovoGenere.addSottogeneri(genere);
            }
        }
        if(!generiPadriSelezionati.isEmpty()){
            for(Genere genere : generiPadriSelezionati){
                genere.addSottogeneri(nuovoGenere);
                nuovoGenere.addGeneriPadre(genere);
            }
        }
        controller.verificaGeneri(nuovoGenere);
        controller.scriviGenereDataBase(nuovoGenere);
        JOptionPane.showMessageDialog(null, "Genere creato con successo!");
        frame.dispose();
        new Home(controller,frame,utente);

    }

}
