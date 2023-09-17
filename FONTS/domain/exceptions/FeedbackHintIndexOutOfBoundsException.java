package domain.exceptions;

public class FeedbackHintIndexOutOfBoundsException extends Exception {
    public FeedbackHintIndexOutOfBoundsException() {
        super("La posición del índice de la pista es mayor o menor que la longitud de secret_code.");
    }
}
