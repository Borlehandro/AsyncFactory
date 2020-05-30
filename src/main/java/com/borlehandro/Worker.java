package com.borlehandro;

import com.borlehandro.details.Car;
import com.borlehandro.stores.AccessoryStore;
import com.borlehandro.stores.BodyStore;
import com.borlehandro.stores.EngineStore;
import com.borlehandro.stores.cars.CarsStore;
import com.borlehandro.threadpool.Task;

import java.io.IOException;

public class Worker implements Task {

    private final BodyStore bodyStore;
    private final EngineStore engineStore;
    private final AccessoryStore accessoryStore;
    private final CarsStore carsStore;

    private static int count = 0;
    private final int number;

    private final int workingTime;

    public Worker(BodyStore bodyStore, EngineStore engineStore, AccessoryStore accessoryStore, CarsStore carsStore, int time) throws IOException {
        this.bodyStore = bodyStore;
        this.engineStore = engineStore;
        this.accessoryStore = accessoryStore;
        this.carsStore = carsStore;
        workingTime = time;
        count++;
        number = count;
    }


    @Override
    public String getName() {
        return "Worker #" + number;
    }

    @Override
    public void performWork() throws InterruptedException {
        Car car = new Car(bodyStore.get(), engineStore.get(), accessoryStore.get());
        for (int i = 0; i < 100; ++i) {
            Thread.sleep(workingTime);
            System.out.println("Working #" + number + ":" + i + "%");
        }
        // System.err.println(getName() + " completed...");
        carsStore.put(car);

    }
}
