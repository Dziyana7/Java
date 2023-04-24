package com.github.learning.service.impl;

import com.github.learning.entity.CacheEntry;
import com.github.learning.service.CacheService;
import com.github.learning.entity.CacheStatistics;
import com.google.common.cache.Cache;
import com.google.common.cache.RemovalListener;

public class GuavaCacheService implements CacheService {
    private final Cache<String, CacheEntry> cache;
    private final CacheStatistics stats;

    public GuavaCacheService(int maxSize, RemovalListener<String, CacheEntry> removalListener, CacheStatistics statistics) {
        // implement your constructor by using guava module
    }

    @Override
    public CacheEntry get(String key) {
        return null; // implement get
    }

    @Override
    public void put(String key, CacheEntry entry) {
        return; // implement put
    }

    @Override
    public CacheStatistics getStatistics() {
        return stats;
    }
}
