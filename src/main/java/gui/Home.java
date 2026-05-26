package gui;

import controller.Controller;

import javax.swing.*;

public class Home {
    private JPanel mainPanel;
    private JButton creaPropostaButton;
    private JButton esploraArtistiButton;
    private JButton esploraGeneriButton;

    public Home(Controller controller,JFrame frameChiamante){
        JFrame frame = new JFrame("Home");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    };
}
