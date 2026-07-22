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
    private JList listGeneriPadre;
    private JList listGeneriFigli;
    private JButton creaGenereButton;
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
     *
     * @param controller
     * @param frameChiamante
     * @param utente
     * @throws CampoNonValido
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
