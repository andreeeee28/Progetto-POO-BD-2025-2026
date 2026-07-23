package model;

import java.util.ArrayList;

/**
 * Rappresenta un utente standard registrato all'interno del sistema.
 * Memorizza le credenziali di accesso (username e password), la nazione di provenienza
 * e lo storico di tutte le proposte di inserimento inviate dall'utente.
 */
public class Utente {
    private String username;
    private String password;
    private Nazione nazione;
    private ArrayList <Proposta> proposteInviate;

    /**
     * Istanzia un nuovo oggetto Utente validando e assegnando username, password e nazione.
     *
     * @param username Lo pseudonimo univoco scelto dall'utente per il login (tra 5 e 15 caratteri).
     * @param password La chiave di accesso per l'account dell'utente (tra 10 e 20 caratteri).
     * @param nazione La nazione di provenienza dell'utente (valore Enum Nazione).
     * @throws CampoNonValido Se uno dei parametri non rispetta i vincoli di validazione dei relativi setter.
     */
    public Utente(String username, String password, Nazione nazione) throws CampoNonValido {
        setUsername(username);
        setPassword(password);
        setNazione(nazione);
        this.proposteInviate = new ArrayList<>();
    }

    /**
     * Restituisce l'username associato all'account dell'utente.
     *
     * @return La stringa contenente il nome utente.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta o modifica l'username dell'utente.
     *
     * @param username Il nuovo nome utente da assegnare.
     * @throws CampoNonValido Se l'username è nullo, vuoto o non è compreso tra 5 e 15 caratteri.
     */
    public void setUsername(String username) throws CampoNonValido {
        if(username == null ||  username.trim().length()<5 || username.trim().length()>15){
            throw new CampoNonValido("L'username deve avere minimo 5 caratteri e massimo 15!");
        }
        this.username = username;
    }


    /**
     * Restituisce la password dell'account dell'utente.
     *
     * @return La stringa contenente la password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta o modifica la password dell'account dell'utente.
     *
     * @param password La nuova password da assegnare.
     * @throws CampoNonValido Se la password è nulla o non è compresa tra 10 e 20 caratteri.
     */
    public void setPassword(String password) throws  CampoNonValido{
        if (password == null  || password.length() < 10 || password.length() > 20) {
            throw new CampoNonValido("La password deve avere minimo 10 caratteri e massimo 20!");
        }
        this.password = password;
    }


    /**
     * Restituisce la nazione di provenienza dell'utente.
     *
     * @return L'oggetto Enum Nazione associato all'utente.
     */
    public Nazione getNazione() {
        return nazione;
    }

    /**
     * Imposta o modifica la nazione di provenienza dell'utente.
     *
     * @param nazione La nuova nazione da assegnare.
     * @throws CampoNonValido Se il parametro nazione passato risulta nullo.
     */
    public void setNazione(Nazione nazione) throws CampoNonValido {
        if(nazione == null){

            throw new CampoNonValido ("La nazione nn può essere null");
        }
        this.nazione = nazione;

    }

    /**
     * Aggiunge una nuova proposta allo storico delle proposte inviate da questo utente.
     *
     * @param propostaInviata L'oggetto Proposta da aggiungere alla lista.
     * @throws CampoNonValido Se la proposta è nulla o è già presente nello storico dell'utente.
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