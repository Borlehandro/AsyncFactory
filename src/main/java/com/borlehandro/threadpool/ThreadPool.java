package com.borlehandro.threadpool;

import com.borlehandro.PropertiesManager;

import java.io.IOException;
import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 *
 * @author
 * @version 1.0
 */

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
        for (Iterator iter = availableThreads.iterator(); iter.hasNext(); ) {
            ((Thread) iter.next()).start();
        }

    }

    public int getTasksSize() {
        return taskQueue.size();
    }
}