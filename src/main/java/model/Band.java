package model;

import java.util.ArrayList;

public class Band extends Artista {
    private int numeroMembri;
    private int annoScioglimento;
    private ArrayList<MembroBand> membriBand;

    public Band(String nomeArte, int annoInizioAttivita, String idArtista, int numeroMembri, int annoScioglimento) throws CampoNonValido {
        super(nomeArte, annoInizioAttivita, idArtista);
        this.numeroMembri = numeroMembri;
        this.annoScioglimento = annoScioglimento;
        this.membriBand = new ArrayList<MembroBand>();

    }

    public int getNumeroMembri() {
        return numeroMembri;
    }

    public void setNumeroMembri(int numeroMembri) {
        this.numeroMembri = numeroMembri;
    }

    public int getAnnoScioglimento() {
        return annoScioglimento;
    }

    public void setAnnoScioglimento(int annoScioglimento) {
        this.annoScioglimento = annoScioglimento;
    }

    public void addMembroBand(MembroBand membroBand){
        this.membriBand.add(membroBand);
    }
}
