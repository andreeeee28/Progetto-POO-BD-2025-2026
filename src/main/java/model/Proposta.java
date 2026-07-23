package model;

import java.time.LocalDate;

/**
 * Rappresenta una proposta di inserimento inviata da un utente standard.
 * Contiene le informazioni necessarie all'amministratore per valutare l'aggiunta
 * di un nuovo elemento (es. Album, Artista, Genere) al database del sistema.
 */
public class Proposta {
    private TipoProposta tipoElemento;
    private String titoloElemento;
    private String descrizione;
    private LocalDate dataRichiesta;
    private StatoProposta statoProposta;
    private Utente autoreProposta;

    /**
     * Istanzia un nuovo oggetto Proposta con i dati forniti dall'utente.
     * La data della richiesta e lo stato iniziale vengono impostati automaticamente.
     *
     * @param tipoElemento Il tipo di elemento che si vuole proporre (valore Enum TipoProposta).
     * @param descrizione Le motivazioni o i dettagli aggiuntivi forniti dall'utente (tra 100 e 300 caratteri).
     * @param titoloElemento Il titolo o nome dell'elemento proposto (tra 1 e 30 caratteri).
     * @param autoreProposta L'oggetto Utente che ha generato e inviato la proposta.
     * @throws CampoNonValido Se uno dei parametri non rispetta i vincoli di validazione dei relativi setter.
     */
    public Proposta(TipoProposta tipoElemento, String descrizione, String titoloElemento, Utente autoreProposta) throws CampoNonValido{
        setTipoElemento(tipoElemento);
        setDataRichiestaDefault();
        setDescrizione(descrizione);
        setTitoloElemento(titoloElemento);
        setStatoPropostaDefault();
        setAutoreProposta(autoreProposta);
    }

    //Getter

    /**
     * Restituisce la categoria dell'elemento proposto.
     *
     * @return L'enum TipoProposta corrispondente all'elemento.
     */
    public TipoProposta getTipoElemento() {
        return tipoElemento;
    }

    /**
     * Restituisce il nome o titolo dell'elemento proposto.
     *
     * @return La stringa contenente il titolo dell'elemento.
     */
    public String getTitoloElemento() {
        return titoloElemento;
    }

    /**
     * Restituisce la descrizione o motivazione allegata alla proposta.
     *
     * @return La stringa contenente la descrizione.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Restituisce lo stato attuale di valutazione della proposta.
     *
     * @return L'enum StatoProposta (es. in valutazione, accettata, rifiutata).
     */
    public StatoProposta getStatoProposta() {
        return statoProposta;
    }

    /**
     * Restituisce la data in cui la proposta è stata generata e inviata.
     *
     * @return L'oggetto LocalDate rappresentante la data di richiesta.
     */
    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    /**
     * Restituisce l'utente che ha creato la proposta.
     *
     * @return L'oggetto Utente autore della proposta.
     */
    public Utente getAutoreProposta() {
        return autoreProposta;
    }

    //Setter

    /**
     * Imposta o modifica la categoria dell'elemento proposto.
     *
     * @param tipoElemento Il nuovo tipo di elemento da assegnare.
     * @throws CampoNonValido Se il parametro passato risulta nullo.
     */
    public void setTipoElemento(TipoProposta tipoElemento) throws CampoNonValido{
        if(tipoElemento == null){
            throw new CampoNonValido("Il Tipo Elemento non è valido.");
        }
        this.tipoElemento = tipoElemento;
    }

    /**
     * Inizializza lo stato della proposta al valore di default (VALUTAZIONE_IN_CORSO).
     */
    public void setStatoPropostaDefault() {
        this.statoProposta = StatoProposta.VALUTAZIONE_IN_CORSO;
    }

    /**
     * Imposta o aggiorna l'esito della valutazione per la proposta.
     *
     * @param giudizio Il nuovo stato da assegnare alla proposta.
     * @throws CampoNonValido Se il parametro giudizio passato risulta nullo.
     */
    public void setStatoProposta(StatoProposta giudizio) throws CampoNonValido {
        if (giudizio == null) {
            throw new CampoNonValido("Lo stato della proposta non può essere nullo.");
        }
        this.statoProposta = giudizio;
    }


    /**
     * Inizializza la data della proposta assegnando automaticamente la data odierna.
     */
    public void setDataRichiestaDefault() {
        this.dataRichiesta = LocalDate.now();
    }

    /**
     * Imposta o sovrascrive la data in cui è stata effettuata la proposta.
     *
     * @param dataRichiesta La nuova data da assegnare alla richiesta.
     * @throws CampoNonValido Se la data è nulla, successiva a quella odierna o precedente al 1900.
     */
    public void setDataRichiesta(LocalDate dataRichiesta) throws CampoNonValido{
        if (dataRichiesta == null){
            throw new CampoNonValido("La data della richiesta non può essere vuota.");
        }
        if (dataRichiesta.isAfter(LocalDate.now())){
            throw new CampoNonValido("La data della richiesta non può superare la data attuale.");
        }
        if (dataRichiesta.isBefore(LocalDate.of(1900,1,1))){
            throw new CampoNonValido("La data della richiesta inserita non è valida.");
        }
        this.dataRichiesta = dataRichiesta;
    }


    /**
     * Imposta o modifica la descrizione allegata alla proposta.
     *
     * @param descrizione La nuova stringa descrittiva da assegnare.
     * @throws CampoNonValido Se la descrizione è nulla, vuota, o non è compresa tra 100 e 300 caratteri.
     */
    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<100 || descrizione.trim().length()>300){
            throw new CampoNonValido("La descrizione deve avere minimo 100 carattere e massimo 300!");
        }
        this.descrizione = descrizione;
    }


    /**
     * Imposta o modifica il titolo o nome dell'elemento proposto.
     *
     * @param titoloElemento Il nuovo titolo da assegnare all'elemento.
     * @throws CampoNonValido Se il titolo è nullo, vuoto o supera i 30 caratteri di lunghezza.
     */
    public void setTitoloElemento(String titoloElemento) throws CampoNonValido {
        if(titoloElemento == null ||  titoloElemento.trim().length()<1 || titoloElemento.trim().length()>30){
            throw new CampoNonValido("Il Titolo dell'Elemento deve avere minimo 1 carattere e massimo 30!");
        }
        this.titoloElemento = titoloElemento;
    }


    /**
     * Imposta o modifica l'utente autore della proposta.
     *
     * @param autoreProposta L'oggetto Utente da collegare come autore.
     * @throws CampoNonValido Se l'utente passato risulta nullo.
     */
    public void setAutoreProposta(Utente autoreProposta) throws CampoNonValido {
        if(autoreProposta == null){
            throw  new CampoNonValido("L'autore della proposta non può essere null");
        }
        this.autoreProposta = autoreProposta;
    }

    /**
     * Restituisce una rappresentazione testuale sintetica della proposta.
     *
     * @return La concatenazione del tipo di elemento e del suo titolo.
     */
    @Override
    public String toString() {
        return  tipoElemento + " " + titoloElemento;
    }
}