package domain.exceptions;

public class InvalidCodeSizeException extends Exception {
    public InvalidCodeSizeException() {
        super("Longitud de código incorrecto.");
    }
}
