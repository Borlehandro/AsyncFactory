package com.borlehandro.stores;

import com.borlehandro.details.Accessory;

public class AccessoryStore extends Store {

    public AccessoryStore(int size) {
        super(size);
    }

    @Override
    public synchronized Accessory get() {
        return (Accessory) super.get();
    }

    public synchronized void put(Accessory detail) {
        System.err.println("AccessoryStore: " + details.size() + "/" + limit);
        try {
            while (details.size() >= limit) {
                // System.err.println("Accessory store is empty.");
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
