package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Album {

    private String titolo;
    private Date dataPubblicazione;
    private float voto;
    private Artista artista;
    private ArrayList<Canzone> tracklist;
    private ArrayList<Genere> generi;
    private ArrayList<Recensione> recensioni;

    //Costruttore
    public Album(String titolo, Date dataPubblicazione, Artista artista, Genere genere, int noCanzoni){
        Scanner kbdInput = new Scanner(System.in);

        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.artista = artista;
        artista.aggiungiAlbum(this);;
        generi.add(genere);

        String titoloCanzone;
        int durataCanzone;
        for(int i = 0; i<noCanzoni; i++){
            System.out.println("Digitare il titolo della traccia: ");
            titoloCanzone = kbdInput.nextLine();
            System.out.println("Digitare la durata della traccia: ");
            durataCanzone = kbdInput.nextInt();
            tracklist.add(new Canzone(titoloCanzone, durataCanzone));
        }
    }



}
