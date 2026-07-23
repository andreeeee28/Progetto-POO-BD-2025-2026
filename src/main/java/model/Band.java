package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Rappresenta un gruppo musicale (Band) all'interno del sistema.
 * Estende la classe astratta Artista aggiungendo le caratteristiche specifiche di una band,
 * come il numero totale dei componenti, l'eventuale anno di scioglimento e la lista dei membri (MembroBand).
 */
public class Band extends Artista {
    private int numeroMembri;
    private Integer annoScioglimento;
    private ArrayList<MembroBand> membriBand;

    /**
     * Istanzia un nuovo oggetto Band assegnando i parametri specifici e passando quelli base alla superclasse Artista.
     *
     * @param nomeArte Il nome d'arte della band (tra 1 e 30 caratteri).
     * @param annoInizioAttivita L'anno di fondazione della band.
     * @param idArtista L'identificativo alfanumerico univoco associato alla band.
     * @param numeroMembri Il numero totale dei componenti della band (deve essere almeno 2).
     * @param annoScioglimento L'eventuale anno di scioglimento della band (null se ancora in attività).
     * @param membriband La lista iniziale dei membri che compongono la band.
     * @throws CampoNonValido Se uno dei parametri non rispetta i vincoli di validazione (es. meno di 2 membri o anno incoerente).
     */
    public Band(String nomeArte, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento,ArrayList<MembroBand> membriband) throws CampoNonValido {
        super(nomeArte, annoInizioAttivita, idArtista);
        setNumeroMembri(numeroMembri);
        setAnnoScioglimento(annoScioglimento);
        setMembriBand(membriband);

    }

    // Getter

    /**
     * Restituisce il numero totale dei componenti della band.
     *
     * @return Il numero dei membri.
     */
    public int getNumeroMembri() {
        return numeroMembri;
    }

    /**
     * Restituisce l'anno in cui la band si è sciolta.
     *
     * @return L'anno di scioglimento, oppure null se la band è ancora attiva.
     */
    public Integer getAnnoScioglimento() {
        return annoScioglimento;
    }

    /**
     * Restituisce la lista dei membri che fanno o hanno fatto parte della band.
     *
     * @return L'ArrayList contenente gli oggetti MembroBand.
     */
    public ArrayList<MembroBand> getMembriBand() {
        return membriBand;
    }

    // Setter

    /**
     * Imposta o modifica il numero totale dei componenti della band.
     *
     * @param numeroMembri Il nuovo numero di membri da assegnare.
     * @throws CampoNonValido Se il numero inserito è inferiore a 2.
     */
    public void setNumeroMembri(int numeroMembri) throws CampoNonValido {
        // Logicamente, una band deve avere più di un membro
        if (numeroMembri < 2) {
            throw new CampoNonValido("Una band deve avere almeno 2 membri (altrimenti è un solista!).");
        }
        this.numeroMembri = numeroMembri;
    }

    /**
     * Imposta o modifica l'anno di scioglimento della band.
     *
     * @param annoScioglimento L'anno di scioglimento da assegnare (può essere null se ancora attivi).
     * @throws CampoNonValido Se l'anno è successivo a quello corrente o precedente all'anno di inizio attività.
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
     * Sostituisce l'intera lista dei componenti della band con quella fornita.
     *
     * @param membriBand L'ArrayList di oggetti MembroBand da impostare.
     * @throws CampoNonValido Se la lista fornita è nulla o contiene meno di 2 membri.
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
     * Aggiunge un singolo componente alla lista dei membri della band, verificando che non sia un duplicato.
     *
     * @param membroBand L'oggetto MembroBand da aggiungere.
     * @throws CampoNonValido Se il membro passato è nullo o è già presente nella lista.
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

    /**
     * Restituisce una rappresentazione testuale della band.
     *
     * @return Il nome d'arte della band.
     */
    @Override
    public String toString() {
        return this.getNomeArte();
    }
}