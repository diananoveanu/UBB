package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.types.Type;
import domain.values.Value;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException {
        return value;
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
