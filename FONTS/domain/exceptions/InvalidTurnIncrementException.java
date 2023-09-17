package domain.exceptions;

public class InvalidTurnIncrementException extends Exception {
    public InvalidTurnIncrementException() {
        super("Error al incrementar el contador de turnos.");
    }
}
