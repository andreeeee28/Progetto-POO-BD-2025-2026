package model;

import java.time.LocalDate;

public class MembroBand {
    private Strumento strumentoPrincipale;
    private int annoIngresso;
    private Integer annoUscita;
    private Musicista musicista;
    private Band band;

    //Getter

    public Strumento getStrumentoPrincipale() {
        return strumentoPrincipale;
    }

    public int getAnnoIngresso() {
        return annoIngresso;
    }

    public Integer getAnnoUscita() {
        return annoUscita;
    }

    public Musicista getMusicista() {
        return musicista;
    }

    public Band getBand() {
        return band;
    }

    //Setter
    // MODIFICA RISPETTO ALLA CONSEGNA DEL SECONDO HOMEWORK RIMOZIONE DAL COSTRUTTORE DEL PARAMETRO BAND PER EVITARE LOOP INFINITO NELLA CREAZIONE DEI DATABASES FITTIZZI
    public MembroBand(Strumento strumentoPrincipale, int annoIngresso, Integer annoUscita, Musicista musicista) throws CampoNonValido{
        setStrumentoPrincipale(strumentoPrincipale);
        setAnnoIngresso(annoIngresso);
        setAnnoUscita(annoUscita);
        setMusicista(musicista);
    }

    public void setStrumentoPrincipale(Strumento strumentoPrincipale) throws  CampoNonValido{
        if(strumentoPrincipale == null){
            throw new CampoNonValido("Strumento non valido.");
        }
        this.strumentoPrincipale = strumentoPrincipale;
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

    public void setAnnoUscita(Integer annoUscita) throws CampoNonValido {
      if(annoUscita!=null) {
          if (annoUscita > LocalDate.now().getYear()) {
              throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
          }
          if (annoUscita < 1900) {
              throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
          }
          if(annoUscita< this.annoIngresso){
              throw new CampoNonValido("L'anno di uscita non può essere minore dell anno di entrata");
          }
      }
        this.annoUscita = annoUscita;
    }

    public void setMusicista(Musicista musicista) throws CampoNonValido {
        if(musicista == null){
            throw new CampoNonValido("Il musicista non può essere Null");
        }
        this.musicista = musicista;
    }


    public void setBand(Band band) throws CampoNonValido {
        if (band == null) {
            throw new CampoNonValido("La band non può essere nulla.");
        }
        this.band = band;
    }
}
