package me.practice.lrucache;

public interface Cache<K, V> {
    void set(K key, V value);

    V get(K key);

    void remove(K key);
}
