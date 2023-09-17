package domain.exceptions;

public class NotEmptyColorsException extends Exception{
    public NotEmptyColorsException() {
        super("No se permite introducir colores vacíos en el código.");
    }
}
