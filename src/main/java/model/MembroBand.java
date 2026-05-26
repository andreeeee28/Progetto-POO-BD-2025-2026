package model;

import java.time.LocalDate;

public class MembroBand {
    private Strumento strumentoPrincipale;
    private int annoIngresso;
    private Integer annoUscita;
    private Musicista musicista;
    private Band band;

    public MembroBand(Strumento strumentoPrincipale, int annoIngresso, int annoUscita, Musicista musicista, Band band) throws CampoNonValido{
        setStrumentoPrincipale(strumentoPrincipale);
        setAnnoIngresso(annoIngresso);
        setAnnoUscita(annoUscita);
    }

    public Strumento getStrumentoPrincipale() {
        return strumentoPrincipale;
    }

    public void setStrumentoPrincipale(Strumento strumentoPrincipale) throws  CampoNonValido{
        if(strumentoPrincipale == null){
            throw new CampoNonValido("Strumento non valido.");
        }
        this.strumentoPrincipale = strumentoPrincipale;
    }

    public int getAnnoIngresso() {
        return annoIngresso;
    }

    public void setAnnoIngresso(int annoIngresso) throws CampoNonValido {
        if (annoIngresso > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
        }
        if (annoIngresso < 1900){
            throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
        }
        this.annoIngresso = annoIngresso;
    }

    public int getAnnoUscita() {
        return annoUscita;
    }

    public void setAnnoUscita(Integer annoUscita) throws CampoNonValido {
      if(annoUscita!=null) {
          if (annoUscita > LocalDate.now().getYear()) {
              throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
          }
          if (annoUscita < 1900) {
              throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
          }
      }
        this.annoUscita = annoUscita;
    }

    public Musicista getMusicista() {
        return musicista;
    }

    public void setMusicista(Musicista musicista) throws CampoNonValido {
        if(musicista == null){
            throw new CampoNonValido("Il musicista non può essere Null");
        }
        this.musicista = musicista;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) throws CampoNonValido {
        if (band == null) {
            throw new CampoNonValido("La band non può essere nulla.");
        }
        this.band = band;
    }
}
