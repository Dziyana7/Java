package com.github.learning.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import static java.util.Arrays.stream;

public class CacheStatistics {
    private long hits;
    private long misses;
    private long evictionCount;

    private final List<Long> putTimes = new ArrayList<>();

    public synchronized int incrementHits() {

        hits++;
        return 0;
    }

    public synchronized int incrementMisses() {
        misses++;
        return 0;
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
        // insert your realization of getting average put time here
        OptionalDouble timeAverage = putTimes.stream().mapToLong(Long::longValue).average();
        if(timeAverage.isPresent()) {
            return timeAverage.getAsDouble();
        }else {
            return 0;
        }
    }

    public long getEvictionCount() {
        return evictionCount;
    }
}
