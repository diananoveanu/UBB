package domain.statements.exam.latch;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.exceptions.statementExecutionExceptions.VariableNotInLatchTableException;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

public class AwaitLatchStatement implements IStatement {
    private String var;

    public AwaitLatchStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if(state.getSymbolTable().containsKey(var)){
            Value varValue = state.getSymbolTable().get(var);
            if(varValue.getType().equals(new IntegerType())){
                Integer intVal = ((IntegerValue)varValue).getValue();
                if(state.getLatchTable().containsKey(intVal)){
                    Integer latchIndex = state.getLatchTable().get(intVal);
                    if(latchIndex != 0){
                        state.getExecutionStack().push(this);
                    }
                }else{
                    throw new VariableNotInLatchTableException(var + " is not in Latch table");
                }
            }else{
                throw new UnmatchedTypesExpression(var + " is not of type int");
            }
        }else{
            throw new VariableNotDeclaredException(var + " is not declared");
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if(typeEnv.get(var).equals(new IntegerType())){
            return typeEnv;
        }else{
            throw new UnmatchedTypesExpression(var + " is not of type Integer!");
        }
    }

    @Override
    public String toString(){
        return "await( " + var + " )";
    }
}
