package com.borlehandro.stores.cars;

import com.borlehandro.details.Car;
import com.borlehandro.stores.Store;

public class CarsStore extends Store {

    public final Object controllerMonitor = new Object();

    public CarsStore(int size) {
        super(size);
    }

    @Override
    public synchronized Car get() {
        // System.err.println("Get");
        synchronized (controllerMonitor) {
            // System.err.println("Get contr monitor in store.");
            controllerMonitor.notify();
            try {
                controllerMonitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Car) super.get();
    }

    public synchronized void put(Car detail) {
        System.err.println("Car put.");
        if (details.size() < limit) {
            details.push(detail);
            // System.err.println("Add car : " + detail.getUID());
            System.err.println("Cars store: " + details.size() + "/" + limit);
            notifyAll();
            synchronized (sizeMonitor) {
                sizeMonitor.setSize(sizeMonitor.getSize() + 1);
                sizeMonitor.notify();
            }
        } else System.err.println("Cars store is full!");
    }

}