package domain.exceptions;

public class InvalidStartingOptionException extends Exception {
    public InvalidStartingOptionException() {
        super("Escoge una opción de las ofertadas, por favor :)");
    }
}
