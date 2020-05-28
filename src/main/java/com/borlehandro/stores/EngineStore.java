package com.borlehandro.stores;

import com.borlehandro.details.Engine;

public class EngineStore extends Store {

    public EngineStore(int size) {
        super(size);
    }

    @Override
    public Engine get() {
        return (Engine) super.get();
    }

    public void put(Engine detail) {
        if(details.size()<limit)
            details.push(detail);
        else System.err.println("Engine store is full!");
    }

}
