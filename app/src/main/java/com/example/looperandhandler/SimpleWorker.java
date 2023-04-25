package com.example.looperandhandler;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread{

    private static final String TAG = "SimpleWorker";
    private final AtomicBoolean alive = new AtomicBoolean(true);
    /*------------AtomicBoolean has methods that perform their compound operations atomically and
    without having to use a synchronized block. On the other hand, volatile boolean can only perform
    compound operations if done so within a synchronized block.------------*/

    private final ConcurrentLinkedDeque<Runnable> taskQueue = new ConcurrentLinkedDeque<>();
    /*------------The ConcurrentLinkedDeque class in Java is a thread-safe implementation of the
    Deque interface that uses a linked list to store its elements.------------*/

    public SimpleWorker(){
        super(TAG);
        start();
    }

    @Override
    public void run() {
        while(alive.get()){
            Runnable task = taskQueue.poll();
            if(task != null)
                task.run();
        }
        Log.i(TAG, "SimpleWorker Terminated");
    }

    public SimpleWorker execute(Runnable task){
        taskQueue.add(task);
        return this;
    }

    public void quit(){
        alive.set(false);
    }
}
