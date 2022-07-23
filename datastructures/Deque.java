package datastructures;

public interface Deque<T> extends Queue<T> {
    public void addFirst(T e);

    public void addLast(T e);

    public T removeFirst();

    public T removeLast();

    public T peekFirst();

    public T peekLast();
}
