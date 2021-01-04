package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    int[] posterID;
    String[] title;
    int[] like;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //1. 데이터 준비
        Intent intent = getIntent();
        posterID = intent.getIntArrayExtra("posterID");
        title = intent.getStringArrayExtra("title");
        like = intent.getIntArrayExtra("like");

        Toast.makeText(getApplicationContext(),posterID.length + " " + title[0] + " " + like[0], Toast.LENGTH_SHORT).show();
        //각 영화마다의 좋아요 득표를 정리해서 보여주세요.!
        final ArrayList<String> all = new ArrayList<>();
        for (int i = 0; i < posterID.length; i++){
            all.add(title[i]+like[i]+"");
        }

        //2. 뷰 지정
        listView = findViewById(R.id.main3ListView);
        //3. 어댑터 준비
        final ArrayAdapter adapterString = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,all);
        //4. 뷰 어댑터에 설정
        listView.setAdapter(adapterString);
        //5. item 이벤트 지정
    }
}
