package com.borlehandro.stores.cars;

import com.borlehandro.details.Car;
import com.borlehandro.stores.Store;

public class CarsStore extends Store {

    public CarsStore(int size) {
        super(size);
    }

    @Override
    public Car get() {
        return (Car) super.get();
    }

    public void put(Car detail) {
        if(details.size()<limit)
            details.push(detail);
        else System.err.println("Accessory store is full!");
    }
}