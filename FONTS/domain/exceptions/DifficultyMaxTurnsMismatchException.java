package domain.exceptions;

public class DifficultyMaxTurnsMismatchException extends Exception {
    public DifficultyMaxTurnsMismatchException() {
        super("Los turnos máximos introducidos en Dificultad no tienen un valor correcto. El valor debe ser mayor que 0 y menor o igual que 10.");
    }
}
