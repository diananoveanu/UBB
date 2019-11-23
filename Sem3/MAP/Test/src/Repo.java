import java.util.ArrayList;

public class Repo implements IRepo {
    private ArrayList<IExpression> expressions;

    public Repo() {
        expressions = new ArrayList<>();
    }

    @Override
    public ArrayList<IExpression> getAll() {
        return this.expressions;
    }

    @Override
    public void add(IExpression ex) {
        this.expressions.add(ex);
    }

    @Override
    public IExpression find(Integer id) {
        return expressions.get(id);
    }

}
