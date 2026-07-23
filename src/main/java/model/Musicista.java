package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Rappresenta un singolo musicista (artista solista o componente di un gruppo) all'interno del sistema.
 * Estende la classe astratta Artista aggiungendo dati anagrafici specifici come il vero nome,
 * il vero cognome, la data di nascita e uno storico di tutte le sue partecipazioni nelle varie band.
 */
public class Musicista extends Artista {
    private String nomeVero;
    private String cognonomeVero;
    private LocalDate dataDiNascita;
    private ArrayList<MembroBand> partecipazioniBand;

    /**
     * Istanzia un nuovo oggetto Musicista assegnando i parametri anagrafici e artistici, passando quelli base alla superclasse Artista.
     *
     * @param nomeArte Il nome d'arte del musicista (tra 1 e 30 caratteri).
     * @param annoInizioAttivita L'anno in cui il musicista ha iniziato la sua carriera.
     * @param idArtista L'identificativo alfanumerico univoco associato al musicista.
     * @param nomeVero Il vero nome di battesimo del musicista (tra 1 e 15 caratteri).
     * @param cognomeVero Il vero cognome del musicista (tra 1 e 30 caratteri).
     * @param dataDiNascita La data di nascita del musicista.
     * @throws CampoNonValido Se uno dei parametri non rispetta i vincoli di validazione dei relativi setter.
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
     * Restituisce il vero nome di battesimo del musicista.
     *
     * @return La stringa contenente il nome vero.
     */
    public String getNomeVero() {
        return nomeVero;
    }

    /**
     * Restituisce il vero cognome del musicista.
     *
     * @return La stringa contenente il cognome vero.
     */
    public String getCognonomeVero() {
        return cognonomeVero;
    }

    /**
     * Restituisce la data di nascita del musicista.
     *
     * @return L'oggetto LocalDate con la data di nascita.
     */
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Restituisce lo storico di tutte le band in cui il musicista suona o ha suonato.
     *
     * @return L'ArrayList contenente gli oggetti MembroBand associati a questo musicista.
     */
    public ArrayList<MembroBand> getPartecipazioniBand() {
        return partecipazioniBand;
    }

    // Setter

    /**
     * Imposta o modifica il vero nome di battesimo del musicista.
     *
     * @param nomeVero Il nuovo nome vero da assegnare.
     * @throws CampoNonValido Se il nome è nullo, vuoto o supera i 15 caratteri di lunghezza.
     */
    public void setNomeVero(String nomeVero) throws CampoNonValido {
        if(nomeVero == null ||  nomeVero.trim().length()<1 || nomeVero.trim().length()>15){
            throw new CampoNonValido("Il Nome deve avere minimo 1 carattere e massimo 15!");
        }
        this.nomeVero = nomeVero;
    }

    /**
     * Imposta o modifica il vero cognome del musicista.
     *
     * @param cognonomeVero Il nuovo cognome vero da assegnare.
     * @throws CampoNonValido Se il cognome è nullo, vuoto o supera i 30 caratteri di lunghezza.
     */
    public void setCognonomeVero(String cognonomeVero) throws CampoNonValido {
        if(cognonomeVero == null ||  cognonomeVero.trim().length()<1 || cognonomeVero.trim().length()>30){
            throw new CampoNonValido("Il Cognome deve avere minimo 1 carattere e massimo 30!");
        }
        this.cognonomeVero = cognonomeVero;
    }

    /**
     * Imposta o modifica la data di nascita del musicista.
     *
     * @param dataDiNascita La nuova data di nascita da assegnare.
     * @throws CampoNonValido Se l'anno di nascita è successivo a quello in corso o precedente al 1900.
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
     * Sostituisce l'intero storico delle partecipazioni alle band del musicista con la lista fornita.
     *
     * @param partecipazioniBand L'ArrayList di oggetti MembroBand da impostare.
     * @throws CampoNonValido Se la lista fornita risulta nulla.
     */
    public void setPartecipazioniBand(ArrayList<MembroBand> partecipazioniBand) throws CampoNonValido {
        if(partecipazioniBand == null){
            throw new CampoNonValido("Partecipazioni alle band non possono essere null");
        }
        this.partecipazioniBand = partecipazioniBand;
    }

    // Altri Metodi

    /**
     * Aggiunge una nuova partecipazione a una band per questo musicista, verificando che non sia un duplicato.
     *
     * @param partecipazioneBand L'oggetto MembroBand da inserire nello storico.
     * @throws CampoNonValido Se la partecipazione passata è nulla o è già presente nella lista.
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

    /**
     * Restituisce una rappresentazione testuale del musicista unendo nome e cognome.
     *
     * @return La concatenazione di nome vero e cognome vero.
     */
    @Override
    public String toString() {
        return nomeVero + cognonomeVero;
    }
}