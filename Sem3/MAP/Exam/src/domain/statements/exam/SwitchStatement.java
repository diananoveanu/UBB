package domain.statements.exam;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.BooleanType;
import domain.types.Type;

public class SwitchStatement implements IStatement {
    Expression expression;
    Expression expression1;
    Expression expression2;
    IStatement statement1;
    IStatement statement2;
    IStatement statement3;

    public SwitchStatement(Expression expression, Expression expression1, Expression expression2, IStatement statement1, IStatement statement2, IStatement statement3) {
        this.expression = expression;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    //switch(exp) (case exp1: stmt1) (case exp2: stmt2) (default: stmt3)
    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (expression.evaluate(state.getSymbolTable(), state.getHeapTable()).equals(expression1.evaluate(state.getSymbolTable(), state.getHeapTable()))) {
            state.getExecutionStack().push(statement1);
        } else if (expression.evaluate(state.getSymbolTable(), state.getHeapTable()).equals(expression2.evaluate(state.getSymbolTable(), state.getHeapTable()))) {
            state.getExecutionStack().push(statement2);
        } else {
            state.getExecutionStack().push(statement3);
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type t1 = expression.typeCheck(typeEnv);
        Type t2 = expression1.typeCheck(typeEnv);
        Type t3 = expression2.typeCheck(typeEnv);

        if (t1.equals(t2) && t1.equals(t3)) {
            statement1.typeCheck(typeEnv);
            statement2.typeCheck(typeEnv);
            statement3.typeCheck(typeEnv);
            return typeEnv;
        } else throw new UnmatchedTypesExpression("The expressions are not of the same type");
    }

    @Override
    public java.lang.String toString() {
        return "switch(" +
                "if(" + expression +
                "==" + expression1 +
                "{" + statement1 + "}" +
                "else if(" + expression +
                "==" + expression2 +
                "{" + statement2 + "}" +
                "else{" + statement3 + "}" +
                ')';
    }
}
