package datastructures;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T> implements Stack<T>, Deque<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    public LinkedList() {
        root = new Node<>(null);
        root.next = root;
        root.prev = root;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("list index out of bounds");

        Node<T> curNode;

        if (index > size / 2) {
            curNode = root;
            for (int i = size; i > index; i--) {
                curNode = curNode.prev;
            }
        } else {
            curNode = root.next;
            for (int i = 0; i < index; i++) {
                curNode = curNode.next;
            }
        }
        return curNode;
    }

    private Node<T> getNode(T e) {
        Node<T> curNode = root.next;
        while (curNode != root) {
            if (curNode.data.equals(e)) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    @Override
    public void add(int index, T e) {
        Node<T> newNode = new Node<>(e);
        Node<T> curNode = getNode(index);
        Node<T> prevNode = curNode.prev;
        curNode.prev = newNode;
        newNode.next = curNode;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        size++;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T set(int index, T e) {
        Node<T> curNode = getNode(index);
        T replaced = curNode.data;
        curNode.data = e;
        return replaced;
    }

    @Override
    public int indexOf(T e) {
        Node<T> curNode = root.next;
        int index = 0;
        while (curNode != root) {
            if (curNode.data.equals(e)) {
                return index;
            }
            index++;
            curNode = curNode.next;
        }
        return -1;
    }

    private T removeNode(Node<T> curNode) {
        if (curNode == root)
            throw new IllegalArgumentException("cannot remove base node");
        T removed = curNode.data;
        curNode.prev.next = curNode.next;
        curNode.next.prev = curNode.prev;
        size--;
        return removed;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("list index out of bounds");

        return removeNode(getNode(index));
    }

    @Override
    public T remove(T e) {
        Node<T> curNode = getNode(e);
        if (curNode == null)
            return null;
        return removeNode(curNode);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cur = root.next;
            boolean hasRemoved = true;

            @Override
            public boolean hasNext() {
                return cur != root;
            }

            @Override
            public T next() {
                if (cur == root)
                    throw new NoSuchElementException("reached end of list");
                T next = cur.data;
                cur = cur.next;
                hasRemoved = false;
                return next;
            }

            @Override
            public void remove() {
                if (hasRemoved)
                    throw new IllegalStateException("cannot remove right now");
                removeNode(cur.prev);
                hasRemoved = true;
            }

        };
    }

    @Override
    public void sort(Comparator<T> c) {
        throw new UnsupportedOperationException("havent implemented sorting for linked lists yet");
    }

    @Override
    public T peek() {
        if (isEmpty())
            return null;
        return get(0);
    }

    @Override
    public T peekFirst() {
        return peek();
    }

    @Override
    public T peekLast() {
        if (isEmpty())
            return null;
        return getLast();
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return remove();
    }

    @Override
    public void push(T e) {
        addFirst(e);
    }

}
