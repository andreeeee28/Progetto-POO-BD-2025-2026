package model;

public class Canzone {
    private String titolo;
    private int durataSecondi;
    private Album albumDiAppartenenza;

    public Canzone (String titolo, int durataSecondi) throws CampoNonValido{
        setTitolo(titolo);
        setDurataSecondi(durataSecondi);
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

    public void setDurataSecondi(int durataSecondi) throws CampoNonValido{
        if(durataSecondi<1){
            throw new CampoNonValido("Durata della traccia non valida");
        }
        this.durataSecondi = durataSecondi;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>30){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 30!");
        }
        this.titolo = titolo;
    }

    public void setAlbumDiAppartenenza(Album albumDiAppartenenza) throws CampoNonValido {
        if(albumDiAppartenenza == null ){
            throw new CampoNonValido("l' album di appartenenza della canzone non può essere null");

        }
        this.albumDiAppartenenza = albumDiAppartenenza;
    }

    public Album getAlbumDiAppartenenza() {
        return albumDiAppartenenza;
    }
}
