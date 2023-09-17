package domain.exceptions;

public class InvalidPlayerNameException extends Exception {
    public InvalidPlayerNameException() {
        super("Nombre de jugador inválido.");
    }
}
