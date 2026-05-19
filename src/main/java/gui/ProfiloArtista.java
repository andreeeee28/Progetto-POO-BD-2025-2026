package gui;

import javax.swing.*;

public class ProfiloArtista {
    private JPanel mainPanel;
    private JTextArea Biografia;
    private JList listaAlbum;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ProfiloArtista");
        frame.setContentPane(new ProfiloArtista().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
