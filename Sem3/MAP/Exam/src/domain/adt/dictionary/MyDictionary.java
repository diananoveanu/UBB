package domain.adt.dictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private HashMap<K, V> dict;

    public MyDictionary() {
        dict = new HashMap<K, V>();
    }

    @Override
    public V get(K key) {
        return dict.get(key);
    }

    @Override
    public K getKey(V value) {
        for (K key : dict.keySet()) {
            if (dict.get(key).equals(value))
                return key;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return dict.put(key, value);
    }

    @Override
    public String toString() {
        return dict.toString();
    }

    @Override
    public int size() {
        return dict.size();
    }

    @Override
    public boolean containsKey(K name) {
        return dict.containsKey(name);
    }

    @Override
    public void remove(K id) {
        dict.remove(id);
    }

    @Override
    public Collection<V> values() {
        return dict.values();
    }

    @Override
    public boolean containsValue(V element) {
        return dict.containsValue(element);
    }

    @Override
    public Set<K> keySet() {
        return dict.keySet();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dict.entrySet();
    }

    @Override
    public Set<Map.Entry<K, V>> getContent() {
        return dict.entrySet();
    }

    @Override
    public void setContent(Set<Map.Entry<K, V>> set) {
        dict.clear();
        for (Map.Entry<K, V> entry : set) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public IDictionary<K, V> cloneDict() {
        IDictionary<K, V> di = new MyDictionary<>();
        for (K key : this.keySet())
            di.put(key, dict.get(key));
        return di;
    }

    @Override
    public Map<K, V> getMap() {
        return this.dict;
    }
}