package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    MyGridAdapter myGridAdapter;
    Button shop_btn;
    //1.데이터를 지정
    int[] posterID = {R.drawable.sh01, R.drawable.sh02, R.drawable.sh03, R.drawable.sh04, R.drawable.sh05};
    String[] title = {"버거킹", "KFC", "맥도널드", "MAX", "노브랜드버거"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shop_btn = findViewById(R.id.shop_btn);

        shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //명시적 인텐트
                Intent intent = new Intent(getApplicationContext(), OpenActivity.class);
                startActivity(intent);
            }
        });
        //2. view객체를 지정
        gridView = findViewById(R.id.gridView1);
        //3. adapter를 생성
        //단순한 형태의 adapter가 아니면 baseadapter를 상속을 받아서 adapter클래스를 만들어야 한다.
        myGridAdapter = new MyGridAdapter(this);//this 이 액팁비티를 넘길것이다.
        //4. view객체에 adapter지정
        gridView.setAdapter(myGridAdapter);
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        //입력 값으로 객체가 넘어오기 때문에 생성자를 만들어 주어야 한다.
        public MyGridAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return posterID.length;//전체 갯수가 몇개인지 리턴
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }

        //adapter에서 view를 만들어 주는 녀석
        //반복되는 하나하나를 정의
        //view > viewGroup
        @Override // getVie는 하나 하나 반복하기 위한 설정
        public View getView(final int position, View convertView, ViewGroup parent) {
            //shop_list 레이아웃을 만들고
            View shop_list_view = View.inflate(getApplicationContext(), R.layout.shop_list, null);
            //shop_list 레이아웃의 내용을 가져와 지정하고
            TextView text = shop_list_view.findViewById(R.id.text);
            ImageView imageView = shop_list_view.findViewById(R.id.image);
            text.setText(title[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//위치 설정
            imageView.setPadding(0,15,5,15);//여백 설정
            imageView.setImageResource(posterID[position]);//이미지 바꾸는 설정
            return shop_list_view;
        }
    }
}