package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Artista {
    protected String nomeArte;
    protected int annoInizioAttivita;
    protected String idArtista;
    protected ArrayList<Album> albumPubblicati;

    public Artista(String nomeArte, int annoInizioAttivita, String idArtista) throws CampoNonValido{
        setNomeArte(nomeArte);
        setAnnoInizioAttivita(annoInizioAttivita);
        setIdArtista(idArtista);
        this.albumPubblicati = new ArrayList<Album>();
    }

    public String getNomeArte() {
        return nomeArte;
    }

    public void setNomeArte(String nomeArte) throws CampoNonValido {
        if(nomeArte == null ||  nomeArte.trim().length()<1 || nomeArte.trim().length()>30){
            throw new CampoNonValido("Il Nome d'Arte deve avere minimo 1 carattere e massimo 30!");
        }
        this.nomeArte = nomeArte;
    }

    public int getAnnoInizioAttivita() {
        return annoInizioAttivita;
    }

    public void setAnnoInizioAttivita(int annoInizioAttivita) throws CampoNonValido {
        if (annoInizioAttivita > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
        }
        if (annoInizioAttivita < 1900){
            throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
        }
        this.annoInizioAttivita = annoInizioAttivita;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) throws CampoNonValido{
        if(idArtista == null){
            throw new CampoNonValido("Id Artista non valido.");
        }
        this.idArtista = idArtista;
    }

    public ArrayList<Album> getAlbumPubblicati(){
        return albumPubblicati;
    }

    public void addAlbum(Album nuovoAlbum) throws CampoNonValido{
        if(nuovoAlbum == null){
            throw new CampoNonValido("Album non valido.");
        }
        this.albumPubblicati.add(nuovoAlbum);
    }
}
