package com.borlehandro;

import com.borlehandro.stores.AccessoryStore;
import com.borlehandro.stores.BodyStore;
import com.borlehandro.stores.EngineStore;
import com.borlehandro.stores.cars.CarsStore;
import com.borlehandro.stores.cars.CarsStoreController;
import com.borlehandro.suppliers.AccessorySupplier;
import com.borlehandro.suppliers.BodySupplier;
import com.borlehandro.suppliers.EngineSupplier;
import com.borlehandro.threadpool.ThreadPool;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ThreadPool workersPool = new ThreadPool(PropertiesManager.getValue("workers"));

        EngineStore engineStore = new EngineStore(PropertiesManager.getValue("engineStoreSize"));
        AccessoryStore accessoryStore = new AccessoryStore(PropertiesManager.getValue("accessoryStoreSize"));
        BodyStore bodyStore = new BodyStore(PropertiesManager.getValue("bodyStoreSize"));
        CarsStore carsStore = new CarsStore(PropertiesManager.getValue("carsStoreSize"));

        ThreadGroup bodySuppliers = new ThreadGroup("bodySuppliers");
        ThreadGroup engineSuppliers = new ThreadGroup("engineSuppliers");
        ThreadGroup accessorySuppliers = new ThreadGroup("accessorySuppliers");

        for (int i = 0; i < PropertiesManager.getValue("bodySuppliers"); i++) {
            new BodySupplier(bodySuppliers, "Body supplier #" + i, bodyStore).start();
        }
        for (int i = 0; i < PropertiesManager.getValue("engineSuppliers"); i++) {
            new EngineSupplier(engineSuppliers, "Engine supplier #" + i, engineStore).start();
        }
        for (int i = 0; i < PropertiesManager.getValue("accessorySuppliers"); i++) {
            new AccessorySupplier(accessorySuppliers, "Accessory supplier #" + i, accessoryStore).start();
        }

        CarsStoreController controller = new CarsStoreController(carsStore, workersPool, engineStore, accessoryStore, bodyStore);
        controller.start();

        int dealers = PropertiesManager.getValue("dealers");

        for (int i = 0; i < dealers; i++) {
            new Dealer(carsStore, PropertiesManager.getValue("dealingTime")).start();
        }

    }

}