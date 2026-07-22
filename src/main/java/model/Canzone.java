package model;

/**
 * The type Canzone.
 */
public class Canzone {
    private String titolo;
    private int durataSecondi;
    private Album albumDiAppartenenza;

    /**
     * Instantiates a new Canzone.
     *
     * @param titolo        the titolo
     * @param durataSecondi the durata secondi
     * @throws CampoNonValido the campo non valido
     */
    public Canzone (String titolo, int durataSecondi) throws CampoNonValido{
        setTitolo(titolo);
        setDurataSecondi(durataSecondi);
    }
    // Getter

    /**
     * Gets durata minuti secondi.
     *
     * @return the durata minuti secondi
     */
    public String getDurataMinutiSecondi() {
        int minuti =   durataSecondi / 60;
        int secondi =  durataSecondi % 60;
        return String.format("%d:%02d", minuti, secondi);
    }

    /**
     * Get durata secondi int.
     *
     * @return the int
     */
    public int getDurataSecondi(){
        return durataSecondi;
    }

    /**
     * Gets titolo.
     *
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Gets album di appartenenza.
     *
     * @return the album di appartenenza
     */
    public Album getAlbumDiAppartenenza() {return albumDiAppartenenza;}

    // Setter

    /**
     * Sets durata secondi.
     *
     * @param durataSecondi the durata secondi
     * @throws CampoNonValido the campo non valido
     */
    public void setDurataSecondi(int durataSecondi) throws CampoNonValido{
        if(durataSecondi<1){
            throw new CampoNonValido("Durata della traccia non valida");
        }
        this.durataSecondi = durataSecondi;
    }

    /**
     * Sets titolo.
     *
     * @param titolo the titolo
     * @throws CampoNonValido the campo non valido
     */
    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>50){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 50!");
        }
        this.titolo = titolo;
    }

    /**
     * Sets album di appartenenza.
     *
     * @param albumDiAppartenenza the album di appartenenza
     * @throws CampoNonValido the campo non valido
     */
    public void setAlbumDiAppartenenza(Album albumDiAppartenenza) throws CampoNonValido {
        if(albumDiAppartenenza == null ){
            throw new CampoNonValido("l' album di appartenenza della canzone non può essere null");

        }
        this.albumDiAppartenenza = albumDiAppartenenza;
    }

}
