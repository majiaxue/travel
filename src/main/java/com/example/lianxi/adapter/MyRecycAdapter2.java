package com.example.lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi.R;
import com.example.lianxi.bean.DbBean;
import com.example.lianxi.bean.InfoBean;

import java.util.ArrayList;

public class MyRecycAdapter2 extends RecyclerView.Adapter<MyRecycAdapter2.ViewHolder> {
    private ArrayList<DbBean> list;
    private Context context;

    public MyRecycAdapter2(ArrayList<DbBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<DbBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getUrl()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItem(i,list.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tv_title=itemView.findViewById(R.id.tv_title);
        }
    }

    private onItemClick onItemClick;

    public void setOnItemClick(MyRecycAdapter2.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface onItemClick{
        void onItem(int position, DbBean bean);
    }
}
