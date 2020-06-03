package me.practice.lrucache.linkedlist;

public class DoublyLinkedList<T> {
    private DoublyNode<T> head;
    private DoublyNode<T> tail;

    public DoublyLinkedList() {
        head = new DoublyNode<>(null);
        tail = new DoublyNode<>(null);
        linkTwoNodes(head, tail);
    }

    public void pushExistingNode(DoublyNode<T> node) {
        unlinkNode(node);
        linkAsLastNode((DoublyNode<T>) node);
    }

    public DoublyNode<T> push(T value) {
        DoublyNode<T> newNode = new DoublyNode<>(value);
        linkAsLastNode(newNode);
        return newNode;
    }

    private void linkAsLastNode(DoublyNode<T> node) {
        linkTwoNodes(tail.getPrevious(), node);
        linkTwoNodes(node, tail);
    }

    public DoublyNode<T> getFirstNode() {
        return head.getNext();
    }

    public void unlinkNode(DoublyNode<T> node) {
        linkTwoNodes(node.getPrevious(), node.getNext());
        node.setPrevious(null);
        node.setNext(null);
    }

    private void linkTwoNodes(DoublyNode<T> first, DoublyNode<T> second) {
        first.setNext(second);
        second.setPrevious(first);
    }
}
