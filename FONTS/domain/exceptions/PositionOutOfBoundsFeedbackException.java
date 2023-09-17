package domain.exceptions;

public class PositionOutOfBoundsFeedbackException extends Exception {
    public PositionOutOfBoundsFeedbackException() {
        super("Turno de acceso inválido.");
    }
}
