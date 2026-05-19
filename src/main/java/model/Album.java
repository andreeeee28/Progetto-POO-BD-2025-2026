package model;

import java.sql.Date;
import java.util.ArrayList;

public class Album {

    private String titolo;
    private Date dataPubblicazione;
    private float voto;
    private Artista artista;
    private ArrayList<Canzone> tracklist;
    private ArrayList<Genere> generi;
    private ArrayList<Recensione> recensioni;

    //Costruttore
    public Album(String titolo, Date dataPubblicazione, Artista artista, Genere genere, ArrayList<Canzone> tracklist){


        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.artista = artista;
        artista.aggiungiAlbum(this);
        generi.add(genere);
        this.tracklist = tracklist;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public float getVoto() {
        return voto;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
