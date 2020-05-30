package com.borlehandro;

import com.borlehandro.stores.cars.CarsStore;

public class Dealer extends Thread {

    private static int count;

    private final CarsStore carsStore;
    private final int dealingTime;
    private final int number;

    public Dealer(CarsStore carsStore, int time) {
        this.carsStore = carsStore;
        dealingTime = time;
        count++;
        number = count;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(dealingTime);
                System.err.println("Dealer #" + number + " get: " + carsStore.get().getUID());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
