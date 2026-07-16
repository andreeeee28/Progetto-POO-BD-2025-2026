package model;

public class Canzone {
    private String titolo;
    private int durataSecondi;
    private Album albumDiAppartenenza;

    public Canzone (String titolo, int durataSecondi) throws CampoNonValido{
        setTitolo(titolo);
        setDurataSecondi(durataSecondi);
    }
    // Getter

    public String getDurataMinutiSecondi() {
        int minuti =   durataSecondi / 60;
        int secondi =  durataSecondi % 60;
        return String.format("%d:%02d", minuti, secondi);
    }

    public int getDurataSecondi(){
        return durataSecondi;
    }

    public String getTitolo() {
        return titolo;
    }

    public Album getAlbumDiAppartenenza() {return albumDiAppartenenza;}

    // Setter

    public void setDurataSecondi(int durataSecondi) throws CampoNonValido{
        if(durataSecondi<1){
            throw new CampoNonValido("Durata della traccia non valida");
        }
        this.durataSecondi = durataSecondi;
    }

    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>50){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 50!");
        }
        this.titolo = titolo;
    }

    public void setAlbumDiAppartenenza(Album albumDiAppartenenza) throws CampoNonValido {
        if(albumDiAppartenenza == null ){
            throw new CampoNonValido("l' album di appartenenza della canzone non può essere null");

        }
        this.albumDiAppartenenza = albumDiAppartenenza;
    }

}
