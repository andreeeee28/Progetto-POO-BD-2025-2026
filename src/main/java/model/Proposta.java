package model;

import java.time.LocalDate;

/**
 * The type Proposta.
 */
public class Proposta {
    private TipoProposta tipoElemento;
    private String titoloElemento;
    private String descrizione;
    private LocalDate dataRichiesta;
    private StatoProposta statoProposta;
    private Utente autoreProposta;

    /**
     * Instantiates a new Proposta.
     *
     * @param tipoElemento   the tipo elemento
     * @param descrizione    the descrizione
     * @param titoloElemento the titolo elemento
     * @param autoreProposta the autore proposta
     * @throws CampoNonValido the campo non valido
     */
    public Proposta(TipoProposta tipoElemento, String descrizione, String titoloElemento, Utente autoreProposta) throws CampoNonValido{
        setTipoElemento(tipoElemento);
        setDataRichiestaDefault();
        setDescrizione(descrizione);
        setTitoloElemento(titoloElemento);
        setStatoPropostaDefault();
        setAutoreProposta(autoreProposta);
    }

    /**
     * Gets tipo elemento.
     *
     * @return the tipo elemento
     */
//Getter
    public TipoProposta getTipoElemento() {
        return tipoElemento;
    }

    /**
     * Gets titolo elemento.
     *
     * @return the titolo elemento
     */
    public String getTitoloElemento() {
        return titoloElemento;
    }

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Gets stato proposta.
     *
     * @return the stato proposta
     */
    public StatoProposta getStatoProposta() {
        return statoProposta;
    }

    /**
     * Gets data richiesta.
     *
     * @return the data richiesta
     */
    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    /**
     * Gets autore proposta.
     *
     * @return the autore proposta
     */
    public Utente getAutoreProposta() {
        return autoreProposta;
    }

    //Setter

    /**
     * Sets tipo elemento.
     *
     * @param tipoElemento the tipo elemento
     * @throws CampoNonValido the campo non valido
     */
    public void setTipoElemento(TipoProposta tipoElemento) throws CampoNonValido{
        if(tipoElemento == null){
            throw new CampoNonValido("Il Tipo Elemento non è valido.");
        }
        this.tipoElemento = tipoElemento;
    }

    /**
     * Sets stato proposta default.
     */
    public void setStatoPropostaDefault() {
        this.statoProposta = StatoProposta.VALUTAZIONE_IN_CORSO;
    }

    /**
     * Sets stato proposta.
     *
     * @param giudizio the giudizio
     * @throws CampoNonValido the campo non valido
     */
    public void setStatoProposta(StatoProposta giudizio) throws CampoNonValido {
        if (giudizio == null) {
            throw new CampoNonValido("Lo stato della proposta non può essere nullo.");
        }
        this.statoProposta = giudizio;
    }


    /**
     * Sets data richiesta default.
     */
    public void setDataRichiestaDefault() {
        this.dataRichiesta = LocalDate.now();
    }

    /**
     * Sets data richiesta.
     *
     * @param dataRichiesta the data richiesta
     * @throws CampoNonValido the campo non valido
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
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     * @throws CampoNonValido the campo non valido
     */
    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<100 || descrizione.trim().length()>300){
            throw new CampoNonValido("La descrizione deve avere minimo 100 carattere e massimo 300!");
        }
        this.descrizione = descrizione;
    }


    /**
     * Sets titolo elemento.
     *
     * @param titoloElemento the titolo elemento
     * @throws CampoNonValido the campo non valido
     */
    public void setTitoloElemento(String titoloElemento) throws CampoNonValido {
        if(titoloElemento == null ||  titoloElemento.trim().length()<1 || titoloElemento.trim().length()>30){
            throw new CampoNonValido("Il Titolo dell'Elemento deve avere minimo 1 carattere e massimo 30!");
        }
        this.titoloElemento = titoloElemento;
    }


    /**
     * Sets autore proposta.
     *
     * @param autoreProposta the autore proposta
     * @throws CampoNonValido the campo non valido
     */
    public void setAutoreProposta(Utente autoreProposta) throws CampoNonValido {
        if(autoreProposta == null){
            throw  new CampoNonValido("L'autore della proposta non può essere null");
        }
        this.autoreProposta = autoreProposta;
    }

    @Override
    public String toString() {
        return  tipoElemento + " " + titoloElemento;

    }
}
