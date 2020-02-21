package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.types.Type;
import domain.values.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException;
    Type typeCheck(IDictionary<String, Type> typeEnv) throws GenericException;
}
