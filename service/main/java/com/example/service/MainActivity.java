package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button play, stop, timePlay, timeStop;
    Intent intent, intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("음악 서비스 백그라운드 실행");

        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        timePlay = findViewById(R.id.timePlay);
        timeStop = findViewById(R.id.timeStop);
        //MusicService 클래스 intent로 만들기
        intent = new Intent(getApplicationContext(), MusicService.class);
        intent2 = new Intent(getApplicationContext(), TimerService.class);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Manifest 에 서비스 설정을 해야한다.
                startService(intent); //intent로 만든 MusicService 클래스를 서비스로 실행 해라.
                android.util.Log.i("서비스 ", "서비스 요청됨 >> startService()");
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        timePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent2);
                android.util.Log.i("서비스 ", "서비스 요청됨 >> TimerService()");
            }
        });

        timeStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent2);
            }
        });
    }
}