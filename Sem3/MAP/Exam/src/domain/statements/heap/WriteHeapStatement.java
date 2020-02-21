package domain.statements.heap;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.expressionEvaluationExceptions.VariableNotDefinedException;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.ReferenceType;
import domain.types.Type;
import domain.values.ReferenceValue;
import domain.values.Value;

public class WriteHeapStatement implements IStatement {
    String variableName;
    Expression e;

    public WriteHeapStatement(String variableName, Expression e) {
        this.variableName = variableName;
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        IDictionary<String, Value> symTabel = state.getSymbolTable();
        IHeap<Value> heap = state.getHeapTable();
        if (symTabel.containsKey(variableName)) {
            Value fromSymTable = symTabel.get(variableName);
            if (fromSymTable.getType() instanceof ReferenceType) {
                ReferenceValue ref = (ReferenceValue) fromSymTable;
                if (heap.get(ref.getAddress()) != null) {
                    Value evalExp = e.evaluate(symTabel, heap);
                    if (evalExp.getType().equals(((ReferenceType) ref.getType()).getInner())) {
                        heap.put(ref.getAddress(), evalExp);
                        return null;
                    } else {
                        //System.out.println("=========== " + evalExp.getType() + " ======= " + ref.getType());
                        throw new IncorrectTypeException("Types don't match!");
                    }
                } else {
                    throw new VariableNotDefinedException(ref.getAddress() + " is not a valid address in heap!");
                }
            } else {
                throw new IncorrectTypeException(variableName + " is not of type Ref");
            }
        } else {
            throw new VariableNotDeclaredException(variableName + " is not declared");
        }
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeVar = typeEnv.get(variableName);
        Type typeExp = e.typeCheck(typeEnv);
        if (new ReferenceType(typeExp).equals(typeVar)) return typeEnv;
        else throw new GenericException("Write heap: left side is not of type ref ");
    }

    @Override
    public String toString() {
        return "wH(" + variableName + ", " + e.toString() + ")";
    }

}
