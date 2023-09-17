package domain.exceptions;

public class FeedbackSecretCodeLengthMismatchException extends Exception {
    public FeedbackSecretCodeLengthMismatchException() {
        super("Desajuste entre la longitud del código secreto y la longitud del código a comparar");
    }
}
