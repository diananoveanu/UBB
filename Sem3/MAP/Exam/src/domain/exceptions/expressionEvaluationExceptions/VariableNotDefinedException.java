package domain.exceptions.expressionEvaluationExceptions;

import domain.exceptions.GenericException;

public class VariableNotDefinedException extends GenericException {
    public VariableNotDefinedException(String message) {
        super(message);
    }
}
