package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class VariableNotDeclaredException extends GenericException {
    public VariableNotDeclaredException(String message) {
        super(message);
    }
}
