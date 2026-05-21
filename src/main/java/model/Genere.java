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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoNonValido {
        if(nome == null ||  nome.trim().length()<1 || nome.trim().length()>30){
            throw new CampoNonValido("Il nome deve avere minimo 1 carattere e massimo 30!");
        }
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<1 || descrizione.trim().length()>150){
            throw new CampoNonValido("La descrizione deve avere minimo 1 carattere e massimo 150!");
        }
        this.descrizione = descrizione;
    }

    public ArrayList<Genere> getGeneriPadre() {
        return generiPadre;
    }

    public void addGeneriPadre(Genere newGenere) throws CampoNonValido{
        if (newGenere == null){
            throw new CampoNonValido("Genere non valido.");
        }
        this.generiPadre.add(newGenere);
    }

    public ArrayList<Genere> getSottogeneri() {
        return sottogeneri;
    }

    public void addSottogeneri(Genere newGenere) throws CampoNonValido{
        if (newGenere == null){
            throw new CampoNonValido("Genere non valido.");
        }
        this.sottogeneri.add(newGenere);
    }

    public ArrayList<Album> getListaAlbum() {
        return listaAlbum;
    }

    public void addListaAlbum(Album newAlbum) throws CampoNonValido{
        if (newAlbum == null){
            throw new CampoNonValido("Album non valido.");
        }
        this.listaAlbum.add(newAlbum);
    }
}
