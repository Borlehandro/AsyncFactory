package com.borlehandro.suppliers;

import com.borlehandro.PropertiesManager;
import com.borlehandro.details.Body;
import com.borlehandro.stores.BodyStore;

import java.io.IOException;

public class BodySupplier extends Supplier {

    public BodySupplier(ThreadGroup group, String name, BodyStore store) {
        super(group, name, store);
        try {
            workTime = PropertiesManager.getValue("bodyMakingTime");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(workTime);
                ((BodyStore) store).put(new Body());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
