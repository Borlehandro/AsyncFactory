package com.borlehandro;

public class SizeMonitor {
    private int size;
    private int limit;

    public SizeMonitor(int size, int limit) {
        this.size = size;
        this.limit = limit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
