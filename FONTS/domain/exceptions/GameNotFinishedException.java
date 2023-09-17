package domain.exceptions;

public class GameNotFinishedException extends Exception {
    public GameNotFinishedException() {
        super("El juego aún no ha terminado.");
    }
}
