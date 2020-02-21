package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.expressions.enums.BooleanOperation;
import domain.types.BooleanType;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.BooleanValue;
import domain.values.Value;

public class LogicExpression implements Expression {
    Expression e1;
    Expression e2;
    BooleanOperation booleanOperation;

    LogicExpression(Expression e1, Expression e2, BooleanOperation op) {
        this.booleanOperation = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException {
        Value value1 = this.e1.evaluate(table, heap);
        if (value1.getType().equals(new BooleanType())) {
            Value value2 = this.e2.evaluate(table, heap);
            if (value2.getType().equals(new BooleanType())) {
                BooleanValue booleanValue1 = (BooleanValue) value1;
                BooleanValue booleanValue2 = (BooleanValue) value2;
                switch (this.booleanOperation) {
                    case OR:
                        return new BooleanValue(booleanValue1.getValue() | booleanValue2.getValue());
                    case AND:
                        return new BooleanValue(booleanValue1.getValue() & booleanValue2.getValue());
                }
            } else throw new IncorrectTypeException("Boolean required, " + value2.getType() + "was given.");
        } else throw new IncorrectTypeException("Boolean required, " + value1.getType() + "was given.");
        return null;
    }

    @Override
    public String toString(){
        if(booleanOperation.equals(BooleanOperation.AND)){
            return e1.toString() + " ^ " + e2.toString();
        }
        return e1.toString() + " | " + e2.toString();
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new BooleanType())) {
            if (type2.equals(new BooleanType())) {
                return new BooleanType();
            } else
                throw new IncorrectTypeException("Operand is not an bool");
        } else
            throw new IncorrectTypeException("Operand is not an bool");
    }

}
