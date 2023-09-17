package domain.exceptions;

public class LengthCodeTooSmallException extends Exception {
    public LengthCodeTooSmallException() {
        super("El código pasado es mas corto de lo que debería");
    }
}
