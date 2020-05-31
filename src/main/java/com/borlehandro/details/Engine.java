package com.borlehandro.details;

public class Engine extends Detail {

    private static long count = 0;

    public Engine() {
        UID = count;
        count++;
    }
}
