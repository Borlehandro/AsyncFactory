package com.borlehandro;

import com.borlehandro.stores.cars.CarsStore;
import com.borlehandro.stores.cars.CarsStoreController;
import com.borlehandro.threadpool.ThreadPool;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ThreadPool workersPool = new ThreadPool(PropertiesManager.getValue("workers"));

        CarsStore carsStore = new CarsStore(PropertiesManager.getValue("carsStoreSize"));

        for(int i=0; i<15; i++) {
            new Dealer(carsStore, PropertiesManager.getValue("dealingTime")).start();
        }

        CarsStoreController controller = new CarsStoreController(carsStore, workersPool);
        controller.start();

    }

}