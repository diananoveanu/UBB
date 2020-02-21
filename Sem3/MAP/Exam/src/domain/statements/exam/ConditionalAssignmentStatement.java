package domain.statements.exam;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.statements.*;
import domain.types.BooleanType;
import domain.types.Type;

public class ConditionalAssignmentStatement implements IStatement {
    Expression expression1;
    Expression expression2;
    Expression expression3;
    String v;

    public ConditionalAssignmentStatement(String v, Expression expression1, Expression expression2, Expression expression3) {
        this.v = v;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (expression1.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new BooleanType())) {
            if (expression2.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(expression3.evaluate(state.getSymbolTable(), state.getHeapTable()).getType())) {
                state.getExecutionStack().push(new IfStatement(expression1, new AssignStatement(v, expression2), new AssignStatement(v, expression3)));
            }
        } else throw new UnmatchedTypesExpression("Expression " + expression1.toString() + "is not of type Boolean");
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if (expression1.typeCheck(typeEnv).equals(new BooleanType())) {
//            if (expression2.typeCheck(typeEnv).equals(new IntegerType())) {
//                if (expression3.typeCheck(typeEnv).equals(new IntegerType())) {
            return typeEnv;
//                } else
//                    throw new UnmatchedTypesExpression("Expression " + expression3.toString() + "is not of type Integer");
//            } else
//                throw new UnmatchedTypesExpression("Expression " + expression2.toString() + "is not of type Integer");
        } else throw new UnmatchedTypesExpression("Expression " + expression1.toString() + "is not of type Boolean");
    }

    @Override
    public String toString() {
        return "{" + this.v + "=" + expression1 + "?" + expression2 + ":" + expression3 + '}';
    }
}
