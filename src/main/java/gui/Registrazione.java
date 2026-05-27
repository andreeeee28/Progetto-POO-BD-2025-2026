package gui;

import controller.Controller;
import model.CampoNonValido;
import model.Nazione;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrazione {
    private JPanel mainPanel;
    private JTextField campoUtente;
    private JButton registratiButton;
    private JFrame frame;
    private JTextField campoPassword;
    private JComboBox <Nazione> campoNazione;
    private JButton tornaAlLoginButton;


    public Registrazione(Controller controller, JFrame frameChiamante){
        frame = new JFrame("Registrazione");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        campoNazione.setModel(new DefaultComboBoxModel<>(Nazione.values()));

        //Tasto torna al Login
        tornaAlLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        //Tasto registrati
        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringaCampoUtente = campoUtente.getText();
                String stringaCampoPassword = campoPassword.getText();
                Nazione enumCampoNazione = (Nazione) campoNazione.getSelectedItem();
                try {
                    Utente utenteAttuale = controller.cliccatoRegistrati(stringaCampoUtente,stringaCampoPassword,enumCampoNazione);
                    new Home(controller,frame, utenteAttuale);
                    frame.setVisible(false);
                    javax.swing.JOptionPane.showMessageDialog(null,"Registrazione avvenuta con successo");
                } catch (CampoNonValido ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
}
