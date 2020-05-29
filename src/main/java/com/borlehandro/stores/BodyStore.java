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
        if(details.size()<limit) {
            details.push(detail);
            notify();
        }
        else System.err.println("Body store is full!");
    }
}