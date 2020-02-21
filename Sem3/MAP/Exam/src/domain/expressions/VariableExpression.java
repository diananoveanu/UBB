package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.types.Type;
import domain.values.Value;

public class VariableExpression implements Expression {
    String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException {
        return table.get(id);
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        return typeEnv.get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
