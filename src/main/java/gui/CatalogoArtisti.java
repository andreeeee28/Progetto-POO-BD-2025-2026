package gui;

import javax.swing.*;

public class CatalogoArtisti {
    private JPanel mainPanel;
    private JList list1;
    private JTextField textField1;
    private JButton cercaButton;
    private JButton visualizzaButton;


    public static void main(String[] args) {
        JFrame frame = new JFrame("CatalogoArtisti");
        frame.setContentPane(new CatalogoArtisti().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
