package model;

import java.util.ArrayList;

public class Utente {
    private String username;
    private String password;
    private Nazione nazione;
    private ArrayList <Proposta> proposteInviate;

    public Utente(String username, String password, Nazione nazione) throws CampoNonValido {
        setUsername(username);
        setPassword(password);
        setNazione(nazione);
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


    public Nazione getNazione() throws  CampoNonValido{
        return nazione;
    }

    public void setNazione(Nazione nazione) throws CampoNonValido {
        if(nazione == null){

            throw new CampoNonValido ("La nazione nn può essere null");
        }
        this.nazione = nazione;

    }
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
