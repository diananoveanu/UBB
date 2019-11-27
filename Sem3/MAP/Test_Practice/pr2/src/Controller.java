import java.util.ArrayList;

public class Controller {
    private IRepo repo;

    public Controller(IRepo rep) {
        this.repo = rep;
    }

    void addExpression(IExpression exp) {
        this.repo.add(exp);
    }

    ArrayList<IExpression> getExpressions() {
        return repo.getAll();
    }

    IExpression getExpression(Integer id) {
        return repo.find(id);
    }


    public Integer evaluateOne(Integer id) {
        return repo.find(id).evaluate();
    }
}
