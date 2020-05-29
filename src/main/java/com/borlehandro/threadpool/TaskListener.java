package com.borlehandro.threadpool;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

public interface TaskListener {
   void taskInterrupted(Task t);
   void taskFinished(Task t);
   void taskStarted(Task t);
}