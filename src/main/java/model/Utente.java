package model;

import java.util.ArrayList;

/**
 * The type Utente.
 */
public class Utente {
    private String username;
    private String password;
    private Nazione nazione;
    private ArrayList <Proposta> proposteInviate;

    /**
     * Instantiates a new Utente.
     *
     * @param username the username
     * @param password the password
     * @param nazione  the nazione
     * @throws CampoNonValido the campo non valido
     */
    public Utente(String username, String password, Nazione nazione) throws CampoNonValido {
        setUsername(username);
        setPassword(password);
        setNazione(nazione);
        this.proposteInviate = new ArrayList<>();
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     * @throws CampoNonValido the campo non valido
     */
    public void setUsername(String username) throws CampoNonValido {
        if(username == null ||  username.trim().length()<5 || username.trim().length()>15){
            throw new CampoNonValido("L'username deve avere minimo 5 caratteri e massimo 15!");
        }
        this.username = username;
        }


    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @throws CampoNonValido the campo non valido
     */
    public void setPassword(String password) throws  CampoNonValido{
        if (password == null  || password.length() < 10 || password.length() > 20) {
            throw new CampoNonValido("La password deve avere minimo 10 caratteri e massimo 20!");
        }
        this.password = password;
    }


    /**
     * Gets nazione.
     *
     * @return the nazione
     * @throws CampoNonValido the campo non valido
     */
    public Nazione getNazione() throws  CampoNonValido{
        return nazione;
    }

    /**
     * Sets nazione.
     *
     * @param nazione the nazione
     * @throws CampoNonValido the campo non valido
     */
    public void setNazione(Nazione nazione) throws CampoNonValido {
        if(nazione == null){

            throw new CampoNonValido ("La nazione nn può essere null");
        }
        this.nazione = nazione;

    }

    /**
     * Add proposta.
     *
     * @param propostaInviata the proposta inviata
     * @throws CampoNonValido the campo non valido
     */
    public void addProposta(Proposta propostaInviata) throws  CampoNonValido{
        if(propostaInviata == null){
            throw new CampoNonValido("La proposta non può essere null");
        }
        if(!proposteInviate.contains(propostaInviata)){
            this.proposteInviate.add(propostaInviata);
        } else {
            throw new CampoNonValido("Questa proposta è gia presente nella lista delle proposte inviate");
        }

    }
}
