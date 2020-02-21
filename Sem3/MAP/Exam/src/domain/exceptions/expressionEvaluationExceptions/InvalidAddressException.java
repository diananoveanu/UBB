package domain.exceptions.expressionEvaluationExceptions;

import domain.exceptions.GenericException;

public class InvalidAddressException extends GenericException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
