package model;


import java.time.LocalDate;
import java.util.ArrayList;

public class Album {

    private String titolo;
    private LocalDate dataPubblicazione;
    private Artista artista;
    private ArrayList<Canzone> tracklist;
    private ArrayList<Genere> generi;
    private ArrayList<Recensione> recensioni;

    //Costruttore
    public Album(String titolo, LocalDate dataPubblicazione, Artista artista, ArrayList <Genere> generi, ArrayList<Canzone> tracklist) throws CampoNonValido {
        setTitolo(titolo);
        setDataPubblicazione(dataPubblicazione);
        setTracklist(tracklist);
        this.recensioni = new ArrayList<>();
        setGeneri(generi);
        setArtista(artista);
      }

    //Getter
    public String getTitolo() {
        return titolo;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public float getRating() {
        if (recensioni == null || recensioni.isEmpty()) {
            return 0.0f;
        }
        float sommaTot = 0;
        for (Recensione recensione : recensioni){
            sommaTot += recensione.getVoto();
        }
        float numeroRecensioni = (float) recensioni.size();
        return (sommaTot/numeroRecensioni);
    }

    public ArrayList<Canzone> getTracklist() {
        return tracklist;
    }

    public Artista getArtista() {
        return artista;
    }

    public ArrayList<Genere> getGeneri() {
        return generi;
    }

    public ArrayList<Recensione> getRecensioni() {return recensioni;}

    //Setter

    public void setTitolo(String titolo) throws CampoNonValido {
        if(titolo == null ||  titolo.trim().length()<1 || titolo.trim().length()>30){
            throw new CampoNonValido("Il titolo deve avere minimo 1 carattere e massimo 30!");
        }
        this.titolo = titolo;
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

    public void setArtista(Artista artista) throws CampoNonValido{
        if(artista == null){
            throw new CampoNonValido("Artista non valido.");
        }
        this.artista = artista;
    }

    public void setTracklist(ArrayList<Canzone> tracklist) throws CampoNonValido {
        if(tracklist == null || tracklist.isEmpty()){
            throw new CampoNonValido("Tracklist non valida.");
        }
        this.tracklist = tracklist;
        for(Canzone canzone : this.tracklist){
            canzone.setAlbumDiAppartenenza(this);
        }
    }

    public void setGeneri(ArrayList<Genere> generi) throws CampoNonValido {
        if(generi == null|| generi.isEmpty()){
            throw new CampoNonValido("la lista dei generi non può essere null e non può essere vuota ");
        }
        this.generi = generi;
    }

    public void setRecensioni(ArrayList<Recensione> recensioni) throws CampoNonValido{
        if(recensioni == null){
            throw new CampoNonValido("Lista recensioni inserita non valida.");
        }
        this.recensioni = recensioni;
    }

    // Altri metodi

    public void addGeneri(Genere genere) throws CampoNonValido {
        if(genere == null){
            throw new CampoNonValido("Genere inserito non valido.");
        }
        if (!this.generi.contains(genere)) {
            this.generi.add(genere);
        } else {
            throw new CampoNonValido("Genere già presente nella lista dei generi dell' album!");
        }
    }

    public void addRecensioni(Recensione recensione) throws CampoNonValido{
        if(recensione == null){
            throw new CampoNonValido("Recensione inserita non valida.");
        }
        if(!recensioni.contains(recensione)){
            this.recensioni.add(recensione);
        } else{
            throw new CampoNonValido("Recensione gia presente nella lista delle recensioni");
        }

    }

}




