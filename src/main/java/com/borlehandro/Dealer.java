package com.borlehandro;

import com.borlehandro.stores.cars.CarsStore;

public class Dealer extends Thread {

    private final CarsStore carsStore;

    public Dealer(CarsStore carsStore) {
        this.carsStore = carsStore;
    }

    @Override
    public void run() {
        while (true) {
            System.err.println(carsStore.get());
        }
    }
}
