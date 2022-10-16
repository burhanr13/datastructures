package datastructures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> extends AbstractList<T> {

    private T[] elements;
    private int size;

    public ArrayList() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int length) {
        if (length <= 0)
            throw new IllegalArgumentException("invalid length, must be > 0");
        elements = (T[]) new Object[length];
    }

    @Override
    public void add(int index, T e) {
        if (size == elements.length)
            resize(2 * size);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = e;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
        return elements[index];
    }

    @Override
    public int indexOf(T e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
        T removed = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        if (size < elements.length / 4) {
            resize(size / 2);
        }
        return removed;
    }

    @Override
    public T remove(T e) {
        int i = indexOf(e);
        if (i < 0)
            return null;
        return remove(i);
    }

    @Override
    public T set(int index, T e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
        T replaced = elements[index];
        elements[index] = e;
        return replaced;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;
            boolean removed = false;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                if (i == size)
                    throw new NoSuchElementException("reached the end of list");

                removed = false;
                T next = elements[i];
                i++;
                return next;
            }

            @Override
            public void remove() {
                if (removed)
                    throw new IllegalStateException("already removed an item");
                if (i <= 0)
                    throw new IllegalStateException("iteration has not begun yet");
                removed = true;
                ArrayList.this.remove(i - 1);
                i--;
            }

        };
    }

    private void quickSort(T[] arr, int start, int end, Comparator<T> c) {
        if (start >= end)
            return;
        T pivot = arr[(start + end) / 2];
        int a = start;
        int b = end;
        while (a < b) {
            while (c.compare(arr[a], pivot) < 0) {
                a++;
            }
            while (c.compare(arr[b], pivot) > 0) {
                b--;
            }
            if (a > b)
                break;
            T temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
            a++;
            b--;
        }
        quickSort(arr, start, b, c);
        quickSort(arr, a, end, c);
    }

    public void sort(Comparator<T> c) {
        quickSort(elements, 0, size - 1, c);
    }

}
