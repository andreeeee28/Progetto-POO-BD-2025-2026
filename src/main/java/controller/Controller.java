package controller;

import model.*;

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
   public void creaArtisti() throws CampoNonValido {
       // --- ROCK ---
       Artista beatles = new Artista("The Beatles", 1960, "ART-R01");
       Artista stones = new Artista("The Rolling Stones", 1962, "ART-R02");
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


       // ==========================================
       // 💾 SALVATAGGIO NEL "DATABASE" (ARRAYLIST)
       // ==========================================

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

   }
   public void cliccatoAccedi(String campoNomeUtente, String campoPassword) throws CampoNonValido{
        for(Utente utente : utentiRegistrati ){
            String passwordUtente = utente.getPassword();
            String nomeUtente = utente.getUsername();
            if(passwordUtente.equals(campoPassword) && nomeUtente.equals(campoNomeUtente)) {

                return;
            }

        }
       throw new CampoNonValido("ERRORE! Credenziali non valide");

   }


}
