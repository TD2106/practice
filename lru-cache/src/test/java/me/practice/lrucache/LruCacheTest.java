package me.practice.lrucache;

import static org.junit.Assert.*;

import org.junit.Test;

public class LruCacheTest {
    @Test(expected = IllegalArgumentException.class)
    public void lruCacheShouldThrowExceptionWhenCapacityIsZero() {
        new LruCache<Integer, Integer>(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lruCacheShouldThrowExceptionWhenCapacityIsLessThanZero() {
        new LruCache<Integer, Integer>(-1);
    }

    @Test
    public void getSetShouldWorkProperlyWhenTheCapacityIsNotReached() {
        Cache<Integer, Integer> cache = new LruCache<>(3);
        cache.set(1, 2);
        cache.set(2, 3);
        assertEquals(2, cache.get(1).intValue());
        assertEquals(3, cache.get(2).intValue());
    }

    @Test
    public void cacheShouldDeleteLruElement() {
        Cache<Integer, Integer> cache = new LruCache<>(3);
        cache.set(1, 2);
        cache.set(2, 3);
        cache.set(3, 4);
        cache.set(4, 5);
        assertNull(cache.get(1));
        assertEquals(3, cache.get(2).intValue());
        cache.set(5, 6);
        assertNull(cache.get(3));
        cache.remove(4);
        cache.set(6, 7);
        cache.set(7, 8);
        assertNull(cache.get(2));
    }
}