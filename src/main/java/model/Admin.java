package model;

/**
 * Rappresenta un utente con privilegi speciali. In particolare l'admin in aggiunta all'utente standard è
 * identificato con unoo speciale id (idAdmin) e
 * può aggiungere al sistema nuovi album, artisti e generi e gestisce le proposte.
 */
public class Admin extends Utente {
    private String idAdmin;

    /**
     * Istanzia un nuovo Admin nel sistema.
     * I parametri di base vengono passati al costruttore della superclasse Utente.
     *
     * @param username
     * Lo pseudonimo univoco scelto per il login dell'admin.
     * @param password
     * La chiave di accesso per l'account dell'admin.
     * @param nazione
     * La nazione di provenienza (valore dell'enum Nazione).
     * @param idAdmin
     * L'identificativo univoco che distingue questo specifico amministratore.
     * @throws CampoNonValido
     * Se uno dei parametri di base forniti alla superclasse non è valido o contiene caratteri non ammessi.
     */
    public Admin(String username, String password, Nazione nazione, String idAdmin) throws CampoNonValido {
        super(username, password, nazione);
        this.idAdmin = idAdmin;

    }

    /**
     * Restituisce l'id univoco dell'Admin.
     *
     * @return La stringa contenente l'Id dell'admin
     *
     */
    public String getIdAdmin() {
        return idAdmin;
    }

    /**
     * Imposta o modifica l'id dell'admin.
     *
     * @param idAdmin Il nuovo identificativo da assegnare all'admin.
     * @throws CampoNonValido Se il parametro idAdmin passato risulta nullo.
     */
    public void setIdAdmin(String idAdmin) throws CampoNonValido{
        if(idAdmin == null){
            throw new CampoNonValido("Id Admin non valido.");
        }
        this.idAdmin = idAdmin;
    }

    /**
     * Cambia lo stato di una proposta in sospeso trasformandola in "ACCETTATA".
     *
     * @param propostaInviata L'oggetto Proposta su cui l'amministratore ha dato esito positivo.
     * @throws CampoNonValido Se ci sono problemi legati alla validazione dello stato della proposta (se implementati).
     */
    public void setStatoPropostaAccettata(Proposta propostaInviata) throws CampoNonValido{
        propostaInviata.setStatoProposta(StatoProposta.ACCETTATA);
    }

    /**
     * Cambia lo stato di una proposta in sospeso trasformandola in "RIFIUTATA".
     *
     * @param propostaInviata L'oggetto Proposta che l'amministratore ha deciso di bocciare.
     * @throws CampoNonValido Se ci sono problemi legati alla validazione dello stato della proposta (se implementati).
     */
    public void setStatoPropostaRifiutata(Proposta propostaInviata) throws CampoNonValido {
        propostaInviata.setStatoProposta(StatoProposta.RIFIUTATA);
    }
}