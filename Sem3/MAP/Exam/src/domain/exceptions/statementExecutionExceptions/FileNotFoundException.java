package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class FileNotFoundException extends GenericException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
