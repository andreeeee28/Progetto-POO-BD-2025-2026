package model;

import java.util.ArrayList;

/**
 * The type Genere.
 */
public class Genere {
    private String nome;
    private String descrizione;
    private ArrayList<Genere> generiPadre;
    private ArrayList<Genere> sottogeneri;
    private ArrayList<Album> listaAlbum;

    /**
     * Instantiates a new Genere.
     *
     * @param nome        the nome
     * @param descrizione the descrizione
     * @throws CampoNonValido the campo non valido
     */
    public Genere(String nome, String descrizione) throws CampoNonValido{
        setNome(nome);
        setDescrizione(descrizione);
        this.generiPadre = new ArrayList<>();
        this.sottogeneri = new ArrayList<>();
        this.listaAlbum = new ArrayList<>();
    }

    //Getter

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Gets generi padre.
     *
     * @return the generi padre
     */
    public ArrayList<Genere> getGeneriPadre() {
        return generiPadre;
    }

    /**
     * Gets lista album.
     *
     * @return the lista album
     */
    public ArrayList<Album> getListaAlbum() {
        return listaAlbum;
    }

    /**
     * Gets sottogeneri.
     *
     * @return the sottogeneri
     */
    public ArrayList<Genere> getSottogeneri() {
        return sottogeneri;
    }

    //Setter

    /**
     * Sets nome.
     *
     * @param nome the nome
     * @throws CampoNonValido the campo non valido
     */
    public void setNome(String nome) throws CampoNonValido {
        if(nome == null ||  nome.trim().length()<1 || nome.trim().length()>30){
            throw new CampoNonValido("Il nome deve avere minimo 1 carattere e massimo 30!");
        }
        this.nome = nome;
    }

    /**
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     * @throws CampoNonValido the campo non valido
     */
    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<1 || descrizione.trim().length()>150){
            throw new CampoNonValido("La descrizione deve avere minimo 1 carattere e massimo 150!");
        }
        this.descrizione = descrizione;
    }
    // Altri metodi

    /**
     * Add generi padre.
     *
     * @param newGenere the new genere
     * @throws CampoNonValido the campo non valido
     */
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

    /**
     * Add sottogeneri.
     *
     * @param newGenere the new genere
     * @throws CampoNonValido the campo non valido
     */
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

    /**
     * Add lista album.
     *
     * @param newAlbum the new album
     * @throws CampoNonValido the campo non valido
     */
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

    @Override
    public String toString() {
        return this.nome;
    }
}
