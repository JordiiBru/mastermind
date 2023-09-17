package domain.exceptions;

public class DifficultyNumberPositionsMismatchException extends Exception {
    public DifficultyNumberPositionsMismatchException() {
        super("El número de posiciones introducido en Dificultad no tiene un valor correcto. El valor debe ser mayor que 0 y menor o igual que 8.");
    }
}
