package com.borlehandro.threadpool;

public class TestTask implements Task {

    static int count = 0;
    private final int number;

    public TestTask() {
        count++;
        number = count;
    }

    @Override
    public String getName() {
        return "SomeTask #" + number;
    }

    @Override
    public void performWork() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println( getName() + " progress : " + i + "%");
            Thread.sleep(1000);
        }
    }
}
