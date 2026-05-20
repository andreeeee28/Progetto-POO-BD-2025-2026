package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Album {

    private String titolo;
    private Date dataPubblicazione;
    private float voto;
    private Artista artista;
    private ArrayList<Canzone> tracklist;
    private ArrayList<Genere> generi;
    private ArrayList<Recensione> recensioni;

    //Costruttore
    public Album(String titolo, Date dataPubblicazione, Artista artista, Genere genere, ArrayList<Canzone> tracklist) {
        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.artista = artista;
        this.tracklist = tracklist; // Prendo la lista già creata dal Controller/GUI
        this.generi = new ArrayList<>();
        this.recensioni = new ArrayList<>();

        // 2. Associo il genere base
        this.generi.add(genere);

        // 3. Associo l'album all'artista (con controllo di sicurezza)
        if (this.artista != null) {
            this.artista.aggiungiAlbum(this);
        }
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

    public ArrayList<Canzone> getTracklist() {
        return tracklist;
    }

    public void setTracklist(ArrayList<Canzone> tracklist) {
        this.tracklist = tracklist;
    }

    public ArrayList<Genere> getGeneri() {
        return generi;
    }

    public void setGeneri(ArrayList<Genere> generi) {
        this.generi = generi;
    }

    public ArrayList<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(ArrayList<Recensione> recensioni) {
        this.recensioni = recensioni;
    }
}




