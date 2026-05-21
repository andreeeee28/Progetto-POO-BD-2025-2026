package model;

import java.util.Date;

public class Musicista extends Artista {
    private String nomeVero;
    private String cognonomeVero;
    private Date dataDiNascita;


    public Musicista(String nomeArte, int annoInizioAttività, String idArtista, String nomeVero, String cognomeVero, Date dataDiNascita) throws CampoNonValido {
        super(nomeArte, annoInizioAttività, idArtista);
        this.nomeVero = nomeVero;
        this.cognonomeVero = cognomeVero;
        this.dataDiNascita = dataDiNascita;
    }
}
