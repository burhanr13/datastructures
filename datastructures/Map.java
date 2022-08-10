package datastructures;

public interface Map<K,V> extends Iterable<MapEntry<K,V>>{
    
    public void put(K key, V val);

    public V get(K key);

    public V remove(K key);

    public boolean containsKey(K key);

    public boolean isEmpty();

    public int size();

}
