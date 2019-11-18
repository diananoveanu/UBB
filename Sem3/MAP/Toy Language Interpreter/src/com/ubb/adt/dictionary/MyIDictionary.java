package com.ubb.adt.dictionary;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<K, V> {
    V get(K key);

    V put(K key, V value);

    String toString();

    int size();

    boolean containsKey(K name);

    void remove(K id);

    public Set<Map.Entry<K, V>> getContent();

    Collection<V> values();

    boolean containsValue(V element);

    Set<K> keySet();

    Set<Map.Entry<K, V>> entrySet();

    void setContent(Set<Map.Entry<K, V>> set);

    K getKey(V value);

    MyIDictionary<K, V> cloneDict();
}