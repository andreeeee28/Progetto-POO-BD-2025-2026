package gui;

import controller.Controller;

import javax.swing.*;

public class CatalogoArtisti {
    private JPanel mainPanel;
    private JList listaArtisti;
    private JTextField campoCerca;
    private JButton cercaButton;
    private JButton visualizzaButton;
    private JFrame frame;


   public CatalogoArtisti(Controller controller, JFrame frameChiamante) {
       frame = new JFrame("Catalogo Artisti");
       frame.setContentPane(mainPanel);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
    }


}
