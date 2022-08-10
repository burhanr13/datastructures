package datastructures;

public class ArrayDeque<T> implements Deque<T>, Stack<T> {

    private T[] elements;
    private int first;
    private int last;

    public ArrayDeque() {
        this(10);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayDeque(int size) {
        elements = (T[]) (new Object[size]);
        first = 0;
        last = 0;
    }

    @Override
    public void addFirst(T e) {
        first--;
        first = mod(first, elements.length);
        if (first == last)
            resize(2 * elements.length);
        
        elements[first] = e;
    }

    @Override
    public void addLast(T e) {
        elements[last] = e;
        last++;
        last = mod(last, elements.length);
        if (first == last)
            resize(2 * elements.length);
    }

    @Override
    public T peekFirst() {
        if (!isEmpty()) {
            return elements[first];
        }
        return null;
    }

    @Override
    public T peekLast() {
        if (!isEmpty()) {
            return elements[(last - 1) % elements.length];
        }
        return null;
    }

    @Override
    public T removeFirst() {
        T removed = elements[first];
        elements[first] = null;
        first++;
        first = mod(first,elements.length);
        return removed;
    }

    @Override
    public T removeLast() {
        last--;
        last = mod(last, elements.length);
        T removed = elements[last];
        elements[last] = null;
        return removed;
    }

    @Override
    public void add(T e) {
        addLast(e);
    }

    @Override
    public boolean isEmpty() {
        return first == last;
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public void push(T e) {
        addFirst(e);
    }

    private int mod(int a, int b) {
        return (a % b + b) % b;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newsize) {
        T[] old = elements;
        int start = first;
        elements = (T[]) (new Object[newsize]);
        first = 0;
        last = 0;
        add(old[start]);
        for (int i = (start+1)%elements.length; i != start; i = (i + 1) % old.length) {
            add(old[i]);
        }
    }
}
