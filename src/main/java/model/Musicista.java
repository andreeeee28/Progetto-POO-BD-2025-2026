package model;

import java.time.LocalDate;


public class Musicista extends Artista {
    private String nomeVero;
    private String cognonomeVero;
    private LocalDate dataDiNascita;


    public Musicista(String nomeArte, int annoInizioAttivita, String idArtista, String nomeVero, String cognomeVero, LocalDate dataDiNascita) throws CampoNonValido {
        super(nomeArte, annoInizioAttivita, idArtista);
        setNomeVero(nomeVero);
        setCognonomeVero(cognomeVero);
        setDataDiNascita(dataDiNascita);
    }

    public String getNomeVero() {
        return nomeVero;
    }

    public void setNomeVero(String nomeVero) throws CampoNonValido {
        if(nomeVero == null ||  nomeVero.trim().length()<1 || nomeVero.trim().length()>15){
            throw new CampoNonValido("Il Nome deve avere minimo 1 carattere e massimo 15!");
        }
        this.nomeVero = nomeVero;
    }

    public String getCognonomeVero() {
        return cognonomeVero;
    }

    public void setCognonomeVero(String cognonomeVero) throws CampoNonValido {
        if(cognonomeVero == null ||  cognonomeVero.trim().length()<1 || cognonomeVero.trim().length()>30){
            throw new CampoNonValido("Il Cognome deve avere minimo 1 carattere e massimo 30!");
        }
        this.cognonomeVero = cognonomeVero;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) throws CampoNonValido {
        if (dataDiNascita.getYear() > LocalDate.now().getYear()){
            throw new CampoNonValido("L'anno di nascita non può superare l'anno in corso.");
        }
        if (dataDiNascita.getYear() < 1900){
            throw new CampoNonValido("L'anno di nascita inserito non è valido.");
        }
        this.dataDiNascita = dataDiNascita;
    }
}
