package model;


import java.time.LocalDate;
import java.util.ArrayList;

public class Album {

    private String titolo;
    private LocalDate dataPubblicazione;
    private float voto;
    private Artista artista;
    private ArrayList<Canzone> tracklist;
    private ArrayList<Genere> generi;
    private ArrayList<Recensione> recensioni;

    //Costruttore
    public Album(String titolo, LocalDate dataPubblicazione, Artista artista, Genere genere, ArrayList<Canzone> tracklist) throws CampoNonValido {
        setTitolo(titolo);
        setDataPubblicazione(dataPubblicazione);
        setTracklist(tracklist); // Prendo la lista già creata dal Controller/GUI
        this.recensioni = new ArrayList<>();

        // 2. Associo il genere base
        this.generi = new ArrayList<>();
        addGeneri(genere);

        // 3. Associo l'album all'artista (con controllo di sicurezza)
        setArtista(artista);
        if (this.artista != null) {
            this.artista.addAlbum(this);
        }
      }

    //Get & Setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>30){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 30!");
        }
        this.titolo = titolo;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) throws CampoNonValido {
        if (dataPubblicazione == null){
            throw new CampoNonValido("La data di pubblicazione non può essere vuota.");
        }
        if (dataPubblicazione.isAfter(LocalDate.now())){
            throw new CampoNonValido("La data di pubblicazione non può superare la data attuale.");
        }
        if (dataPubblicazione.isBefore(LocalDate.of(1900,1,1))){
            throw new CampoNonValido("La data di pubblicazione inserita non è valida.");
        }
        this.dataPubblicazione = dataPubblicazione;
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) throws CampoNonValido{
        if(artista == null){
            throw new CampoNonValido("Artista non valido.");
        }
        this.artista = artista;
    }

    public ArrayList<Canzone> getTracklist() {
        return tracklist;
    }

    public void setTracklist(ArrayList<Canzone> tracklist) throws CampoNonValido {
        if(tracklist == null){
            throw new CampoNonValido("Tracklist non valida.");
        }
        this.tracklist = tracklist;
    }

    public ArrayList<Genere> getGeneri() {
        return generi;
    }

    public void setGeneri(ArrayList<Genere> generi) throws CampoNonValido{
        this.generi = generi;
    }

    public void addGeneri(Genere genere) throws CampoNonValido{
        if(genere == null){
            throw new CampoNonValido("Genere inserito non valido.");
        }
            this.generi.add(genere);
    }

    public ArrayList<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(ArrayList<Recensione> recensioni) throws CampoNonValido{
        if(recensioni == null){
            throw new CampoNonValido("Lista recensioni inserita non valida.");
        }
        this.recensioni = recensioni;
    }

    public void addRecensioni(Recensione recensione) throws CampoNonValido{
        if(recensione == null){
            throw new CampoNonValido("Recensione inserita non valida.");
        }
        this.recensioni.add(recensione);
    }
}




