package model;

import java.time.LocalDate;

public class Proposta {
    private String tipoElemento;
    private String titoloElemento;
    private String descrizione;
    private LocalDate dataRichiesta;
    private StatoProposta statoProposta;

    //Costruttore
    public Proposta(String tipoElemento, LocalDate dataRichiesta, String descrizione, String titoloElemento) throws CampoNonValido{
        setTipoElemento(tipoElemento);
        setDataRichiestaDefault();
        setDescrizione(descrizione);
        setTitoloElemento(titoloElemento);
        setStatoPropostaDefault();
    }


    //Getter & Setter
    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) throws CampoNonValido{
        if(tipoElemento == null ||  tipoElemento.trim().length()<1 || tipoElemento.trim().length()>30){
            throw new CampoNonValido("Il Tipo Elemento deve avere minimo 1 carattere e massimo 30!");
        }
        this.tipoElemento = tipoElemento;
    }

    public StatoProposta getStatoProposta() {
        return statoProposta;
    }

    public void setStatoPropostaDefault() {
        this.statoProposta = StatoProposta.VALUTAZIONE_IN_CORSO;
    }

    public void setStatoProposta(StatoProposta giudizio) {
        this.statoProposta = giudizio;
    }


    public LocalDate getDataRichiesta() {
        return dataRichiesta;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) throws CampoNonValido {
        if(descrizione == null ||  descrizione.trim().length()<1 || descrizione.trim().length()>100){
            throw new CampoNonValido("La descrizione deve avere minimo 1 carattere e massimo 100!");
        }
        this.descrizione = descrizione;
    }

    public String getTitoloElemento() {
        return titoloElemento;
    }

    public void setTitoloElemento(String titoloElemento) throws CampoNonValido {
        if(titoloElemento == null ||  titoloElemento.trim().length()<1 || titoloElemento.trim().length()>30){
            throw new CampoNonValido("Il Titolo dell'Elemento deve avere minimo 1 carattere e massimo 30!");
        }
        this.titoloElemento = titoloElemento;
    }
}
