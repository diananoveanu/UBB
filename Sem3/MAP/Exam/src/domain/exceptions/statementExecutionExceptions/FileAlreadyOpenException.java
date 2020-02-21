package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class FileAlreadyOpenException extends GenericException {
    public FileAlreadyOpenException(String message) {
        super(message);
    }
}
