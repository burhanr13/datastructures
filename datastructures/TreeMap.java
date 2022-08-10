package datastructures;

import java.util.Iterator;

public class TreeMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

    private BinarySearchTree<Entry<K, V>> tree = new BinarySearchTree<>();

    private static class Entry<K extends Comparable<K>, V> implements MapEntry<K, V>, Comparable<Entry<K, V>> {
        K key;
        V val;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getVal() {
            return val;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            return key.compareTo(o.key);
        }
    }

    @Override
    public V get(K key) {
        Entry<K, V> e = tree.search(new Entry<>(key, null));
        if (e != null)
            return e.val;
        return null;
    }

    @Override
    public void put(K key, V val) {
        tree.add(new Entry<>(key, val));
    }

    public V remove(K key) {
        return tree.remove(new Entry<>(key, null)).val;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Iterator<MapEntry<K, V>> iterator() {
        return (Iterator)tree.iterator();
    }
}


