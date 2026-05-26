package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Band extends Artista {
    private int numeroMembri;
    private Integer annoScioglimento; // Modificato in Integer per permettere il valore null
    private ArrayList<MembroBand> membriBand;

    public Band(String nomeArte, int annoInizioAttivita, String idArtista, int numeroMembri, Integer annoScioglimento) throws CampoNonValido {
        super(nomeArte, annoInizioAttivita, idArtista);
        setNumeroMembri(numeroMembri);
        setAnnoScioglimento(annoScioglimento);
        this.membriBand = new ArrayList<>();
    }

    public int getNumeroMembri() {
        return numeroMembri;
    }

    public void setNumeroMembri(int numeroMembri) throws CampoNonValido {
        // Logicamente, una band deve avere più di un membro
        if (numeroMembri < 2) {
            throw new CampoNonValido("Una band deve avere almeno 2 membri (altrimenti è un solista!).");
        }
        this.numeroMembri = numeroMembri;
    }

    public Integer getAnnoScioglimento() {
        return annoScioglimento;
    }

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

    public ArrayList<MembroBand> getMembriBand() {
        return membriBand;
    }

    public void addMembroBand(MembroBand membroBand) throws CampoNonValido {
        // Aggiunto il controllo di sicurezza anche qui
        if (membroBand == null) {
            throw new CampoNonValido("Il membro della band non può essere nullo.");
        }
        this.membriBand.add(membroBand);
    }
}
