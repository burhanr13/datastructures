package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> extends AbstractMap<K, V> {

    private Entry<K, V>[] entries;
    private int size;
    private double loadFactor;

    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V val;
        Entry<K, V> next = null;

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
    }

    public HashMap() {
        this(10);
    }

    public HashMap(int size) {
        this(size, 0.75);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int size, double loadFactor) {
        entries = new Entry[size];
        this.loadFactor = loadFactor;
        if (this.loadFactor < 0 || this.loadFactor > 1) {
            this.loadFactor = 0.75;
        }
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] getEntry(K key) {
        int i = key.hashCode() % entries.length;
        Entry<K, V> cur = entries[i];
        Entry<K, V> prev = null;
        while (cur != null) {
            if (cur.key.equals(key)) {
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        return new Entry[]{prev,cur};
    }
    
    @Override
    public boolean containsKey(K key) {
        return getEntry(key)[1] != null;
    }
    
    @Override
    public V get(K key) {
        Entry<K, V> cur = getEntry(key)[1];
        if (cur != null)
            return cur.val;
        return null;
    }

    @Override
    public void put(K key, V val) {
        Entry<K, V>[] e = getEntry(key);
        Entry<K, V> prev = e[0];
        Entry<K, V> cur = e[1];
        if (cur != null) {
            cur.val = val;
            return;
        }
        Entry<K, V> n = new Entry<>(key, val);
        if (prev == null) {
            entries[key.hashCode() % entries.length] = n;
        } else {
            prev.next = n;
        }
        
        size++;
        if (size > loadFactor * entries.length) {
            resize(2 * entries.length);
        }
    }

    @Override
    public V remove(K key) {
        Entry<K, V>[] e = getEntry(key);
        Entry<K, V> prev = e[0];
        Entry<K, V> cur = e[1];
        if (cur == null)
            return null;
        prev.next = cur.next;
        size--;
        return cur.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            private int i = entries.length - 1;
            private Entry<K, V> cur = null;
            private int n = 0;

            @Override
            public boolean hasNext() {
                return n < size;
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no more entries in map");
                }

                if (cur != null) {
                    cur = cur.next;
                }
                while (cur == null) {
                    i++;
                    i %= entries.length;
                    cur = entries[i];
                }

                n++;
                return cur;
            }
        };
    }

    @SuppressWarnings("unchecked")
    private void resize(int newsize) {
        Map.Entry<K, V>[] a = new Map.Entry[size];
        int i = 0;
        for (Map.Entry<K, V> e : this) {
            a[i] = e;
            i++;
        }

        entries = new Entry[newsize];
        size = 0;
        for (Map.Entry<K, V> e : a) {
            put(e.getKey(), e.getVal());
        }
        
    }

}
