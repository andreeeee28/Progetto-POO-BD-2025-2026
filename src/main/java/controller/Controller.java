package controller;

import model.*;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The type Controller.
 */
public class Controller {
    private ArrayList<Utente> utentiRegistrati;
    private ArrayList<Album> albumPresenti;
    private ArrayList<Artista> artistiPresenti;
    private ArrayList<Genere> generiPresenti;
    private ArrayList<MembroBand> membriBandPresenti;
    private ArrayList<Proposta> propostePresenti;
    private ArrayList<Recensione> recensioniPresenti;

    /**
     * Instantiates a new Controller.
     */
    public Controller() {
        this.utentiRegistrati = new ArrayList<>();
        this.albumPresenti = new ArrayList<>();
        this.artistiPresenti = new ArrayList<>();
        this.generiPresenti = new ArrayList<>();
        this.membriBandPresenti = new ArrayList<>();
        this.propostePresenti = new ArrayList<>();
        this.recensioniPresenti = new ArrayList<>();
        try {
            caricaUtentiDaFile();
            caricaGeneriDaFile();
            caricaMusicistiDaFile();
            caricaBandDaFile();
            caricaAlbumDaFile();
            caricaProposteDaFile();
            caricaRecensioniDaFile();
            setRecensioniAlbum();

        } catch (CampoNonValido e) {
            System.out.println("Errore nella creazione dei dati fittizzi");
        }

    }

    // GETTER

    /**
     * Gets artisti presenti.
     *
     * @return the artisti presenti
     */
    public ArrayList<Artista> getArtistiPresenti() {
        return artistiPresenti;
    }

    /**
     * Filtra gli artisti presenti nel database fittizio e restituisce l'ArrayList con solo gli oggetti instanza della classe Band
     *
     * @return L'ArrayList con gli oggetti istanza della classe Band trovato
     */
    public ArrayList<Band> getBandPresenti() {
        ArrayList<Band> bandPresenti = new ArrayList<>();
        for (Artista artista : artistiPresenti) {
            if (artista instanceof Band) {
                bandPresenti.add((Band) artista);
            }
        }
        return bandPresenti;
    }

    /**
     * Filtra gli artisti presenti nel database fittizio e restituisce l'ArrayList con solo gli oggetti instanza della classe Musicista
     *
     * @return L'ArrayList con gli oggetti istanza della classe Musicista trovato
     */
    public ArrayList<Musicista> getMusicistiPresenti() {
        ArrayList<Musicista> musicistiPresenti = new ArrayList<>();
        for (Artista artista : artistiPresenti) {
            if (artista instanceof Musicista) {
                musicistiPresenti.add((Musicista) artista);
            }
        }
        return musicistiPresenti;
    }

    /**
     * Gets generi presenti.
     *
     * @return the generi presenti
     */
    public ArrayList<Genere> getGeneriPresenti() {
        return generiPresenti;
    }

    /**
     * Filtra le proposte presenti nel database fittizio e restituisce l' Arraylist con solo gli oggetti istanza della classe Proposta
     * il cui attributo statoProposta è "VALUTAZIONE_IN_CORSO"
     *
     * @return L'ArrayList con gli oggetti istanza della classe Proposta trovato
     */
    public ArrayList<Proposta> getProposteDaValutare() {
        ArrayList<Proposta> proposteDaValutare = new ArrayList<>();
        for (Proposta proposta : propostePresenti) {
            if (proposta.getStatoProposta() == StatoProposta.VALUTAZIONE_IN_CORSO) {
                proposteDaValutare.add(proposta);
            }
        }

        return proposteDaValutare;
    }

    /**
     * Get proposte presenti array list.
     *
     * @return the array list
     */
    public ArrayList<Proposta> getPropostePresenti() {
        return propostePresenti;
    }

    // SETTER

    /**
     * Modifica l'attirbuto statoProposta dell'oggetto Proposta selezionato dall'Admin da "VALUTAZIONE_IN_CORSO" ad "ACCETTATA" nel .
     * database fittizio. Per farlo a causa dell'utilizzo di un file text viene cancellato l'intero contenuto del file
     * e poi riscritto con la modifica
     *
     * @param propostaDaAccettare L'oggetto istanza della classe Proposta selezionato dall'admin per essere modificato
     */
    public void setPropostaAccettaDataBase(Proposta propostaDaAccettare) {
        String percorsoFile = "DB_fittizzi/proposte.txt";
        ArrayList<String> righeAggiornate = new ArrayList<>();

        // FASE 1: Lettura e Modifica in memoria
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";;;;");

                // Estraiamo i dati che ci servono per il riconoscimento
                String titoloSalvato = dati[1];
                LocalDate dataSalvata = LocalDate.parse(dati[3]);
                String usernameSalvato = dati[5];

                // Verifichiamo se è esattamente la proposta che vogliamo accettare
                if (titoloSalvato.equals(propostaDaAccettare.getTitoloElemento()) &&
                        dataSalvata.equals(propostaDaAccettare.getDataRichiesta()) &&
                        usernameSalvato.equals(propostaDaAccettare.getAutoreProposta().getUsername())) {

                    //  Cambiamo lo stato
                    dati[4] = StatoProposta.ACCETTATA.name();

                    // Ricostruiamo la riga con lo stato aggiornato
                    String rigaModificata = dati[0] + ";;;;" + dati[1] + ";;;;" + dati[2] + ";;;;" + dati[3] + ";;;;" + dati[4] + ";;;;" + dati[5];
                    righeAggiornate.add(rigaModificata);

                    // Aggiorniamo anche l'oggetto in memoria nel Controller
                    propostaDaAccettare.setStatoProposta(StatoProposta.ACCETTATA);

                } else {

                    righeAggiornate.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file proposte: " + e.getMessage());
            return;
        }

        // Ora obbligatoriamente riscriviamo l'intero file da zero
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

    /**
     * Modifica l'attributo statoProposta dell'oggetto Proposta selezionato dall'Admin da "VALUTAZIONE_IN_CORSO" ad "RIFIUTATA" nel
     * database fittizio. Per farlo a causa dell'utilizzo di un file text viene cancellato l'intero contenuto del file
     * e poi riscritto con la modifica
     *
     * @param propostaDaAccettare L'oggetto istanza della classe Proposta selezionato dall'admin per essere modificata
     */
    public void setPropostaRifiutataDataBase(Proposta propostaDaAccettare) {
        String percorsoFile = "DB_fittizzi/proposte.txt";
        ArrayList<String> righeAggiornate = new ArrayList<>();

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] dati = linea.split(";;;;");

                String titoloSalvato = dati[1];
                LocalDate dataSalvata = LocalDate.parse(dati[3]);
                String usernameSalvato = dati[5];

                if (titoloSalvato.equals(propostaDaAccettare.getTitoloElemento()) &&
                        dataSalvata.equals(propostaDaAccettare.getDataRichiesta()) &&
                        usernameSalvato.equals(propostaDaAccettare.getAutoreProposta().getUsername())) {

                    dati[4] = StatoProposta.RIFIUTATA.name();

                    String rigaModificata = dati[0] + ";;;;" + dati[1] + ";;;;" + dati[2] + ";;;;" + dati[3] + ";;;;" + dati[4] + ";;;;" + dati[5];
                    righeAggiornate.add(rigaModificata);

                    propostaDaAccettare.setStatoProposta(StatoProposta.RIFIUTATA);

                } else {
                    righeAggiornate.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file proposte: " + e.getMessage());
            return;
        }

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

    /**
     * Assegna ad ogni album presente nel database fittizio la rispettiva lista di recensioni.
     * Il metodo scorre tutte le recensioni in memoria e le raggruppa per album.
     */
    public void setRecensioniAlbum() {
        for (Album album : albumPresenti) {
            ArrayList<Recensione> recensioniAlbumSingolo = new ArrayList<>();
            for (Recensione recensione : recensioniPresenti) {
                if (recensione.getAlbum().equals(album)) {
                    recensioniAlbumSingolo.add(recensione);
                }
            }

            try {
                album.setRecensioni(recensioniAlbumSingolo);
            } catch (CampoNonValido e) {
                System.out.println("Errore imprevisto nell'assegnazione recensioni: " + e.getMessage());
            }
        }
    }

    //  TROVA ELEMENTI

    /**
     * Filtra gli album presenti nel database fittizio e restituisce solo l'oggetto istanza della classe Album che ha
     * come attributo titolo proprio la stringa passata come parametro
     *
     * @param titoloAlbum Il titolo esatto dell'oggetto istanza della classe Album da cercare.
     * @return L'oggetto Album trovato, oppure null se non esiste un oggetto istanza della classe Album con quel titolo
     */
    public Album trovaAlbumDaTitolo(String titoloAlbum) {
        for (Album album : albumPresenti) {
            if (album.getTitolo().equals(titoloAlbum)) {
                return album;
            }
        }
        return null;
    }

    /**
     * Filtra gli artisti presenti nel database fittizio e restituisce solo l'oggetto istanza della classe Artista
     * che ha come attributo nomeArte proprio la stringa passata come parametro
     *
     * @param nomeArt il nome d'arte esatto dell'oggetto istanza della classe Artista da cercare
     * @return L'oggetto Artista trovato, oppure null se non esiste un oggetto istanza della classe Artista con quel nome d'arte
     */
    public Artista trovaArtistaDaNomeArte(String nomeArt) {
        for (Artista artista : artistiPresenti) {
            if (artista.getNomeArte().equals(nomeArt)) {
                return artista;
            }
        }
        return null;
    }

    /**
     * Filtra i generi presenti nel database fittizio e restituisce solo l'oggetto istanza della classe Genere che
     * ha come attributo nomeGenere proprio la stringa passata come parametro
     *
     * @param nomeGenere il nome esatto dell'oggetto istanza della classe Genere da cercare
     * @return L'oggetto Genere trovato, oppure null se non esiste un oggetto istanza della classe Artista con quel nome
     */
    public Genere trovaGenereDaNomeGenere(String nomeGenere) {
        for (Genere genere : generiPresenti) {
            if (genere.getNome().equals(nomeGenere)) {
                return genere;
            }
        }
        return null;
    }

    /**
     * Filtra gli utenti presenti nel database fittizio e restituisce solo l'oggetto istanza della classe Utente che
     * ha come attributo username proprio la stringa passata come parametro
     *
     * @param username l'username esatto dell'oggetto istanza della classe Utente da cercare
     * @return l'oggetto Utente trovato, oppure null se non esiste un oggetto istanza della classe Utente con quell'username
     */
    public Utente trovaUtenteDaUsername(String username) {
        for (Utente utente : utentiRegistrati) {
            if (utente.getUsername().equals(username)) {
                return utente;
            }
        }
        return null;
    }

    /**
     * Filtra gli artisti presenti nel database fittizio e restituisce solo l'oggetto istanza della classe Artista che
     * ha come attributo idArtista proprio la stringa passata come parametro
     *
     * @param idArtista L'idArtista esatto dell'oggetto istanza della classe Artista da cercare
     * @return l'oggetto Artista trovato, oppure null se non esiste un oggetto istanza della classe Artista con quell'idArtista
     */
    public Artista trovaArtistaDaId(String idArtista) {
        for (Artista artista : artistiPresenti) {
            if (artista.getIdArtista().equals(idArtista)) {
                return artista;
            }
        }
        return null;
    }

    // VERIFICA ELEMENTI

    /**
     * Verifica che una nuova proposta non contenga caratteri illegali e che non sia un duplicato.
     *
     * @param proposta L'oggetto Proposta da validare.
     * @throws CampoNonValido Se il titolo contiene la sequenza vietata ';;;;', oppure se
     *                        lo stesso utente ha già inviato una proposta per lo stesso elemento (stesso titolo e tipo).
     */
    public void verificaProposta(Proposta proposta) throws CampoNonValido {
        if (proposta.getTitoloElemento().contains(";;;;")) {
            throw new CampoNonValido("Errore: Il titolo non può contenere ';;;;'");
        }

        for (Proposta p : propostePresenti) {
            if (p.getTitoloElemento().equalsIgnoreCase(proposta.getTitoloElemento()) &&
                    p.getTipoElemento() == proposta.getTipoElemento() &&
                    p.getAutoreProposta().getUsername().equals(proposta.getAutoreProposta().getUsername())) {
                throw new CampoNonValido("Errore: Hai già inviato una proposta identica per questo elemento!");
            }
        }
    }

    /**
     * Controlla che l'utente non abbia già lasciato una recensione per l'album specificato.
     * Se il controllo va a buon fine, il metodo salva automaticamente la recensione in memoria.
     *
     * @param recensioneDaVerificare L'oggetto Recensione contenente i dati dell'album, dell'utente e il voto.
     * @throws CampoNonValido Se viene trovata nello storico una recensione per lo stesso album lasciata dallo stesso utente.
     */
    public void verificaRecensione(Recensione recensioneDaVerificare) throws CampoNonValido {
        Album albumDaRecensire = recensioneDaVerificare.getAlbum();
        Utente utenteRecensore = recensioneDaVerificare.getUtente();

        for (Recensione r : recensioniPresenti) {
            if (r.getAlbum().getTitolo().equals(albumDaRecensire.getTitolo()) &&
                    r.getUtente().getUsername().equals(utenteRecensore.getUsername())) {

                throw new CampoNonValido("Errore: Hai già recensito questo album! Non puoi lasciare più di un voto.");
            }
        }

        this.recensioniPresenti.add(recensioneDaVerificare);
        albumDaRecensire.getRecensioni().add(recensioneDaVerificare);
    }

    /**
     * Controlla la validità dei dati inseriti per la creazione di una nuova band, evitando
     * caratteri illegali e conflitti con band già esistenti.
     *
     * @param nomeBand      Il nome d'arte della band da verificare.
     * @param musicistiBand La lista dei membri che comporranno la band.
     * @param id            L'identificativo univoco assegnato alla band.
     * @throws CampoNonValido Se i campi contengono ';;;;', se l'ID è già utilizzato a sistema,
     *                        o se esiste già una band con lo stesso nome e gli stessi identici membri.
     */
    public void verificaBand(String nomeBand, ArrayList<Musicista> musicistiBand, String id) throws CampoNonValido {

        if (nomeBand.contains(";;;;") || id.contains(";;;;")) {
            throw new CampoNonValido("Errore: I campi non possono contenere i caratteri ';;;;'");
        }

        for (Band band : getBandPresenti()) {

            if (band.getIdArtista().equals(id)) {
                throw new CampoNonValido("Errore: Esiste già un artista/band con questo ID (" + id + ")!");
            }

            if (band.getNomeArte().equals(nomeBand)) {
                ArrayList<Musicista> musicistiGiaPresenti = new ArrayList<>();
                for (MembroBand membro : band.getMembriBand()) {
                    musicistiGiaPresenti.add(membro.getMusicista());
                }

                if (musicistiBand.size() == musicistiGiaPresenti.size() && musicistiBand.containsAll(musicistiGiaPresenti)) {
                    throw new CampoNonValido("Errore: Questa band esiste già nel database con lo stesso nome e gli stessi componenti!");
                }
            }
        }
    }

    /**
     * Verifica che i dati di un nuovo album siano validi e non costituiscano un duplicato per l'artista specificato.
     *
     * @param titoloAlbum Il titolo dell'album da controllare.
     * @param artista     L'artista autore dell'album.
     * @throws CampoNonValido Se il titolo contiene la sequenza ';;;;' o se l'artista indicato
     *                        ha già pubblicato un album con questo esatto titolo.
     */
    public void verificaAlbum(String titoloAlbum, Artista artista) throws CampoNonValido {
        if (titoloAlbum.contains(";;;;")) {
            throw new CampoNonValido("Errore: Il titolo dell'album non può contenere ';;;;'");
        }

        for (Album album : albumPresenti) {
            if (album.getTitolo().equalsIgnoreCase(titoloAlbum) && album.getArtista().equals(artista)) {
                throw new CampoNonValido("Errore: Questo album esiste già per questo artista!");
            }
        }
    }

    /**
     * Controlla che i campi di un nuovo genere siano validi e che non esista già un genere omologo a sistema.
     *
     * @param genereDaVerificare L'oggetto Genere da validare.
     * @throws CampoNonValido Se il nome o la descrizione contengono ';;;;', oppure se un genere
     *                        con lo stesso nome (ignorando maiuscole/minuscole) è già presente.
     */
    public void verificaGeneri(Genere genereDaVerificare) throws CampoNonValido {

        if (genereDaVerificare.getNome().contains(";;;;") || genereDaVerificare.getDescrizione().contains(";;;;")) {
            throw new CampoNonValido("Errore: Il nome o la descrizione non possono contenere i caratteri ';;;;'");
        }

        for (Genere genereSalvato : getGeneriPresenti()) {
            if (genereSalvato.getNome().equalsIgnoreCase(genereDaVerificare.getNome())) {
                throw new CampoNonValido("Errore: Esiste già un genere con il nome '" + genereDaVerificare.getNome() + "' nel database!");
            }
        }
    }

    /**
     * Verifica i dati anagrafici e artistici di un nuovo musicista per evitare caratteri illegali
     * e duplicati nel sistema.
     *
     * @param musicista L'oggetto Musicista da controllare.
     * @throws CampoNonValido Se i campi di testo contengono ';;;;', se l'ID specificato è già in uso
     *                        da un altro artista, o se esiste già un artista con lo stesso nome d'arte.
     */
    public void verificaMusicista(Musicista musicista) throws CampoNonValido {
        if (musicista.getNomeArte().contains(";;;;") || musicista.getNomeVero().contains(";;;;") ||
                musicista.getCognonomeVero().contains(";;;;") || musicista.getIdArtista().contains(";;;;")) {
            throw new CampoNonValido("Errore: I campi non possono contenere ';;;;'");
        }

        for (Artista artista : artistiPresenti) {
            if (artista.getIdArtista().equals(musicista.getIdArtista())) {
                throw new CampoNonValido("Errore: Esiste già un artista con questo ID!");
            }
            if (artista.getNomeArte().equalsIgnoreCase(musicista.getNomeArte())) {
                throw new CampoNonValido("Errore: Esiste già un artista chiamato " + musicista.getNomeArte());
            }
        }
    }

    // CARICA ELEMENTI DA DB

    /**
     * Legge il file di testo contenente gli utenti registrati e popola la lista in memoria.
     * Crea oggetti di tipo Utente o Admin in base al ruolo letto nel file.
     *
     * @throws CampoNonValido Se la sintassi del file non è valida o i dati sono corrotti
     *                        (gestito internamente con un blocco catch generale).
     */
    public void caricaUtentiDaFile() throws CampoNonValido {

        String percorsoFile = "DB_fittizzi/utenti.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty() || !linea.contains(";;;;")) {
                    continue;
                }

                String[] dati = linea.split(";;;;");

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
            utentiRegistrati.sort(Comparator.comparing(Utente::getUsername));

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

    /**
     * Carica i generi musicali dal file di testo e ricostruisce la gerarchia in memoria.
     * Ricrea anche i collegamenti bidirezionali tra generi padri e sottogeneri.
     *
     * @throws CampoNonValido Se la struttura delle stringhe lette non rispetta il formato standard
     *                        (gestito internamente con blocco catch).
     */
    public void caricaGeneriDaFile() throws CampoNonValido {

        String percorsoFile = "DB_fittizzi/generi.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty() || !linea.contains(";;;;")) {
                    continue; // Se la riga è vuota, questo comando blocca tutto e passa subito al prossimo "giro"
                }
                String[] dati = linea.split(";;;;");

                String nome = dati[0];
                String descrizione = dati[1];
                Genere nuovoGenere = new Genere(nome, descrizione);
                this.generiPresenti.add(nuovoGenere);

                if (dati.length >= 3 && !dati[2].isEmpty()) {
                    String[] generiPadriString = dati[2].split(",,,,");
                    for (String generePadre : generiPadriString) {
                        Genere padre = trovaGenereDaNomeGenere(generePadre.trim());
                        if (padre != null) {
                            nuovoGenere.addGeneriPadre(padre);
                            padre.addSottogeneri(nuovoGenere);
                        }
                    }
                }
                if (dati.length >= 4 && !dati[3].isEmpty()) {
                    String[] generiFigliString = dati[3].split(",,,,");
                    for (String genereFiglio : generiFigliString) {
                        Genere figlio = trovaGenereDaNomeGenere(genereFiglio.trim());
                        if (figlio != null) {
                            nuovoGenere.addSottogeneri(figlio);
                            figlio.addGeneriPadre(nuovoGenere); // Doppio collegamento inverso
                        }
                    }
                }
            }
            System.out.println("Generi caricati con successo dal file txt!");
            generiPresenti.sort(Comparator.comparing(Genere::getNome));

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

    /**
     * Legge il database testuale dei musicisti e aggiunge i rispettivi oggetti alla lista in memoria
     * di tutti gli artisti.
     *
     * @throws CampoNonValido Se c'è un errore nella conversione dei tipi di dato
     *                        (es. testo invece di numeri) durante la lettura delle righe.
     */
    public void caricaMusicistiDaFile() throws CampoNonValido {

        String percorsoFile = "DB_fittizzi/musicisti.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty() || !linea.contains(";;;;")) {
                    continue;
                }

                String[] dati = linea.split(";;;;");

                String nomeArte = dati[0];
                int annoInizioAttivita = Integer.parseInt(dati[1]);
                String idArtistia = dati[2];
                String nomeVero = dati[3];
                String cognomeVero = dati[4];
                LocalDate dataDiNascita = LocalDate.parse(dati[5]);
                Musicista NuovoMusicista = new Musicista(nomeArte, annoInizioAttivita, idArtistia, nomeVero, cognomeVero, dataDiNascita);
                this.artistiPresenti.add(NuovoMusicista);

            }
            System.out.println("Musicisti caricati con successo dal file txt!");
            artistiPresenti.sort(Comparator.comparing(Artista::getNomeArte));

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

    /**
     * Legge il file delle band e dei membri associati, ricostruendo gli oggetti Band
     * e agganciandoli correttamente ai rispettivi musicisti tramite la classe MembroBand.
     *
     * @throws CampoNonValido Se una band salvata risulta avere meno membri del consentito
     *                        o se il collegamento con i musicisti fallisce.
     */
    public void caricaBandDaFile() throws CampoNonValido {
        String percorsoBand = "DB_fittizzi/band.txt";
        String percorsoMembri = "DB_fittizzi/membroBand.txt";

        ArrayList<String> righeMembri = new ArrayList<>();
        try (BufferedReader brMembri = new BufferedReader(new FileReader(percorsoMembri))) {
            String lineaMembro;
            while ((lineaMembro = brMembri.readLine()) != null) {

                if (lineaMembro.trim().isEmpty() || !lineaMembro.contains(";;;;")) {
                    continue;
                }

                righeMembri.add(lineaMembro);
            }
        } catch (Exception e) {
            System.out.println("Nessun file membri trovato, partirà vuoto.");
        }

        try (BufferedReader brBand = new BufferedReader(new FileReader(percorsoBand))) {
            String lineaBand;

            while ((lineaBand = brBand.readLine()) != null) {

                if (lineaBand.trim().isEmpty() || !lineaBand.contains(";;;;")) {
                    continue;
                }

                String[] dati = lineaBand.split(";;;;");

                String nomeArte = dati[0];
                int annoInizioAttivita = Integer.parseInt(dati[1]);
                String idBand = dati[2]; // Ci serve per trovare i suoi membri!

                Integer annoScioglimento = null;
                if (!dati[3].equals("null")) {
                    annoScioglimento = Integer.parseInt(dati[3]);
                }

                ArrayList<MembroBand> membriDiQuestaBand = new ArrayList<>();

                // Scendiamo nell'ArrayList dei membri che avevamo salvato prima
                for (String rigaMembro : righeMembri) {
                    String[] datiMembro = rigaMembro.split(";;;;");
                    String idBandAppartenenza = datiMembro[4];

                    // Se questo membro appartiene alla band che stiamo caricando in questo momento...
                    if (idBandAppartenenza.equals(idBand)) {
                        String idMusicista = datiMembro[0];
                        Strumento strumento = Strumento.valueOf(datiMembro[1]);
                        int ingresso = Integer.parseInt(datiMembro[2]);

                        Integer uscita = null;
                        if (!datiMembro[3].equals("null")) {
                            uscita = Integer.parseInt(datiMembro[3]);
                        }

                        Musicista musicista = (Musicista) trovaArtistaDaId(idMusicista);

                        // Creiamo il membro e lo aggiungiamo alla lista di questa band
                        MembroBand nuovoMembro = new MembroBand(strumento, ingresso, uscita, musicista);
                        membriBandPresenti.add(nuovoMembro);
                        membriDiQuestaBand.add(nuovoMembro);
                    }
                }

                int numeroMembri = membriDiQuestaBand.size();
                Band nuovaBand = new Band(nomeArte, annoInizioAttivita, idBand, numeroMembri, annoScioglimento, membriDiQuestaBand);

                // Agganciamo i collegamenti
                for (MembroBand m : membriDiQuestaBand) {
                    m.setBand(nuovaBand);
                    m.getMusicista().addPartecipazioneBand(m);
                }

                // Aggiungiamo la band all'elenco ufficiale del programma
                this.artistiPresenti.add(nuovaBand);
            }

            System.out.println("Band e Membri caricati con successo (tramite ArrayList)!");
            artistiPresenti.sort(Comparator.comparing(Artista::getNomeArte));

        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file band: " + e.getMessage());
            // Se c'è un errore tipo "La band ha meno di 2 membri", lo rilanciamo alla GUI
            if (e instanceof CampoNonValido) {
                throw (CampoNonValido) e;
            }
        }
    }

    /**
     * Legge il file degli album, decodifica le tracklist e ripristina i collegamenti con l'artista
     * autore e i generi musicali a cui appartiene.
     *
     * @throws CampoNonValido Se un artista o un genere a cui fa riferimento un album
     *                        non viene trovato tra quelli attualmente caricati in memoria.
     */
    public void caricaAlbumDaFile() throws CampoNonValido {
        String percorsoFile = "DB_fittizzi/album.txt";
        java.io.BufferedReader br = null;

        try {
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || !linea.contains(";;;;")) {
                    continue;
                }
                // 1. Tagliamo i dati principali dell'album
                String[] dati = linea.split(";;;;");

                String titolo = dati[0];
                LocalDate dataPubblicazione = LocalDate.parse(dati[1]);
                String nomeArtista = dati[2];
                String stringaGeneri = dati[3]; // Es: "Pop,R&B,Funk"

                // 2. Cerchiamo il vero oggetto Artista
                Artista artista = trovaArtistaDaNomeArte(nomeArtista);

                // 3. Prepariamo la lista dei generi
                ArrayList<Genere> listaGeneri = new ArrayList<>();
                String[] nomiGeneri = stringaGeneri.split(",,,,");

                for (int i = 0; i < nomiGeneri.length; i++) {
                    Genere genereTrovato = trovaGenereDaNomeGenere(nomiGeneri[i]);
                    if (genereTrovato != null) {
                        listaGeneri.add(genereTrovato);
                    }
                }

                // 4. Prepariamo la tracklist
                ArrayList<Canzone> tracklist = new ArrayList<>();
                String[] tracce = dati[4].split(",,,,");

                for (int i = 0; i < tracce.length; i++) {
                    String[] datiCanzone = tracce[i].split("::::");
                    String titoloCanzone = datiCanzone[0];
                    int durata = Integer.parseInt(datiCanzone[1]);
                    tracklist.add(new Canzone(titoloCanzone, durata));
                }

                // 5. Creiamo e colleghiamo l'album
                // Ci assicuriamo di aver trovato l'artista e Almeno un genere valido
                if (artista != null && !listaGeneri.isEmpty()) {
                    Album nuovoAlbum = new Album(titolo, dataPubblicazione, artista, listaGeneri, tracklist);
                    this.albumPresenti.add(nuovoAlbum);

                    // aggiungiamo il nuovo album all'artista
                    artista.addAlbum(nuovoAlbum);

                    // aggiungiamo il nuovo album a tutti i generi
                    for (Genere g : listaGeneri) {
                        g.addListaAlbum(nuovoAlbum);
                    }
                } else {
                    System.out.println("Attenzione: Artista o Genere non trovato per l'album " + titolo);
                }
            }
            System.out.println("Album caricati con successo dal file txt!");
            albumPresenti.sort(Comparator.comparing(Album::getTitolo));

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

    /**
     * Carica lo storico di tutte le proposte dal database testuale e ripristina il collegamento
     * con l'utente autore della proposta. Reinterpreta inoltre i ritorni a capo salvati.
     */
    public void caricaProposteDaFile() {
        String percorsoFile = "DB_fittizzi/proposte.txt";
        java.io.BufferedReader br = null;

        try {
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty() || !linea.contains(";;;;")) {
                    continue;
                }
                String[] dati = linea.split(";;;;");

                TipoProposta tipo = TipoProposta.valueOf(dati[0]);
                String titolo = dati[1];
                String descrizioneRipristinata = dati[2].replace("[ACCAPO]", "\n");
                LocalDate dataSalvata = LocalDate.parse(dati[3]);
                StatoProposta statoSalvato = StatoProposta.valueOf(dati[4]);
                String usernameAutore = dati[5];

                // Recupero dell'utente
                Utente autore = trovaUtenteDaUsername(usernameAutore);

                // Creiamo la proposta con il costruttore base
                Proposta p = new Proposta(tipo, descrizioneRipristinata, titolo, autore);

                // Sovrascriviamo i dati
                p.setDataRichiesta(dataSalvata);
                p.setStatoProposta(statoSalvato);
                this.propostePresenti.add(p);
            }
            System.out.println("Proposte caricate con successo dal file txt!");
            propostePresenti.sort(Comparator.comparing(Proposta::getDataRichiesta));

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

    /**
     * Legge lo storico delle recensioni e ricrea in memoria gli oggetti,
     * collegandoli agli utenti recensori e agli album corrispondenti.
     *
     * @throws CampoNonValido Se si verifica un errore durante il parsing dei dati letti.
     */
    public void caricaRecensioniDaFile() throws CampoNonValido {

        String percorsoFile = "DB_fittizzi/recensioni.txt";
        java.io.BufferedReader br = null;
        try {
            // Apriamo il file
            br = new java.io.BufferedReader(new java.io.FileReader(percorsoFile));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || !linea.contains(";;;;")) {
                    continue;
                }
                String[] dati = linea.split(";;;;");

                String nomeAlbum = dati[0];
                Album albumRecensione = trovaAlbumDaTitolo(nomeAlbum);
                String nomeUtente = dati[1];
                Utente utenteRecensione = trovaUtenteDaUsername(nomeUtente);
                if (albumRecensione != null && utenteRecensione != null) {
                    float voto = Float.parseFloat(dati[2]);
                    LocalDate dataRecensione = LocalDate.parse(dati[3]);
                    Recensione nuovaRecensione = new Recensione(albumRecensione, utenteRecensione, voto, dataRecensione);
                    recensioniPresenti.add(nuovaRecensione);
                } else {
                    System.out.println("Attenzione: recensione saltata, utente o album non trovati.");
                }
            }
            System.out.println("Recensioni caricate con successo dal file txt!");
            recensioniPresenti.sort(Comparator.comparing(Recensione::getData));

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

    // SCRIVI ELEMENTI NEL DB

    /**
     * Aggiunge un nuovo utente alla fine del file di testo del database fittizio scrivendo al suo interno in modo
     * ordinato tutte le informazione necessarie per salvarlo e successivamente leggerlo correttamente
     *
     * @param utenteDaScrivere L'oggetto Utente da aggiungere al database
     *
     */
    public void scriviUtenteDataBase(Utente utenteDaScrivere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/utenti.txt", true))) {
            writer.write(utenteDaScrivere.getUsername() + ";;;;" + utenteDaScrivere.getPassword() + ";;;;" +
                    utenteDaScrivere.getNazione().name() + ";;;;" + "Utente");
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }

    }

    /**
     * Aggiunge una nuova proposta alla fine del file di testo del database fittizio scrivendo al suo interno in modo
     * ordinato tutte le informazioni necessarie per salvarla e successivamente leggerla correttamente
     *
     * @param propostaDaAggiungere l'oggetto Proposta da aggiungere al database
     */
    public void scriviPropostaNelDataBase(Proposta propostaDaAggiungere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/proposte.txt", true))) {
            String descrizioneSicura = propostaDaAggiungere.getDescrizione().replace("\n", "[ACCAPO]");
            writer.write(propostaDaAggiungere.getTipoElemento() + ";;;;" + propostaDaAggiungere.getTitoloElemento() + ";;;;" +
                    propostaDaAggiungere.getDescrizione() + ";;;;" + propostaDaAggiungere.getDataRichiesta() + ";;;;" +
                    propostaDaAggiungere.getStatoProposta() + ";;;;" + propostaDaAggiungere.getAutoreProposta().getUsername());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * Aggiunge una nuova recensione alla fine del file di testo del database fittizio scrivendo al suo interno
     * in modo ordinato tutte le informazione necessarie per salvarla e successivamente leggerla correttamente
     *
     * @param recensioneDaAggiungere l'oggetto Recensione da aggiungere al database
     */
    public void scriviRecensioniDataBase(Recensione recensioneDaAggiungere) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/recensioni.txt", true))) {
            writer.write(recensioneDaAggiungere.getAlbum().getTitolo() + ";;;;" + recensioneDaAggiungere.getUtente().getUsername()
                    + ";;;;" + recensioneDaAggiungere.getVoto() + ";;;;" + recensioneDaAggiungere.getData());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }

    }

    /**
     * Aggiunge un nuovo musicista alla fine del file di testo del database fittizio scrivendo al suo interno
     * in modo ordinato tutte le informazioni necessarie per salvarlo e successivamente leggerlo correttamente
     *
     * @param musicistaAggiunto L'oggetto Musicista da aggiungere al database
     */
    public void scriviMusicistaDataBase(Musicista musicistaAggiunto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/musicisti.txt", true))) {
            writer.write(musicistaAggiunto.getNomeArte() + ";;;;" + musicistaAggiunto.getAnnoInizioAttivita() + ";;;;" +
                    musicistaAggiunto.getIdArtista() + ";;;;" + musicistaAggiunto.getNomeVero() + ";;;;" + musicistaAggiunto.getCognonomeVero()
                    + ";;;;" + musicistaAggiunto.getDataDiNascita().toString());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * Aggiunge un nuovo membro band alla fine del file di testo del database fittizio scrivendo al suo interno
     * in modo ordinato tutte le informazioni necessarie per salvarlo e successivamente leggerlo correttamente
     *
     * @param membroBandDaAggiungere L'oggetto MembroBand da aggiungere al database
     */
    public void scriviMembroBandDataBase(MembroBand membroBandDaAggiungere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/membroBand.txt", true))) {
            writer.write(membroBandDaAggiungere.getMusicista().getIdArtista() + ";;;;" + membroBandDaAggiungere.getStrumentoPrincipale()
                    + ";;;;" + membroBandDaAggiungere.getAnnoIngresso() + ";;;;" + membroBandDaAggiungere.getAnnoUscita() + ";;;;" +
                    membroBandDaAggiungere.getBand().getIdArtista());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * Aggiunge un nuovo genere alla fine del file di testo del database fittizio scrivendo al suo interno
     * in modo ordinato tutte le informazioni necessarie per salvarlo e successivamente leggerlo correttamente
     *
     * @param genereDaAggiungere L'oggetto Genere da aggiungere al database
     */
    public void scriviGenereDataBase(Genere genereDaAggiungere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/generi.txt", true))) {

            // Costruiamo la stringa dei Padri separati da virgola
            String stringaPadri = "";
            ArrayList<Genere> padri = genereDaAggiungere.getGeneriPadre();
            if (padri != null && !padri.isEmpty()) {
                for (int i = 0; i < padri.size(); i++) {
                    stringaPadri += padri.get(i).getNome();
                    if (i < padri.size() - 1) {
                        stringaPadri += ",,,,"; // Aggiunge la virgola tranne che all'ultimo elemento
                    }
                }
            }

            // Costruiamo la stringa dei Figli separati da virgola
            String stringaFigli = "";
            ArrayList<Genere> figli = genereDaAggiungere.getSottogeneri();
            if (figli != null && !figli.isEmpty()) {
                for (int i = 0; i < figli.size(); i++) {
                    stringaFigli += figli.get(i).getNome();
                    if (i < figli.size() - 1) {
                        stringaFigli += ",,,,";
                    }
                }
            }

            // Scriviamo tutto nel file: Nome;Descrizione;Padri;Figli
            writer.write(genereDaAggiungere.getNome() + ";;;;" +
                    genereDaAggiungere.getDescrizione() + ";;;;" +
                    stringaPadri + ";;;;" +
                    stringaFigli);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * Aggiunge un nuovo album alla fine del file di testo del database fittizio scrivendo al suo interno
     * in modo ordinato tutte le informazioni necessarie per salvarlo e successivamente leggerlo correttamente
     *
     * @param albumDaAggiungere L'oggetto Album da aggiungere al database
     */
    public void scriviAlbumDataBase(Album albumDaAggiungere) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/album.txt", true))) {

            // Costruiamo la stringa dei generi separati da ",,,,"
            String stringaGeneri = "";
            ArrayList<Genere> generi = albumDaAggiungere.getGeneri();
            if (generi != null && !generi.isEmpty()) {
                for (int i = 0; i < generi.size(); i++) {
                    stringaGeneri += generi.get(i).getNome();
                    if (i < generi.size() - 1) {
                        stringaGeneri += ",,,,";
                    }
                }
            }

            // Costruiamo la stringa della tracklist (Titolo::::Durata,,,,Titolo::::Durata)
            String stringaTracklist = "";
            ArrayList<Canzone> tracklist = albumDaAggiungere.getTracklist();
            if (tracklist != null && !tracklist.isEmpty()) {
                for (int i = 0; i < tracklist.size(); i++) {
                    stringaTracklist += tracklist.get(i).getTitolo() + "::::" + tracklist.get(i).getDurataSecondi();
                    if (i < tracklist.size() - 1) {
                        stringaTracklist += ",,,,";
                    }
                }
            }

            // Scriviamo tutto nel file, unendo i 5 blocchi principali con i ";;;;"
            writer.write(albumDaAggiungere.getTitolo() + ";;;;" +
                    albumDaAggiungere.getDataPubblicazione().toString() + ";;;;" +
                    albumDaAggiungere.getArtista().getNomeArte() + ";;;;" +
                    stringaGeneri + ";;;;" +
                    stringaTracklist);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * Aggiunge una nuova band alla fine del file di testo del database fittizio scrivendo al suo interno
     * in modo ordinato tutte le informazioni necessarie per salvarla e successivamente leggerla correttamente
     *
     * @param nuovaBand L'oggetto Band da aggiungere al database
     */
    public void scriviBandDataBase(Band nuovaBand) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB_fittizzi/band.txt", true))) {
            writer.write(nuovaBand.getNomeArte() + ";;;;" + nuovaBand.getAnnoInizioAttivita() + ";;;;" + nuovaBand.getIdArtista() + ";;;;" + nuovaBand.getAnnoScioglimento());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    // ALTRI METODI DELLE FORM

    /**
     * Verifica le credenziali inserite dall'utente durante il login, confrontandole
     * con la lista di tutti gli utenti caricati in memoria.
     *
     * @param campoNomeUtente Il nome utente digitato nel form di login
     * @param campoPassword   La password digitata nel form di login
     * @return L'oggetto Utente autenticato con successo
     * @throws CampoNonValido Se le credenziali inserite non corrispondono a nessun
     *                        utente registrato nel sistema
     */
    public Utente cliccatoAccedi(String campoNomeUtente, String campoPassword) throws CampoNonValido {
        for (Utente utente : utentiRegistrati) {
            String passwordUtente = utente.getPassword();
            String nomeUtente = utente.getUsername();

            if (passwordUtente.equals(campoPassword) && nomeUtente.equals(campoNomeUtente)) {
                if (utente instanceof Admin) {
                    throw new CampoNonValido("ERRORE! Crendenziale non valide");
                }
                return utente;
            }

        }
        throw new CampoNonValido("ERRORE! Credenziali non valide");
    }

    /**
     * Verifica le credenziali inserite dall'utente durante il login, confrontandole
     * con la lista di tutti gli utenti caricati in memoria.
     *
     * @param campoNomeUtente Il nome utente digitato nel form di login
     * @param campoPassword   La password digitata nel form di login
     * @param campoID         L'ID Admin digitato nel form di login
     * @return L'oggetto Utente autenticato con successo
     * @throws CampoNonValido Se le credenziali inserite non corrispondono a nessun
     *                        utente registrato nel sistema
     */
    public Utente cliccatoAccedi(String campoNomeUtente, String campoPassword, String campoID) throws CampoNonValido {
        for (Utente utente : utentiRegistrati) {
            if(utente instanceof Admin){
                String idAdmin = ((Admin) utente).getIdAdmin();
                String passwordUtente = utente.getPassword();
                String nomeUtente = utente.getUsername();
                if (passwordUtente.equals(campoPassword) && nomeUtente.equals(campoNomeUtente) && idAdmin.equals(campoID)) {
                    return utente;

                }
            }
        }
        throw new CampoNonValido("ERRORE! Credenziali non valide");
    }

    /**
     * Gestisce la creazione di un nuovo account utente, verificando che l'username sia disponibile
     * e che i campi non contengano caratteri vietati.
     *
     * @param campoNomeUtente L'username scelto per il nuovo account
     * @param campoPassword   La password scelta per il nuovo account
     * @param nazione         La nazione selezionata dall'utente in fase di registrazione
     * @return L'oggetto Utente appena creato e salvato
     * @throws CampoNonValido Se il nome utente è già in uso da un altro utente oppure se
     *                        l'username o la password contengono il separatore vietato ';;;;'
     */
    public Utente cliccatoRegistrati(String campoNomeUtente, String campoPassword, Nazione nazione) throws CampoNonValido {

        if (campoNomeUtente.contains(";;;;") || campoPassword.contains(";;;;")) {
            throw new CampoNonValido("Errore: Il nome utente o la password non possono contenere ';;;;'");
        }

        for (Utente utente : utentiRegistrati) {
            if (campoNomeUtente.equals(utente.getUsername())) {
                throw new CampoNonValido("Errore: Il nome utente '" + campoNomeUtente + "' è già in uso. Scegline un altro!");
            }
        }

        Utente utenteAttuale = new Utente(campoNomeUtente, campoPassword, nazione);
        scriviUtenteDataBase(utenteAttuale);

        this.utentiRegistrati.add(utenteAttuale);

        return utenteAttuale;
    }

    /**
     * Avvia un processo interattivo tramite finestre di dialogo (JOptionPane) per inserire
     * singolarmente i titoli e le durate delle tracce di un nuovo album.
     *
     * @param numeroCanzoni  Il numero totale di canzoni da inserire richiesto precedentemente
     * @param frameChiamante La finestra della GUI che fa da genitore ai popup di input
     * @return Un ArrayList contenente gli oggetti Canzone appena generati dall'utente
     * @throws CampoNonValido Se l'utente preme "Annulla" su un popup, se inserisce lettere
     *                        al posto dei secondi, oppure se i titoli contengono sequenze di caratteri vietate
     */
    public ArrayList<Canzone> inserisciCanzoni(int numeroCanzoni, JFrame frameChiamante) throws CampoNonValido {
        ArrayList<Canzone> canzoniAlbum = new ArrayList<>();
        for (int i = 0; i < numeroCanzoni; i++) {
            String titoloTraccia = JOptionPane.showInputDialog(frameChiamante, "Inserisci il titolo della canzone numero " + (i + 1));
            if (titoloTraccia == null) {
                throw new CampoNonValido("ATTENZIONE cliccando su Canc si annulla l'intera operazione di creazione dell album");
            }
            if (titoloTraccia.contains(";;;;") || titoloTraccia.contains("::::") || titoloTraccia.contains(",,,,")) {
                throw new CampoNonValido("Errore: Il titolo della traccia non può contenere le sequenze ';;;;', '::::' o ',,,,' !");
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

    /**
     * Istanzia un nuovo album in memoria e stabilisce le relazioni bidirezionali con
     * l'artista autore e con i generi a cui appartiene, aggiornando le rispettive liste.
     *
     * @param titolo            Il nome dell'album
     * @param dataPubblicazione La data ufficiale di uscita dell'album
     * @param artista           L'oggetto Artista che ha prodotto l'album
     * @param generi            La lista dei generi musicali selezionati per l'album
     * @param tracklist         La lista ordinata delle canzoni contenute
     * @return Il nuovo oggetto Album creato e collegato a sistema
     * @throws CampoNonValido Se un controllo sulle regole di business fallisce (se implementato)
     */
    public Album creaAlbum(String titolo, LocalDate dataPubblicazione, Artista artista, ArrayList<Genere> generi, ArrayList<Canzone> tracklist) throws CampoNonValido {
        // 1. Crea l'oggetto
        Album nuovoAlbum = new Album(titolo, dataPubblicazione, artista, generi, tracklist);

        this.albumPresenti.add(nuovoAlbum);

        artista.addAlbum(nuovoAlbum);
        for (Genere genere : generi) {
            genere.addListaAlbum(nuovoAlbum);
        }

        return nuovoAlbum;
    }

    /**
     * Genera le righe di dati formattate per poter popolare il modello della tabella
     * nella finestra dell'amministratore contenente lo storico delle proposte.
     *
     * @return Una lista di array di Object, dove ogni array rappresenta fedelmente
     * le colonne di una singola riga della tabella
     */
    public ArrayList<Object[]> creaRigheTabella() {
        ArrayList<Object[]> listaRighe = new ArrayList<>();
        for (Proposta proposta : propostePresenti) {
            if (proposta.getStatoProposta() != StatoProposta.VALUTAZIONE_IN_CORSO) {
                String tipoElemento = proposta.getTipoElemento().name();
                String titoloElemento = proposta.getTitoloElemento();
                String utente = proposta.getAutoreProposta().getUsername();
                String statoProposta = proposta.getStatoProposta().name();
                listaRighe.add(new Object[]{tipoElemento, titoloElemento, statoProposta, utente});
            }
        }
        return listaRighe;
    }

}
