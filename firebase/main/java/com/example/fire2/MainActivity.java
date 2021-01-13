package com.example.fire2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText et_user_name, et_user_email, et_user_id;
    Button btn_save, btn_read;
    TextView read_data;
    ListView listView;
    TextView text1, text2;
    DatabaseReference database;
    MyListAdapter adapter;

    ArrayList<User> arrayList;
    //리스트의 값을 카운터 하기 위한 값.
    int i = 1; //리스트의 갯수를 체크해 줄 i 변수값 지정
    //파이어베이스 user의 시작값이 1 이여서 기준을 잡아 주었다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_user_id = findViewById(R.id.et_user_id);
        et_user_name = findViewById(R.id.et_user_name);
        et_user_email = findViewById(R.id.et_user_email);
        btn_save = findViewById(R.id.btn_save);
        btn_read = findViewById(R.id.btn_read);
        read_data = findViewById(R.id.read_data);
        read_data = findViewById(R.id.read_data);
        listView = findViewById(R.id.listView);

        database = FirebaseDatabase.getInstance().getReference("users"); //파이어베이스 객체 가져오기
        Log.d("파이어베이스>>", database + " ");

        //arrayList 객체 생성 - db에서 가지고 오는 유저들의 목록을 넣을 공간
        arrayList = new ArrayList<>();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override // 값 가져오는 메서드
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //유저들의 목록을 가지고 오는 메서드
                arrayList.clear();
                //파이어베이스의 등록된 json을 다 가져와라
                Log.d("파이어베이스>>", "user아래의 자식들의 개수" + snapshot.getChildrenCount());
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {//DataSnapshot - for문으로 목록에 들어있는 user를 한명씩 꺼내줌.
                    //snapshot.getChildren() - users아래에 있는 user목록을 다 가지고 온다.
                    //user의 값들을 가지고와서, User vo에 넣는다.
                    //getValue(User.class) - 해당하는 멤버변수와 동일한 set메서드를 자동으로 부른다.
                    User user = snapshot1.getValue(User.class);
                    arrayList.add(user);//arrayList에 담기
                    Log.d("파이어베이스>>", "user 1명:" + user);
                }

                //i를 현재 파이어베이스가 가지고 있는 데이터 총 사이즈로 초기화
                i = arrayList.size(); // getChildrenCount() 와 같다.

                //inner class로 만든 adapter 객체를 생성
                adapter = new MyListAdapter(getApplicationContext());
                listView.setAdapter(adapter); //listView에 적용 시키기
                adapter.notifyDataSetChanged();//변경된 데이터 적용 시키기
            }

            @Override // 실패했을때 실행하는 메서드
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });//database

        //파이어베이스에서 데이터 읽어 오는 버튼 이벤트 설정
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUser();
            }
        });//btn_read

        //파이어베이스에 데이터 저장 하는 버튼 이벤트
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 입력한 값 가져오기
                String getUserName = et_user_name.getText().toString();
                String getUserEmail = et_user_email.getText().toString();
                //key : value 의 형식인 Hashmap으로 만들기
                HashMap result = new HashMap<>();//Hashmap 객체 생성
                result.put("name", getUserName);
                result.put("email", getUserEmail);
                i++;
                writeNewUser(i + "", getUserName, getUserEmail);
            }
        });//btn_save
    }//onCreate()

    //읽기 버튼 클릭시 실행되는 함수
    private void readUser() {
        String userId = et_user_id.getText().toString();
        database.child(userId).addValueEventListener(new ValueEventListener() {
            @Override//userId 을 잘 가져왔을때
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(User.class) != null) {//파이어베이스에 데이터가 null이 아니라면
                    User user = snapshot.getValue(User.class);//읽을 데이터를 넣은 user 객체를 넘겨준다.
                    read_data.setText(user.userName + " " + user.email);
                } else {
                    Toast.makeText(MainActivity.this, "데이터 없음", Toast.LENGTH_SHORT).show();
                }
            }

            @Override//userId 을 가져오지 못 했을때
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }//readUser();

    //저장하기 버튼 클릭시 실행되는 함수
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);//유저 DTO에 넣어서

        database.child(userId).setValue(user)//데이터 베이스에 userId(총 길이 기준)에서 +1 되어서 데이터를 넣어라
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
                        Toast.makeText(MainActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }//writeNewUser

    //리스트 뷰에 넣을 어댑터 클래스 생성
    public class MyListAdapter extends BaseAdapter {

        Context context;

        public MyListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //view 재사용을 위한 convertView 사용
            //재사용하기 때문에 새롭게 View를 inflate 할 필요 없이 데이터만 바꾸는 작업을 진행하면 된다.
            View oneView = View.inflate(getApplicationContext(), R.layout.one, null);
            //파이어베이스 에서 가져온 데이터를 listView에 넣어서 setAdapter 하기 위해 설정
            text1 = oneView.findViewById(R.id.text1);
            text2 = oneView.findViewById(R.id.text2);

            text1.setText("email:" + arrayList.get(position).email);
            text2.setText("email:" + arrayList.get(position).userName);
            //적용한 데이터를 oneView로 넘기기
            return oneView;
        }
    }//MyListAdapter

}//MainActivity