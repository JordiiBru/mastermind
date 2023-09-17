package domain.exceptions;

/**
 * Exception when a variable is not defined or is called and it returns a wrong value when communicating with other classes
 */
public class VariableNotDefinedException extends Exception {
    public VariableNotDefinedException() {
        super("Variable inválida o no definida.");
    }
}
