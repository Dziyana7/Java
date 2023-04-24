package com.github.learning.service;

import com.github.learning.entity.CacheEntry;
import com.github.learning.entity.CacheStatistics;

public interface CacheService {
    CacheEntry get(String key);
    void put(String key, CacheEntry entry);

    CacheStatistics getStatistics();
}
