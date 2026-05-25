package model;

import java.time.LocalDate;

public class MembroBand {
    private Strumento strumentoPrincipale;
    private int annoIngresso;
    private int annoUscita;

    public MembroBand(Strumento strumentoPrincipale, int annoIngresso, int annoUscita) throws CampoNonValido{
        setStrumentoPrincipale(strumentoPrincipale);
        this.annoIngresso = annoIngresso;
        this.annoUscita = annoUscita;
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

    public void setAnnoUscita(int annoUscita) throws CampoNonValido {
        if (annoUscita > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di inizio attività non può superare l'anno in corso.");
        }
        if (annoUscita < 1900){
            throw new CampoNonValido("L'anno di inizio attività inserito non è valido.");
        }
        this.annoUscita = annoUscita;
    }
}
