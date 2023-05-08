package com.github.learning.service.impl;

import com.github.learning.entity.CacheEntry;
import com.github.learning.entity.CacheStatistics;
import com.github.learning.service.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.TimeUnit;

public class GuavaCacheService implements CacheService {
    private final Cache<String, CacheEntry> cache;
    private final CacheStatistics stats;
    private final int maxSize = 100000;


    public GuavaCacheService(int maxSize, RemovalListener<String, CacheEntry> removalListener, CacheStatistics stats, int maxSize1) {
        // implement your constructor by using guava module
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .removalListener(removalListener)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build();
        this.stats = stats;

    }

    @Override
    public CacheEntry get(String key) {
        // implement get
        CacheEntry getKey = cache.getIfPresent(key);
        if (getKey != null){
            stats.incrementHits();
        }else {
            stats.incrementMisses();
        }
        return getKey;
    }

    @Override
    public void put(String key, CacheEntry entry) {
        // implement put
        if(cache.size()>=maxSize) {
            stats.incrementEvictionCount();
        }
        cache.put(key, entry);
    }

    @Override
    public CacheStatistics getStatistics() {
        return stats;
    }
}
