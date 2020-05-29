package com.borlehandro.threadpool;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 *
 * @author
 * @version 1.0
 */

public class PooledThread extends Thread {
    private List taskQueue;

    public PooledThread(String name, List taskQueue) {
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
        ThreadPoolTask toExecute = null;
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
                    toExecute = (ThreadPoolTask) taskQueue.remove(0);
                }
            }
            System.out.println(getName() + " got the job: " + toExecute.getName());
            performTask(toExecute);
        }
    }
}