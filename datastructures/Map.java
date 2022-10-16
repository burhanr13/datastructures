package datastructures;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    
    public interface Entry<K, V> {
        public K getKey();

        public V getVal();
    }
    
    public void put(K key, V val);

    public V get(K key);

    public V remove(K key);

    public boolean containsKey(K key);

    public boolean isEmpty();

    public int size();

}
