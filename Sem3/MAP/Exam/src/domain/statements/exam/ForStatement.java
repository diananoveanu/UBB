package domain.statements.exam;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.*;
import domain.expressions.enums.ArithmeticOperation;
import domain.expressions.enums.RelationalOperation;
import domain.statements.*;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;

public class ForStatement implements IStatement {
    Expression expression1;
    Expression expression2;
    Expression expression3;
    IStatement statement;

    public ForStatement(Expression expression1, Expression expression2, Expression expression3, IStatement statement) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (expression1.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new IntegerType())) {
            if (expression2.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new IntegerType())) {
                if (expression3.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new IntegerType())) {
                    state.getExecutionStack().push(new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                            new CompoundStatement(
                                    // v = expression1
                                    new AssignStatement("v", expression1),
                                    new WhileStatement(
                                            // while (v < expression2)
                                            new RelationalExpression(
                                                    new VariableExpression("v"), expression2, RelationalOperation.Lower),
                                            //statement;
                                            //v = v + 1
                                            new CompoundStatement(statement,
                                                    new AssignStatement("v",
                                                            new ArithmeticExpression(
                                                                    new VariableExpression("v"), expression3, ArithmeticOperation.ADDITION)))
                                    ))));

                } else
                    throw new UnmatchedTypesExpression("Expression " + expression3.toString() + "is not of type Integer");
            } else
                throw new UnmatchedTypesExpression("Expression " + expression2.toString() + "is not of type Integer");
        } else throw new UnmatchedTypesExpression("Expression " + expression1.toString() + "is not of type Integer");
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if (expression1.typeCheck(typeEnv).equals(new IntegerType())) {
            if (expression2.typeCheck(typeEnv).equals(new IntegerType())) {
                if (expression3.typeCheck(typeEnv).equals(new IntegerType())) {
                    new VariableDeclarationStatement("v", new IntegerType()).typeCheck(typeEnv);
                    statement.typeCheck(typeEnv);
                    return typeEnv;
                } else
                    throw new UnmatchedTypesExpression("Expression " + expression3.toString() + "is not of type Integer");
            } else
                throw new UnmatchedTypesExpression("Expression " + expression2.toString() + "is not of type Integer");
        } else throw new UnmatchedTypesExpression("Expression " + expression1.toString() + "is not of type Integer");
    }

    @Override
    public String toString() {
        return "for(" +
                "v=" + expression1 +
                ", v<" + expression2 +
                ", v=v +" + expression3 +
                ")\t{" + statement +
                '}';
    }
}
