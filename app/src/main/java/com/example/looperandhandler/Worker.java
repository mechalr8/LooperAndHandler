package com.example.looperandhandler;

import android.os.Handler;
import android.os.HandlerThread;

public class Worker extends HandlerThread {

    private final Handler handler;
    private static final String TAG = "Worker";
    public Worker() {
        super(TAG);
        start();
        handler = new Handler(getLooper());
    }

    public Worker execute(Runnable task){
        handler.post(task);
        return this;
    }
}
