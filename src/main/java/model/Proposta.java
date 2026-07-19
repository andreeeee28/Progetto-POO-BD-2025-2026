package model;

import java.time.LocalDate;

public class Proposta {
    private TipoProposta tipoElemento;
    private String titoloElemento;
    private String descrizione;
    private LocalDate dataRichiesta;
    private StatoProposta statoProposta;
    private Utente autoreProposta;

    public Proposta(TipoProposta tipoElemento, String descrizione, String titoloElemento, Utente autoreProposta) throws CampoNonValido{
        setTipoElemento(tipoElemento);
        setDataRichiestaDefault();
        setDescrizione(descrizione);
        setTitoloElemento(titoloElemento);
        setStatoPropostaDefault();
        setAutoreProposta(autoreProposta);
    }

    //Getter
    public TipoProposta getTipoElemento() {
        return tipoElemento;
    }

    public String getTitoloElemento() {
        return titoloElemento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public StatoProposta getStatoProposta() {
        return statoProposta;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public Utente getAutoreProposta() {
        return autoreProposta;
    }

    //Setter

    public void setTipoElemento(TipoProposta tipoElemento) throws CampoNonValido{
        if(tipoElemento == null){
            throw new CampoNonValido("Il Tipo Elemento non è valido.");
        }
        this.tipoElemento = tipoElemento;
    }

    public void setStatoPropostaDefault() {
        this.statoProposta = StatoProposta.VALUTAZIONE_IN_CORSO;
    }

    public void setStatoProposta(StatoProposta giudizio) throws CampoNonValido {
        if (giudizio == null) {
            throw new CampoNonValido("Lo stato della proposta non può essere nullo.");
        }
        this.statoProposta = giudizio;
    }


    public void setDataRichiestaDefault() {
        this.dataRichiesta = LocalDate.now();
    }

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


    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<100 || descrizione.trim().length()>300){
            throw new CampoNonValido("La descrizione deve avere minimo 100 carattere e massimo 300!");
        }
        this.descrizione = descrizione;
    }


    public void setTitoloElemento(String titoloElemento) throws CampoNonValido {
        if(titoloElemento == null ||  titoloElemento.trim().length()<1 || titoloElemento.trim().length()>30){
            throw new CampoNonValido("Il Titolo dell'Elemento deve avere minimo 1 carattere e massimo 30!");
        }
        this.titoloElemento = titoloElemento;
    }


    public void setAutoreProposta(Utente autoreProposta) throws CampoNonValido {
        if(autoreProposta == null){
            throw  new CampoNonValido("L'autore della proposta non può essere null");
        }
        this.autoreProposta = autoreProposta;
    }

    @Override
    public String toString() {
        return  tipoElemento+ titoloElemento;

    }
}
