package controller;

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
            caricaUtentiDaFile();

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
    public void caricaUtentiDaFile() throws CampoNonValido{

        String percorsoFile = "DB_fittizzi/utenti.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";");

                    String username = dati[0];
                    String password = dati[1];
                    Nazione nazione = Nazione.valueOf(dati[2]);
                    String ruolo = dati[3];

                    if (ruolo.equals("Admin")) {
                        String idAdmin = dati[4];
                        Utente nuovoAdmin = new Admin(username, password, nazione, idAdmin);
                        this.utentiRegistrati.add(nuovoAdmin);
                    } else {
                        Utente nuovoUtente = new Utente(username, password, nazione);
                        this.utentiRegistrati.add(nuovoUtente);
                    }

            }
            System.out.println("Utenti caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file utenti: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close(); // Chiudiamo il file per liberare la memoria
                } catch (Exception e) {
                    System.out.println("Errore durante la chiusura del file: " + e.getMessage());
                }
            }
        }
    }
    public void caricaGeneriDaFile() throws CampoNonValido{

        String percorsoFile = "DB_fittizzi/generi.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";");

                String nome = dati[0];
                String descrizione = dati[1];
                Genere nuovoGenere = new Genere(nome,descrizione);
                this.generiPresenti.add(nuovoGenere);

            }
            System.out.println("Utenti caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file utenti: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close(); // Chiudiamo il file per liberare la memoria
                } catch (Exception e) {
                    System.out.println("Errore durante la chiusura del file: " + e.getMessage());
                }
            }
        }
    }
    public void caricaMusicistiDaFile() throws CampoNonValido{

        String percorsoFile = "DB_fittizzi/Musicisti.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";");

                String nomeArte = dati[0];
                int annoInizioAttivita = Integer.parseInt(dati[1]);
                String idArtistia = dati[2];
                String nomeVero = dati[3];
                String cognomeVero = dati [4];
                LocalDate dataDiNascita = LocalDate.parse(dati[5]);
                Musicista NuovoMusicista = new Musicista(nomeArte,annoInizioAttivita,idArtistia,nomeVero,cognomeVero,dataDiNascita);
                this.artistiPresenti.add(NuovoMusicista);

            }
            System.out.println("Utenti caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file utenti: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close(); // Chiudiamo il file per liberare la memoria
                } catch (Exception e) {
                    System.out.println("Errore durante la chiusura del file: " + e.getMessage());
                }
            }
        }
    }

    // --- METODO PER CARICARE GLI BAND DAL FILE TXT ---
    public void caricaBandDaFile() throws CampoNonValido{

        String percorsoFile = "DB_fittizzi/band.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";");

                String nomeArte = dati[0];
                int annoInizioAttivita = Integer.parseInt(dati[1]);
                String idArtista = dati[2];
                int numeroMembri = Integer.parseInt(dati[3]);
                int annoScioglimento = Integer.parseInt(dati[4]);

                ArrayList<MembroBand> membriBand = new ArrayList<>();
                String[] listaMembri = dati[5].split(",");
                for(int i = 0; i < listaMembri.length; i++){
                    String[] datiMembro = listaMembri[i].split(":");

                    String idMusicista = datiMembro[0];
                    Musicista musicista = (Musicista) idToArtista(idMusicista);

                    Strumento strumento = Strumento.valueOf(datiMembro[1]);
                    int ingresso = Integer.parseInt(dati[2]);
                    Integer uscita = Integer.parseInt(dati[3]);

                    membriBand.add(new MembroBand(strumento, ingresso, uscita, musicista));
                }

                Band nuovaBand = new Band(nomeArte, annoInizioAttivita, idArtista, numeroMembri, annoScioglimento, membriBand);
                this.artistiPresenti.add(nuovaBand);

            }
            System.out.println("Utenti caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file utenti: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close(); // Chiudiamo il file per liberare la memoria
                } catch (Exception e) {
                    System.out.println("Errore durante la chiusura del file: " + e.getMessage());
                }
            }
        }
    }

    public Artista idToArtista(String idArtista){
        for (Artista artista : artistiPresenti){
            if (artista.getIdArtista().equals(idArtista)){
                return artista;
            }
        }
        return null;
    }

}
