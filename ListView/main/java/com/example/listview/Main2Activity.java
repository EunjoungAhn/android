package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    GridView gridView;
    MyGridAdapter myGridAdapter;
    Button button3;

    int[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};

    String[] title = {"써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이", "백투더퓨터"};

    int[] like = new int[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //1.데이터를 지정

        //2. view객체를 지정
        gridView = findViewById(R.id.gridView1);

        //3. adapter를 생성
        //단순한 형태의 adapter가 아니면 baseadapter를 상속을 받아서 adapter클래스를 만들어야 한다.
        myGridAdapter = new MyGridAdapter(this);//this 이 액팁비티를 넘길것이다.

        //4. view객체에 adapter지정
        gridView.setAdapter(myGridAdapter);

        //5. item하나에 대한 이벤트 설정

        //버튼을 누르면 activity4으로 넘어가게 해주세요!
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                //배열 3개를 넘기겠다.
                intent.putExtra("posterID", posterID);
                intent.putExtra("title", title);
                intent.putExtra("like", like);
                startActivity(intent);
            }//button3
        });//on

    }
    public class MyGridAdapter extends BaseAdapter{
        //1.데이터를 지정
//        Integer[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
//                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};

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
            //one 레이아웃을 만들고
            View oneView = View.inflate(getApplicationContext(), R.layout.one, null);
            //one 레이아웃의 내용을 가져와 지정하고
            TextView text2 = oneView.findViewById(R.id.text2);
            ImageView imageView = oneView.findViewById(R.id.image2);
            text2.setText(title[position] + ":" + like[position]);
//            text2.setTextSize(20);
            // imageView 설정
            //ImageView imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(500,500));//크기 설정
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//위치 설정
            imageView.setPadding(0,15,5,15);//여백 설정
            imageView.setImageResource(posterID[position]);//이미지 바꾸는 설정

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dialog를 이용해서 imageView내용을 띄울 예정
                    final AlertDialog.Builder aBuilder = new AlertDialog.Builder(Main2Activity.this);

                    //제목 지정
                    aBuilder.setTitle("영화 상세내용");

                    //배치 레이아웃 인플레이션 (객체로 만들어 주는 것을 inflate이다.)
                    //따로 만든 dialog를 만들어서 넣어주어야 한다. root = 다른 곳에 속해 있는 것이냐?
                    View dialogView = View.inflate(Main2Activity.this, R.layout.dialog, null);
                    aBuilder.setView(dialogView);

                    //이미지 추가
                    dialogView.findViewById(R.id.dialogView);
                    ImageView image = dialogView.findViewById(R.id.img);
                    image.setImageResource(posterID[position]);

                    //입력한 점수를 가지고 와서 like배열에 넣어주세요.
                    final EditText jumsu = dialogView.findViewById(R.id.jumsu);
                    Button button2 = dialogView.findViewById(R.id.button2);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //like 기존에 있는 값에 새롭게 입력한 값을 더해서 다시 넣어주세요.
                            like[position] = like[position] + Integer.parseInt(jumsu.getText().toString());
                            myGridAdapter.notifyDataSetChanged();
                            final AlertDialog.Builder aBuilder2 = new AlertDialog.Builder(Main2Activity.this);
                            aBuilder2.setTitle("영화 상세 내용");
                            aBuilder2.setMessage(title[position] + "의 좋아요!" + like[position]);
                            aBuilder2.setNegativeButton("확인완료", null);
                            aBuilder2.show();
                        }
                    });

                    //버튼 추가
                    aBuilder.setIcon(R.drawable.ic_launcher);
                    aBuilder.setNegativeButton("닫기", null);

                    //설정하고 보여지는 명령
                    aBuilder.show();
                }
            });
//            return imageView;
            return oneView;
        }
    }
}
