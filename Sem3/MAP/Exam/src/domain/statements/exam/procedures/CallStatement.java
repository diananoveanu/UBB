package domain.statements.exam.procedures;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.utils.ProcedurePair;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.FunctionNotDeclaredException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.Type;
import domain.values.Value;

import java.util.ArrayList;
import java.util.List;

public class CallStatement implements IStatement {
    private List<Expression> params;
    private String fName;

    public CallStatement(String fName, ArrayList<Expression> params){
        this.fName = fName;
        this.params = params;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if(state.getProcedureTable().containsKey(fName)){
            IDictionary<String, Value> newSymTable = new MyDictionary<>();
            ProcedurePair pair = state.getProcedureTable().get(fName);
            for(int i = 0; i<params.size(); i++){
                newSymTable.put(pair.getKey().get(i), params.get(i).evaluate(state.getSymbolTable(), state.getHeapTable()));
            }
            state.getSymbolTables().push(newSymTable);
            state.getExecutionStack().push(new ReturnStatement());
            state.getExecutionStack().push(pair.getValue());
        }else{
            throw new FunctionNotDeclaredException("Procedure " + fName + " is not declared");
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        return null;
    }

    @Override
    public String toString() {
        return "call " + fName + "( " + params.toString() + ")";
    }
}
