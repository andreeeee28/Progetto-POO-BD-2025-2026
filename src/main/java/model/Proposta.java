package model;

import java.util.Date;

public class Proposta {
    private String tipoElemento;
    private String titoloElemento;
    private String descrizione;
    private Date dataRichiesta;
    private StatoProposta statoProposta;

    public Proposta(String tipoElemento, Date dataRichiesta, String descrizione, String titoloElemento) {
        this.tipoElemento = tipoElemento;
        this.dataRichiesta = dataRichiesta;
        this.descrizione = descrizione;
        this.titoloElemento = titoloElemento;
        setStatoPropostaDefault();


    }

    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
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


    public Date getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(Date dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTitoloElemento() {
        return titoloElemento;
    }

    public void setTitoloElemento(String titoloElemento) {
        this.titoloElemento = titoloElemento;
    }
}
