package com.borlehandro.suppliers;

import com.borlehandro.PropertiesManager;
import com.borlehandro.details.Engine;
import com.borlehandro.stores.EngineStore;

import java.io.IOException;

public class EngineSupplier extends Supplier {

    public EngineSupplier(ThreadGroup group, String name, EngineStore store) {
        super(group, name, store);
        try {
            workTime = PropertiesManager.getValue("engineMakingTime");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                sleep(workTime);
                ((EngineStore) store).put(new Engine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
