package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.stack.MyStack;
import domain.exceptions.GenericException;
import domain.types.Type;
import domain.values.Value;

import java.util.Map;
import java.util.Stack;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
//        IDictionary<String, Value> newSymTable = new MyDictionary<>();
//
//        for (Map.Entry<String, Value> entry : state.getSymbolTable().getMap().entrySet()) {
//            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
//        }
        Stack<IDictionary<String, Value>> newSymTables = new Stack<>();
        state.getSymbolTables().forEach((symTable) -> {
            IDictionary<String, Value> newSymTable = new MyDictionary<>();
            for(Map.Entry<String, Value> entry: symTable.getMap().entrySet()){
                newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
            }
            newSymTables.push(newSymTable);
        });

        ProgramState newPS = new ProgramState(
                new MyStack<>(),
                newSymTables,
                state.getOut(),
                this.statement,
                state.getFileTable(),
                state.getHeapTable(),
                state.getSemaphoreTable(),
                state.getLockTable(),
                state.getBarrierTable(),
                state.getLatchTable(),
                state.getToySemaphoreTable(),
                state.getProcedureTable()
        );

        newPS.setNewId();

        return newPS;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        return statement.typeCheck(typeEnv);
    }


    @Override
    public String toString() {
        return "fork(" + statement + ")";
    }
}