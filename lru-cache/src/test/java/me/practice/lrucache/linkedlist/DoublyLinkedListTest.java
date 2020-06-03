package me.practice.lrucache.linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {
    @Test
    public void newLinkedListShouldHaveHeadAndTailWithValueNull() {
        final DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        assertNull(linkedList.getHead().getValue());
        assertNull(linkedList.getTail().getValue());
    }

    @Test
    public void linkedListWithOneNodeShouldHaveSameHeadAndTailNode() {
        final DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        final Integer value = 1;
        linkedList.push(value);
        assertEquals(linkedList.getTail(), linkedList.getHead());
        assertEquals(value, linkedList.getTail().getValue());
        assertEquals(value, linkedList.getHead().getValue());
    }

    @Test
    public void linkedListPushMethodShouldAlwaysUpdateTheTailNode() {
        final DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        Integer value = 1;
        linkedList.push(value.intValue());
        assertEquals(value, linkedList.getTail().getValue());
        value = 2;
        linkedList.push(value.intValue());
        assertEquals(value, linkedList.getTail().getValue());
        value = 3;
        linkedList.push(value.intValue());
        assertEquals(value, linkedList.getTail().getValue());
    }
}