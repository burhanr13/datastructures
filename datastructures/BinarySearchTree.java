package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString() + ((left == null && right == null) ? ""
                    : (" (" + (left != null ? left.toString() : "") + ", " + (right != null ? right.toString() : "")
                            + ")"));
        }
    }

    private Node<T> getNode(T e) {
        Node<T> curNode = root;
        while (curNode != null) {
            if (curNode.data.compareTo(e) > 0) {
                curNode = curNode.left;
            } else if(curNode.data.compareTo(e) < 0){
                curNode = curNode.right;
            } else {
                return curNode;
            }
        }
        return null;
    }

    public void add(T e) {
        Node<T> newNode = new Node<T>(e);
        size++;

        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> curNode = root;
        while (true) {
            if (curNode.data.compareTo(e) < 0) {
                if (curNode.right == null) {
                    curNode.right = newNode;
                    newNode.parent = curNode;
                    break;
                } else {
                    curNode = curNode.right;
                }
            } else if (curNode.data.compareTo(e) > 0) {
                if (curNode.left == null) {
                    curNode.left = newNode;
                    newNode.parent = curNode;
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else {
                return;
            }
        }
    }

    private void removeNode(Node<T> node) {
        Node<T> parent = node.parent;

        if (node.left == null) {
            if (node == parent.left) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
            size--;
        } else if (node.right == null) {
            if (node == parent.left) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
            size--;
        } else {
            Node<T> succ = node.right;
            while (succ.left != null) {
                succ = succ.left;
            }
            node.data = succ.data;
            removeNode(succ);
        }
    }

    public T remove(T e) {
        Node<T> node = getNode(e);
        if (node == null)
            return null;
        T removed = node.data;
        removeNode(node);
        return removed;
    }

    public T search(T e) {
        Node<T> node = getNode(e);
        if (node != null)
            return node.data;
        return null;
    }

    public T getMin() {
        Node<T> curNode = root;
        if (curNode == null)
            return null;
        while (curNode.left != null)
            curNode = curNode.left;
        return curNode.data;
    }

    public T getMax() {
        Node<T> curNode = root;
        if (curNode == null)
            return null;
        while (curNode.right != null)
            curNode = curNode.right;
        return curNode.data;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (root == null)
            return "";
        return root.toString();
    }

    private void inOrder(Node<T> node, Consumer<T> c) {
        if (node == null)
            return;
        inOrder(node.left, c);
        c.accept(node.data);
        inOrder(node.right, c);
    }

    public void inOrder(Consumer<T> c) {
        inOrder(root, c);
    }

    private void preOrder(Node<T> node, Consumer<T> c) {
        if (node == null)
            return;
        c.accept(node.data);
        preOrder(node.left, c);
        preOrder(node.right, c);
    }

    public void preOrder(Consumer<T> c) {
        preOrder(root, c);
    }

    private void postOrder(Node<T> node, Consumer<T> c) {
        if (node == null)
            return;
        postOrder(node.left, c);
        postOrder(node.right, c);
        c.accept(node.data);
    }

    public void postOrder(Consumer<T> c) {
        postOrder(root, c);
    }

    public Iterator<T> iterator() {
        Queue<Node<T>> nodes = new ArrayDeque<>();
        if (root != null)
            nodes.add(root);

        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return !nodes.isEmpty();
            }

            @Override
            public T next() {
                if (nodes.isEmpty())
                    throw new NoSuchElementException("no more elements");
                Node<T> n = nodes.remove();
                if (n.left != null)
                    nodes.add(n.left);
                if (n.right != null)
                    nodes.add(n.right);
                return n.data;
            }
        };
    }
}
