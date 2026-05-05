package model;

public class Canzone {
    private String titolo;
    private int durataSecondi;

    public Canzone (String titolo, int durataSecondi) {
        this.titolo = titolo;
        this.durataSecondi = durataSecondi;
    }

    public String getDurataMinutiSecondi() {
        /* Seppure l ' attributo è la durata in secondi voglio che ci sia la
        possibilità di visualizzare la durata in minuti e secondi */
        int minuti =   durataSecondi / 60;
        int secondi =  durataSecondi % 60;
        return String.format("%d:%02d", minuti, secondi);
    }

    public int getDurataSecondi(){
        return durataSecondi;
    }

    public void setDurataSecondi(int durataSecondi) {
        this.durataSecondi = durataSecondi;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
