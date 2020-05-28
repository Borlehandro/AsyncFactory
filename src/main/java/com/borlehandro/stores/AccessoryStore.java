package com.borlehandro.stores;

import com.borlehandro.details.Accessory;

public class AccessoryStore extends Store {

    public AccessoryStore(int size) {
        super(size);
    }

    @Override
    public Accessory get() {
        return (Accessory) super.get();
    }

    public void put(Accessory detail) {
        if(details.size()<limit)
            details.push(detail);
        else System.err.println("Accessory store is full!");
    }
}
