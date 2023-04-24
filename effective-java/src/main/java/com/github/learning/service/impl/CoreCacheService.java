package com.github.learning.service.impl;

import com.github.learning.entity.CacheEntry;
import com.github.learning.entity.CacheStatistics;
import com.github.learning.service.CacheService;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CoreCacheService implements CacheService {
    private final int maxSize;
    private final Map<String, CacheEntry> cache;
    private final CacheStatistics stats;

    public CoreCacheService(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<>(maxSize, 0.75f, true);
        this.stats = new CacheStatistics();
    }

    @Override
    public synchronized CacheEntry get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null) {
            stats.incrementMisses();
            return null;
        }

        stats.incrementHits();
        entry.setLastAccessTime(System.currentTimeMillis());

        return entry;
    }

    @Override
    public synchronized void put(String key, CacheEntry value) {
        if (cache.containsKey(key)) {
            // If key already exists, update the value and increase frequency
            long start = System.nanoTime();
            cache.put(key, value);
            long end = System.nanoTime();
            value.setLastAccessTime(System.currentTimeMillis());
            stats.addPutTime(end - start);
            return;
        }

        // If cache is full, evict the least frequently used entry
        if (cache.size() >= maxSize) {
            evictLeastFrequentlyUsedEntry();
        }

        // Add the new entry to the cache
        long start = System.nanoTime();
        cache.put(key, value);
        long end = System.nanoTime();
        stats.addPutTime(end - start);
    }

    private void evictLeastFrequentlyUsedEntry() {
        Map.Entry<String, CacheEntry> entry = cache.entrySet()
                .stream()
                .min(Comparator.comparing(e -> e.getValue().getLastAccessTime()))
                .orElseThrow();

        cache.remove(entry.getKey());
        stats.incrementEvictionCount();
    }

    @Override
    public synchronized CacheStatistics getStatistics() {
        return stats;
    }
}
