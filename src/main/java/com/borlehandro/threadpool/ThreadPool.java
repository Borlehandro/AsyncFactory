package com.borlehandro.threadpool;

import java.util.*;

public class ThreadPool implements TaskListener {

    private final int THREAD_COUNT;
    //private List allocatedThreads = new ArrayList();
    private final List<ThreadPoolTask> taskQueue = new LinkedList<>();
    private final Set<PooledThread> availableThreads = new HashSet<>();

    public void taskStarted(Task t) {
        System.out.println("Started: " + t.getName());
    }

    public void taskFinished(Task t) {
        System.out.println("Finished: " + t.getName());
    }

    public void taskInterrupted(Task t) {
        System.out.println("Interrupted: " + t.getName());
    }

    public void addTask(Task t) {
        addTask(t, this);
    }

    public void addTask(Task t, TaskListener l) {
        synchronized (taskQueue) {
            taskQueue.add(new ThreadPoolTask(t, l));
            taskQueue.notify();
        }
    }

    public ThreadPool(int threadCount) {
        THREAD_COUNT = threadCount;
        for (int i = 0; i < THREAD_COUNT; i++) {
            availableThreads.add(new PooledThread("Performer_" + i, taskQueue));
        }
        for (PooledThread availableThread : availableThreads) {
            availableThread.start();
        }

    }

    public int getTasksSize() {
        return taskQueue.size();
    }
}