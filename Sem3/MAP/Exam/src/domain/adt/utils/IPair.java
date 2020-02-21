package domain.adt.utils;

public interface IPair<K, V> {
    K getKey();

    V getValue();

    void setKey(K k);

    void setValue(V v);
}
