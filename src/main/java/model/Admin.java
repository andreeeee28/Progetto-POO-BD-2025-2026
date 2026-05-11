package model;

public class Admin extends Utente {
    private String idAdmin;

    public Admin(String username, String password, String nazione, String idAdmin) {
        super(username, password, nazione);
        this.idAdmin = idAdmin;

    }

    public void setStatoPropostaAccettata(Proposta propostaInviata) {
        propostaInviata.setStatoProposta("Accettata");
    }

    public void setStatoPropostaRifiutata(Proposta propostaInviata) {
        propostaInviata.setStatoProposta("Rifiutata");
    }
}

