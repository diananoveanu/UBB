package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.types.BooleanType;
import domain.types.Type;
import domain.values.BooleanValue;

public class IfStatement implements IStatement {
    IStatement statement1;
    IStatement statement2;
    Expression condition;

    public IfStatement(Expression a, IStatement assignStatement, IStatement assignStatement1) {
        this.statement1 = assignStatement;
        this.statement2 = assignStatement1;
        this.condition = a;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (condition.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new BooleanType())) {
            BooleanValue value = (BooleanValue) condition.evaluate(state.getSymbolTable(), state.getHeapTable());
            if (value.getValue()) {
                state.getExecutionStack().push(statement1);
            } else state.getExecutionStack().push(statement2);
        } else throw new IncorrectTypeException("Conditional expression is not a boolean");
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typexp = condition.typeCheck(typeEnv);
        if (typexp.equals(new BooleanType())) {
            statement1.typeCheck(typeEnv.cloneDict());
            statement2.typeCheck(typeEnv.cloneDict());
            return typeEnv;
        } else throw new UnmatchedTypesExpression("The condition of IF doesn't have the type bool");
    }


    @Override
    public String toString() {
        return "if(" + condition.toString() + ") then\t\n\t(" + statement1.toString()
                + ");\nelse\n\t(" + statement2.toString() + ")";
    }
}
