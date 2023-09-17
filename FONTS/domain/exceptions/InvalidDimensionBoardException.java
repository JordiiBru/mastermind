package domain.exceptions;

public class InvalidDimensionBoardException extends Exception {
    public InvalidDimensionBoardException() {
        super("Dimensiones incorrectas del tablero.");
    }
}
