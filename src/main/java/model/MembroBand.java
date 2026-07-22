package model;

import java.time.LocalDate;

/**
 * The type Membro band.
 */
public class MembroBand {
    private Strumento strumentoPrincipale;
    private int annoIngresso;
    private Integer annoUscita;
    private Musicista musicista;
    private Band band;

    //Getter

    /**
     * Gets strumento principale.
     *
     * @return the strumento principale
     */
    public Strumento getStrumentoPrincipale() {
        return strumentoPrincipale;
    }

    /**
     * Gets anno ingresso.
     *
     * @return the anno ingresso
     */
    public int getAnnoIngresso() {
        return annoIngresso;
    }

    /**
     * Gets anno uscita.
     *
     * @return the anno uscita
     */
    public Integer getAnnoUscita() {
        return annoUscita;
    }

    /**
     * Gets musicista.
     *
     * @return the musicista
     */
    public Musicista getMusicista() {
        return musicista;
    }

    /**
     * Gets band.
     *
     * @return the band
     */
    public Band getBand() {
        return band;
    }

    /**
     * Instantiates a new Membro band.
     *
     * @param strumentoPrincipale the strumento principale
     * @param annoIngresso        the anno ingresso
     * @param annoUscita          the anno uscita
     * @param musicista           the musicista
     * @throws CampoNonValido the campo non valido
     */
//Setter
    // MODIFICA RISPETTO ALLA CONSEGNA DEL SECONDO HOMEWORK RIMOZIONE DAL COSTRUTTORE DEL PARAMETRO BAND PER EVITARE LOOP INFINITO NELLA CREAZIONE DEI DATABASES FITTIZZI
    public MembroBand(Strumento strumentoPrincipale, int annoIngresso, Integer annoUscita, Musicista musicista) throws CampoNonValido{
        setStrumentoPrincipale(strumentoPrincipale);
        setAnnoIngresso(annoIngresso);
        setAnnoUscita(annoUscita);
        setMusicista(musicista);
    }

    /**
     * Sets strumento principale.
     *
     * @param strumentoPrincipale the strumento principale
     * @throws CampoNonValido the campo non valido
     */
    public void setStrumentoPrincipale(Strumento strumentoPrincipale) throws  CampoNonValido{
        if(strumentoPrincipale == null){
            throw new CampoNonValido("Strumento non valido.");
        }
        this.strumentoPrincipale = strumentoPrincipale;
    }

    /**
     * Sets anno ingresso.
     *
     * @param annoIngresso the anno ingresso
     * @throws CampoNonValido the campo non valido
     */
    public void setAnnoIngresso(int annoIngresso) throws CampoNonValido {
        if (annoIngresso > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
        }
        if (annoIngresso < 1900){
            throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
        }
        this.annoIngresso = annoIngresso;
    }

    /**
     * Sets anno uscita.
     *
     * @param annoUscita the anno uscita
     * @throws CampoNonValido the campo non valido
     */
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

    /**
     * Sets musicista.
     *
     * @param musicista the musicista
     * @throws CampoNonValido the campo non valido
     */
    public void setMusicista(Musicista musicista) throws CampoNonValido {
        if(musicista == null){
            throw new CampoNonValido("Il musicista non può essere Null");
        }
        this.musicista = musicista;
    }


    /**
     * Sets band.
     *
     * @param band the band
     * @throws CampoNonValido the campo non valido
     */
    public void setBand(Band band) throws CampoNonValido {
        if (band == null) {
            throw new CampoNonValido("La band non può essere nulla.");
        }
        this.band = band;
    }
}
