public class ModuloMulExpression implements IExpression {
    IExpression e1;
    IExpression e2;

    public ModuloMulExpression(IExpression e1, IExpression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Integer evaluate() {
        Integer val1 = this.e1.evaluate();
        Integer val2 = this.e2.evaluate();
        return (val1 * val2) % 10;
    }

    @Override
    public String toString() {
        return "mul(" + e1.toString() + "*" + e2.toString() + ")";
    }
}
