package com.github.learning.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheStatistics {
    private long hits;
    private long misses;
    private long evictionCount;

    private List<Long> putTimes = new ArrayList<>();

    public synchronized void incrementHits() {
        hits++;
    }

    public synchronized void incrementMisses() {
        misses++;
    }

    public synchronized void incrementEvictionCount() {
        evictionCount++;
    }

    public synchronized void addPutTime(long time) {
        putTimes.add(time);
    }

    public long getHitCount() {
        return hits;
    }

    public long getMissCount() {
        return misses;
    }

    public double getAvgPutTime() {
        return putTimes.stream().mapToLong(Long::longValue).average().orElse(0);
    }

    public long getEvictionCount() {
        return evictionCount;
    }
}
