package com.example.service;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;


public class TimerService extends Service {
    TimerTask timerTask;
    Timer timer = new Timer();
    private Handler handler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        android.util.Log.i("서비스", "타이머 서비스 객체 생성됨>> onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스", "서비스 시작됨.>> 타이머 시작");
        timerTask = new TimerTask()
        {
            int count = 0;

            @Override
            public void run()
            {
                count++;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        android.util.Log.i("서비스", "타이머 >> " + count +"초");
                    }
                });
            }
        };
        timer.schedule(timerTask,0 ,1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스", "서비스 시작됨.>> 타이머 종료");
        timerTask.cancel();
        super.onDestroy();
    }
}