import java.beans.Expression;

public class ModuloExpression implements IExpression {
    IExpression e1;

    public ModuloExpression(IExpression exp) {
        this.e1 = exp;
    }

    @Override
    public Integer evaluate() {
        Integer value = e1.evaluate();
        return value % 10;
    }

    @Override
    public String toString() {
        return "mod(" + e1.toString() + ")";
    }
}
