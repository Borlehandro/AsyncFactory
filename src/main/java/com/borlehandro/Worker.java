package com.borlehandro;

import com.borlehandro.details.Car;
import com.borlehandro.stores.AccessoryStore;
import com.borlehandro.stores.BodyStore;
import com.borlehandro.stores.EngineStore;
import com.borlehandro.stores.cars.CarsStore;

import java.io.IOException;

public class Worker extends Thread {

    private final BodyStore bodyStore;
    private final EngineStore engineStore;
    private final AccessoryStore accessoryStore;
    private final CarsStore carsStore;

    private final int workingTime;

    public Worker(BodyStore bodyStore, EngineStore engineStore, AccessoryStore accessoryStore, CarsStore carsStore) throws IOException {
        this.bodyStore = bodyStore;
        this.engineStore = engineStore;
        this.accessoryStore = accessoryStore;
        this.carsStore = carsStore;
        workingTime = PropertiesManager.getValue("carMakingTime");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Car car = new Car(bodyStore.get(), engineStore.get(), accessoryStore.get());
                sleep(workingTime);
                carsStore.put(car);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
