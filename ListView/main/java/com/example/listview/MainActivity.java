package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView  listView;
    EditText edit;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//액티비티를 초기화하면서 객체생성
        //객체생성만 된것이다. 실행하는 함수는 눈에 안보이지만 부모(AppCompatActivity)가 가지고 있다.
        //스프링으로 치면 init 이다.
        //서비스 호출은 jsp가 첫 호출 될때, 수정되기 전까지 init은 한번 호출 된다.


        //1. 데이터 준비(array, arraylist 준비)
        //final String[] food = {"감자","고구마","양파"};
        //배열은 크기가 고정이라 ArrayList로 변경
        final ArrayList<String> food = new ArrayList<>();
        food.add("감자");
        food.add("고구마");
        food.add("양파");

        //2. view 객체를 준비
        listView = findViewById(R.id.listView1);

        //3. adapter준비          new ArrayAdapter<>(여기서 쓸 것인가, );
        //                      안드로이드에서 context는 액티비티를 의미한다. , 어떤 방식으로 붙을지, 어떤 데이터를 넣을지
        //ArrayAdapter 배열과 리스트 둘다 넣을 수 있어, 배열에서 > 리스트로 바꾸어도 adapter은 변경할 필요가 없다.
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, food);

        //4. view에 adapter지정/설정
        listView.setAdapter(adapter);

        //5. 하나의 item마다 어떻게 처리할지 이벤트 지정
        //list 하나하나가 item(항목)이다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                              //어디에 넣을지, 어떤 자료를 넣을지, 어떤식으로
//                Toast.makeText(getApplicationContext(),food[position], Toast.LENGTH_SHORT).show();//배열 방식
                Toast.makeText(getApplicationContext(),food.get(position), Toast.LENGTH_SHORT).show();//리스트 방식
            }
        });

        edit = findViewById(R.id.edit);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arraylist에 추가를 해야함.
                food.add(edit.getText().toString());

                //adapter에게 데이터의 변경이 있음을 알려주어야함.
                adapter.notifyDataSetChanged();
            }
        });
    }
}
