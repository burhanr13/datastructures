package datastructures;

public abstract class AbstractMap<K,V> implements Map<K,V> {

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("{ ");
        for (MapEntry<K, V> e : this) {
            str.append(e.getKey().toString() + ":" + e.getVal().toString() + ", ");
        }
        str.replace(str.length() - 2, str.length(), " }");

        return str.toString();
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public abstract V get(K key);

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public abstract void put(K key, V val);

    @Override
    public abstract V remove(K key);

    @Override
    public abstract int size();
    
}
