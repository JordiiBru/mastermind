package domain.exceptions;

public class IncorrectColorException extends Exception {
    public IncorrectColorException() {
        super("El color escogido no es válido.");
    }
}
