package domain.exceptions.expressionEvaluationExceptions;

import domain.exceptions.GenericException;

public class DivisionByZeroException extends GenericException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
