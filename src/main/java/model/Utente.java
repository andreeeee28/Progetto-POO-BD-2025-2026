package model;

import java.util.ArrayList;

public class Utente {
    protected String username;
    protected String password;
    protected String nazione;
    protected ArrayList <Proposta> proposteInviate;

    public Utente(String username, String password, String nazione) {
        this.username = username;
        this.password = password;
        this.nazione = nazione;
        this.proposteInviate = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
    public void addProposta(Proposta propostaInviata){
        this.proposteInviate.add(propostaInviata);
    }
}
