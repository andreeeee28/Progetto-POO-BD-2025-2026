package model;

/**
 * The type Admin.
 */
public class Admin extends Utente {
    private String idAdmin;

    /**
     * Instantiates a new Admin.
     *
     * @param username the username
     * @param password the password
     * @param nazione  the nazione
     * @param idAdmin  the id admin
     * @throws CampoNonValido the campo non valido
     */
    public Admin(String username, String password, Nazione nazione, String idAdmin) throws CampoNonValido {
        super(username, password, nazione);
        this.idAdmin = idAdmin;

    }

    /**
     * Gets id admin.
     *
     * @return the id admin
     */
    public String getIdAdmin() {
        return idAdmin;
    }

    /**
     * Sets id admin.
     *
     * @param idAdmin the id admin
     * @throws CampoNonValido the campo non valido
     */
    public void setIdAdmin(String idAdmin) throws CampoNonValido{
        if(idAdmin == null){
            throw new CampoNonValido("Id Admin non valido.");
        }
        this.idAdmin = idAdmin;
    }

    /**
     * Sets stato proposta accettata.
     *
     * @param propostaInviata the proposta inviata
     * @throws CampoNonValido the campo non valido
     */
    public void setStatoPropostaAccettata(Proposta propostaInviata) throws CampoNonValido{
        propostaInviata.setStatoProposta(StatoProposta.ACCETTATA);
    }

    /**
     * Sets stato proposta rifiutata.
     *
     * @param propostaInviata the proposta inviata
     * @throws CampoNonValido the campo non valido
     */
    public void setStatoPropostaRifiutata(Proposta propostaInviata) throws CampoNonValido {
        propostaInviata.setStatoProposta(StatoProposta.RIFIUTATA);
    }
}

