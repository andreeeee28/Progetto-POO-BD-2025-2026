package model;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Rappresenta un album musicale all'interno del sistema.
 * Raggruppa una lista di canzoni (tracklist) ed è associato a un artista autore,
 * a uno o più generi musicali e a una raccolta di recensioni lasciate dagli utenti di qualsiasi grado (sia admin che Utente standard).
 * L'aggiunta diretta di un nuovo album al sistema è una prerogativa esclusiva degli admin;
 * gli utenti comuni possono unicamente inviare una proposta di inserimento, che dovrà poi essere valutata da un Admin.
 */
public class Album {

    private String titolo;
    private LocalDate dataPubblicazione;
    private Artista artista;
    private ArrayList<Canzone> tracklist;
    private ArrayList<Genere> generi;
    private ArrayList<Recensione> recensioni;

    /**
     * Istanzia un nuovo oggetto Album, validando e assegnando i parametri principali.
     * La lista delle recensioni viene inizializzata vuota di default.
     *
     * @param titolo Il titolo dell'album (tra 1 e 30 caratteri).
     * @param dataPubblicazione La data di uscita ufficiale dell'album.
     * @param artista L'oggetto Artista che ha prodotto o rilasciato l'album.
     * @param generi La lista dei generi musicali a cui l'album appartiene.
     * @param tracklist La lista ordinata delle canzoni contenute nell'album.
     * @throws CampoNonValido Se uno dei parametri non rispetta i vincoli di validazione imposti dai relativi setter.
     */
    public Album(String titolo, LocalDate dataPubblicazione, Artista artista, ArrayList <Genere> generi, ArrayList<Canzone> tracklist) throws CampoNonValido {
        setTitolo(titolo);
        setDataPubblicazione(dataPubblicazione);
        setTracklist(tracklist);
        this.recensioni = new ArrayList<>();
        setGeneri(generi);
        setArtista(artista);
      }

    /**
     * Restituisce il titolo dell'album.
     *
     * @return Il titolo dell'album.
     */
    //Getter
    public String getTitolo() {
        return titolo;
    }

    /**
     * Restituisce la data di uscita dell'album.
     *
     * @return La data di pubblicazione dell'album.
     */
    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    /**
     * Calcola e restituisce la valutazione media dell'album basata sui voti delle recensioni.
     *
     * @return La media matematica dei voti, oppure 0.0f se non sono presenti recensioni.
     */
    public float getRating() {
        if (recensioni == null || recensioni.isEmpty()) {
            return 0.0f;
        }
        float sommaTot = 0;
        for (Recensione recensione : recensioni){
            sommaTot += recensione.getVoto();
        }
        float numeroRecensioni = (float) recensioni.size();
        return (sommaTot/numeroRecensioni);
    }

    /**
     * Restituisce la lista dei brani musicali inclusi nell'album.
     *
     * @return L'ArrayList contenente gli oggetti Canzone dell'album.
     */
    public ArrayList<Canzone> getTracklist() {
        return tracklist;
    }

    /**
     * Restituisce l'autore dell'album.
     *
     * @return L'oggetto Artista associato all'album.
     */
    public Artista getArtista() {
        return artista;
    }

    /**
     * Restituisce i generi musicali a cui l'album è stato associato.
     *
     * @return L'ArrayList contenente gli oggetti Genere dell'album.
     */
    public ArrayList<Genere> getGeneri() {
        return generi;
    }

    /**
     * Restituisce la lista di tutte le recensioni lasciate dagli utenti per questo album.
     *
     * @return L'ArrayList contenente gli oggetti Recensione associati all'album.
     */
    public ArrayList<Recensione> getRecensioni() {return recensioni;}

    /**
     * Imposta o modifica il titolo dell'album.
     *
     * @param titolo Il nuovo titolo da assegnare all'album.
     * @throws CampoNonValido Se il titolo è nullo, vuoto o supera i 30 caratteri di lunghezza.
     */
    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>30){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 30!");
        }
        this.titolo = titolo;
    }

    /**
     * Imposta o modifica la data di uscita dell'album.
     *
     * @param dataPubblicazione La nuova data di pubblicazione da assegnare.
     * @throws CampoNonValido Se la data è nulla, successiva alla data odierna o precedente al 1 Gennaio 1900.
     */
    public void setDataPubblicazione(LocalDate dataPubblicazione) throws CampoNonValido {
        if (dataPubblicazione == null){
            throw new CampoNonValido("La data di pubblicazione non può essere vuota.");
        }
        if (dataPubblicazione.isAfter(LocalDate.now())){
            throw new CampoNonValido("La data di pubblicazione non può superare la data attuale.");
        }
        if (dataPubblicazione.isBefore(LocalDate.of(1900,1,1))){
            throw new CampoNonValido("La data di pubblicazione inserita non è valida.");
        }
        this.dataPubblicazione = dataPubblicazione;
    }

    /**
     * Collega l'album all'artista specificato.
     *
     * @param artista L'oggetto Artista da impostare come autore.
     * @throws CampoNonValido Se il parametro artista passato risulta nullo.
     */
    public void setArtista(Artista artista) throws CampoNonValido{
        if(artista == null){
            throw new CampoNonValido("Artista non valido.");
        }
        this.artista = artista;
    }

    /**
     * Imposta la lista dei brani per l'album e aggiorna automaticamente il riferimento dell'album all'interno di ogni singola canzone.
     *
     * @param tracklist L'ArrayList di oggetti Canzone da inserire.
     * @throws CampoNonValido Se la tracklist passata è nulla oppure vuota.
     */
    public void setTracklist(ArrayList<Canzone> tracklist) throws CampoNonValido {
        if(tracklist == null || tracklist.isEmpty()){
            throw new CampoNonValido("Tracklist non valida.");
        }
        this.tracklist = tracklist;
        for(Canzone canzone : this.tracklist){
            canzone.setAlbumDiAppartenenza(this);
        }
    }

    /**
     * Imposta la lista dei generi musicali dell'album.
     *
     * @param generi L'ArrayList di oggetti Genere da associare all'album.
     * @throws CampoNonValido Se la lista dei generi passata è nulla oppure vuota.
     */
    public void setGeneri(ArrayList<Genere> generi) throws CampoNonValido {
        if(generi == null|| generi.isEmpty()){
            throw new CampoNonValido("la lista dei generi non può essere null e non può essere vuota ");
        }
        this.generi = generi;
    }

    /**
     * Sostituisce l'intera lista delle recensioni dell'album con quella fornita.
     *
     * @param recensioni L'ArrayList di oggetti Recensione da impostare.
     * @throws CampoNonValido Se la lista delle recensioni passata risulta nulla.
     */
    public void setRecensioni(ArrayList<Recensione> recensioni) throws CampoNonValido{
        if(recensioni == null){
            throw new CampoNonValido("Lista recensioni inserita non valida.");
        }
        this.recensioni = recensioni;
    }

    /**
     * Aggiunge un singolo genere musicale alla lista dei generi dell'album, verificando che non sia un duplicato.
     *
     * @param genere L'oggetto Genere da aggiungere all'album.
     * @throws CampoNonValido Se il genere specificato è nullo o è già presente nella lista dei generi dell'album.
     */
    public void addGeneri(Genere genere) throws CampoNonValido {
        if(genere == null){
            throw new CampoNonValido("Genere inserito non valido.");
        }
        if (!this.generi.contains(genere)) {
            this.generi.add(genere);
        } else {
            throw new CampoNonValido("Genere già presente nella lista dei generi dell' album!");
        }
    }

    /**
     * Aggiunge una nuova recensione alla lista di recensioni collegate all'album, verificando che non sia già presente.
     *
     * @param recensione L'oggetto Recensione da inserire.
     * @throws CampoNonValido Se la recensione passata è nulla o se è già registrata in questo album.
     */
    public void addRecensioni(Recensione recensione) throws CampoNonValido{
        if(recensione == null){
            throw new CampoNonValido("Recensione inserita non valida.");
        }
        if(!recensioni.contains(recensione)){
            this.recensioni.add(recensione);
        } else{
            throw new CampoNonValido("Recensione gia presente nella lista delle recensioni");
        }

    }

}