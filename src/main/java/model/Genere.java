package model;

import java.util.ArrayList;

/**
 * Rappresenta un genere musicale all'interno del sistema.
 * Mantiene le informazioni relative al nome e alla descrizione, e gestisce la gerarchia
 * (generi padre e sottogeneri), oltre a tenere traccia di tutti gli album associati a questo specifico genere.
 */
public class Genere {
    private String nome;
    private String descrizione;
    private ArrayList<Genere> generiPadre;
    private ArrayList<Genere> sottogeneri;
    private ArrayList<Album> listaAlbum;

    /**
     * Istanzia un nuovo oggetto Genere assegnando il nome, la descrizione e inizializzando le liste vuote per gerarchie e album.
     *
     * @param nome Il nome del genere musicale (tra 1 e 30 caratteri).
     * @param descrizione La descrizione del genere musicale (tra 1 e 150 caratteri).
     * @throws CampoNonValido Se il nome o la descrizione non rispettano i vincoli di validazione dei relativi setter.
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
     * Restituisce il nome del genere musicale.
     *
     * @return La stringa contenente il nome del genere.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce la descrizione esplicativa del genere musicale.
     *
     * @return La stringa contenente la descrizione.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Restituisce la lista dei generi musicali da cui questo genere deriva (macrocategorie).
     *
     * @return L'ArrayList contenente gli oggetti Genere impostati come padri.
     */
    public ArrayList<Genere> getGeneriPadre() {
        return generiPadre;
    }

    /**
     * Restituisce la lista di tutti gli album associati a questo specifico genere musicale.
     *
     * @return L'ArrayList contenente gli oggetti Album di questo genere.
     */
    public ArrayList<Album> getListaAlbum() {
        return listaAlbum;
    }

    /**
     * Restituisce la lista dei generi musicali che derivano da questo genere (sottocategorie).
     *
     * @return L'ArrayList contenente gli oggetti Genere impostati come sottogeneri.
     */
    public ArrayList<Genere> getSottogeneri() {
        return sottogeneri;
    }

    //Setter

    /**
     * Imposta o modifica il nome del genere musicale.
     *
     * @param nome Il nuovo nome da assegnare al genere.
     * @throws CampoNonValido Se il nome è nullo, vuoto o supera i 30 caratteri di lunghezza.
     */
    public void setNome(String nome) throws CampoNonValido {
        if(nome == null ||  nome.trim().length()<1 || nome.trim().length()>30){
            throw new CampoNonValido("Il nome deve avere minimo 1 carattere e massimo 30!");
        }
        this.nome = nome;
    }

    /**
     * Imposta o modifica la descrizione esplicativa del genere musicale.
     *
     * @param descrizione La nuova descrizione da assegnare.
     * @throws CampoNonValido Se la descrizione è nulla, vuota o supera i 150 caratteri di lunghezza.
     */
    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<1 || descrizione.trim().length()>150){
            throw new CampoNonValido("La descrizione deve avere minimo 1 carattere e massimo 150!");
        }
        this.descrizione = descrizione;
    }
    // Altri metodi

    /**
     * Aggiunge un genere alla lista dei generi padre, verificando che non sia un duplicato.
     *
     * @param newGenere L'oggetto Genere da impostare come macrocategoria.
     * @throws CampoNonValido Se il genere passato è nullo o è già presente nella lista dei padri.
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
     * Aggiunge un genere alla lista dei sottogeneri, verificando che non sia un duplicato.
     *
     * @param newGenere L'oggetto Genere da impostare come sottocategoria derivata.
     * @throws CampoNonValido Se il genere passato è nullo o è già presente nella lista dei sottogeneri.
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
     * Aggiunge un nuovo album alla lista degli album collegati a questo genere, verificando che non sia già presente.
     *
     * @param newAlbum L'oggetto Album da associare al genere.
     * @throws CampoNonValido Se l'album passato è nullo o è già presente in questa lista.
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

    /**
     * Restituisce una rappresentazione testuale del genere musicale.
     *
     * @return Il nome del genere.
     */
    @Override
    public String toString() {
        return this.nome;
    }
}