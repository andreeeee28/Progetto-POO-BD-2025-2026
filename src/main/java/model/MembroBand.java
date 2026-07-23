package model;

import java.time.LocalDate;

/**
 * Rappresenta il ruolo e la permanenza di un musicista all'interno di una band (MembroBand).
 * Agisce come classe associativa tra un oggetto Musicista e un oggetto Band, memorizzando
 * lo strumento principale suonato, l'anno di ingresso e l'eventuale anno di uscita dal gruppo.
 */
public class MembroBand {
    private Strumento strumentoPrincipale;
    private int annoIngresso;
    private Integer annoUscita;
    private Musicista musicista;
    private Band band;

    /**
     * Istanzia un nuovo MembroBand assegnando lo strumento, le date di permanenza e il musicista.
     *
     * @param strumentoPrincipale Lo strumento principale suonato dal musicista nella band (valore Enum).
     * @param annoIngresso L'anno in cui il musicista è entrato a far parte della band.
     * @param annoUscita L'anno di uscita del musicista dalla band (null se ne fa ancora parte).
     * @param musicista L'oggetto Musicista fisico che ricopre questo ruolo.
     * @throws CampoNonValido Se uno dei parametri non rispetta le regole di validazione dei relativi setter.
     */
    // MODIFICA RISPETTO ALLA CONSEGNA DEL SECONDO HOMEWORK RIMOZIONE DAL COSTRUTTORE DEL PARAMETRO BAND PER EVITARE LOOP INFINITO NELLA CREAZIONE DEI DATABASES FITTIZZI
    public MembroBand(Strumento strumentoPrincipale, int annoIngresso, Integer annoUscita, Musicista musicista) throws CampoNonValido{
        setStrumentoPrincipale(strumentoPrincipale);
        setAnnoIngresso(annoIngresso);
        setAnnoUscita(annoUscita);
        setMusicista(musicista);
    }

    //Getter

    /**
     * Restituisce lo strumento principale suonato dal membro nella band.
     *
     * @return Il valore dell'enum Strumento associato a questo ruolo.
     */
    public Strumento getStrumentoPrincipale() {
        return strumentoPrincipale;
    }

    /**
     * Restituisce l'anno in cui il membro è entrato nella band.
     *
     * @return L'anno di ingresso.
     */
    public int getAnnoIngresso() {
        return annoIngresso;
    }

    /**
     * Restituisce l'anno in cui il membro ha lasciato la band.
     *
     * @return L'anno di uscita, oppure null se il membro fa ancora parte della band.
     */
    public Integer getAnnoUscita() {
        return annoUscita;
    }

    /**
     * Restituisce il musicista associato a questo ruolo all'interno della band.
     *
     * @return L'oggetto Musicista.
     */
    public Musicista getMusicista() {
        return musicista;
    }

    /**
     * Restituisce la band a cui il musicista è collegato tramite questo ruolo.
     *
     * @return L'oggetto Band di appartenenza.
     */
    public Band getBand() {
        return band;
    }

    //Setter

    /**
     * Imposta o modifica lo strumento principale suonato dal musicista in questa band.
     *
     * @param strumentoPrincipale Il nuovo strumento da assegnare.
     * @throws CampoNonValido Se lo strumento passato risulta nullo.
     */
    public void setStrumentoPrincipale(Strumento strumentoPrincipale) throws  CampoNonValido{
        if(strumentoPrincipale == null){
            throw new CampoNonValido("Strumento non valido.");
        }
        this.strumentoPrincipale = strumentoPrincipale;
    }

    /**
     * Imposta o modifica l'anno di ingresso del membro nella band.
     *
     * @param annoIngresso Il nuovo anno di ingresso da assegnare.
     * @throws CampoNonValido Se l'anno inserito è successivo all'anno in corso o precedente al 1900.
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
     * Imposta o modifica l'anno di uscita del membro dalla band.
     *
     * @param annoUscita Il nuovo anno di uscita da assegnare (null se il musicista è ancora attivo).
     * @throws CampoNonValido Se l'anno è successivo a quello in corso, precedente al 1900 o precedente all'anno di ingresso.
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
     * Imposta o modifica il musicista associato a questo ruolo nella band.
     *
     * @param musicista L'oggetto Musicista da assegnare.
     * @throws CampoNonValido Se il musicista passato risulta nullo.
     */
    public void setMusicista(Musicista musicista) throws CampoNonValido {
        if(musicista == null){
            throw new CampoNonValido("Il musicista non può essere Null");
        }
        this.musicista = musicista;
    }

    /**
     * Collega questo specifico ruolo alla band di appartenenza.
     *
     * @param band L'oggetto Band di cui il musicista entra a far parte.
     * @throws CampoNonValido Se la band passata risulta nulla.
     */
    public void setBand(Band band) throws CampoNonValido {
        if (band == null) {
            throw new CampoNonValido("La band non può essere nulla.");
        }
        this.band = band;
    }
}