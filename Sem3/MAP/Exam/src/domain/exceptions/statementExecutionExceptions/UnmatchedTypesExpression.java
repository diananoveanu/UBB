package domain.exceptions.statementExecutionExceptions;

import domain.exceptions.GenericException;

public class UnmatchedTypesExpression extends GenericException {
    public UnmatchedTypesExpression(String message) {
        super(message);
    }
}
