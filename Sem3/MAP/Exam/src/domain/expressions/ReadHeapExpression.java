package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.expressionEvaluationExceptions.InvalidAddressException;
import domain.types.ReferenceType;
import domain.types.Type;
import domain.values.ReferenceValue;
import domain.values.Value;

public class ReadHeapExpression implements Expression {
    Expression e;

    public ReadHeapExpression(Expression e) {
        this.e = e;

    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException {
        Value ref = e.evaluate(table, heap);

        if (ref instanceof ReferenceValue) {
            int addr = ((ReferenceValue) ref).getAddress();
            if (heap.get(addr) != null) {
                return heap.get(addr);
            } else
                throw new InvalidAddressException("Invalid address.");
        } else
            throw new IncorrectTypeException("Expression does not represent a reference in heap.");
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typ = e.typeCheck(typeEnv);
        if (typ instanceof ReferenceType) {
            ReferenceType reft = (ReferenceType) typ;
            return reft.getInner();
        } else
            throw new IncorrectTypeException("The ReadHeap argument is not a Reference Type");
    }

    @Override
    public String toString() {
        return "rH(" + e.toString() + ")";
    }

}
