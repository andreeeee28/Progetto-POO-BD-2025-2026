package model;

import java.time.LocalDate;

/**
 * Rappresenta una recensione lasciata da un utente per un determinato album musicale.
 * Memorizza in modo strutturato il riferimento all'album valutato, l'autore della recensione,
 * il punteggio numerico assegnato e la data esatta in cui è stata pubblicata.
 */
public class Recensione {
    private Album album;
    private Utente utente;
    private float voto;
    private LocalDate data;

    /**
     * Istanzia un nuovo oggetto Recensione validando e assegnando l'album, l'utente, il voto e la data.
     *
     * @param album L'oggetto Album che viene recensito.
     * @param utente L'oggetto Utente che ha generato la recensione.
     * @param voto Il punteggio numerico assegnato all'album (compreso tra 1 e 10).
     * @param data La data di creazione della recensione.
     * @throws CampoNonValido Se uno dei parametri non rispetta i vincoli di validazione dei relativi setter.
     */
    public Recensione(Album album, Utente utente, float voto, LocalDate data) throws CampoNonValido{
        setAlbum(album);
        setUtente(utente);
        setVoto(voto);
        setData(data);
    }

    /**
     * Restituisce l'album a cui fa riferimento questa recensione.
     *
     * @return L'oggetto Album recensito.
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Imposta o modifica l'album oggetto della recensione.
     *
     * @param album Il nuovo oggetto Album da associare alla recensione.
     * @throws CampoNonValido Se il parametro album passato risulta nullo.
     */
    public void setAlbum(Album album) throws CampoNonValido{
        if(album == null){
            throw new CampoNonValido("Album non valido.");
        }
        this.album = album;
    }

    /**
     * Restituisce l'utente autore della recensione.
     *
     * @return L'oggetto Utente che ha lasciato il voto.
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Imposta o modifica l'autore della recensione.
     *
     * @param utente Il nuovo oggetto Utente da impostare come autore.
     * @throws CampoNonValido Se il parametro utente passato risulta nullo.
     */
    public void setUtente(Utente utente) throws CampoNonValido{
        if(utente == null){
            throw new CampoNonValido("Utente non valido.");
        }
        this.utente = utente;
    }

    /**
     * Restituisce il punteggio numerico assegnato all'album.
     *
     * @return Il voto in formato decimale (float).
     */
    public float getVoto() {
        return voto;
    }

    /**
     * Imposta o modifica il punteggio assegnato all'album.
     *
     * @param voto Il nuovo voto da assegnare.
     * @throws CampoNonValido Se il voto specificato è inferiore a 1 o superiore a 10.
     */
    public void setVoto(float voto) throws CampoNonValido{
        if( voto<1 || voto>10){
            throw new CampoNonValido("Il voto deve essere compreso tra 1 e 10.");
        }
        this.voto = voto;
    }

    /**
     * Restituisce la data in cui la recensione è stata scritta.
     *
     * @return L'oggetto LocalDate rappresentante la data.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Imposta o sovrascrive la data in cui è stata rilasciata la recensione.
     *
     * @param data La nuova data da assegnare.
     * @throws CampoNonValido Se la data è nulla, successiva alla data odierna o precedente al 1900.
     */
    public void setData(LocalDate data) throws CampoNonValido {
        if (data == null){
            throw new CampoNonValido("La data di recensione non può essere vuota.");
        }
        if (data.isAfter(LocalDate.now())){
            throw new CampoNonValido("La data di recensione non può superare la data attuale.");
        }
        if (data.isBefore(LocalDate.of(1900,1,1))){
            throw new CampoNonValido("La data di recensione inserita non è valida.");
        }
        this.data = data;
    }

}