package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class EmptyStackException extends GenericException {
    public EmptyStackException(String message) {
        super(message);
    }
}
