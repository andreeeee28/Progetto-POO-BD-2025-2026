package controller;

import gui.CreaProposta;
import model.*;

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
            creaUtentiRegistrati();
            creaGeneri();
        } catch (CampoNonValido e) {
            System.out.println("Errore nella creazione dei dati fittizzi");
        }

    }
    // con questi metodi vogliamo riempire il database fittizio iniziale che poi potrà essere
    // ulteriermente ingrandito dall Admin
   public void creaGeneri() throws CampoNonValido{
        Genere rock = new Genere("Rock", "Tipicamente utilizza una struttura strofa-ritornello con un ritmo di backbeat e la chitarra elettrica in primo piano; generalmente più pesante (heavy) e/o veloce dei suoi predecessori come blues e gospel");
        Genere pop = new Genere("Pop","Si tratta di un insieme di stili musicali popolari strettamente legati alla produzione e al marketing di massa, incentrati sulla orecchiabilità e l'accessibilità attraverso melodia, ritmo, testi e ritornelli accattivanti.");
        Genere punk = new Genere("Punk","Prodotto musicale e culturale del Punk Rock, noto per il suo stile semplice e sfrontato e per i temi anti-establishment.");
        Genere blues = new Genere("Blues","Nato verso la fine del XIX secolo nelle comunità afroamericane degli Stati Uniti, in particolare nel profondo Sud, questo genere musicale traeva ispirazione dai canti spirituali e di lavoro tradizionali, esercitando una grande influenza su tutta la musica popolare occidentale.");
        Genere metal = new Genere("Metal","Riff trascinanti e distorti, batteria aggressiva, voce vigorosa e, agli inizi, una dimostrazione generale di forza bruta, per poi ramificarsi in decine di sottogeneri.");
        Genere hipHop = new Genere("Hip Hop","Nato principalmente sulla costa orientale degli Stati Uniti, nelle comunità afroamericane, alla fine degli anni '70, questo genere musicale si caratterizza per i suoi schemi ritmici e per una particolare modalità di espressione vocale nota come rapping.");
        Genere jazz = new Genere("Jazz","Nato nelle comunità afroamericane del Sud degli Stati Uniti all'inizio del XX secolo, il genere swing si è sviluppato a partire dalle sonorità delle brass band di New Orleans e dalle influenze del ragtime e del blues, diventando uno stile molto popolare con l'avvento dello swing negli anni '30.");
        Genere ambient = new Genere("Ambient","Privilegia la consistenza e il timbro rispetto alla struttura musicale tradizionale, allo scopo di evocare una particolare atmosfera o stato d'animo.");
        Genere artPunk = new Genere("Art Punk", "Coniuga l'aggressività essenziale del punk rock rudimentale con frequenti sperimentazioni che spaziano dall'interazione strumentale, al rumore, alla dissonanza e/o alle influenze di altri generi come il jazz o il funk.");
        Genere hardcorePunk = new Genere("Hardcore Punk", "Nato alla fine degli anni '70, questo genere è caratterizzato spesso da un uso massiccio di urla e grida, da uno stile di produzione essenziale e da brani molto brevi.");
        Genere alternativeRock = new Genere("Alternative Rock","Eseguita con un approccio meno commerciale, utilizzando sonorità più eccentriche e di ispirazione punk, testi più malinconici o stravaganti e, talvolta, un uso abbondante di distorsione, spesso abbinata a una struttura compositiva di ispirazione pop.");
        Genere grunge = new Genere("Grunge","Unisce elementi punk e metal in un sound dal ritmo moderato, caratterizzato da un suono di chitarra pesante e “sludgy”, una voce “rauca” e testi angoscianti.");
        Genere altPop = new Genere("Alt-Pop", "Nato tra la fine degli anni 2000 e l'inizio degli anni 2010, questo genere combina le convenzioni del pop da classifica con la sensibilità alternativa/indie e un'atmosfera talvolta più minimalista e contemplativa, spesso ispirata all'R&B alternativo.");
        Genere cityPop = new Genere("cityPop","Il movimento della musica pop giapponese mirava a riflettere la vita urbana durante il periodo di forte espansione economica del Paese negli anni '70 e '80, caratterizzato da un sound occidentale contemporaneo e da arrangiamenti ricchi e sontuosi.");
        Genere acousticBlues = new Genere("Acoustic Blues","Si è evoluto dal “Work Song” e dallo “Spiritual” fino a diventare la forma più significativa delle origini del blues.");
        Genere boogieWoogie = new Genere("Boogie Woogie","Blues dal ritmo vivace e incalzante, con il pianoforte come strumento principale, caratterizzato da assoli e improvvisazioni su una linea di basso ripetitiva composta da ottavi.");
        Genere funkMetal = new Genere("Funk Metal","Unisce ritmi funk rock a linee di basso slap e riff di chitarra dal suono metallico.");
        Genere deathMetal = new Genere("Death Metal","Chitarre fortemente distorte e accordate in tonalità più basse, riff suonati con il palm muting e il tremolo, percussioni con doppia cassa e blast beat, progressioni di accordi cromatici, tonalità minori, bruschi cambi di tempo e voci gutturali.");
        Genere bePop = new Genere("Bepop","Tempi veloci, improvvisazione, cromatismo melodico, armonie complesse e progressioni di accordi.");
        Genere afroJazz = new Genere("Afro-Jazz","Percussioni e ritmi in stile africano.");
        Genere popRap = new Genere("Pop Rap","Incorpora elementi pop quali voci melodiche, melodie orecchiabili, strutture strofa-ritornello e testi adatti alla radio.");
        Genere trap = new Genere("Trap","Nato ad Atlanta nei primi anni 2000; caratterizzato da un suono rapido e incisivo del charleston e da un basso potente a tempi moderati.");
        Genere darkAmbient = new Genere("Dark Ambient","Evoca un'atmosfera minacciosa, cupa e dissonante.");
        Genere tribalAmbient = new Genere("Tribal Ambient","Coniuga la filosofia e l'atmosfera dell'Ambient con strumenti tradizionali e suoni provenienti da ogni parte del mondo.");
        rock.addSottogeneri(alternativeRock);
        rock.addSottogeneri(grunge);
        punk.addSottogeneri(artPunk);
        punk.addSottogeneri(hardcorePunk);
        pop.addSottogeneri(cityPop);
        pop.addSottogeneri(altPop);
        jazz.addSottogeneri(afroJazz);
        jazz.addSottogeneri(bePop);
        metal.addSottogeneri(deathMetal);
        metal.addSottogeneri(funkMetal);
        ambient.addSottogeneri(darkAmbient);
        ambient.addSottogeneri(tribalAmbient);
        hipHop.addSottogeneri(trap);
        hipHop.addSottogeneri(popRap);
        blues.addSottogeneri(acousticBlues);
        blues.addSottogeneri(boogieWoogie);
        alternativeRock.addGeneriPadre(rock);
        grunge.addGeneriPadre(rock);
        artPunk.addGeneriPadre(punk);
        hardcorePunk.addGeneriPadre(punk);
        cityPop.addGeneriPadre(pop);
        altPop.addGeneriPadre(pop);
        afroJazz.addGeneriPadre(jazz);
       bePop.addGeneriPadre(jazz);
       deathMetal.addGeneriPadre(metal);
       funkMetal.addGeneriPadre(metal);
       darkAmbient.addGeneriPadre(ambient);
       tribalAmbient.addGeneriPadre(ambient);
       trap.addGeneriPadre(hipHop);
       popRap.addGeneriPadre(hipHop);
       acousticBlues.addGeneriPadre(blues);
       boogieWoogie.addGeneriPadre(blues);
   }
   /*public void creaArtisti() throws CampoNonValido {
       // --- ROCK ---
       Artista beatles = new Band("The Beatles", 1960, "ART-R01");
       Artista stones = new Band("The Rolling Stones", 1962, "ART-R02");
       Artista floyd = new Artista("Pink Floyd", 1965, "ART-R03");

       // --- POP ---
       Artista mj = new Artista("Michael Jackson", 1964, "ART-P01");
       Artista prince = new Artista("Prince", 1975, "ART-P02"); // Sostituita Madonna
       Artista weeknd = new Artista("The Weeknd", 2009, "ART-P03");

       // --- PUNK ---
       Artista ramones = new Artista("Ramones", 1974, "ART-PK1");
       Artista clash = new Artista("The Clash", 1976, "ART-PK2");
       Artista sexPistols = new Artista("Sex Pistols", 1975, "ART-PK3");

       // --- BLUES ---
       Artista bbKing = new Artista("B.B. King", 1948, "ART-B01");
       Artista muddyWaters = new Artista("Muddy Waters", 1941, "ART-B02");
       Artista robertJohnson = new Artista("Robert Johnson", 1929, "ART-B03");

       // --- METAL ---
       Artista blackSabbath = new Artista("Black Sabbath", 1968, "ART-M01");
       Artista ironMaiden = new Artista("Iron Maiden", 1975, "ART-M02");
       Artista metallica = new Artista("Metallica", 1981, "ART-M03");

       // --- HIP HOP ---
       Artista tupac = new Artista("Tupac Shakur", 1989, "ART-H01");
       Artista biggie = new Artista("The Notorious B.I.G.", 1992, "ART-H02");
       Artista eminem = new Artista("Eminem", 1988, "ART-H03");

       // --- JAZZ ---
       Artista milesDavis = new Artista("Miles Davis", 1944, "ART-J01");
       Artista coltrane = new Artista("John Coltrane", 1945, "ART-J02");
       Artista armstrong = new Artista("Louis Armstrong", 1919, "ART-J03");

       // --- AMBIENT ---
       Artista brianEno = new Artista("Brian Eno", 1970, "ART-A01");
       Artista aphexTwin = new Artista("Aphex Twin", 1985, "ART-A02");
       Artista basinski = new Artista("William Basinski", 1998, "ART-A03");


       // ==========================================
       // 🎧 ARTISTI DEI SOTTOGENERI
       // ==========================================

       // Rock Sottogeneri
       Artista radiohead = new Artista("Radiohead", 1985, "ART-SUB-R1"); // Alternative Rock
       Artista nirvana = new Artista("Nirvana", 1987, "ART-SUB-R2"); // Grunge

       // Punk Sottogeneri
       Artista television = new Artista("Television", 1973, "ART-SUB-PK1"); // Art Punk
       Artista blackFlag = new Artista("Black Flag", 1976, "ART-SUB-PK2"); // Hardcore Punk

       // Pop Sottogeneri
       Artista yamashita = new Artista("Tatsuro Yamashita", 1973, "ART-SUB-P1"); // City Pop
       Artista lorde = new Artista("Lorde", 2012, "ART-SUB-P2"); // Alt-Pop

       // Jazz Sottogeneri
       Artista felaKuti = new Artista("Fela Kuti", 1958, "ART-SUB-J1"); // Afro-Jazz
       Artista charlieParker = new Artista("Charlie Parker", 1937, "ART-SUB-J2"); // Bebop

       // Metal Sottogeneri
       Artista death = new Artista("Death", 1983, "ART-SUB-M1"); // Death Metal
       Artista faithNoMore = new Artista("Faith No More", 1979, "ART-SUB-M2"); // Funk Metal

       // Ambient Sottogeneri
       Artista lustmord = new Artista("Lustmord", 1980, "ART-SUB-A1"); // Dark Ambient
       Artista steveRoach = new Artista("Steve Roach", 1979, "ART-SUB-A2"); // Tribal Ambient

       // Hip Hop Sottogeneri
       Artista future = new Artista("Future", 2003, "ART-SUB-H1"); // Trap
       Artista drake = new Artista("Drake", 2006, "ART-SUB-H2"); // Pop Rap

       // Blues Sottogeneri
       Artista sonHouse = new Artista("Son House", 1930, "ART-SUB-B1"); // Acoustic Blues
       Artista albertAmmons = new Artista("Albert Ammons", 1934, "ART-SUB-B2"); // Boogie Woogie



       //  SALVATAGGIO NEL "DATABASE" (ARRAYLIST)


       // Principali
       this.artistiPresenti.add(beatles); this.artistiPresenti.add(stones); this.artistiPresenti.add(floyd);
       this.artistiPresenti.add(mj); this.artistiPresenti.add(prince); this.artistiPresenti.add(weeknd);
       this.artistiPresenti.add(ramones); this.artistiPresenti.add(clash); this.artistiPresenti.add(sexPistols);
       this.artistiPresenti.add(bbKing); this.artistiPresenti.add(muddyWaters); this.artistiPresenti.add(robertJohnson);
       this.artistiPresenti.add(blackSabbath); this.artistiPresenti.add(ironMaiden); this.artistiPresenti.add(metallica);
       this.artistiPresenti.add(tupac); this.artistiPresenti.add(biggie); this.artistiPresenti.add(eminem);
       this.artistiPresenti.add(milesDavis); this.artistiPresenti.add(coltrane); this.artistiPresenti.add(armstrong);
       this.artistiPresenti.add(brianEno); this.artistiPresenti.add(aphexTwin); this.artistiPresenti.add(basinski);

       // Sottogeneri
       this.artistiPresenti.add(radiohead); this.artistiPresenti.add(nirvana);
       this.artistiPresenti.add(television); this.artistiPresenti.add(blackFlag);
       this.artistiPresenti.add(yamashita); this.artistiPresenti.add(lorde);
       this.artistiPresenti.add(felaKuti); this.artistiPresenti.add(charlieParker);
       this.artistiPresenti.add(death); this.artistiPresenti.add(faithNoMore);
       this.artistiPresenti.add(lustmord); this.artistiPresenti.add(steveRoach);
       this.artistiPresenti.add(future); this.artistiPresenti.add(drake);
       this.artistiPresenti.add(sonHouse); this.artistiPresenti.add(albertAmmons);

   } */
   public void creaUtentiRegistrati() throws CampoNonValido {
       // 1. Utente dall'Italia
       Utente u1 = new Utente("mario99", "PassMario123", Nazione.ITALIA);

       // 2. Utente admin
       Utente u2 = new Utente("admin_root", "SuperSegreta!!", Nazione.USA);

       // 3. Utente dalla Francia
       Utente u3 = new Utente("giulia_fr", "Paris2024!!!", Nazione.FRANCIA);

       // 4. Utente dalla Germania
       Utente u4 = new Utente("hans_berlin", "BerlinPass24", Nazione.GERMANIA);

       // 5. Utente dalla Cina
       Utente u5 = new Utente("mei_ling_9", "BeijingPass*", Nazione.CINA);

       // 6. Utente dall'Irlanda
       Utente u6 = new Utente("irish_luck", "DublinPub!!!", Nazione.IRLANDA);

       // 7. Utente dalla Romania
       Utente u7 = new Utente("vlad_rom", "VladDracula1", Nazione.ROMANIA);

       // 8. Utente dalla Svezia
       Utente u8 = new Utente("sven_stock", "SveziaNordic", Nazione.SVEZIA);

       // 9. Utente dai Paesi Bassi
       Utente u9 = new Utente("dutch_boy", "Amsterdam123", Nazione.PAESI_BASSI);

       // 10. Utente dal Kazakistan
       Utente u10 = new Utente("borat_kaz", "VeryNice1234", Nazione.KAZAKISTAN);

       // --- SALVATAGGIO NELLA LISTA DEL CONTROLLER ---
       // (Assicurati che la tua lista nel controller si chiami "utentiRegistrati")
       this.utentiRegistrati.add(u1);
       this.utentiRegistrati.add(u2);
       this.utentiRegistrati.add(u3);
       this.utentiRegistrati.add(u4);
       this.utentiRegistrati.add(u5);
       this.utentiRegistrati.add(u6);
       this.utentiRegistrati.add(u7);
       this.utentiRegistrati.add(u8);
       this.utentiRegistrati.add(u9);
       this.utentiRegistrati.add(u10);
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

    private void aggiungiAlbumFinto(String titolo, LocalDate data, String nomeArtista, String nomeGenere, ArrayList<Canzone> tracce) throws CampoNonValido {
        Artista art = trovaArtista(nomeArtista);
        Genere gen = trovaGenere(nomeGenere);

        if (art != null && gen != null) {

            ArrayList<Genere> listaGeneri = new ArrayList<>();
            listaGeneri.add(gen);
            Album nuovoAlbum = new Album(titolo, data, art, listaGeneri, tracce); // iniziamo a creare l'album con un solo genere nell'array list, eventuali altri generi saranno inseriti successivamente con un altro metodo
            this.albumPresenti.add(nuovoAlbum);
            art.addAlbum(nuovoAlbum);
            gen.addListaAlbum(nuovoAlbum);

        } else {
            System.out.println(" Attenzione: Artista o Genere non trovato per l'album: " + titolo);
        }
    }


    public void creaAlbum() throws CampoNonValido {

        // ---  ROCK ---
        ArrayList<Canzone> trcAbbey = new ArrayList<>();
        trcAbbey.add(new Canzone("Come Together", 259)); trcAbbey.add(new Canzone("Something", 182));
        aggiungiAlbumFinto("Abbey Road", LocalDate.of(1969, 9, 26), "The Beatles", "Rock", trcAbbey);

        ArrayList<Canzone> trcHelp = new ArrayList<>();
        trcHelp.add(new Canzone("Help!", 138)); trcHelp.add(new Canzone("Yesterday", 125));
        aggiungiAlbumFinto("Help!", LocalDate.of(1965, 8, 6), "The Beatles", "Rock", trcHelp);

        ArrayList<Canzone> trcBleed = new ArrayList<>();
        trcBleed.add(new Canzone("Gimme Shelter", 270)); trcBleed.add(new Canzone("Midnight Rambler", 412));
        aggiungiAlbumFinto("Let It Bleed", LocalDate.of(1969, 12, 5), "The Rolling Stones", "Rock", trcBleed);

        ArrayList<Canzone> trcSticky = new ArrayList<>();
        trcSticky.add(new Canzone("Brown Sugar", 228)); trcSticky.add(new Canzone("Wild Horses", 342));
        aggiungiAlbumFinto("Sticky Fingers", LocalDate.of(1971, 4, 23), "The Rolling Stones", "Rock", trcSticky);

        ArrayList<Canzone> trcDarkSide = new ArrayList<>();
        trcDarkSide.add(new Canzone("Time", 421)); trcDarkSide.add(new Canzone("Money", 382));
        aggiungiAlbumFinto("The Dark Side", LocalDate.of(1973, 3, 1), "Pink Floyd", "Rock", trcDarkSide);

        ArrayList<Canzone> trcWall = new ArrayList<>();
        trcWall.add(new Canzone("Hey You", 280)); trcWall.add(new Canzone("Comfortably Numb", 382));
        aggiungiAlbumFinto("The Wall", LocalDate.of(1979, 11, 30), "Pink Floyd", "Rock", trcWall);


        // ---  POP ---
        ArrayList<Canzone> trcThriller = new ArrayList<>();
        trcThriller.add(new Canzone("Thriller", 357)); trcThriller.add(new Canzone("Billie Jean", 294));
        aggiungiAlbumFinto("Thriller", LocalDate.of(1982, 11, 29), "Michael Jackson", "Pop", trcThriller);

        ArrayList<Canzone> trcBad = new ArrayList<>();
        trcBad.add(new Canzone("Bad", 247)); trcBad.add(new Canzone("Smooth Criminal", 257));
        aggiungiAlbumFinto("Bad", LocalDate.of(1987, 8, 31), "Michael Jackson", "Pop", trcBad);

        ArrayList<Canzone> trcPurple = new ArrayList<>();
        trcPurple.add(new Canzone("Purple Rain", 521)); trcPurple.add(new Canzone("When Doves Cry", 352));
        aggiungiAlbumFinto("Purple Rain", LocalDate.of(1984, 6, 25), "Prince", "Pop", trcPurple);

        ArrayList<Canzone> trc1999 = new ArrayList<>();
        trc1999.add(new Canzone("1999", 379)); trc1999.add(new Canzone("Little Red Corvette", 303));
        aggiungiAlbumFinto("1999", LocalDate.of(1982, 10, 27), "Prince", "Pop", trc1999);

        ArrayList<Canzone> trcStarboy = new ArrayList<>();
        trcStarboy.add(new Canzone("Starboy", 230)); trcStarboy.add(new Canzone("I Feel It Coming", 269));
        aggiungiAlbumFinto("Starboy", LocalDate.of(2016, 11, 25), "The Weeknd", "Pop", trcStarboy);

        ArrayList<Canzone> trcAfterH = new ArrayList<>();
        trcAfterH.add(new Canzone("Blinding Lights", 200)); trcAfterH.add(new Canzone("Save Your Tears", 215));
        aggiungiAlbumFinto("After Hours", LocalDate.of(2020, 3, 20), "The Weeknd", "Pop", trcAfterH);


        // --- PUNK ---
        ArrayList<Canzone> trcRamones = new ArrayList<>();
        trcRamones.add(new Canzone("Blitzkrieg Bop", 134)); trcRamones.add(new Canzone("Judy Is a Punk", 92));
        aggiungiAlbumFinto("Ramones", LocalDate.of(1976, 4, 23), "Ramones", "Punk", trcRamones);

        ArrayList<Canzone> trcRocket = new ArrayList<>();
        trcRocket.add(new Canzone("Sheena Is", 169)); trcRocket.add(new Canzone("Teenage Lobotomy", 121));
        aggiungiAlbumFinto("Rocket to Russia", LocalDate.of(1977, 11, 4), "Ramones", "Punk", trcRocket);

        ArrayList<Canzone> trcLondon = new ArrayList<>();
        trcLondon.add(new Canzone("London Calling", 199)); trcLondon.add(new Canzone("Spanish Bombs", 198));
        aggiungiAlbumFinto("London Calling", LocalDate.of(1979, 12, 14), "The Clash", "Punk", trcLondon);

        ArrayList<Canzone> trcCombat = new ArrayList<>();
        trcCombat.add(new Canzone("Should I Stay", 188)); trcCombat.add(new Canzone("Rock the Casbah", 222));
        aggiungiAlbumFinto("Combat Rock", LocalDate.of(1982, 5, 14), "The Clash", "Punk", trcCombat);

        ArrayList<Canzone> trcBollocks = new ArrayList<>();
        trcBollocks.add(new Canzone("Anarchy in the UK", 212)); trcBollocks.add(new Canzone("God Save the Queen", 199));
        aggiungiAlbumFinto("Never Mind the Bollocks", LocalDate.of(1977, 10, 28), "Sex Pistols", "Punk", trcBollocks);

        ArrayList<Canzone> trcSpunk = new ArrayList<>();
        trcSpunk.add(new Canzone("Seventeen", 122)); trcSpunk.add(new Canzone("Liar", 160));
        aggiungiAlbumFinto("Spunk", LocalDate.of(1977, 9, 1), "Sex Pistols", "Punk", trcSpunk);


        // ---  BLUES ---
        ArrayList<Canzone> trcRegal = new ArrayList<>();
        trcRegal.add(new Canzone("Every Day I Have the Blues", 158)); trcRegal.add(new Canzone("Sweet Little Angel", 250));
        aggiungiAlbumFinto("Live at the Regal", LocalDate.of(1965, 11, 21), "B.B. King", "Blues", trcRegal);

        ArrayList<Canzone> trcLucille = new ArrayList<>();
        trcLucille.add(new Canzone("Lucille", 616)); trcLucille.add(new Canzone("You Move Me So", 123));
        aggiungiAlbumFinto("Lucille", LocalDate.of(1968, 12, 1), "B.B. King", "Blues", trcLucille);

        ArrayList<Canzone> trcFolk = new ArrayList<>();
        trcFolk.add(new Canzone("My Home Is in the Delta", 238)); trcFolk.add(new Canzone("Cold Weather Blues", 280));
        aggiungiAlbumFinto("Folk Singer", LocalDate.of(1964, 4, 1), "Muddy Waters", "Blues", trcFolk);

        ArrayList<Canzone> trcHard = new ArrayList<>();
        trcHard.add(new Canzone("Mannish Boy", 323)); trcHard.add(new Canzone("I Can't Be Satisfied", 210));
        aggiungiAlbumFinto("Hard Again", LocalDate.of(1977, 1, 10), "Muddy Waters", "Blues", trcHard);

        ArrayList<Canzone> trcKingDelta = new ArrayList<>();
        trcKingDelta.add(new Canzone("Cross Road Blues", 159)); trcKingDelta.add(new Canzone("Sweet Home Chicago", 179));
        aggiungiAlbumFinto("King of the Delta Blues", LocalDate.of(1961, 1, 1), "Robert Johnson", "Blues", trcKingDelta);

        ArrayList<Canzone> trcHellhound = new ArrayList<>();
        trcHellhound.add(new Canzone("Hellhound", 156)); trcHellhound.add(new Canzone("Me and the Devil", 150));
        aggiungiAlbumFinto("Hellhound on My Trail", LocalDate.of(1937, 6, 20), "Robert Johnson", "Blues", trcHellhound);


        // --- METAL ---
        ArrayList<Canzone> trcParanoid = new ArrayList<>();
        trcParanoid.add(new Canzone("War Pigs", 474)); trcParanoid.add(new Canzone("Paranoid", 168));
        aggiungiAlbumFinto("Paranoid", LocalDate.of(1970, 9, 18), "Black Sabbath", "Metal", trcParanoid);

        ArrayList<Canzone> trcMasterR = new ArrayList<>();
        trcMasterR.add(new Canzone("Sweet Leaf", 305)); trcMasterR.add(new Canzone("Children of the Grave", 315));
        aggiungiAlbumFinto("Master of Reality", LocalDate.of(1971, 7, 21), "Black Sabbath", "Metal", trcMasterR);

        ArrayList<Canzone> trcNumber = new ArrayList<>();
        trcNumber.add(new Canzone("Run to the Hills", 233)); trcNumber.add(new Canzone("The Number", 291));
        aggiungiAlbumFinto("Number of the Beast", LocalDate.of(1982, 3, 22), "Iron Maiden", "Metal", trcNumber);

        ArrayList<Canzone> trcKillers = new ArrayList<>();
        trcKillers.add(new Canzone("Wrathchild", 174)); trcKillers.add(new Canzone("Murders", 258));
        aggiungiAlbumFinto("Killers", LocalDate.of(1981, 2, 2), "Iron Maiden", "Metal", trcKillers);

        ArrayList<Canzone> trcMasterP = new ArrayList<>();
        trcMasterP.add(new Canzone("Battery", 312)); trcMasterP.add(new Canzone("Master of Puppets", 515));
        aggiungiAlbumFinto("Master of Puppets", LocalDate.of(1986, 3, 3), "Metallica", "Metal", trcMasterP);

        ArrayList<Canzone> trcRide = new ArrayList<>();
        trcRide.add(new Canzone("Fade to Black", 417)); trcRide.add(new Canzone("Creeping Death", 396));
        aggiungiAlbumFinto("Ride the Lightning", LocalDate.of(1984, 7, 27), "Metallica", "Metal", trcRide);


        // --- 🎤 HIP HOP ---
        ArrayList<Canzone> trcEyez = new ArrayList<>();
        trcEyez.add(new Canzone("Ambitionz", 279)); trcEyez.add(new Canzone("California Love", 241));
        aggiungiAlbumFinto("All Eyez on Me", LocalDate.of(1996, 2, 13), "Tupac Shakur", "Hip Hop", trcEyez);

        ArrayList<Canzone> trcAgainst = new ArrayList<>();
        trcAgainst.add(new Canzone("Dear Mama", 279)); trcAgainst.add(new Canzone("Temptations", 300));
        aggiungiAlbumFinto("Me Against the World", LocalDate.of(1995, 3, 14), "Tupac Shakur", "Hip Hop", trcAgainst);

        ArrayList<Canzone> trcReady = new ArrayList<>();
        trcReady.add(new Canzone("Juicy", 302)); trcReady.add(new Canzone("Big Poppa", 252));
        aggiungiAlbumFinto("Ready to Die", LocalDate.of(1994, 9, 13), "The Notorious B.I.G.", "Hip Hop", trcReady);

        ArrayList<Canzone> trcLife = new ArrayList<>();
        trcLife.add(new Canzone("Hypnotize", 230)); trcLife.add(new Canzone("Mo Money Mo Problems", 257));
        aggiungiAlbumFinto("Life After Death", LocalDate.of(1997, 3, 25), "The Notorious B.I.G.", "Hip Hop", trcLife);

        ArrayList<Canzone> trcMarshall = new ArrayList<>();
        trcMarshall.add(new Canzone("Stan", 404)); trcMarshall.add(new Canzone("The Real Slim Shady", 284));
        aggiungiAlbumFinto("The Marshall Mathers LP", LocalDate.of(2000, 5, 23), "Eminem", "Hip Hop", trcMarshall);

        ArrayList<Canzone> trcEminemShow = new ArrayList<>();
        trcEminemShow.add(new Canzone("Without Me", 290)); trcEminemShow.add(new Canzone("Sing for the Moment", 339));
        aggiungiAlbumFinto("The Eminem Show", LocalDate.of(2002, 5, 26), "Eminem", "Hip Hop", trcEminemShow);


        // --- 🎺 JAZZ ---
        ArrayList<Canzone> trcBlue = new ArrayList<>();
        trcBlue.add(new Canzone("So What", 562)); trcBlue.add(new Canzone("Blue in Green", 337));
        aggiungiAlbumFinto("Kind of Blue", LocalDate.of(1959, 8, 17), "Miles Davis", "Jazz", trcBlue);

        ArrayList<Canzone> trcBrew = new ArrayList<>();
        trcBrew.add(new Canzone("Pharaoh's Dance", 1200)); trcBrew.add(new Canzone("Spanish Key", 1050));
        aggiungiAlbumFinto("Bitches Brew", LocalDate.of(1970, 3, 30), "Miles Davis", "Jazz", trcBrew);

        ArrayList<Canzone> trcSteps = new ArrayList<>();
        trcSteps.add(new Canzone("Giant Steps", 283)); trcSteps.add(new Canzone("Naima", 261));
        aggiungiAlbumFinto("Giant Steps", LocalDate.of(1960, 1, 1), "John Coltrane", "Jazz", trcSteps);

        ArrayList<Canzone> trcSupreme = new ArrayList<>();
        trcSupreme.add(new Canzone("Acknowledgment", 467)); trcSupreme.add(new Canzone("Resolution", 442));
        aggiungiAlbumFinto("A Love Supreme", LocalDate.of(1965, 1, 1), "John Coltrane", "Jazz", trcSupreme);

        ArrayList<Canzone> trcDolly = new ArrayList<>();
        trcDolly.add(new Canzone("Hello Dolly", 144)); trcDolly.add(new Canzone("Moon River", 175));
        aggiungiAlbumFinto("Hello Dolly", LocalDate.of(1964, 5, 1), "Louis Armstrong", "Jazz", trcDolly);

        ArrayList<Canzone> trcWorld = new ArrayList<>();
        trcWorld.add(new Canzone("Wonderful World", 139)); trcWorld.add(new Canzone("Cabaret", 164));
        aggiungiAlbumFinto("What a Wonderful World", LocalDate.of(1968, 1, 1), "Louis Armstrong", "Jazz", trcWorld);


        // --- ☁️ AMBIENT ---
        ArrayList<Canzone> trcAirports = new ArrayList<>();
        trcAirports.add(new Canzone("1/1", 1050)); trcAirports.add(new Canzone("2/1", 500));
        aggiungiAlbumFinto("Music for Airports", LocalDate.of(1978, 3, 1), "Brian Eno", "Ambient", trcAirports);

        ArrayList<Canzone> trcApollo = new ArrayList<>();
        trcApollo.add(new Canzone("An Ending", 265)); trcApollo.add(new Canzone("Deep Blue Day", 238));
        aggiungiAlbumFinto("Apollo", LocalDate.of(1983, 7, 1), "Brian Eno", "Ambient", trcApollo);

        ArrayList<Canzone> trcSelected = new ArrayList<>();
        trcSelected.add(new Canzone("Xtal", 294)); trcSelected.add(new Canzone("Tha", 540));
        aggiungiAlbumFinto("Selected Ambient Works", LocalDate.of(1992, 11, 9), "Aphex Twin", "Ambient", trcSelected);

        ArrayList<Canzone> trcRichard = new ArrayList<>();
        trcRichard.add(new Canzone("4", 217)); trcRichard.add(new Canzone("Fingerbib", 228));
        aggiungiAlbumFinto("Richard D. James Album", LocalDate.of(1996, 11, 4), "Aphex Twin", "Ambient", trcRichard);

        ArrayList<Canzone> trcLoops = new ArrayList<>();
        trcLoops.add(new Canzone("dlp 1.1", 3780)); trcLoops.add(new Canzone("dlp 2.1", 650));
        aggiungiAlbumFinto("The Disintegration Loops", LocalDate.of(2002, 1, 1), "William Basinski", "Ambient", trcLoops);

        ArrayList<Canzone> trcWater = new ArrayList<>();
        trcWater.add(new Canzone("Watermusic I", 3600)); trcWater.add(new Canzone("Watermusic II", 3600));
        aggiungiAlbumFinto("Watermusic", LocalDate.of(2001, 1, 1), "William Basinski", "Ambient", trcWater);



        // Album sottogeneri

        ArrayList<Canzone> trcOk = new ArrayList<>();
        trcOk.add(new Canzone("Paranoid Android", 383)); trcOk.add(new Canzone("Karma Police", 261));
        aggiungiAlbumFinto("OK Computer", LocalDate.of(1997, 5, 21), "Radiohead", "Alternative Rock", trcOk);

        ArrayList<Canzone> trcKidA = new ArrayList<>();
        trcKidA.add(new Canzone("Everything in Its Right", 251)); trcKidA.add(new Canzone("Idioteque", 309));
        aggiungiAlbumFinto("Kid A", LocalDate.of(2000, 10, 2), "Radiohead", "Alternative Rock", trcKidA);

        ArrayList<Canzone> trcNevermind = new ArrayList<>();
        trcNevermind.add(new Canzone("Smells Like Teen Spirit", 301)); trcNevermind.add(new Canzone("Come as You Are", 219));
        aggiungiAlbumFinto("Nevermind", LocalDate.of(1991, 9, 24), "Nirvana", "Grunge", trcNevermind);

        ArrayList<Canzone> trcUtero = new ArrayList<>();
        trcUtero.add(new Canzone("Heart-Shaped Box", 281)); trcUtero.add(new Canzone("All Apologies", 230));
        aggiungiAlbumFinto("In Utero", LocalDate.of(1993, 9, 21), "Nirvana", "Grunge", trcUtero);

        ArrayList<Canzone> trcMarquee = new ArrayList<>();
        trcMarquee.add(new Canzone("See No Evil", 233)); trcMarquee.add(new Canzone("Marquee Moon", 640));
        aggiungiAlbumFinto("Marquee Moon", LocalDate.of(1977, 2, 8), "Television", "Art Punk", trcMarquee);

        ArrayList<Canzone> trcAdventure = new ArrayList<>();
        trcAdventure.add(new Canzone("Glory", 191)); trcAdventure.add(new Canzone("Foxhole", 288));
        aggiungiAlbumFinto("Adventure", LocalDate.of(1978, 4, 1), "Television", "Art Punk", trcAdventure);

        ArrayList<Canzone> trcDamaged = new ArrayList<>();
        trcDamaged.add(new Canzone("Rise Above", 146)); trcDamaged.add(new Canzone("TV Party", 211));
        aggiungiAlbumFinto("Damaged", LocalDate.of(1981, 12, 5), "Black Flag", "Hardcore Punk", trcDamaged);

        ArrayList<Canzone> trcWar = new ArrayList<>();
        trcWar.add(new Canzone("My War", 226)); trcWar.add(new Canzone("Nothing Left Inside", 404));
        aggiungiAlbumFinto("My War", LocalDate.of(1984, 3, 1), "Black Flag", "Hardcore Punk", trcWar);

        ArrayList<Canzone> trcForYou = new ArrayList<>();
        trcForYou.add(new Canzone("Sparkle", 253)); trcForYou.add(new Canzone("Morning Glory", 208));
        aggiungiAlbumFinto("For You", LocalDate.of(1982, 1, 21), "Tatsuro Yamashita", "cityPop", trcForYou);

        ArrayList<Canzone> trcRideOn = new ArrayList<>();
        trcRideOn.add(new Canzone("Ride on Time", 354)); trcRideOn.add(new Canzone("Daydream", 268));
        aggiungiAlbumFinto("Ride on Time", LocalDate.of(1980, 9, 19), "Tatsuro Yamashita", "cityPop", trcRideOn);

        ArrayList<Canzone> trcHeroine = new ArrayList<>();
        trcHeroine.add(new Canzone("Royals", 190)); trcHeroine.add(new Canzone("Tennis Court", 198));
        aggiungiAlbumFinto("Pure Heroine", LocalDate.of(2013, 9, 27), "Lorde", "Alt-Pop", trcHeroine);

        ArrayList<Canzone> trcMelodrama = new ArrayList<>();
        trcMelodrama.add(new Canzone("Green Light", 234)); trcMelodrama.add(new Canzone("Perfect Places", 221));
        aggiungiAlbumFinto("Melodrama", LocalDate.of(2017, 6, 16), "Lorde", "Alt-Pop", trcMelodrama);

        ArrayList<Canzone> trcZombie = new ArrayList<>();
        trcZombie.add(new Canzone("Zombie", 745)); trcZombie.add(new Canzone("Mister Follow Follow", 776));
        aggiungiAlbumFinto("Zombie", LocalDate.of(1976, 1, 1), "Fela Kuti", "Afro-Jazz", trcZombie);

        ArrayList<Canzone> trcExpensive = new ArrayList<>();
        trcExpensive.add(new Canzone("Expensive Shit", 791)); trcExpensive.add(new Canzone("Water No Get", 660));
        aggiungiAlbumFinto("Expensive Shit", LocalDate.of(1975, 1, 1), "Fela Kuti", "Afro-Jazz", trcExpensive);

        ArrayList<Canzone> trcBird = new ArrayList<>();
        trcBird.add(new Canzone("Bloomdido", 205)); trcBird.add(new Canzone("Leap Frog", 149));
        aggiungiAlbumFinto("Bird and Diz", LocalDate.of(1952, 1, 1), "Charlie Parker", "Bepop", trcBird);

        ArrayList<Canzone> trcMagnificent = new ArrayList<>();
        trcMagnificent.add(new Canzone("Star Eyes", 210)); trcMagnificent.add(new Canzone("Blues", 165));
        aggiungiAlbumFinto("Magnificent", LocalDate.of(1955, 1, 1), "Charlie Parker", "Bepop", trcMagnificent);

        ArrayList<Canzone> trcGore = new ArrayList<>();
        trcGore.add(new Canzone("Zombie Ritual", 275)); trcGore.add(new Canzone("Denial of Life", 217));
        aggiungiAlbumFinto("Scream Bloody Gore", LocalDate.of(1987, 5, 25), "Death", "Death Metal", trcGore);

        ArrayList<Canzone> trcLeprosy = new ArrayList<>();
        trcLeprosy.add(new Canzone("Leprosy", 379)); trcLeprosy.add(new Canzone("Pull the Plug", 265));
        aggiungiAlbumFinto("Leprosy", LocalDate.of(1988, 8, 12), "Death", "Death Metal", trcLeprosy);

        ArrayList<Canzone> trcReal = new ArrayList<>();
        trcReal.add(new Canzone("Epic", 293)); trcReal.add(new Canzone("Falling to Pieces", 313));
        aggiungiAlbumFinto("The Real Thing", LocalDate.of(1989, 6, 20), "Faith No More", "Funk Metal", trcReal);

        ArrayList<Canzone> trcAngel = new ArrayList<>();
        trcAngel.add(new Canzone("Midlife Crisis", 261)); trcAngel.add(new Canzone("Easy", 186));
        aggiungiAlbumFinto("Angel Dust", LocalDate.of(1992, 6, 8), "Faith No More", "Funk Metal", trcAngel);

        ArrayList<Canzone> trcHeresy = new ArrayList<>();
        trcHeresy.add(new Canzone("Heresy Part 1", 900)); trcHeresy.add(new Canzone("Heresy Part 2", 900));
        aggiungiAlbumFinto("Heresy", LocalDate.of(1990, 1, 1), "Lustmord", "Dark Ambient", trcHeresy);

        ArrayList<Canzone> trcMonstrous = new ArrayList<>();
        trcMonstrous.add(new Canzone("Primordial", 1200)); trcMonstrous.add(new Canzone("The Awakening", 1100));
        aggiungiAlbumFinto("The Monstrous Soul", LocalDate.of(1992, 1, 1), "Lustmord", "Dark Ambient", trcMonstrous);

        ArrayList<Canzone> trcSilence = new ArrayList<>();
        trcSilence.add(new Canzone("Quiet Friend", 795)); trcSilence.add(new Canzone("Structures", 1700));
        aggiungiAlbumFinto("Structures from Silence", LocalDate.of(1984, 1, 1), "Steve Roach", "Tribal Ambient", trcSilence);

        ArrayList<Canzone> trcDreamtime = new ArrayList<>();
        trcDreamtime.add(new Canzone("Towards the Dream", 432)); trcDreamtime.add(new Canzone("The Continent", 300));
        aggiungiAlbumFinto("Dreamtime Return", LocalDate.of(1988, 1, 1), "Steve Roach", "Tribal Ambient", trcDreamtime);

        ArrayList<Canzone> trcDs2 = new ArrayList<>();
        trcDs2.add(new Canzone("Thought It Was a Drought", 265)); trcDs2.add(new Canzone("Stick Talk", 170));
        aggiungiAlbumFinto("DS2", LocalDate.of(2015, 7, 17), "Future", "Trap", trcDs2);

        ArrayList<Canzone> trcPluto = new ArrayList<>();
        trcPluto.add(new Canzone("Turn On the Lights", 244)); trcPluto.add(new Canzone("Same Damn Time", 272));
        aggiungiAlbumFinto("Pluto", LocalDate.of(2012, 4, 17), "Future", "Trap", trcPluto);

        ArrayList<Canzone> trcTake = new ArrayList<>();
        trcTake.add(new Canzone("Marvins Room", 347)); trcTake.add(new Canzone("Headlines", 236));
        aggiungiAlbumFinto("Take Care", LocalDate.of(2011, 11, 15), "Drake", "Pop Rap", trcTake);

        ArrayList<Canzone> trcViews = new ArrayList<>();
        trcViews.add(new Canzone("Hotline Bling", 267)); trcViews.add(new Canzone("One Dance", 173));
        aggiungiAlbumFinto("Views", LocalDate.of(2016, 4, 29), "Drake", "Pop Rap", trcViews);

        ArrayList<Canzone> trcFather = new ArrayList<>();
        trcFather.add(new Canzone("Death Letter Blues", 260)); trcFather.add(new Canzone("John the Revelator", 150));
        aggiungiAlbumFinto("Father of Folk Blues", LocalDate.of(1965, 1, 1), "Son House", "Acoustic Blues", trcFather);

        ArrayList<Canzone> trcDelta = new ArrayList<>();
        trcDelta.add(new Canzone("Shetland Pony Blues", 200)); trcDelta.add(new Canzone("Camp Hollers", 180));
        aggiungiAlbumFinto("Delta Blues", LocalDate.of(1941, 1, 1), "Son House", "Acoustic Blues", trcDelta);

        ArrayList<Canzone> trcRocks = new ArrayList<>();
        trcRocks.add(new Canzone("Boogie Rocks", 175)); trcRocks.add(new Canzone("Swanee River", 160));
        aggiungiAlbumFinto("Boogie Rocks", LocalDate.of(1944, 1, 1), "Albert Ammons", "Boogie Woogie", trcRocks);

        ArrayList<Canzone> trcRhythm = new ArrayList<>();
        trcRhythm.add(new Canzone("Shout for Joy", 150)); trcRhythm.add(new Canzone("Bear Cat Crawl", 145));
        aggiungiAlbumFinto("Rhythm Boogie", LocalDate.of(1939, 1, 1), "Albert Ammons", "Boogie Woogie", trcRhythm);
    }

}
