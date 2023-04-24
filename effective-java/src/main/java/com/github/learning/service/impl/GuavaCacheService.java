package com.github.learning.service.impl;

import com.github.learning.entity.CacheEntry;
import com.github.learning.service.CacheService;
import com.github.learning.entity.CacheStatistics;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.TimeUnit;

public class GuavaCacheService implements CacheService {
    private final Cache<String, CacheEntry> cache;
    private final CacheStatistics stats;

    public GuavaCacheService(int maxSize, RemovalListener<String, CacheEntry> removalListener, CacheStatistics statistics) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterAccess(5, TimeUnit.SECONDS) // set your preferred time
                .removalListener(removalListener)
                .build();
        this.stats = statistics;
    }

    @Override
    public CacheEntry get(String key) {
        CacheEntry entry = cache.getIfPresent(key);
        if (entry != null) {
            stats.incrementHits();
        } else {
            stats.incrementMisses();
        }
        return entry;
    }

    @Override
    public void put(String key, CacheEntry entry) {
        long start = System.nanoTime();
        cache.put(key, entry);
        long end = System.nanoTime();
        long elapsed = end - start;
        stats.addPutTime(elapsed);
    }

    @Override
    public CacheStatistics getStatistics() {
        return stats;
    }
}
