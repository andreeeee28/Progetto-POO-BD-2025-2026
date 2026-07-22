package model;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The type Musicista.
 */
public class Musicista extends Artista {
    private String nomeVero;
    private String cognonomeVero;
    private LocalDate dataDiNascita;
    private ArrayList<MembroBand> partecipazioniBand;


    /**
     * Instantiates a new Musicista.
     *
     * @param nomeArte           the nome arte
     * @param annoInizioAttivita the anno inizio attivita
     * @param idArtista          the id artista
     * @param nomeVero           the nome vero
     * @param cognomeVero        the cognome vero
     * @param dataDiNascita      the data di nascita
     * @throws CampoNonValido the campo non valido
     */
    public Musicista(String nomeArte, int annoInizioAttivita, String idArtista, String nomeVero, String cognomeVero, LocalDate dataDiNascita) throws CampoNonValido {
        super(nomeArte, annoInizioAttivita, idArtista);
        setNomeVero(nomeVero);
        setCognonomeVero(cognomeVero);
        setDataDiNascita(dataDiNascita);
        partecipazioniBand = new ArrayList<>();
    }
    //Getter

    /**
     * Gets nome vero.
     *
     * @return the nome vero
     */
    public String getNomeVero() {
        return nomeVero;
    }

    /**
     * Gets cognonome vero.
     *
     * @return the cognonome vero
     */
    public String getCognonomeVero() {
        return cognonomeVero;
    }

    /**
     * Gets data di nascita.
     *
     * @return the data di nascita
     */
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Gets partecipazioni band.
     *
     * @return the partecipazioni band
     */
    public ArrayList<MembroBand> getPartecipazioniBand() {
        return partecipazioniBand;
    }

    // Setter

    /**
     * Sets nome vero.
     *
     * @param nomeVero the nome vero
     * @throws CampoNonValido the campo non valido
     */
    public void setNomeVero(String nomeVero) throws CampoNonValido {
        if(nomeVero == null ||  nomeVero.trim().length()<1 || nomeVero.trim().length()>15){
            throw new CampoNonValido("Il Nome deve avere minimo 1 carattere e massimo 15!");
        }
        this.nomeVero = nomeVero;
    }


    /**
     * Sets cognonome vero.
     *
     * @param cognonomeVero the cognonome vero
     * @throws CampoNonValido the campo non valido
     */
    public void setCognonomeVero(String cognonomeVero) throws CampoNonValido {
        if(cognonomeVero == null ||  cognonomeVero.trim().length()<1 || cognonomeVero.trim().length()>30){
            throw new CampoNonValido("Il Cognome deve avere minimo 1 carattere e massimo 30!");
        }
        this.cognonomeVero = cognonomeVero;
    }


    /**
     * Sets data di nascita.
     *
     * @param dataDiNascita the data di nascita
     * @throws CampoNonValido the campo non valido
     */
    public void setDataDiNascita(LocalDate dataDiNascita) throws CampoNonValido {
        if (dataDiNascita.getYear() > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di nascita non può superare l'anno in corso.");
        }
        if (dataDiNascita.getYear() < 1900){
            throw new CampoNonValido("L'anno di nascita inserito non è valido.");
        }
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * Sets partecipazioni band.
     *
     * @param partecipazioniBand the partecipazioni band
     * @throws CampoNonValido the campo non valido
     */
    public void setPartecipazioniBand(ArrayList<MembroBand> partecipazioniBand) throws CampoNonValido {
        if(partecipazioniBand == null){
            throw new CampoNonValido("Partecipazioni alle band non possono essere null");
        }
        this.partecipazioniBand = partecipazioniBand;
    }

    // Altri Metodi

    /**
     * Add partecipazione band.
     *
     * @param partecipazioneBand the partecipazione band
     * @throws CampoNonValido the campo non valido
     */
    public void addPartecipazioneBand(MembroBand partecipazioneBand) throws CampoNonValido{
        if(partecipazioneBand == null){
            throw new CampoNonValido ("La partecipazione alla Band da aggiungere non può essere null");
        }
        if(!partecipazioniBand.contains(partecipazioneBand)){
            partecipazioniBand.add(partecipazioneBand);
        } else {
            throw new CampoNonValido("Partecipazione alla band già presente nella lista di partecipazioni");
        }
    }

    @Override
    public String toString() {
        return nomeVero + cognonomeVero;
    }
}
