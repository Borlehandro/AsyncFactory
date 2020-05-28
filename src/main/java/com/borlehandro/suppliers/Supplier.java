package com.borlehandro.suppliers;

import com.borlehandro.stores.Store;

public abstract class Supplier extends Thread {

    protected Store store;
    protected int workTime;

    public Supplier(Store store) {
        this.store = store;
    }

}