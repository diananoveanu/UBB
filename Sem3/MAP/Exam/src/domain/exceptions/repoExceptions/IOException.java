package domain.exceptions.repoExceptions;

import domain.exceptions.GenericException;

public class IOException extends GenericException {
    public IOException(String message) {
        super(message);
    }
}
