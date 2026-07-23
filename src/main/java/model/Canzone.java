package model;

/**
 * Rappresenta un singolo brano musicale (Canzone) all'interno del sistema.
 * Contiene le informazioni di base come il titolo, la durata in secondi e
 * un riferimento diretto all'album (Album) in cui la traccia è inclusa.
 * Questa classe è ideata come
 */
public class Canzone {
    private String titolo;
    private int durataSecondi;
    private Album albumDiAppartenenza;

    /**
     * Istanzia un nuovo oggetto Canzone assegnando il titolo e la durata.
     * Il riferimento all'album verrà impostato in automatico successivamente, quando la canzone verrà inserita in una tracklist.
     *
     * @param titolo Il titolo del brano musicale (tra 1 e 50 caratteri).
     * @param durataSecondi La durata della canzone espressa in secondi (deve essere maggiore di 0).
     * @throws CampoNonValido Se il titolo o la durata non rispettano i vincoli di validazione dei relativi setter.
     */
    public Canzone (String titolo, int durataSecondi) throws CampoNonValido{
        setTitolo(titolo);
        setDurataSecondi(durataSecondi);
    }

    // Getter

    /**
     * Converte e restituisce la durata della canzone nel formato classico minuti:secondi (es. 3:05).
     *
     * @return La stringa formattata rappresentante la durata (MM:SS).
     */
    public String getDurataMinutiSecondi() {
        int minuti =   durataSecondi / 60;
        int secondi =  durataSecondi % 60;
        return String.format("%d:%02d", minuti, secondi);
    }

    /**
     * Restituisce la durata totale della canzone espressa esclusivamente in secondi.
     *
     * @return La durata esatta in secondi.
     */
    public int getDurataSecondi(){
        return durataSecondi;
    }

    /**
     * Restituisce il titolo della canzone.
     *
     * @return La stringa contenente il titolo del brano.
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Restituisce l'oggetto Album a cui appartiene questa specifica canzone.
     *
     * @return L'album di appartenenza, oppure null se la canzone non è ancora stata agganciata a una tracklist.
     */
    public Album getAlbumDiAppartenenza() {return albumDiAppartenenza;}

    // Setter

    /**
     * Imposta o modifica la durata della canzone in secondi.
     *
     * @param durataSecondi La nuova durata in secondi da assegnare.
     * @throws CampoNonValido Se la durata inserita è inferiore a 1 secondo.
     */
    public void setDurataSecondi(int durataSecondi) throws CampoNonValido{
        if(durataSecondi<1){
            throw new CampoNonValido("Durata della traccia non valida");
        }
        this.durataSecondi = durataSecondi;
    }

    /**
     * Imposta o modifica il titolo della canzone.
     *
     * @param titolo Il nuovo titolo da assegnare al brano.
     * @throws CampoNonValido Se il titolo è nullo, vuoto o supera i 50 caratteri di lunghezza.
     */
    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>50){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 50!");
        }
        this.titolo = titolo;
    }

    /**
     * Collega la canzone all'album in cui è contenuta.
     *
     * @param albumDiAppartenenza L'oggetto Album a cui associare il brano.
     * @throws CampoNonValido Se il parametro album passato risulta nullo.
     */
    public void setAlbumDiAppartenenza(Album albumDiAppartenenza) throws CampoNonValido {
        if(albumDiAppartenenza == null ){
            throw new CampoNonValido("l' album di appartenenza della canzone non può essere null");

        }
        this.albumDiAppartenenza = albumDiAppartenenza;
    }

}