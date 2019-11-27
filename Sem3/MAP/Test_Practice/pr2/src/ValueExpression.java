public class ValueExpression implements IExpression {
    private Integer e1;

    public ValueExpression(Integer in) {
        this.e1 = in;
    }

    @Override
    public Integer evaluate() {
        return this.e1;
    }

    @Override
    public String toString() {
        return this.e1.toString();
    }
}
