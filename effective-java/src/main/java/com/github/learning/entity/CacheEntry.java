package com.github.learning.entity;

public class CacheEntry {
    private String data;
    private long lastAccessTime;
    private int accessCount;

    public CacheEntry(String data, long lastAccessTime, int accessCount) {
        // insert implementation of constructor here
        this.data = data;
        this.lastAccessTime = lastAccessTime;
        this.accessCount = accessCount;
    }

    public String getData() {
        return data;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void incrementAccessCount() {
        this.accessCount++;
    }
}
