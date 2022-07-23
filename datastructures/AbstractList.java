package datastructures;

import java.util.NoSuchElementException;

abstract class AbstractList<T> implements List<T> {

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";

        StringBuilder str = new StringBuilder("[ ");
        for (T t: this) {
            str.append(t + ", ");
        }
        return str.replace(str.length() - 2, str.length(), " ]").toString();
    }

    @Override
    public abstract void add(int index, T e);

    @Override
    public void add(T e) {
        add(size(), e);
    }

    @Override
    public void addFirst(T e) {
        add(0, e);
    }

    @Override
    public void addLast(T e) {
        add(e);
    }

    @Override
    public boolean contains(T e) {
        return indexOf(e) >= 0;
    }

    @Override
    public abstract T get(int index);

    @Override
    public T getFirst() {
        if (isEmpty())
            throw new NoSuchElementException("list empty");
        return get(0);
    }

    @Override
    public T getLast() {
        if (isEmpty())
            throw new NoSuchElementException("list empty");
        return get(size() - 1);
    }

    @Override
    public abstract int indexOf(T e);

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public abstract T remove(int index);

    @Override
    public abstract T remove(T e);

    @Override
    public T remove() {
        if (isEmpty())
            throw new NoSuchElementException("list empty");
        return remove(0);
    }

    @Override
    public T removeFirst() {
        return remove();
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("list empty");
        return remove(size() - 1);
    }

    @Override
    public abstract T set(int index, T e);

    @Override
    public abstract int size();

}
