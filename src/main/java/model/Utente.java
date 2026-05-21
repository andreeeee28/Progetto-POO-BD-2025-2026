package model;

import java.util.ArrayList;

public class Utente {
    protected String username;
    protected String password;
    protected String nazione;
    protected ArrayList <Proposta> proposteInviate;

    public Utente(String username, String password, String nazione) throws CampoNonValido {
        setUsername(username);
        setPassword(password);
        this.nazione = nazione;
        this.proposteInviate = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws CampoNonValido {
        if(username == null ||  username.trim().length()<5 || username.trim().length()>15){
            throw new CampoNonValido("L'username deve avere minimo 5 caratteri e massimo 15!");
        }
        this.username = username;
        }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws  CampoNonValido{
        if (password == null  || password.length() < 10 || password.length() > 20) {
            throw new CampoNonValido("La password deve avere minimo 10 caratteri e massimo 20!");
        }
        this.password = password;
    }


    public String getNazione() throws  CampoNonValido{
        return nazione;
    }

    public void setNazione(String nazione) throws CampoNonValido {
        // La Repubblica Democratica del Congo è la nazione col nome più lungo in italiano
        if(nazione == null || nazione.length()<4 || nazione.length()>32){
            throw new CampoNonValido("Non esistono nazioni in italiano col nome di questa lunghezza");
        }
        this.nazione = nazione;
    }
    public void addProposta(Proposta propostaInviata){
        this.proposteInviate.add(propostaInviata);
    }
}
