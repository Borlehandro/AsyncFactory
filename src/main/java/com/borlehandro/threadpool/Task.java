package com.borlehandro.threadpool;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

public interface Task {
   String getName();
   public void performWork() throws InterruptedException;
}