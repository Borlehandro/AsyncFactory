package com.borlehandro.suppliers;

import com.borlehandro.stores.Store;

public abstract class Supplier extends Thread {

    protected Store store;
    protected int workTime;

    public Supplier(ThreadGroup group, String name, Store store) {
        super(group, name);
        this.store = store;
    }

}