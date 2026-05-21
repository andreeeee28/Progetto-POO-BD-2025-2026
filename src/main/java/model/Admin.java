package model;

public class Admin extends Utente {
    private String idAdmin;

    public Admin(String username, String password, String nazione, String idAdmin) throws CampoNonValido {
        super(username, password, nazione);
        this.idAdmin = idAdmin;

    }

    public void setStatoPropostaAccettata(Proposta propostaInviata) {
        propostaInviata.setStatoProposta(StatoProposta.ACCETTATA);
    }

    public void setStatoPropostaRifiutata(Proposta propostaInviata) {
        propostaInviata.setStatoProposta(StatoProposta.RIFIUTATA);
    }
}

