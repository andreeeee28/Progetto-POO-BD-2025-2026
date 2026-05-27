package model;

import java.util.ArrayList;

public class Genere {
    private String nome;
    private String descrizione;
    private ArrayList<Genere> generiPadre;
    private ArrayList<Genere> sottogeneri;
    private ArrayList<Album> listaAlbum;

    public Genere(String nome, String descrizione) throws CampoNonValido{
        setNome(nome);
        setDescrizione(descrizione);
        this.generiPadre = new ArrayList<>();
        this.sottogeneri = new ArrayList<>();
        this.listaAlbum = new ArrayList<>();
    }

    //Getter

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public ArrayList<Genere> getGeneriPadre() {
        return generiPadre;
    }

    public ArrayList<Album> getListaAlbum() {
        return listaAlbum;
    }

    public ArrayList<Genere> getSottogeneri() {
        return sottogeneri;
    }

    //Setter

    public void setNome(String nome) throws CampoNonValido {
        if(nome == null ||  nome.trim().length()<1 || nome.trim().length()>30){
            throw new CampoNonValido("Il nome deve avere minimo 1 carattere e massimo 30!");
        }
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<1 || descrizione.trim().length()>150){
            throw new CampoNonValido("La descrizione deve avere minimo 1 carattere e massimo 150!");
        }
        this.descrizione = descrizione;
    }
    // Altri metodi

    public void addGeneriPadre(Genere newGenere) throws CampoNonValido{
        if (newGenere == null){
            throw new CampoNonValido("Genere non valido.");
        }
        if (!this.generiPadre.contains(newGenere)) {
            this.generiPadre.add(newGenere);
        } else {
            throw new CampoNonValido("Genere padre già presente nella lista dei generi padre di questo genere");
        }
    }

    public void addSottogeneri(Genere newGenere) throws CampoNonValido{
        if (newGenere == null){
            throw new CampoNonValido("Genere non valido.");
        }
        if (!this.sottogeneri.contains(newGenere)) {
            this.sottogeneri.add(newGenere);
        } else {
            throw new CampoNonValido("Sottogenere già presente nella lista dei sottogeneri del genere");
        }
    }

    public void addListaAlbum(Album newAlbum) throws CampoNonValido {
        if (newAlbum == null){
            throw new CampoNonValido("Album non valido.");
        }

        // Se il genere NON ha già questo album nella sua lista...
        if (!this.listaAlbum.contains(newAlbum)) {
            this.listaAlbum.add(newAlbum); // 1. Lo aggiunge a se stesso
        } else{
            throw new CampoNonValido("Questo album è gia presente nella lista degli album per genere");

        }
    }
}
