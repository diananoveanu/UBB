package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.types.BooleanType;
import domain.types.Type;
import domain.values.BooleanValue;
import domain.values.Value;

public class WhileStatement implements IStatement {
    Expression e;
    IStatement s;

    public WhileStatement(Expression v, IStatement compoundStatement) {
        this.e = v;
        this.s = compoundStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        IDictionary<String, Value> symTable = state.getSymbolTable();
        IHeap<Value> heapTable = state.getHeapTable();

        Value exprValue = e.evaluate(symTable, heapTable);
        if (exprValue.getType().equals(new BooleanType())) {
            if (exprValue.equals(new BooleanValue(true))) {
                state.getExecutionStack().push(new WhileStatement(e, s));
                state.getExecutionStack().push(s);
                return null;
            }
        } else {
            throw new IncorrectTypeException("Expresion is not bool!");
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = e.typeCheck(typeEnv);
        if (typeExp.equals(new BooleanType())) {
            s.typeCheck(typeEnv.cloneDict());
            return typeEnv;
        } else
            throw new UnmatchedTypesExpression("The condition of WHILE doesn't have the type bool");
    }

    @Override
    public String toString() {
        return "while( " + e.toString() + " ) { " + s.toString() + " }";
    }

}
