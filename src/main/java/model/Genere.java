package model;

import java.util.ArrayList;

public class Genere {
    private String nome;
    private String descrizione;
    private ArrayList<Genere> generiPadre;
    private ArrayList<Genere> sottogeneri;
    private ArrayList<Album> listaAlbum;

    public Genere(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.generiPadre = new ArrayList<>();
        this.sottogeneri = new ArrayList<>();
        this.listaAlbum = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<Genere> getGeneriPadre() {
        return generiPadre;
    }

    public void addGeneriPadre(Genere newGenere){
        this.generiPadre.add(newGenere);
    }

    public ArrayList<Genere> getSottogeneri() {
        return sottogeneri;
    }

    public void addSottogeneri(Genere newGenere){
        this.sottogeneri.add(newGenere);
    }

    public ArrayList<Album> getListaAlbum() {
        return listaAlbum;
    }

    public void addListaAlbum(Album newAlbum){
        this.listaAlbum.add(newAlbum);
    }
}
