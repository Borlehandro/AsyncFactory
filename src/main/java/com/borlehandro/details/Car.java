package com.borlehandro.details;

public class Car extends Detail {

    private static long count = 0;

    private Body body;
    private Engine engine;
    private Accessory accessory;

    public Car(Body body, Engine engine, Accessory accessory) {

        this.body = body;
        this.engine = engine;
        this.accessory = accessory;

        UID = count;
        count++;
    }

    public Body getBody() {
        return body;
    }

    public Engine getEngine() {
        return engine;
    }

    public Accessory getAccessory() {
        return accessory;
    }
}