package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class SemaphoreLocationNotFoundException extends GenericException {
    public SemaphoreLocationNotFoundException(String message) {
        super(message);
    }
}
