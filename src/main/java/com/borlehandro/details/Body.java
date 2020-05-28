package com.borlehandro.details;

public class Body extends Detail {

    private static long count = 0;

    public Body() {
        UID = count;
        count++;
    }
}
