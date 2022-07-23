package datastructures;
public interface Queue<T> {
    public void add(T e);

    public T remove();

    public T peek();

    public boolean isEmpty();
}
