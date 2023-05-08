package com.github.learning.service.impl;

import com.github.learning.entity.CacheEntry;
import com.github.learning.entity.CacheStatistics;
import com.github.learning.service.CacheService;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.*;

public class CoreCacheService implements CacheService {
    private final int maxSize;
    private final ConcurrentHashMap<String, CacheEntry> cache;
    private final CacheStatistics stats;

    public CoreCacheService(int maxSize, ConcurrentHashMap<String, CacheEntry> cache, CacheStatistics stats) {
        // implement constructor here by using only core elements
        this.maxSize = maxSize;
        this.cache = cache;
        this.stats = stats;
    }

    @Override
    public synchronized CacheEntry get(String key) {
        CacheEntry getKey = cache.get(key);
        if (getKey != null){
            stats.incrementHits();
        }else {
            stats.incrementMisses();
        }
        return getKey; // insert get realization using only core
    }

    @Override
    public synchronized void put(String key, CacheEntry value) {
       // insert put realization using only core
        if(key != null) {
            if (cache.size() >= maxSize) {
                evictLeastFrequentlyUsedEntry();
            }
            cache.put(key, value);
            stats.addPutTime(System.currentTimeMillis());
        }

    }

    private void evictLeastFrequentlyUsedEntry() {
        // additional method for handling deletion of overflow cache size
        Comparator<Map.Entry<String, CacheEntry>> compValue =
                (Map.Entry<String, CacheEntry> entry1, Map.Entry<String, CacheEntry> entry2) -> Integer.compare(entry1.getValue()
                        .getAccessCount(), entry2.getValue().getAccessCount());
        Map.Entry<String, CacheEntry> minValue = cache.entrySet().stream().min(compValue).orElseThrow();
        cache.remove(minValue.getKey());

        stats.incrementEvictionCount();
    }


    @Override
    public synchronized CacheStatistics getStatistics() {
        return stats;
    }
}
