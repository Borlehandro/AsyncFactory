package com.borlehandro.stores;

import com.borlehandro.details.Engine;

public class EngineStore extends Store {

    public EngineStore(int size) {
        super(size);
    }

    @Override
    public synchronized Engine get() {
        return (Engine) super.get();
    }

    public synchronized void put(Engine detail) {
        System.err.println("EngineStore: " + details.size() + "/" + limit);
        try {
            while (details.size() >= limit) {
                // System.err.println("Engine store is empty.");
                wait();
            }
            details.push(detail);
            notify();
            synchronized (sizeMonitor) {
                sizeMonitor.setSize(sizeMonitor.getSize() + 1);
                sizeMonitor.notify();
            }
        } catch (InterruptedException e) {
            System.err.println("Engine store interrupted.");
        }
    }

}
