public class View {
    public static void main() {
        IRepo rep = new Repo();

        Controller ctrl = new Controller(rep);

        IExpression exp1 = new ModuloExpression(new ValueExpression(2));
        IExpression exp2 = new ModuloAddExpression(new ValueExpression(2), new ValueExpression(10));
        IExpression exp3 = new ModuloMulExpression(new ValueExpression(3), new ValueExpression(10));

        IExpression exp4 = new ModuloAddExpression(new ModuloAddExpression(new ValueExpression(2), new ValueExpression(10)), new ModuloMulExpression(new ValueExpression(7), new ValueExpression(4)));

        ctrl.addExpression(exp1);
        ctrl.addExpression(exp2);
        ctrl.addExpression(exp3);
        ctrl.addExpression(exp4);

        System.out.println(ctrl.evaluateOne(0));
        System.out.println(ctrl.evaluateOne(1));
        System.out.println(ctrl.evaluateOne(2));
        System.out.println(ctrl.evaluateOne(3));
    }
}
