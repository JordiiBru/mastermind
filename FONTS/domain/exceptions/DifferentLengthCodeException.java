package domain.exceptions;

public class DifferentLengthCodeException extends Exception{
    public DifferentLengthCodeException() {
        super("La longitud del código no es correcta.");
    }

}
