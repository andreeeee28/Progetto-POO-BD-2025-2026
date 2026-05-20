package model;

import java.util.ArrayList;

public class Utente {
    protected String username;
    protected String password;
    protected String nazione;
    protected ArrayList <Proposta> proposteInviate;

    public Utente(String username, String password, String nazione) {
        setUsername(username);
        setPassword(password);
        this.nazione = nazione;
        this.proposteInviate = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if(username == null ||  username.trim().length()<5 || username.trim().length()>10){
            throw new IllegalArgumentException("L'username deve avere minimo 5 caratteri e massimo 10!");
        }
        this.username = username;
        }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws  IllegalArgumentException{
        if (password == null  || password.length() < 10 || password.length() > 20) {
            throw new IllegalArgumentException("La password deve avere minimo 10 caratteri e massimo 20!");
        }
        this.password = password;
    }


    public String getNazione() throws  IllegalArgumentException{
        return nazione;
    }

    public void setNazione(String nazione) throws IllegalArgumentException {
        // La Repubblica Democratica del Congo è la nazione col nome più lungo in italiano
        if(nazione == null || nazione.length()<4 || nazione.length()>32){
            throw new IllegalArgumentException("Non esistono nazioni in italiano col nome di questa lunghezza");
        }
        this.nazione = nazione;
    }
    public void addProposta(Proposta propostaInviata){
        this.proposteInviate.add(propostaInviata);
    }
}
