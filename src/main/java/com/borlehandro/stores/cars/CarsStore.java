package com.borlehandro.stores.cars;

import com.borlehandro.details.Car;
import com.borlehandro.stores.Store;

import java.util.concurrent.atomic.AtomicBoolean;

public class CarsStore extends Store {

    public final AtomicBoolean got = new AtomicBoolean(true);

    public CarsStore(int size) {
        super(size);
    }

    @Override
    public synchronized Car get() {
        System.err.println("Get");
        got.set(true);
        return (Car) super.get();
    }

    public synchronized void put(Car detail) {
        if(details.size() < limit) {
            details.push(detail);
            // System.err.println("Add car : " + detail.getUID());
            notifyAll();
        }
        else System.err.println("Cars store is full!");
    }

    public boolean isGot() {
        synchronized (got) {
            if(got.get()) {
                got.set(false);
                System.err.println("Get got.");
                return true;
            } else return false;
        }
    }

}