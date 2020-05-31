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

    private static int workingTime;

    static {
        try {
            workingTime = PropertiesManager.getValue("carMakingTime");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Worker(BodyStore bodyStore, EngineStore engineStore, AccessoryStore accessoryStore, CarsStore carsStore) {
        this.bodyStore = bodyStore;
        this.engineStore = engineStore;
        this.accessoryStore = accessoryStore;
        this.carsStore = carsStore;
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
        Thread.sleep(workingTime);
        carsStore.put(car);
    }

    public static void changeWorkTime(int newTime) {
        workingTime = newTime;
    }

}
