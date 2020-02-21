package domain.adt.list;

public interface IList<T> {
    void delete(T elem);

    void add(T elem);

    T[] getAll();

    Boolean isEmpty();

    T getFromIndex(Integer index);

    void removeAll();

    Integer size();

    Boolean containsValue(T elem);
}
