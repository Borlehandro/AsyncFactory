package com.borlehandro;

import com.borlehandro.stores.Store;
import javafx.scene.control.ProgressBar;

public class StoreProgressListener extends Thread {

    private final SizeMonitor sizeMonitor;
    private final ProgressBar progressBar;

    public StoreProgressListener(Store store, ProgressBar progressBar) {
        this.sizeMonitor = store.sizeMonitor;
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        synchronized (sizeMonitor) {
            try {
                while (true) {
                    sizeMonitor.wait();
                    System.err.println("Monitor response");
                    progressBar.setProgress((double) sizeMonitor.getSize() / sizeMonitor.getLimit());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}