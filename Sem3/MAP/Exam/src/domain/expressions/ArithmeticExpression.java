package domain.expressions;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.DivisionByZeroException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.expressions.enums.ArithmeticOperation;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

public class ArithmeticExpression implements Expression {
    Expression e1;
    Expression e2;
    ArithmeticOperation arithmeticOperation;

    public ArithmeticExpression(Expression e1, Expression e2, ArithmeticOperation op) {
        this.e1 = e1;
        this.e2 = e2;
        this.arithmeticOperation = op;
    }


    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws GenericException {
        Value value1, value2;
        value1 = this.e1.evaluate(table, heap);

        if (value1.getType().equals(new IntegerType())) {
            value2 = this.e2.evaluate(table, heap);
            if (value2.getType().equals(new IntegerType())) {
                IntegerValue integerValue1 = (IntegerValue) value1;
                IntegerValue integerValue2 = (IntegerValue) value2;

                switch (this.arithmeticOperation) {
                    case ADDITION:
                        return new IntegerValue(integerValue1.getValue() + integerValue2.getValue());
                    case DIVISION:
                        if (integerValue2.getValue().equals(0))
                            throw new DivisionByZeroException("Cannot divide by 0.");
                        return new IntegerValue(integerValue1.getValue() / integerValue2.getValue());
                    case SUBTRACTION:
                        return new IntegerValue(integerValue1.getValue() - integerValue2.getValue());
                    case MULTIPLICATION:
                        return new IntegerValue(integerValue1.getValue() * integerValue2.getValue());
                }
            } else throw new IncorrectTypeException("Integer required, " + value2.getType() + " was given");

        } else throw new IncorrectTypeException("Integer required, " + value1.getType() + " was given");

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
        switch (arithmeticOperation) {
            case MULTIPLICATION:
                return e1.toString() + " * " + e2.toString();
            case ADDITION:
                return e1.toString() + " + " + e2.toString();
            case DIVISION:
                return e1.toString() + " / " + e2.toString();
            case SUBTRACTION:
                return e1.toString() + " -" + e2.toString().replace('-', ' ');
        }
        return "";
    }
}
