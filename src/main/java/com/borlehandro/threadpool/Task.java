package com.borlehandro.threadpool;

public interface Task {
   String getName();
   public void performWork() throws InterruptedException;
}