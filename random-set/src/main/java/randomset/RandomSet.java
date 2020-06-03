package randomset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSet<K, V> {
    private final Map<K, V> keyToValMapping;
    private final Map<K, Integer> keyToIndexMapping;
    private final List<K> keys;

    public RandomSet() {
        keyToValMapping = new HashMap<>();
        keyToIndexMapping = new HashMap<>();
        keys = new ArrayList<>();
    }

    public void set(K key, V value) {
        if(keyToValMapping.containsKey(key)) {
            return;
        }
        keyToValMapping.put(key, value);
        keys.add(key);
        keyToIndexMapping.put(key, keys.size() - 1);
    }

    public V get(K key) {
        if(keyToValMapping.containsKey(key)){
            return keyToValMapping.get(key);
        }
        return null;
    }

    public void remove(K key) {
        if(keyToValMapping.containsKey(key)) {
            swapLastKeyWithKeyToDelete(key);
            keyToValMapping.remove(key);
            keyToIndexMapping.remove(key);
            removeLastInsertedKey();
        }
    }

    public V getRandomValue() {
        return keyToValMapping.get(keys.get(getRandomIndex()));
    }

    public int size() {
        return keys.size();
    }

    private void removeLastInsertedKey() {
        keys.remove(keys.size() - 1);
    }

    private void swapLastKeyWithKeyToDelete(K keyToDelete) {
        final K lastKey = keys.get(keys.size() - 1);
        keys.set(keyToIndexMapping.get(keyToDelete), lastKey);
        keyToIndexMapping.put(lastKey, keyToIndexMapping.get(keyToDelete));
    }

    private Integer getRandomIndex() {
        return ThreadLocalRandom.current().nextInt(keys.size());
    }
}
