package com.borlehandro;

import com.borlehandro.details.Car;
import com.borlehandro.stores.cars.CarsStore;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Dealer extends Thread {

    private static int count;

    private final CarsStore carsStore;
    private int dealingTime;
    private final int number;
    private static final Logger logger = Logger.getLogger(Dealer.class.getName());

    public Dealer(ThreadGroup group, String name, CarsStore carsStore) {
        super(group, name);
        this.carsStore = carsStore;
        try {
            dealingTime = PropertiesManager.getValue("dealingTime");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        count++;
        number = count;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(dealingTime);
                Car car = carsStore.get();
                System.err.println("Dealer #" + number + " get: " + car.getUID());

                logger.info("Dealer #" + number + " car: " + car.getUID()
                        + " body: " + car.getBody().getUID()
                        + " engine: " + car.getEngine().getUID()
                        + " accessory: " + car.getAccessory().getUID());

            } catch (InterruptedException e) {
                break;
            }
        }
        System.err.println(getName() + " finished.");
    }

    public synchronized void changeDealingTime(int newTime) {
        System.out.println("Dealer time changed : " + newTime);
        dealingTime = newTime;
    }

}
