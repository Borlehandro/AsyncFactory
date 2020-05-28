package com.borlehandro.stores.cars;

public class CarsStoreController extends Thread {

    private final CarsStore carsStore;

    public CarsStoreController(CarsStore carsStore) {
        this.carsStore = carsStore;
    }
}
