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
            caricaGeneriDaFile();
            caricaMusicistiDaFile();
            caricaBandDaFile();

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

    public ArrayList<Canzone> inserisciCanzoni(int numeroCanzoni, JFrame frameChiamante) throws CampoNonValido {
        ArrayList<Canzone> canzoniAlbum = new ArrayList<>();
        for (int i = 0; i < numeroCanzoni; i++) {
            String titoloTraccia = JOptionPane.showInputDialog(frameChiamante, "Inserisci il titolo della canzone numero " + (i + 1));
            if (titoloTraccia == null) {
                throw new CampoNonValido("ATTENZIONE cliccando su Canc si annulla l'intera operazione di creazione dell album");
            }
            try {
                int durataSecondi = Integer.parseInt(JOptionPane.showInputDialog(frameChiamante, "Inserisci la durata in secondi della canzone"));
                Canzone canzoneCreata = new Canzone(titoloTraccia, durataSecondi);
                canzoniAlbum.add(canzoneCreata);
            } catch (NumberFormatException ex){
                throw new CampoNonValido("Operazione annullata (hai cliccato Canc o inserito lettere al posto dei numeri). L'album non è stato creato.");
            }
        }
        return canzoniAlbum;
    }
    public ArrayList<Genere> getGeneriPresenti(){return generiPresenti;}












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
                Genere nuovoGenere = new Genere(nome, descrizione);
                this.generiPresenti.add(nuovoGenere);

                if (dati.length == 3 && !dati[2].isEmpty()) {
                    Genere padre = trovaGenere(dati[2]);
                    if (padre != null) {
                        nuovoGenere.addGeneriPadre(padre);
                        padre.addSottogeneri(nuovoGenere);
                    }
                }
            }
            System.out.println("Generi caricati con successo dal file txt!");

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

        String percorsoFile = "DB_fittizzi/musicisti.txt";
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
            System.out.println("Musicisti caricati con successo dal file txt!");

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

    // --- METODO PER CARICARE LE BAND DAL FILE TXT ---
    public void caricaBandDaFile() throws CampoNonValido {
        String percorsoFile = "DB_fittizzi/band.txt";
        java.io.BufferedReader br = null;

        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                // Dividiamo i dati principali della band col punto e virgola
                String[] dati = linea.split(";");

                String nomeArte = dati[0];
                int annoInizioAttivita = Integer.parseInt(dati[1]);
                String idArtista = dati[2];
                int numeroMembri = Integer.parseInt(dati[3]);

                // Gestione dell'anno di scioglimento (se la band è attiva, nel file c'è scritto "null")
                Integer annoScioglimento = null;
                if (!dati[4].equals("null")) {
                    annoScioglimento = Integer.parseInt(dati[4]);
                }

                ArrayList<MembroBand> membriBand = new ArrayList<>();
                // Prendiamo tutto il blocco dei membri (indice 5) e lo dividiamo per virgola
                String[] listaMembri = dati[5].split(",");

                for(int i = 0; i < listaMembri.length; i++){
                    // Ora per ogni membro dividiamo le sue specifiche per due punti
                    String[] datiMembro = listaMembri[i].split(":");

                    String idMusicista = datiMembro[0];
                    Musicista musicista = (Musicista) idToArtista(idMusicista);

                    Strumento strumento = Strumento.valueOf(datiMembro[1]);

                    // CORREZIONE: Usiamo datiMembro, non dati
                    int ingresso = Integer.parseInt(datiMembro[2]);

                    // Gestione dell'anno di uscita del membro
                    Integer uscita = null;
                    if (!datiMembro[3].equals("null")) {
                        uscita = Integer.parseInt(datiMembro[3]);
                    }

                    // Creiamo il membro e lo aggiungiamo alla lista temporanea
                    membriBand.add(new MembroBand(strumento, ingresso, uscita, musicista));
                }

                // Infine creiamo la Band vera e propria e la salviamo nel database fittizio
                Band nuovaBand = new Band(nomeArte, annoInizioAttivita, idArtista, numeroMembri, annoScioglimento, membriBand);
                this.artistiPresenti.add(nuovaBand);
            }
            // Messaggio aggiornato per non confonderci con gli utenti!
            System.out.println("Band caricate con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file band: " + e.getMessage());
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
    // --- METODO PER CARICARE GLI ALBUM DAL FILE TXT ---
    public void caricaAlbumDaFile() throws CampoNonValido {
        String percorsoFile = "DB_fittizzi/album.txt";
        java.io.BufferedReader br = null;

        try {
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                // 1. Tagliamo i dati principali dell'album
                String[] dati = linea.split(";");

                String titolo = dati[0];
                LocalDate dataPubblicazione = LocalDate.parse(dati[1]);
                String nomeArtista = dati[2];
                String stringaGeneri = dati[3]; // Es: "Pop,R&B,Funk"

                // 2. Cerchiamo il vero oggetto Artista
                Artista artista = trovaArtista(nomeArtista);

                // 3. Prepariamo la lista dei generi (potrebbero essere più di uno!)
                ArrayList<Genere> listaGeneri = new ArrayList<>();
                String[] nomiGeneri = stringaGeneri.split(",");

                for (int i = 0; i < nomiGeneri.length; i++) {
                    Genere genereTrovato = trovaGenere(nomiGeneri[i]);
                    if (genereTrovato != null) {
                        listaGeneri.add(genereTrovato);
                    }
                }

                // 4. Prepariamo la tracklist
                ArrayList<Canzone> tracklist = new ArrayList<>();
                String[] tracce = dati[4].split(",");

                for (int i = 0; i < tracce.length; i++) {
                    String[] datiCanzone = tracce[i].split(":");
                    String titoloCanzone = datiCanzone[0];
                    int durata = Integer.parseInt(datiCanzone[1]);
                    tracklist.add(new Canzone(titoloCanzone, durata));
                }

                // 5. Creiamo e colleghiamo l'album
                // Ci assicuriamo di aver trovato l'artista e ALMENO un genere valido
                if (artista != null && !listaGeneri.isEmpty()) {
                    Album nuovoAlbum = new Album(titolo, dataPubblicazione, artista, listaGeneri, tracklist);
                    this.albumPresenti.add(nuovoAlbum);

                    // L'artista ha un nuovo album!
                    artista.addAlbum(nuovoAlbum);

                    // Dobbiamo dire a TUTTI i generi coinvolti che hanno un nuovo album
                    for (Genere g : listaGeneri) {
                        g.addListaAlbum(nuovoAlbum);
                    }
                } else {
                    System.out.println("Attenzione: Artista o Genere non trovato per l'album " + titolo);
                }
            }
            System.out.println("Album caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file album: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    System.out.println("Errore durante la chiusura del file: " + e.getMessage());
                }
            }
        }
    }
}
