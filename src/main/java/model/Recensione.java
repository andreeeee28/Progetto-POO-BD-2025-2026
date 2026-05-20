package model;

import java.sql.Date;
import java.time.LocalDate;

public class Recensione {
    private Album album;
    private Utente utente;
    private float voto;
    private LocalDate data;

    public Recensione(Album album, Utente utente, float voto, LocalDate data) {
        this.album = album;
        this.utente = utente;
        this.voto = voto;
        this.data = data;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public float getVoto() {
        return voto;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


}
