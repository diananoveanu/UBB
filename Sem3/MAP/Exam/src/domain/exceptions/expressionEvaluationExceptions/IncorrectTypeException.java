package domain.exceptions.expressionEvaluationExceptions;

import domain.exceptions.GenericException;

public class IncorrectTypeException extends GenericException {
    public IncorrectTypeException(String message) {
        super(message);
    }
}
