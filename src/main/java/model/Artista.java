package model;

import java.util.ArrayList;

public class Artista {
    protected String nomeArte;
    protected int annoInizioAttività;
    protected String idArtista;
    protected ArrayList<Album> albumPubblicati;

    public Artista(String nomeArte, int annoInizioAttività, String idArtista){
        this.nomeArte = nomeArte;
        this.annoInizioAttività = annoInizioAttività;
        this.idArtista = idArtista;
        this.albumPubblicati = new ArrayList<Album>();
    }

    public String getNomeArte() {
        return nomeArte;
    }

    public void setNomeArte(String nomeArte) {
        this.nomeArte = nomeArte;
    }

    public int getAnnoInizioAttività() {
        return annoInizioAttività;
    }

    public void setAnnoInizioAttività(int annoInizioAttività) {
        this.annoInizioAttività = annoInizioAttività;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public ArrayList<Album> getAlbumPubblicati(){
        return albumPubblicati;
    }
    public void aggiungiAlbum(Album nuovoAlbum) {
        this.albumPubblicati.add(nuovoAlbum);
    }
}
