package controller;

import model.*;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Utente> utentiRegistrati;
    private ArrayList<Album> albumPresenti;
    private ArrayList<Artista> artistiPresenti;
    private ArrayList<Genere> generiPresenti;
    private ArrayList<MembroBand> membriBandPresenti;
    private ArrayList<Proposta> propostePresenti;

    public Controller() {
        //RICORDA DI FARE TUTTI I TRY E I CATCH PER L ECCEZIONI LANCIATE ALL INTERNO DI QUESTA CLASSE CONTROLLER DAI METODI
        this.utentiRegistrati = new ArrayList<>();
        this.albumPresenti = new ArrayList<>();
        this.artistiPresenti = new ArrayList<>();
        this.generiPresenti = new ArrayList<>();
        this.membriBandPresenti = new ArrayList<>();
        this.propostePresenti = new ArrayList<>();
        try {
            caricaUtentiDaFile();
            caricaGeneriDaFile();
            caricaMusicistiDaFile();
            caricaBandDaFile();
            caricaAlbumDaFile();
            caricaProposteDaFile();

        } catch (CampoNonValido e) {
            System.out.println("Errore nella creazione dei dati fittizzi");
        }

    }

    // Metodi Class Login
    public Utente cliccatoAccedi(String campoNomeUtente, String campoPassword) throws CampoNonValido {
        for (Utente utente : utentiRegistrati) {
            String passwordUtente = utente.getPassword();
            String nomeUtente = utente.getUsername();
            if (passwordUtente.equals(campoPassword) && nomeUtente.equals(campoNomeUtente)) {

                return utente;
            }

        }
        throw new CampoNonValido("ERRORE! Credenziali non valide");

    }

    // Metodi Class Registrazione

    public Utente cliccatoRegistrati(String campoNomeUtente, String campoPassword, Nazione nazione) throws CampoNonValido {
        Utente utenteAttuale = new Utente(campoNomeUtente, campoPassword, nazione);
        return utenteAttuale;
    }

    // Metodi Class CreaProposta
    public void CreaProposta(TipoProposta tipoElemento, String descrizione, String titoloElemento, Utente utenteAttuale) throws CampoNonValido {
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
            } catch (NumberFormatException ex) {
                throw new CampoNonValido("Operazione annullata (hai cliccato Canc o inserito lettere al posto dei numeri). L'album non è stato creato.");
            }
        }
        return canzoniAlbum;
    }

    public ArrayList<Genere> getGeneriPresenti() {
        return generiPresenti;
    }


    public Artista trovaArtista(String nomeArt) {
        for (Artista artista : artistiPresenti) {
            if (artista.getNomeArte().equals(nomeArt)) {
                return artista;
            }
        }
        return null;
    }

    public Genere trovaGenere(String nomeGenere) {
        for (Genere genere : generiPresenti) {
            if (genere.getNome().equals(nomeGenere)) {
                return genere;
            }
        }
        return null;
    }

    public ArrayList<Genere> inserisciGeneri(int numeroGeneri, JFrame frameChiamante) throws CampoNonValido {
        ArrayList<Genere> generiAlbum = new ArrayList<>();

        for (int i = 0; i < numeroGeneri; i++) {
            String nomeGenere = JOptionPane.showInputDialog(frameChiamante, "Inserisci il nome del genere numero " + (i + 1));
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

    public void creaAlbum(String titolo, LocalDate dataPubblicazione, Artista artista, ArrayList<Genere> generi, ArrayList<Canzone> tracklist) throws CampoNonValido {
        new Album(titolo, dataPubblicazione, artista, generi, tracklist);
    }

    // Metodi Class Catalogo Artisti

    public ArrayList<Artista> getArtistiPresenti() {
        return artistiPresenti;
    }

    // Metodo classe AggiungiArtistaAdmin

    public ArrayList<Band> getBandPresenti() {
        ArrayList<Band> bandPresenti = new ArrayList<>();
        for (Artista artista : artistiPresenti) {
            if (artista instanceof Band) {
                bandPresenti.add((Band) artista);
            }
        }
        return bandPresenti;
    }

    public ArrayList<Musicista> getMusicistiPresenti() {
        ArrayList<Musicista> musicistiPresenti = new ArrayList<>();
        for (Artista artista : artistiPresenti) {
            if (artista instanceof Musicista) {
                musicistiPresenti.add((Musicista) artista);
            }
        }
        return musicistiPresenti;
    }

    public void scriviMusicistaDataBase(Musicista musicistaAggiunto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/musicisti.txt", true))) {
            writer.write(musicistaAggiunto.getNomeArte() + ";" + musicistaAggiunto.getAnnoInizioAttivita() + ";" +
                    musicistaAggiunto.getIdArtista() + ";" + musicistaAggiunto.getNomeVero() + ";" + musicistaAggiunto.getCognonomeVero()
                    + ";" + musicistaAggiunto.getDataDiNascita().toString());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public void scriviMembroBandDataBase(MembroBand membroBandDaAggiungere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/membroBand.txt", true))) {
            writer.write(membroBandDaAggiungere.getMusicista().getIdArtista() + ";" + membroBandDaAggiungere.getStrumentoPrincipale()
                    + ";" + membroBandDaAggiungere.getAnnoIngresso() + ";" + membroBandDaAggiungere.getAnnoUscita() + ";" +
                    membroBandDaAggiungere.getBand().getIdArtista());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    // metodo di class AggiungiGeneriAdmin
    public void scriviGenereDataBase(Genere genereDaAggiungere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/generi.txt", true))) {

            // 1. Costruiamo la stringa dei Padri separati da virgola
            String stringaPadri = "";
            ArrayList<Genere> padri = genereDaAggiungere.getGeneriPadre();
            if (padri != null && !padri.isEmpty()) {
                for (int i = 0; i < padri.size(); i++) {
                    stringaPadri += padri.get(i).getNome();
                    if (i < padri.size() - 1) {
                        stringaPadri += ","; // Aggiunge la virgola tranne che all'ultimo elemento
                    }
                }
            }

            // 2. Costruiamo la stringa dei Figli separati da virgola
            String stringaFigli = "";
            ArrayList<Genere> figli = genereDaAggiungere.getSottogeneri();
            if (figli != null && !figli.isEmpty()) {
                for (int i = 0; i < figli.size(); i++) {
                    stringaFigli += figli.get(i).getNome();
                    if (i < figli.size() - 1) {
                        stringaFigli += ",";
                    }
                }
            }

            // 3. Scriviamo tutto nel file: Nome;Descrizione;Padri;Figli
            writer.write(genereDaAggiungere.getNome() + ";" +
                    genereDaAggiungere.getDescrizione() + ";" +
                    stringaPadri + ";" +
                    stringaFigli);
            writer.newLine(); // Consueto Invio a capo

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }


    // Metodi Class ValutaPropostaAdmin
    public Utente trovaUtentePerUsername (String username){
        for(Utente utente : utentiRegistrati){
            if(utente.getUsername().equals(username)){
                return utente;
            }
        }
        return null;
    }
    public ArrayList<Proposta> getPropostePresenti(){return propostePresenti;}
    public ArrayList<Object[]> creaRigheTabella(){
        ArrayList<Object[]> listaRighe = new ArrayList<>();
        for (Proposta proposta : propostePresenti){
            if(proposta.getStatoProposta()!= StatoProposta.VALUTAZIONE_IN_CORSO){
                String tipoElemento = proposta.getTipoElemento().name();
                String titoloElemento = proposta.getTitoloElemento();
                String utente = proposta.getAutoreProposta().getUsername();
                String statoProposta = proposta.getStatoProposta().name();
                listaRighe.add(new Object[]{tipoElemento,titoloElemento,statoProposta,utente});
            }
        }
        return listaRighe;
    }
    public ArrayList<Proposta> getProposteDaValutare (){
        ArrayList<Proposta> proposteDaValutare = new ArrayList<>();
        for(Proposta proposta : propostePresenti){
            if(proposta.getStatoProposta() == StatoProposta.VALUTAZIONE_IN_CORSO){
                proposteDaValutare.add(proposta);
            }
        }

        return  proposteDaValutare;
    }
    // Metodi Classe Verifica Proposta
    public void setPropostaAccettaDataBase(Proposta propostaDaAccettare) {
        String percorsoFile = "DB_fittizzi/proposte.txt";
        ArrayList<String> righeAggiornate = new ArrayList<>();

        // FASE 1: Lettura e Modifica in memoria
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";;");

                // Estraiamo i 3 dati che ci servono per il riconoscimento
                String titoloSalvato = dati[1];
                LocalDate dataSalvata = LocalDate.parse(dati[3]);
                String usernameSalvato = dati[5];

                // Verifichiamo se è esattamente la proposta che vogliamo accettare
                if (titoloSalvato.equals(propostaDaAccettare.getTitoloElemento()) &&
                        dataSalvata.equals(propostaDaAccettare.getDataRichiesta()) &&
                        usernameSalvato.equals(propostaDaAccettare.getAutoreProposta().getUsername())) {

                    // Abbiamo trovato la riga! Cambiamo lo stato (che si trova all'indice 4)
                    dati[4] = StatoProposta.ACCETTATA.name();

                    // Ricostruiamo la riga con lo stato aggiornato
                    String rigaModificata = dati[0] + ";;" + dati[1] + ";;" + dati[2] + ";;" + dati[3] + ";;" + dati[4] + ";;" + dati[5];
                    righeAggiornate.add(rigaModificata);

                    // (Opzionale) Aggiorniamo anche l'oggetto in memoria nel Controller
                    propostaDaAccettare.setStatoProposta(StatoProposta.ACCETTATA);

                } else {
                    // Se non è lei, aggiungiamo la riga originale intatta
                    righeAggiornate.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file proposte: " + e.getMessage());
            return; // Se c'è un errore di lettura, blocchiamo tutto per non fare danni
        }

        // FASE 2: Riscriviamo l'intero file da zero
        // NOTA BENE: Il 'false' nel FileWriter significa "Non aggiungere alla fine, SOVRASCRIVI TUTTO"
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(percorsoFile, false))) {
            for (String riga : righeAggiornate) {
                bw.write(riga);
                bw.newLine();
            }
            System.out.println("Database aggiornato: Proposta accettata con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante la sovrascrittura del file proposte: " + e.getMessage());
        }
    }
    public void setPropostaRifiutataDataBase(Proposta propostaDaAccettare) {
        String percorsoFile = "DB_fittizzi/proposte.txt";
        ArrayList<String> righeAggiornate = new ArrayList<>();

        // FASE 1: Lettura e Modifica in memoria
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";;");

                // Estraiamo i 3 dati che ci servono per il riconoscimento
                String titoloSalvato = dati[1];
                LocalDate dataSalvata = LocalDate.parse(dati[3]);
                String usernameSalvato = dati[5];

                // Verifichiamo se è esattamente la proposta che vogliamo accettare
                if (titoloSalvato.equals(propostaDaAccettare.getTitoloElemento()) &&
                        dataSalvata.equals(propostaDaAccettare.getDataRichiesta()) &&
                        usernameSalvato.equals(propostaDaAccettare.getAutoreProposta().getUsername())) {

                    // Abbiamo trovato la riga! Cambiamo lo stato (che si trova all'indice 4)
                    dati[4] = StatoProposta.RIFIUTATA.name();

                    // Ricostruiamo la riga con lo stato aggiornato
                    String rigaModificata = dati[0] + ";;" + dati[1] + ";;" + dati[2] + ";;" + dati[3] + ";;" + dati[4] + ";;" + dati[5];
                    righeAggiornate.add(rigaModificata);

                    // (Opzionale) Aggiorniamo anche l'oggetto in memoria nel Controller
                    propostaDaAccettare.setStatoProposta(StatoProposta.RIFIUTATA);

                } else {
                    // Se non è lei, aggiungiamo la riga originale intatta
                    righeAggiornate.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file proposte: " + e.getMessage());
            return; // Se c'è un errore di lettura, blocchiamo tutto per non fare danni
        }

        // FASE 2: Riscriviamo l'intero file da zero
        // NOTA BENE: Il 'false' nel FileWriter significa "Non aggiungere alla fine, SOVRASCRIVI TUTTO"
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(percorsoFile, false))) {
            for (String riga : righeAggiornate) {
                bw.write(riga);
                bw.newLine();
            }
            System.out.println("Database aggiornato: Proposta accettata con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante la sovrascrittura del file proposte: " + e.getMessage());
        }
    }
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

                if (linea.trim().isEmpty() || !linea.contains(";")) {
                    continue; // Se la riga è vuota, questo comando blocca tutto e passa subito al prossimo "giro"
                }
                String[] dati = linea.split(";");

                String nome = dati[0];
                String descrizione = dati[1];
                Genere nuovoGenere = new Genere(nome, descrizione);
                this.generiPresenti.add(nuovoGenere);

                if (dati.length >= 3 && !dati[2].isEmpty()) {
                    String[] generiPadriString = dati[2].split(",");
                    for(String generePadre : generiPadriString ) {
                        Genere padre = trovaGenere(generePadre.trim());
                        if (padre != null) {
                            nuovoGenere.addGeneriPadre(padre);
                            padre.addSottogeneri(nuovoGenere);
                        }
                    }
                }
                if (dati.length >= 4 && !dati[3].isEmpty()) {
                    String[] generiFigliString = dati[3].split(",");
                    for(String genereFiglio : generiFigliString ) {
                        Genere figlio = trovaGenere(genereFiglio.trim());
                        if (figlio != null) {
                            nuovoGenere.addSottogeneri(figlio);
                            figlio.addGeneriPadre(nuovoGenere); // Doppio collegamento inverso
                        }
                    }
                }
            }
            System.out.println("Generi caricati con successo dal file txt!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file genere " + e.getMessage());
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
            System.out.println("Errore durante la lettura del file Musicista: " + e.getMessage());
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
    // --- METODO PER CARICARE LE BAND DAL FILE TXT ---
    public void caricaBandDaFile() throws CampoNonValido {
        String percorsoBand = "DB_fittizzi/band.txt";
        String percorsoMembri = "DB_fittizzi/membroBand.txt";

        // 1. SALVIAMO TUTTE LE RIGHE DEI MEMBRI IN UN SEMPLICE ARRAYLIST
        ArrayList<String> righeMembri = new ArrayList<>();
        try (BufferedReader brMembri = new BufferedReader(new FileReader(percorsoMembri))) {
            String lineaMembro;
            while ((lineaMembro = brMembri.readLine()) != null) {

                // PRIMO SCUDO: Ignoriamo le righe vuote nel file dei membri
                if (lineaMembro.trim().isEmpty() || !lineaMembro.contains(";")) {
                    continue;
                }

                righeMembri.add(lineaMembro); // Salviamo la riga intera così com'è
            }
        } catch (Exception e) {
            System.out.println("Nessun file membri trovato, partirà vuoto.");
        }

        // 2. LEGGIAMO LE BAND E CREIAMOLE
        try (BufferedReader brBand = new BufferedReader(new FileReader(percorsoBand))) {
            String lineaBand;

            while ((lineaBand = brBand.readLine()) != null) {

                // SECONDO SCUDO: Ignoriamo le righe vuote nel file delle band
                if (lineaBand.trim().isEmpty() || !lineaBand.contains(";")) {
                    continue;
                }

                String[] dati = lineaBand.split(";");

                String nomeArte = dati[0];
                int annoInizioAttivita = Integer.parseInt(dati[1]);
                String idBand = dati[2]; // Ci serve per trovare i suoi membri!

                Integer annoScioglimento = null;
                if (!dati[3].equals("null")) {
                    annoScioglimento = Integer.parseInt(dati[3]);
                }

                // 3. PREPARIAMO LA LISTA DEI MEMBRI PER QUESTA SPECIFICA BAND
                ArrayList<MembroBand> membriDiQuestaBand = new ArrayList<>();

                // Scendiamo nell'ArrayList dei membri che avevamo salvato prima
                for (String rigaMembro : righeMembri) {
                    String[] datiMembro = rigaMembro.split(";");
                    String idBandAppartenenza = datiMembro[4]; // L'ID in fondo alla riga

                    // Se questo membro appartiene alla band che stiamo caricando in questo momento...
                    if (idBandAppartenenza.equals(idBand)) {
                        String idMusicista = datiMembro[0];
                        Strumento strumento = Strumento.valueOf(datiMembro[1]);
                        int ingresso = Integer.parseInt(datiMembro[2]);

                        Integer uscita = null;
                        if (!datiMembro[3].equals("null")) {
                            uscita = Integer.parseInt(datiMembro[3]);
                        }

                        // Recuperiamo il musicista (assumendo che idToArtista funzioni già bene)
                        Musicista musicista = (Musicista) idToArtista(idMusicista);

                        // Creiamo il membro e lo aggiungiamo alla lista di QUESTA band
                        MembroBand nuovoMembro = new MembroBand(strumento, ingresso, uscita, musicista);
                        membriBandPresenti.add(nuovoMembro);
                        membriDiQuestaBand.add(nuovoMembro);
                    }
                }

                // 4. CREIAMO LA BAND (La lista membriDiQuestaBand ora contiene i membri giusti!)
                // Il vincolo di almeno 2 elementi non darà errori, perché la lista è piena.
                int numeroMembri = membriDiQuestaBand.size();
                Band nuovaBand = new Band(nomeArte, annoInizioAttivita, idBand, numeroMembri, annoScioglimento, membriDiQuestaBand);

                // 5. AGGANCIAMO I COLLEGAMENTI
                for (MembroBand m : membriDiQuestaBand) {
                    m.setBand(nuovaBand);
                    m.getMusicista().addPartecipazioneBand(m);
                }

                // Aggiungiamo la band all'elenco ufficiale del programma
                this.artistiPresenti.add(nuovaBand);
            }

            System.out.println("Band e Membri caricati con successo (tramite ArrayList)!");

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file band: " + e.getMessage());
            // Se c'è un errore tipo "La band ha meno di 2 membri", lo rilanciamo alla GUI
            if (e instanceof CampoNonValido) {
                throw (CampoNonValido) e;
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
    public void caricaProposteDaFile() {
        String percorsoFile = "DB_fittizzi/proposte.txt";
        java.io.BufferedReader br = null;

        try {
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";;");

                TipoProposta tipo = TipoProposta.valueOf(dati[0]);
                String titolo = dati[1];
                String descrizioneRipristinata = dati[2].replace("[ACCAPO]", "\n");
                LocalDate dataSalvata = LocalDate.parse(dati[3]);
                StatoProposta statoSalvato = StatoProposta.valueOf(dati[4]);
                String usernameAutore = dati[5];

                // Recupero dell'utente
                Utente autore = trovaUtentePerUsername(usernameAutore);

                // Creiamo la proposta con il costruttore base
                Proposta p = new Proposta(tipo, descrizioneRipristinata, titolo, autore);

                // Sovrascriviamo i dati
                p.setDataRichiesta(dataSalvata);
                p.setStatoProposta(statoSalvato);
                this.propostePresenti.add(p);
            }
            System.out.println("Proposte caricate con successo dal file txt!");

        } catch (CampoNonValido e) {
            System.out.println("Errore di validazione durante il caricamento: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void scriviBandDataBase(Band nuovaBand) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/band.txt", true))) {
            writer.write(nuovaBand.getNomeArte() + ";" + nuovaBand.getAnnoInizioAttivita() + ";" + nuovaBand.getIdArtista() + ";" +nuovaBand.getAnnoScioglimento());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
