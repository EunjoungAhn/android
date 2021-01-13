package com.example.firebaselistexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private Context context; //액티비티 마다 Context있으며, adapter에서는 Context가 현재 없어서
    //선택한 액티비티의 Context를 가져올때 사용 된다.


    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //실제 리스트뷰(list_item)가 adapter에 연결되어서 viewHolder를 최초로 만들어낸다.
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
         CustomViewHolder holder = new CustomViewHolder(view);
         return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //실제로 각 item에 대한 매칭을 시켜주는 역할 (서버로 부터 이미지를 받아오는 )
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        //데이터 베이스에서 list_item에 데이터를 넣어줌
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_pw.setText(String.valueOf(arrayList.get(position).getPw()));
        holder.tv_userName.setText(arrayList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        //list_item에서 구현한 것을 여기로 불어올 것이다.
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_userName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView); //recyclerView 에 ViewHolder에서 itemView를 상속을 받았기 때문에
            //상속 받은 itemView의 id 값을 가져와야 한다.
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_pw = itemView.findViewById(R.id.tv_pw);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);
        }
    }
}
