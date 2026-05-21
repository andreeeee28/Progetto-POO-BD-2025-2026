package controller;

import model.*;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Utente> utentiLoggati;
    private ArrayList<Album> albumPresenti;
    private ArrayList<Artista> artistiPresenti;
    private ArrayList<Genere> generiPresenti;

    public Controller() {
        this.utentiLoggati = new ArrayList<>();
        this.albumPresenti = new ArrayList<>();
        this.artistiPresenti = new ArrayList<>();
        this.generiPresenti = new ArrayList<>();
    }
   public void cliccatoAccedi(String campoNomeUtente, String campoPassword) throws CampoNonValido{
        for(Utente utente : utentiLoggati ){
            String passwordUtente = utente.getPassword();
            String nomeUtente = utente.getUsername();
            if(passwordUtente.equals(campoPassword) && nomeUtente.equals(campoNomeUtente)) {

                return;
            }

        }
       throw new CampoNonValido("ERRORE! Credenziali non valide");

   }


}
