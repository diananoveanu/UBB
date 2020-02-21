package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class VariableAlreadyDeclaredException extends GenericException {
    public VariableAlreadyDeclaredException(String message) {
        super(message);
    }
}
