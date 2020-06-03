package me.practice.lrucache;

import java.util.HashMap;
import java.util.Map;

import me.practice.lrucache.linkedlist.DoublyLinkedList;
import me.practice.lrucache.linkedlist.DoublyNode;

public class LruCache<K, V> implements Cache<K, V> {
    private final DoublyLinkedList<K> lruKeys;
    private final Map<K, V> keyToValMapping;
    private final Map<K, DoublyNode<K>> keyToNodeMapping;
    private final int capacity;

    public LruCache(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Capacity needs to be larger than zero");
        }
        this.capacity = capacity;
        lruKeys = new DoublyLinkedList<>();
        keyToValMapping = new HashMap<>();
        keyToNodeMapping = new HashMap<>();
    }

    @Override
    public void set(K key, V value) {
        if (keyToValMapping.containsKey(key)) {
            updateKeyAsLastUsed(key);
            keyToValMapping.put(key, value);
            return;
        }
        if (keyToValMapping.size() >= capacity) {
            removeLruCache();
        }
        insertNewKeyValue(key, value);
    }

    private void insertNewKeyValue(K key, V value) {
        keyToValMapping.put(key, value);
        keyToNodeMapping.put(key, lruKeys.push(key));
    }

    @Override
    public V get(K key) {
        if(keyToValMapping.containsKey(key)) {
            updateKeyAsLastUsed(key);
            return keyToValMapping.get(key);
        }
        return null;
    }

    private void removeLruCache() {
        final DoublyNode<K> lruNode = lruKeys.getHead();
        remove(lruNode.getValue());
    }

    @Override
    public void remove(K key) {
        if(keyToValMapping.containsKey(key)){
            lruKeys.removeNode(keyToNodeMapping.get(key));
            keyToValMapping.remove(key);
            keyToNodeMapping.remove(key);
        }
    }

    private void updateKeyAsLastUsed(K key) {
        final DoublyNode<K> currentKeyNode = getKeyNode(key);
        lruKeys.pushExistingNode(currentKeyNode);
    }

    private DoublyNode<K> getKeyNode(K key) {
        return keyToNodeMapping.get(key);
    }
}
