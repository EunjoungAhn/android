package com.example.myshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OpenActivity extends AppCompatActivity {

    Button location_btn;
    Button call_btn, like_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        //가기 위치 버튼 클릭시 이벤트
        location_btn = findViewById(R.id.location_btn);
        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //묵시적 인텐트
                Intent locationIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://map.naver.com/v5/search/%EB%B2%84%EA%B1%B0%ED%82%B9/place/1726356375?c=14109616.1520745,4507501.1126054,13,0,0,0,dh&placePath=%3F%2526"));
                startActivity(locationIntent);
            }
        });

        //가게로 전화 버튼 클릭시 이벤트
        call_btn = findViewById(R.id.call_btn);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //묵시적 인텐트
                Intent call_Intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:07074626777"));
                startActivity(call_Intent);
            }
        });

        //평점 등록 버튼
        like_btn = findViewById(R.id.like_btn);
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 like_dialog.xml 띄우기
                final AlertDialog.Builder like_Builder = new AlertDialog.Builder(OpenActivity.this);
                View bookingCancelDialog = View.inflate(OpenActivity.this, R.layout.like_dialog, null);
                like_Builder.setView(bookingCancelDialog);
                like_Builder.show();
            }
        });

    }
}