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

    public Detail get() {
        if(!details.isEmpty())
            return details.pop();
        else {
            System.err.println(this.getClass() + "STACK IS EMPTY!");
            return null;
        }
    }

}