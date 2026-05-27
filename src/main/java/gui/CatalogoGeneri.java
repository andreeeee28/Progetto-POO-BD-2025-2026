package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogoGeneri {
    private JPanel mainPanel;
    private JList listaGeneri;
    private JTextField campoCerca;
    private JButton cercaButton;
    private JButton visualizzaButton;
    private JButton tornaAllaHomeButton;
    private JFrame frame;


    public CatalogoGeneri(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Catalogo Generi");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Tasto torna alla home
        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }


}
