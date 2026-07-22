package model;

import java.time.LocalDate;

/**
 * The type Recensione.
 */
public class Recensione {
    private Album album;
    private Utente utente;
    private float voto;
    private LocalDate data;

    /**
     * Instantiates a new Recensione.
     *
     * @param album  the album
     * @param utente the utente
     * @param voto   the voto
     * @param data   the data
     * @throws CampoNonValido the campo non valido
     */
    public Recensione(Album album, Utente utente, float voto, LocalDate data) throws CampoNonValido{
        setAlbum(album);
        setUtente(utente);
        setVoto(voto);
        setData(data);
    }

    /**
     * Gets album.
     *
     * @return the album
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Sets album.
     *
     * @param album the album
     * @throws CampoNonValido the campo non valido
     */
    public void setAlbum(Album album) throws CampoNonValido{
        if(album == null){
            throw new CampoNonValido("Album non valido.");
        }
        this.album = album;
    }

    /**
     * Gets utente.
     *
     * @return the utente
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Sets utente.
     *
     * @param utente the utente
     * @throws CampoNonValido the campo non valido
     */
    public void setUtente(Utente utente) throws CampoNonValido{
        if(utente == null){
            throw new CampoNonValido("Utente non valido.");
        }
        this.utente = utente;
    }

    /**
     * Gets voto.
     *
     * @return the voto
     */
    public float getVoto() {
        return voto;
    }

    /**
     * Sets voto.
     *
     * @param voto the voto
     * @throws CampoNonValido the campo non valido
     */
    public void setVoto(float voto) throws CampoNonValido{
        if( voto<1 || voto>10){
            throw new CampoNonValido("Il voto deve essere compreso tra 1 e 10.");
        }
        this.voto = voto;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     * @throws CampoNonValido the campo non valido
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
