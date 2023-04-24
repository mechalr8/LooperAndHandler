package com.example.looperandhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Worker worker;
    private TextView tvMessage;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            tvMessage.setText((String)msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessage = findViewById(R.id.tvMessage);

        worker = new Worker();

        worker.execute(() -> {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 1 Completed";
            handler.sendMessage(message);
        }).execute(() -> {
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 2 Completed";
            handler.sendMessage(message);
        }).execute(() -> {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 3 Completed";
            handler.sendMessage(message);
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        worker.quit();
    }
}