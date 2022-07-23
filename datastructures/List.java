package datastructures;

import java.util.Comparator;

public interface List<T> extends Iterable<T> {
    
    public void add(int index, T e);

    public void add(T e);

    public void addFirst(T e);

    public void addLast(T e);

    public T remove(int index);

    public T remove(T e);

    public T remove();

    public T removeFirst();

    public T removeLast();

    public int indexOf(T e);

    public boolean contains(T e);

    public T get(int index);

    public T getFirst();

    public T getLast();

    public T set(int index, T e);

    public boolean isEmpty();

    public int size();

    public void sort(Comparator<T> c);
}