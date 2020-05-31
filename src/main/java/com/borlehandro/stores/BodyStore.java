package com.borlehandro.stores;

import com.borlehandro.details.Body;

public class BodyStore extends Store {

    public BodyStore(int size) {
        super(size);
    }

    @Override
    public synchronized Body get() {
        return (Body) super.get();
    }

    public synchronized void put(Body detail) {
        System.err.println("BodyStore: " + details.size() + "/" + limit);
        try {
            while (details.size() >= limit) {
                // System.err.println("Body store is empty.");
                wait();
            }
            details.push(detail);
            notify();
            synchronized (sizeMonitor) {
                sizeMonitor.setSize(sizeMonitor.getSize() + 1);
                sizeMonitor.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}