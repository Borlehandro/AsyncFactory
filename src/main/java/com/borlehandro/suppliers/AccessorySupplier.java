package com.borlehandro.suppliers;

import com.borlehandro.PropertiesManager;
import com.borlehandro.details.Accessory;
import com.borlehandro.stores.AccessoryStore;

import java.io.IOException;

public class AccessorySupplier extends Supplier {

    public AccessorySupplier(ThreadGroup group, String name, AccessoryStore store) {

        super(group, name, store);

        try {
            workTime = PropertiesManager.getValue("accessoryMakingTime");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(workTime);
                ((AccessoryStore) store).put(new Accessory());
            } catch (InterruptedException e) {
                break;
            }
        }
        System.err.println(getName() + " finished.");
    }
}