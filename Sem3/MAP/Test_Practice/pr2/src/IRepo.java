import java.util.ArrayList;

public interface IRepo {
    ArrayList<IExpression> getAll();

    void add(IExpression ex);

    IExpression find(Integer id);
}
