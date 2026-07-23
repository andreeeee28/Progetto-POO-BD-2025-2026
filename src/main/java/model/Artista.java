package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe astratta che rappresenta un artista musicale generico all'interno del sistema.
 * Fornisce le caratteristiche e i comportamenti comuni condivisi dalle sue sottoclassi ( Musicista e Band),
 * tra cui il nome d'arte, l'anno di inizio attività, un identificativo univoco e la discografia degli album pubblicati.
 * L'aggiunta diretta di un nuovo artista al sistema è una prerogativa esclusiva degli admin;
 * gli utenti comuni possono unicamente inviare una proposta di inserimento, che dovrà poi essere valutata da un Admin.
 */
public abstract class Artista {
    private String nomeArte;
    private int annoInizioAttivita;
    private String idArtista;
    private ArrayList<Album> albumPubblicati;

    /**
     * Inizializza un nuovo oggetto Artista assegnando i parametri principali e creando una lista vuota per gli album.
     *
     * @param nomeArte Il nome d'arte dell'artista (tra 1 e 30 caratteri).
     * @param annoInizioAttivita L'anno in cui l'artista ha iniziato la sua carriera musicale.
     * @param idArtista L'identificativo alfanumerico univoco associato all'artista.
     * @throws CampoNonValido Se uno dei parametri forniti non rispetta i vincoli di validazione dei relativi setter.
     */
    public Artista(String nomeArte, int annoInizioAttivita, String idArtista) throws CampoNonValido{
        setNomeArte(nomeArte);
        setAnnoInizioAttivita(annoInizioAttivita);
        setIdArtista(idArtista);
        this.albumPubblicati = new ArrayList<Album>();
    }

    //Getter

    /**
     * Restituisce il nome d'arte dell'artista.
     *
     * @return La stringa contenente il nome d'arte dell'artista.
     */
    public String getNomeArte() {
        return nomeArte;
    }

    /**
     * Restituisce l'anno in cui l'artista ha iniziato la propria attività musicale.
     *
     * @return L'anno di inizio attività (int).
     */
    public int getAnnoInizioAttivita() {
        return annoInizioAttivita;
    }

    /**
     * Restituisce l'identificativo univoco dell'artista.
     *
     * @return La stringa contenente l'ID dell'artista.
     */
    public String getIdArtista() {
        return idArtista;
    }

    /**
     * Restituisce la cronologia degli album pubblicati dall'artista.
     *
     * @return L'ArrayList contenente gli oggetti Album associati all'artista.
     */
    public ArrayList<Album> getAlbumPubblicati(){
        return albumPubblicati;
    }

    // Setter

    /**
     * Imposta o modifica il nome d'arte dell'artista.
     *
     * @param nomeArte Il nuovo nome d'arte da assegnare.
     * @throws CampoNonValido Se il nome è nullo, vuoto o supera i 30 caratteri di lunghezza.
     */
    public void setNomeArte(String nomeArte) throws CampoNonValido {
        if(nomeArte == null ||  nomeArte.trim().length()<1 || nomeArte.trim().length()>30){
            throw new CampoNonValido("Il Nome d'Arte deve avere minimo 1 carattere e massimo 30!");
        }
        this.nomeArte = nomeArte;
    }

    /**
     * Imposta o modifica l'anno di inizio attività dell'artista.
     *
     * @param annoInizioAttivita L'anno di debutto da impostare.
     * @throws CampoNonValido Se l'anno indicato è successivo all'anno corrente o precedente al 1900.
     */
    public void setAnnoInizioAttivita(int annoInizioAttivita) throws CampoNonValido {
        if (annoInizioAttivita > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
        }
        if (annoInizioAttivita < 1900){
            throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
        }
        this.annoInizioAttivita = annoInizioAttivita;
    }

    /**
     * Imposta o modifica l'identificativo univoco dell'artista.
     *
     * @param idArtista Il nuovo ID da assegnare all'artista.
     * @throws CampoNonValido Se il parametro passato risulta nullo.
     */
    public void setIdArtista(String idArtista) throws CampoNonValido{
        if(idArtista == null){
            throw new CampoNonValido("Id Artista non valido.");
        }
        this.idArtista = idArtista;
    }

    /**
     * Sostituisce l'intera lista degli album pubblicati dall'artista con quella fornita.
     *
     * @param albumPubblicati L'ArrayList di oggetti Album da impostare per questo artista.
     * @throws CampoNonValido Se la lista fornita risulta nulla.
     */
    public void setAlbumPubblicati(ArrayList<Album> albumPubblicati) throws CampoNonValido{
        if(albumPubblicati == null){
            throw new CampoNonValido("Gli album publicati non possono essere null");
        }
        this.albumPubblicati = albumPubblicati;
    }

    // Altri metodi

    /**
     * Aggiunge un singolo nuovo album alla discografia dell'artista, verificando che non sia già presente.
     *
     * @param nuovoAlbum L'oggetto Album da collegare a questo artista.
     * @throws CampoNonValido Se l'album passato è nullo o è già presente nella lista degli album pubblicati.
     */
    public void addAlbum(Album nuovoAlbum) throws CampoNonValido{
        if(nuovoAlbum == null){
            throw new CampoNonValido("Album non valido.");
        }
        if(!albumPubblicati.contains(nuovoAlbum)){
            this.albumPubblicati.add(nuovoAlbum);
        } else{
            throw new CampoNonValido("L'album è gia presente nella lista degli album dell'artista");
        }

    }
}