package controller;

import gui.CreaProposta;
import model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Utente> utentiRegistrati;
    private ArrayList<Album> albumPresenti;
    private ArrayList<Artista> artistiPresenti;
    private ArrayList<Genere> generiPresenti;

    public Controller() {
        //RICORDA DI FARE TUTTI I TRY E I CATCH PER L ECCEZIONI LANCIATE ALL INTERNO DI QUESTA CLASSE CONTROLLER DAI METODI
        this.utentiRegistrati = new ArrayList<>();
        this.albumPresenti = new ArrayList<>();
        this.artistiPresenti = new ArrayList<>();
        this.generiPresenti = new ArrayList<>();
        try {
            creaUtentiRegistratiDB();
            creaGeneriDB();
            creaArtistiDB();
        } catch (CampoNonValido e) {
            System.out.println("Errore nella creazione dei dati fittizzi");
        }

    }

    // Metodi Class Login
    public Utente cliccatoAccedi(String campoNomeUtente, String campoPassword) throws CampoNonValido{
        for(Utente utente : utentiRegistrati ){
            String passwordUtente = utente.getPassword();
            String nomeUtente = utente.getUsername();
            if(passwordUtente.equals(campoPassword) && nomeUtente.equals(campoNomeUtente)) {

                return utente;
            }

        }
        throw new CampoNonValido("ERRORE! Credenziali non valide");

    }

    // Metodi Class Registrazione

    public Utente cliccatoRegistrati(String campoNomeUtente, String campoPassword,Nazione nazione) throws CampoNonValido {
        Utente utenteAttuale = new Utente(campoNomeUtente,campoPassword,nazione);
        return utenteAttuale;
    }

    // Metodi Class CreaProposta
    public void CreaProposta(TipoProposta tipoElemento, String descrizione, String titoloElemento, Utente utenteAttuale) throws CampoNonValido{
        Proposta newProposta = new Proposta(tipoElemento, descrizione, titoloElemento, utenteAttuale);
        return;
    }

    // Metodi Class AggiungiElementoAdmin

    public ArrayList<Canzone> inserisciCanzoni(int numeroCanzoni, JFrame frameChiamante) throws CampoNonValido{
        ArrayList<Canzone> canzoniAlbum = new ArrayList<>();
        for(int i =0; i<numeroCanzoni;i++){
            String titoloTraccia = JOptionPane.showInputDialog(frameChiamante,"Inserisci il titolo della canzone  numero " + i);
            int durataSecondi =Integer.parseInt(JOptionPane.showInputDialog(frameChiamante,"Inserisci la durata in secondi della canzone"));
            Canzone canzoneCreata = new Canzone(titoloTraccia,durataSecondi);
            canzoniAlbum.add(canzoneCreata);
        }
        return canzoniAlbum;
    }

    public Artista trovaArtista(String nomeArt){
        for(Artista artista : artistiPresenti){
            if(artista.getNomeArte().equals(nomeArt)){
                return artista;
            }
        }
        return null;
    }

    public Genere trovaGenere(String nomeGenere){
        for(Genere genere : generiPresenti){
            if(genere.getNome().equals(nomeGenere)){
                return genere;
            }
        }
        return null;
    }

    public ArrayList<Genere> inserisciGeneri(int numeroGeneri, JFrame frameChiamante) throws CampoNonValido {
        ArrayList<Genere> generiAlbum = new ArrayList<>();

        for (int i = 0; i < numeroGeneri; i++) {
            String nomeGenere = JOptionPane.showInputDialog(frameChiamante, "Inserisci il nome del genere numero " + (i+1));
            // Usiamo il metodo che fa la ricerca
            Genere trovato = trovaGenere(nomeGenere);

            if (trovato != null) {
                generiAlbum.add(trovato);
            } else {
                JOptionPane.showMessageDialog(frameChiamante, "Attenzione: Genere non trovato nel Database! Riprova.");
                i--; // Diminuisce il contatore di 1, così ripete il giro per fargli inserire un genere valido
            }
        }
        return generiAlbum;
    }

    public void creaAlbum(String titolo, LocalDate dataPubblicazione, Artista artista, ArrayList <Genere> generi, ArrayList<Canzone> tracklist) throws CampoNonValido {
        new Album(titolo,dataPubblicazione,artista,generi,tracklist);
    }

    // Metodi Class Catalogo Artisti

    public ArrayList<Artista> getArtistiPresenti(){
        return artistiPresenti;
    }

    // con questi metodi vogliamo riempire il database fittizio iniziale che poi potrà essere
    // ulteriermente ingrandito dall Admin

    // --- METODO PER CARICARE GLI UTENTI DAL FILE TXT ---
    public void caricaUtentiDaFile() {
        String percorsoFile = "DB_fittizzi/utenti.txt";

        // Dichiariamo il BufferedReader FUORI dal try, così possiamo vederlo anche nel blocco finally
        java.io.BufferedReader br = null;

        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";");

                if (dati.length == 4) {
                    String username = dati[0];
                    String password = dati[1];
                    Nazione nazione = Nazione.valueOf(dati[2]);
                    String ruolo = dati[3];

                    if (ruolo.equals("Admin")) {
                        Utente nuovoAdmin = new Admin(username, password, nazione, "CodiceFittizio");
                        this.utentiRegistrati.add(nuovoAdmin);
                    } else {
                        Utente nuovoUtente = new Utente(username, password, nazione);
                        this.utentiRegistrati.add(nuovoUtente);
                    }
                }
            }
            System.out.println("Utenti caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file utenti: " + e.getMessage());
        } finally {
            // Il blocco finally viene eseguito SEMPRE, sia che vada tutto bene, sia che ci sia un errore.
            // È il posto perfetto per chiudere il file manualmente.
            if (br != null) {
                try {
                    br.close(); // Chiudiamo il file per liberare la memoria
                } catch (Exception e) {
                    System.out.println("Errore durante la chiusura del file: " + e.getMessage());
                }
            }
        }
    }

    public void creaGeneriDB() throws CampoNonValido{
    }
    public void creaArtistiDB() throws CampoNonValido {

    }

   public void creaUtentiRegistratiDB() throws CampoNonValido {

   }

    private void aggiungiAlbumFinto(String titolo, LocalDate data, String nomeArtista, String nomeGenere, ArrayList<Canzone> tracce) throws CampoNonValido {

    }

}
