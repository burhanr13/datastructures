package datastructures;
public interface Stack<T> {
    public void push(T e);

    public T pop();

    public T peek();

    public boolean isEmpty();
}
