package model;

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

    public void setVoto(float voto) throws CampoNonValido{
        if( voto<1 || voto>10){
            throw new CampoNonValido("Il voto deve essere compreso tra 1 e 10.");
        }
        this.voto = voto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws CampoNonValido {
        if (data == null){
            throw new CampoNonValido("La data di pubblicazione non può essere vuota.");
        }
        if (data.isAfter(LocalDate.now())){
            throw new CampoNonValido("La data di pubblicazione non può superare la data attuale.");
        }
        if (data.isBefore(LocalDate.of(1900,1,1))){
            throw new CampoNonValido("La data di pubblicazione inserita non è valida.");
        }
        this.data = data;
    }

}
