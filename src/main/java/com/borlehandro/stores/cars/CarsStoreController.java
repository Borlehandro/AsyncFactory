package com.borlehandro.stores.cars;

import com.borlehandro.PropertiesManager;
import com.borlehandro.Worker;
import com.borlehandro.stores.AccessoryStore;
import com.borlehandro.stores.BodyStore;
import com.borlehandro.stores.EngineStore;
import com.borlehandro.threadpool.ThreadPool;

import java.io.IOException;

public class CarsStoreController extends Thread {

    private final CarsStore carsStore;
    private final ThreadPool workersPool;
    private final EngineStore engineStore;
    private final AccessoryStore accessoryStore;
    private final BodyStore bodyStore;

    public CarsStoreController(CarsStore carsStore, ThreadPool workersPool, EngineStore engineStore, AccessoryStore accessoryStore, BodyStore bodyStore) {
        this.carsStore = carsStore;
        this.workersPool = workersPool;
        this.engineStore = engineStore;
        this.accessoryStore = accessoryStore;
        this.bodyStore = bodyStore;
    }

    @Override
    public void run() {

        while (true) {
            try {
                // System.err.println("Controller get!");
                synchronized (carsStore.controllerMonitor) {
                    // System.err.println("Get monitor in controller");
                    carsStore.controllerMonitor.wait();
                    workersPool.addTask(new Worker(bodyStore, engineStore, accessoryStore, carsStore, PropertiesManager.getValue("carMakingTime")));

                    // System.err.println("Notified!");
                    carsStore.controllerMonitor.notify();
                }
            } catch (IOException | InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
