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
 * The type Aggiungi generi admin.
 */
public class AggiungiGeneriAdmin {
    private JPanel mainPanel;
    private JTextField textFieldNomeGenere;
    private JTextArea textAreaDescrizione;
    private JList generiPadreList;
    private JList sottoGeneriList;
    private JButton creaButton;
    private JButton indietroButton;
    /**
     * The Frame.
     */
    JFrame frame;

    /**
     * Instantiates a new Aggiungi generi admin.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param utente         the utente
     */
    public AggiungiGeneriAdmin(Controller controller, JFrame frameChiamante, Utente utente) {
        frame = new JFrame("AggiungiGeneriAdmin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(creaButton);


        riempiListeGeneri(controller);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        //Tasto indietro
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(frameChiamante, frame);
            }
        });

        creaButton.addActionListener(new ActionListener() {
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

    private void riempiListeGeneri(Controller controller){
        DefaultListModel<Genere> modelGenere = new DefaultListModel<>();
        ArrayList<Genere> generiPresenti = controller.getGeneriPresenti();

        for(Genere genere : generiPresenti){
            modelGenere.addElement(genere);
        }
        sottoGeneriList.setModel(modelGenere);
        generiPadreList.setModel(modelGenere);
        sottoGeneriList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        generiPadreList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }


    //Funzioni Listeners
    //Indietro
    private void indietro(JFrame frameChiamante, JFrame frame) {
        frameChiamante.setLocationRelativeTo(null);
        frameChiamante.setVisible(true);
        frame.dispose();
    }

    /**
     *
     * @param controller
     * @param frameChiamante
     * @param utente
     * @throws CampoNonValido
     */
    private void  cliccatoCreaGenereButton(Controller controller,JFrame frameChiamante, Utente utente) throws CampoNonValido{
        //Prelevamento informazioni
        String nomeGenere = textFieldNomeGenere.getText();
        String descrizioneGenere = textAreaDescrizione.getText();
        Genere nuovoGenere = new Genere(nomeGenere,descrizioneGenere);

        //Inserimento generi
        ArrayList<Genere> generiPadriSelezionati = new ArrayList<>(generiPadreList.getSelectedValuesList());
        ArrayList<Genere> generiFigliSelezionati = new ArrayList<>(sottoGeneriList.getSelectedValuesList());
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
        indietro(frameChiamante, frame);
    }

}
