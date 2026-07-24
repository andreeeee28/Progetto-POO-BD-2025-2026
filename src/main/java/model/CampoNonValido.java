package model;

/**
 * Gestisce gli errori nell'inserimento di dati errati.
 */
public class CampoNonValido extends Exception {
    /**
     * Instantiates a new Campo non valido.
     *
     * @param message the message
     */
    public CampoNonValido(String message) {
        super(message);
    }
}
