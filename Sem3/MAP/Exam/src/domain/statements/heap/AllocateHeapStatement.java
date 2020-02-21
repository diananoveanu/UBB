package domain.statements.heap;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.ReferenceType;
import domain.types.Type;
import domain.values.ReferenceValue;
import domain.values.Value;


public class AllocateHeapStatement implements IStatement {
    private String varName;
    Expression expr;

    public AllocateHeapStatement(String varName, Expression expr) {
        this.varName = varName;
        this.expr = expr;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        IDictionary<String, Value> table = state.getSymbolTable();

        if (table.containsKey(varName)) {
            if (table.get(varName).getType() instanceof ReferenceType) {
                Value val = expr.evaluate(table, state.getHeapTable());
                Value tabVal = table.get(varName);
                if (val.getType().equals(((ReferenceValue) tabVal).getLocationType())) {
                    int addr = state.getHeapTable().allocate(val);
                    table.put(varName, new ReferenceValue(val.getType(), addr));
                    return null;
                } else
                    throw new IncorrectTypeException("Given expression does not match the symbol type.");

            } else throw new IncorrectTypeException("Symbol is not a reference of an instance.");
        } else
            throw new VariableNotDeclaredException("Cannot allocate on a undeclared symbol.");
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeVar = typeEnv.get(varName);
        Type typeExp = expr.typeCheck(typeEnv);
        if (typeVar.equals(new ReferenceType(typeExp))) return typeEnv;
        else
            throw new GenericException("Heap Allocation Statement: right hand side and left hand side have different types.");
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + expr.toString() + ")";
    }

}
