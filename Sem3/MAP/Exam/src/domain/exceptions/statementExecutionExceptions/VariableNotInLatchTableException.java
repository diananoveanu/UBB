package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class VariableNotInLatchTableException extends GenericException {
    public VariableNotInLatchTableException(String message) {
        super(message);
    }
}
