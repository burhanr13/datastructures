package datastructures;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> extends AbstractList<T> implements Stack<T>, Deque<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("list index out of bounds");

        if (index == size - 1) {
            return tail;
        }

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, T e) {
        Node<T> newNode = new Node<>(e);

        if (index == 0) {
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        } else {
            Node<T> curNode = getNode(index - 1);
            Node<T> nextNode = curNode.next;
            curNode.next = newNode;
            newNode.next = nextNode;
            if (nextNode == null)
                tail = newNode;
        }
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
        Node<T> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.data.equals(e)) {
                return index;
            }
            index++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    private T removeAfter(Node<T> curNode) {
        T removed = curNode.next.data;
        if (curNode.next.next == null)
            tail = curNode;
        curNode.next = curNode.next.next;
        return removed;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("list index out of bounds");

        T removed;
        if (index == 0) {
            removed = head.data;
            head = head.next;
            if (head == null)
                tail = null;
        } else {
            removed = removeAfter(getNode(index - 1));
        }

        size--;
        return removed;
    }

    @Override
    public T remove(T e) {
        if (isEmpty())
            return null;

        if (head.data.equals(e))
            return remove();

        Node<T> curNode = head;
        while (curNode.next != null) {
            if (curNode.next.data.equals(e)) {
                size--;
                return removeAfter(curNode);
            }
            curNode = curNode.next;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                if (cur == null)
                    throw new NoSuchElementException("reached end of list");
                T next = cur.data;
                cur = cur.next;
                return next;
            }

        };
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

    @Override
    public void sort(Comparator<T> c) {
        throw new UnsupportedOperationException();
    }

}
