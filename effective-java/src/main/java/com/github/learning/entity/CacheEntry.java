package com.github.learning.entity;

public class CacheEntry {
    private String data;
    private long lastAccessTime;
    private int accessCount;

    public CacheEntry(String data) {
        // insert implementation of constructor here
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
