package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class FunctionNotDeclaredException extends GenericException {
    public FunctionNotDeclaredException(String message) {
        super(message);
    }
}
