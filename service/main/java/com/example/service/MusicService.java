package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        android.util.Log.i("서비스 ", "서비스 요청됨 >> onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스 ", "서비스 요청됨 >>  onStartCommand()");
//        int[] num = {1,2,3,4,5,6,7,8,9,10};
//        for (int x:num){
//            android.util.Log.i("서비스 ", "for문 돌아감>> "+x);
//        }
        mediaPlayer = MediaPlayer.create(this,R.raw.song1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스 ", "서비스 요청됨 >> onDestroy()");
        mediaPlayer.stop();
        super.onDestroy();
    }
}