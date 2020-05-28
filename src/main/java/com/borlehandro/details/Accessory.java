package com.borlehandro.details;

public class Accessory extends Detail {

    private static long count = 0;

    public Accessory() {
        UID = count;
        count++;
    }
}
