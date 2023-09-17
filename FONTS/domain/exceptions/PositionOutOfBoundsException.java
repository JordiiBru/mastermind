package domain.exceptions;

public class PositionOutOfBoundsException extends Exception{
    public PositionOutOfBoundsException() {
        super("La posición está fuera del rango permitido.");
    }
}
