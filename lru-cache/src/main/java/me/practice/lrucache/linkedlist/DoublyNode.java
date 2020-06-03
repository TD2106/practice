package me.practice.lrucache.linkedlist;

public class DoublyNode<T> {
    private DoublyNode<T> next;
    private DoublyNode<T> previous;
    private final T value;

    public DoublyNode(T value) {
        this.value = value;
    }

    public DoublyNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }

    public DoublyNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyNode<T> previous) {
        this.previous = previous;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
