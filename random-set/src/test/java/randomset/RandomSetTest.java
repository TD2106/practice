package randomset;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class RandomSetTest {

    @Test
    public void valueShouldExistsAfterSetting() {
        final RandomSet<Integer, Integer> randomSet = new RandomSet<>();
        randomSet.set(1, 2);
        assertEquals(Integer.valueOf(2), randomSet.get(1));
    }

    @Test
    public void valueShouldNotExistsAfterRemoving() {
        final RandomSet<Integer, Integer> randomSet = new RandomSet<>();
        randomSet.set(1, 2);
        randomSet.remove(1);
        assertNull(randomSet.get(1));
    }

    @Test
    public void getRandomValueShouldReturnAllValueOverTime() {
        final RandomSet<Integer, Integer> randomSet = new RandomSet<>();
        // 50 keys 0, 2, 4,...
        for(int i = 0; i < 100; i += 2) {
            randomSet.set(i, i + 1);
        }
        final Set<Integer> integerSet = new HashSet<>();
        for(int i = 0; i < 2000; i++) {
            integerSet.add(randomSet.getRandomValue());
        }
        assertEquals(randomSet.size(), integerSet.size());
    }

    @Test
    public void getRandomValueShouldValueWithSimilarProbabilityOverTime() {
        final RandomSet<Integer, Integer> randomSet = new RandomSet<>();
        // 50 keys 0, 2, 4,...
        for(int i = 0; i < 100; i += 2) {
            randomSet.set(i, i + 1);
        }
        final Map<Integer, Integer> countMap = new HashMap<>();
        final int tryCounts = 100000;
        for(int i = 0; i < tryCounts; i++) {
            final Integer randomValue = randomSet.getRandomValue();
            countMap.put(randomValue, countMap.getOrDefault(randomValue, 0) + 1);
        }
        final int upperCountLimit = tryCounts / 50 + (tryCounts / 50 / 10);
        final int lowerCountLimit = tryCounts / 50 - (tryCounts / 50 / 10);

        countMap.forEach((_key, count) -> {
            assertTrue(count > lowerCountLimit);
            assertTrue(count < upperCountLimit);

        });
    }
}