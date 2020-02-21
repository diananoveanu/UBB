package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.expressions.enums.RelationalOperation;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.BooleanValue;
import domain.values.IntegerValue;
import domain.values.Value;

public class RelationalExpression implements Expression {
    Expression e1;
    Expression e2;
    RelationalOperation op;

    public RelationalExpression(Expression e1, Expression e2, RelationalOperation op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException {
        Value value1 = e1.evaluate(table, heap);
        if (value1.getType().equals(new IntegerType())) {
            Value value2 = e2.evaluate(table, heap);
            if (value2.getType().equals(new IntegerType())) {
                IntegerValue integerValue1 = (IntegerValue) value1;
                IntegerValue integerValue2 = (IntegerValue) value2;
                switch (op) {
                    case GreaterOrEqual:
                        return new BooleanValue(integerValue1.getValue() >= integerValue2.getValue());
                    case Greater:
                        return new BooleanValue(integerValue1.getValue() > integerValue2.getValue());
                    case Equal:
                        return new BooleanValue(integerValue1.getValue().equals(integerValue2.getValue()));
                    case Different:
                        return new BooleanValue(!integerValue1.getValue().equals(integerValue2.getValue()));
                    case Lower:
                        return new BooleanValue(integerValue1.getValue() < integerValue2.getValue());
                    case LowerOrEqual:
                        return new BooleanValue(integerValue1.getValue() <= integerValue2.getValue());
                }
            } else throw new IncorrectTypeException("Value is not of type Integer, but " + value2.getType());

        } else throw new IncorrectTypeException("Value is not of type Integer, but " + value1.getType());

        return null;
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new IntegerType())) {
            if (type2.equals(new IntegerType())) {
                return new IntegerType();
            } else
                throw new IncorrectTypeException("Operand is not an integer");
        } else
            throw new IncorrectTypeException("Operand is not an integer");
    }

    @Override
    public String toString() {
        switch (op) {
            case GreaterOrEqual:
                return e1.toString() + " >= " + e2.toString();
            case Greater:
                return e1.toString() + " > " + e2.toString();
            case Equal:
                return e1.toString() + " == " + e2.toString();
            case Different:
                return e1.toString() + " != " + e2.toString();
            case Lower:
                return e1.toString() + " < " + e2.toString();
            case LowerOrEqual:
                return e1.toString() + " <= " + e2.toString();
        }
        return "";

    }
}
