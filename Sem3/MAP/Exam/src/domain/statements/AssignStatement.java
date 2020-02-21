package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.expressions.Expression;
import domain.types.Type;
import domain.values.Value;

public class AssignStatement implements IStatement {
    String id;
    Expression e1;

    public AssignStatement(String v, Expression valueExpression) {
        this.id = v;
        this.e1 = valueExpression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (state.getSymbolTable().containsKey(id)) {
            Value value = e1.evaluate(state.getSymbolTable(), state.getHeapTable());
            if (value.getType().equals(state.getSymbolTable().get(id).getType())) {
                state.getSymbolTable().put(id, value);
            } else
                throw new UnmatchedTypesExpression("Expression " + e1.toString() + " and type" + state.getSymbolTable().get(id).getType() + " do not match.");
        } else throw new VariableNotDeclaredException("Variable" + id + " not declared");
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typevar = typeEnv.get(id);
        Type typexp = e1.typeCheck(typeEnv);
        if (typevar.equals(typexp)) return typeEnv;
        else
            throw new UnmatchedTypesExpression("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return id + " = " + e1.toString();
    }
}
