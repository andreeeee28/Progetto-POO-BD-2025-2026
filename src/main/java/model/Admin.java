package model;

public class Admin extends Utente {
    private String idAdmin;

    public Admin(String username, String password, Nazione nazione, String idAdmin) throws CampoNonValido {
        super(username, password, nazione);
        this.idAdmin = idAdmin;

    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) throws CampoNonValido{
        if(idAdmin == null){
            throw new CampoNonValido("Id Admin non valido.");
        }
        this.idAdmin = idAdmin;
    }

    public void setStatoPropostaAccettata(Proposta propostaInviata) throws CampoNonValido{
        propostaInviata.setStatoProposta(StatoProposta.ACCETTATA);
    }

    public void setStatoPropostaRifiutata(Proposta propostaInviata) throws CampoNonValido {
        propostaInviata.setStatoProposta(StatoProposta.RIFIUTATA);
    }
}

