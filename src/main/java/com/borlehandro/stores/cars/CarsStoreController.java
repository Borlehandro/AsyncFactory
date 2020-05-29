package com.borlehandro.stores.cars;

import com.borlehandro.PropertiesManager;
import com.borlehandro.Worker;
import com.borlehandro.details.Accessory;
import com.borlehandro.details.Body;
import com.borlehandro.details.Engine;
import com.borlehandro.stores.AccessoryStore;
import com.borlehandro.stores.BodyStore;
import com.borlehandro.stores.EngineStore;
import com.borlehandro.threadpool.ThreadPool;

import java.io.IOException;

public class CarsStoreController extends Thread {

    private final CarsStore carsStore;
    private final ThreadPool workersPool;
    EngineStore es;
    AccessoryStore as;
    BodyStore bs;

    public CarsStoreController(CarsStore carsStore, ThreadPool workersPool) {
        this.carsStore = carsStore;
        this.workersPool = workersPool;
    }

    @Override
    public void run() {

        try {

            es = new EngineStore(PropertiesManager.getValue("engineStoreSize"));
            as = new AccessoryStore(PropertiesManager.getValue("accessoryStoreSize"));
            bs = new BodyStore(PropertiesManager.getValue("bodyStoreSize"));

            initStores(es, as, bs);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        while (true) {
            if (carsStore.got.get()) {
                try {
                    workersPool.addTask(new Worker(bs, es, as, carsStore, PropertiesManager.getValue("carMakingTime")));
                    System.err.println("Notified!");
                    carsStore.got.set(false);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    static void initStores(EngineStore es, AccessoryStore as, BodyStore bs) {

        for (int i = 0; i < 1000; i++) {
            es.put(new Engine());
            as.put(new Accessory());
            bs.put(new Body());
        }

    }
}
