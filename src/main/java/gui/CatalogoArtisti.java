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


   public CatalogoArtisti(Controller controller,JFrame frameChiamante) {
       frame = new JFrame("CatalogoArtisti");
       frame.setContentPane(mainPanel);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.pack();
       frame.setVisible(true);

       

    }


}
