package com.borlehandro.suppliers;

import com.borlehandro.stores.Store;

public abstract class Supplier extends Thread {

    protected Store store;
    protected int workTime;

    public Supplier(ThreadGroup group, String name, Store store) {
        super(group, name);
        this.store = store;
    }

    public synchronized void changeWorkTime(int newTime) {
        System.err.println("Supplier time changed : " + newTime);
        workTime = newTime;
    }
}