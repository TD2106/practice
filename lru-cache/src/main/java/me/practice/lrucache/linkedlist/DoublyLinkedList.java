package me.practice.lrucache.linkedlist;

public class DoublyLinkedList<T> {
    private final DoublyNode<T> fakeHead;
    private final DoublyNode<T> fakeTail;
    private int length;

    public DoublyLinkedList() {
        fakeHead = new DoublyNode<>(null);
        fakeTail = new DoublyNode<>(null);
        linkTwoNodes(fakeHead, fakeTail);
        length = 0;
    }

    public void pushExistingNode(DoublyNode<T> node) {
        unlinkNode(node);
        linkAsLastNode(node);
    }

    public DoublyNode<T> push(T value) {
        final DoublyNode<T> newNode = new DoublyNode<>(value);
        linkAsLastNode(newNode);
        length++;
        return newNode;
    }

    public void removeNode(DoublyNode<T> node) {
        unlinkNode(node);
        length--;
    }

    private void linkAsLastNode(DoublyNode<T> node) {
        linkTwoNodes(fakeTail.getPrevious(), node);
        linkTwoNodes(node, fakeTail);
    }

    public DoublyNode<T> getHead() {
        return fakeHead.getNext();
    }

    public DoublyNode<T> getTail() {
        return fakeTail.getPrevious();
    }

    public int getLength() {
        return length;
    }

    private void unlinkNode(DoublyNode<T> node) {
        linkTwoNodes(node.getPrevious(), node.getNext());
        node.setPrevious(null);
        node.setNext(null);
    }

    private void linkTwoNodes(DoublyNode<T> first, DoublyNode<T> second) {
        first.setNext(second);
        second.setPrevious(first);
    }
}
