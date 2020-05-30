package com.borlehandro.stores;

import com.borlehandro.details.Detail;

import java.util.LinkedList;

public abstract class Store {

    protected final LinkedList<Detail> details;
    protected int limit;

    protected Store(int size) {
        details = new LinkedList<>();
        limit = size;
    }

    public synchronized Detail get() {
        notifyAll();
        if(!details.isEmpty()) {
            return details.pop();
        }
        else {
            // System.err.println(this.getClass() + "STACK IS EMPTY!");
            try {
                while (details.isEmpty())
                    wait();
                return get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}