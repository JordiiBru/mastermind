package domain.exceptions;

public class GameNotStartedException extends Exception {
    public GameNotStartedException() {
        super("El juego aún no ha empezado.");
    }
}
