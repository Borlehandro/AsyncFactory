package com.borlehandro.threadpool;

import java.util.List;

public class PooledThread extends Thread {
    private final List<ThreadPoolTask> taskQueue;

    public PooledThread(String name, List<ThreadPoolTask> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    private void performTask(ThreadPoolTask t) {
        t.prepare();
        try {
            // Block
            t.run();
        } catch (InterruptedException ex) {
            t.interrupted();
        }
        t.finish();
    }

    public void run() {
        ThreadPoolTask toExecute;
        while (true) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was inetrrupted:" + getName());
                    }
                    continue;
                } else {
                    toExecute = taskQueue.remove(0);
                }
            }
            // System.out.println(getName() + " got the job: " + toExecute.getName());
            performTask(toExecute);
        }
    }
}