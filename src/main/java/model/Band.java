package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Band.
 */
public class Band extends Artista {
    private int numeroMembri;
    private Integer annoScioglimento;
    private ArrayList<MembroBand> membriBand;

    /**
     * Instantiates a new Band.
     *
     * @param nomeArte           the nome arte
     * @param annoInizioAttivita the anno inizio attivita
     * @param idArtista          the id artista
     * @param numeroMembri       the numero membri
     * @param annoScioglimento   the anno scioglimento
     * @param membriband         the membriband
     * @throws CampoNonValido the campo non valido
     */
    public Band(String nomeArte, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento,ArrayList<MembroBand> membriband) throws CampoNonValido {
        super(nomeArte, annoInizioAttivita, idArtista);
        setNumeroMembri(numeroMembri);
        setAnnoScioglimento(annoScioglimento);
        setMembriBand(membriband);

    }

    // Getter

    /**
     * Gets numero membri.
     *
     * @return the numero membri
     */
    public int getNumeroMembri() {
        return numeroMembri;
    }

    /**
     * Gets anno scioglimento.
     *
     * @return the anno scioglimento
     */
    public Integer getAnnoScioglimento() {
        return annoScioglimento;
    }

    /**
     * Gets membri band.
     *
     * @return the membri band
     */
    public ArrayList<MembroBand> getMembriBand() {
        return membriBand;
    }

    // Setter

    /**
     * Sets numero membri.
     *
     * @param numeroMembri the numero membri
     * @throws CampoNonValido the campo non valido
     */
    public void setNumeroMembri(int numeroMembri) throws CampoNonValido {
        // Logicamente, una band deve avere più di un membro
        if (numeroMembri < 2) {
            throw new CampoNonValido("Una band deve avere almeno 2 membri (altrimenti è un solista!).");
        }
        this.numeroMembri = numeroMembri;
    }

    /**
     * Sets anno scioglimento.
     *
     * @param annoScioglimento the anno scioglimento
     * @throws CampoNonValido the campo non valido
     */
    public void setAnnoScioglimento(Integer annoScioglimento) throws CampoNonValido {
        // Se è null, la band è ancora in attività, quindi saltiamo i controlli
        if (annoScioglimento != null) {
            if (annoScioglimento > LocalDate.now().getYear()) {
                throw new CampoNonValido("L'anno di scioglimento non può superare l'anno in corso.");
            }
            // Uso getAnnoInizioAttivita() ereditato dalla classe padre Artista
            if (annoScioglimento < getAnnoInizioAttivita()) {
                throw new CampoNonValido("L'anno di scioglimento non può essere precedente all'anno di inizio attività.");
            }
        }
        this.annoScioglimento = annoScioglimento;
    }


    /**
     * Sets membri band.
     *
     * @param membriBand the membri band
     * @throws CampoNonValido the campo non valido
     */
    public  void setMembriBand(ArrayList<MembroBand> membriBand) throws CampoNonValido{
        if (membriBand == null) {
            throw new CampoNonValido("la lista dei membi della band non può essere nulla.");
        }
        if(membriBand.size()<2){

            throw new CampoNonValido("la band deve avere almeno 2 membri iniziali.");
        }
        this.membriBand = membriBand;
    }

    //Altri metodi

    /**
     * Add membro band.
     *
     * @param membroBand the membro band
     * @throws CampoNonValido the campo non valido
     */
    public void addMembroBand(MembroBand membroBand) throws CampoNonValido {
        // Aggiunto il controllo di sicurezza anche qui
        if (membroBand == null) {
            throw new CampoNonValido("Il membro della band da aggiungere non può essere nullo.");
        }
        if(!membriBand.contains(membroBand)) {
            this.membriBand.add(membroBand);
        } else{
            throw new CampoNonValido("Membro della band già presente nella lista dei membri");
        }
    }
    @Override
    public String toString() {
        return this.getNomeArte();
    }


    }

