package com.github.learning.service.impl;

import com.github.learning.entity.CacheEntry;
import com.github.learning.entity.CacheStatistics;
import com.github.learning.service.CacheService;

import java.util.Map;

public class CoreCacheService implements CacheService {
    private final int maxSize;
    private final Map<String, CacheEntry> cache;
    private final CacheStatistics stats;

    public CoreCacheService(int maxSize) {
        // implement constructor here by using only core elements
    }

    @Override
    public synchronized CacheEntry get(String key) {
        return null; // insert get realization using only core
    }

    @Override
    public synchronized void put(String key, CacheEntry value) {
        return; // insert put realization using only core
    }

    private void evictLeastFrequentlyUsedEntry() {
        return; // additional method for handling deletion of overflow cache size
    }

    @Override
    public synchronized CacheStatistics getStatistics() {
        return stats;
    }
}
