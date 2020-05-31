package com.borlehandro.stores;

import com.borlehandro.SizeMonitor;
import com.borlehandro.details.Detail;

import java.util.LinkedList;

public abstract class Store {

    protected final LinkedList<Detail> details;
    protected int limit;
    public final SizeMonitor sizeMonitor;

    protected Store(int size) {
        details = new LinkedList<>();
        limit = size;
        sizeMonitor = new SizeMonitor(0, limit);
    }

    public synchronized Detail get() {
        notifyAll();
        synchronized (sizeMonitor) {
            sizeMonitor.setSize(sizeMonitor.getSize() - (details.isEmpty() ? 0 : 1));
            sizeMonitor.notify();
        }
        if (!details.isEmpty()) {
            return details.pop();
        } else {
            // System.err.println(this.getClass() + "STACK IS EMPTY!");
            try {
                while (details.isEmpty())
                    wait();
                return get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public synchronized int getSize() {
        return details.size();
    }

    public synchronized int getLimit() {
        return limit;
    }

}