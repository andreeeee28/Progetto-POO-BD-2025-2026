package model;

public class MembroBand {
    private String strumentoPrincipale;
    private int annoIngresso;
    private int annoUscita;

    public MembroBand(String strumentoPrincipale, int annoIngresso, int annoUscita) {
        this.strumentoPrincipale = strumentoPrincipale;
        this.annoIngresso = annoIngresso;
        this.annoUscita = annoUscita;
    }

    public String getStrumentoPrincipale() {
        return strumentoPrincipale;
    }

    public void setStrumentoPrincipale(String strumentoPrincipale) {
        this.strumentoPrincipale = strumentoPrincipale;
    }

    public int getAnnoIngresso() {
        return annoIngresso;
    }

    public void setAnnoIngresso(int annoIngresso) {
        this.annoIngresso = annoIngresso;
    }

    public int getAnnoUscita() {
        return annoUscita;
    }

    public void setAnnoUscita(int annoUscita) {
        this.annoUscita = annoUscita;
    }
}
