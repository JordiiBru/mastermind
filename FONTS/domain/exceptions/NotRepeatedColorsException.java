package domain.exceptions;

public class NotRepeatedColorsException extends Exception {
    public NotRepeatedColorsException() {
        super("No se permite poner colores repetidos en el código.");
    }
}
