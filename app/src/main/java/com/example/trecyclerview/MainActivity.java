package com.example.trecyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView top_recy;
    RecyclerViewAdapter topAdapter;
    List<Integer> topList = new ArrayList<>();


    private RecyclerView bottom_recy;
    RecyclerViewAdapter bottomAdapter;
    List<Integer> bottomList = new ArrayList<>();
    int currentPosition = 1;
    private int[] img ={R.mipmap.one,R.mipmap.ele,R.mipmap.two,R.mipmap.et,R.mipmap.three,R.mipmap.nine,R.mipmap.four,R.mipmap.seven,R.mipmap.five,R.mipmap.ten};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_recy = findViewById(R.id.hor_recy);
        bottom_recy = findViewById(R.id.hor_max_recy);

        for(int i=0;i<img.length;i++){
            topList.add(img[i]);
        }
        top_recy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        topAdapter = new RecyclerViewAdapter(this,R.layout.hot_item_layout,topList);
        top_recy.setAdapter(topAdapter);

        topAdapter.setCallBack(new RecyclerViewAdapter.CallBack() {
            @Override
            public <T> void convert(IViewHolder holder, T bean, int position) {
                ImageView horImageView = (ImageView) holder.getView(R.id.hor_item_img);
                Glide.with(MainActivity.this).load(bean).into(horImageView);
            }
        });

        top_recy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获得recyclerView的线性布局管理器
                LinearLayoutManager topManager = (LinearLayoutManager) top_recy.getLayoutManager();
                LinearLayoutManager bottomManager = (LinearLayoutManager) bottom_recy.getLayoutManager();

                //获取滚动时的第一条展示的position
                 currentPosition = topManager.findFirstVisibleItemPosition();

                 //打这些log 是为了查看下表,刚开始话滑动不好,就是下表出现了问题,配图的时候注意一下呢
                Log.i("-----firsttop", String.valueOf(currentPosition));
                Log.i("------firstbottom", String.valueOf(bottomManager.findFirstVisibleItemPosition()));
                Log.i("------lastbottom", String.valueOf(bottomManager.findLastVisibleItemPosition()));
                if (bottomManager.findFirstVisibleItemPosition()>=currentPosition){
//                    bottomManager.scrollToPositionWithOffset(currentPosition,0); //跳到并行位置,
                    bottom_recy.smoothScrollToPosition(currentPosition);
                }else if (bottomManager.findLastVisibleItemPosition() < currentPosition) {
//                    bottomManager.scrollToPositionWithOffset(currentPosition, 0);
                    bottom_recy.smoothScrollToPosition(currentPosition);

                }

               bottomAdapter.notifyDataSetChanged();


            }
        });


        for(int i=0;i<img.length;i++){
            bottomList.add((img[i]));
        }

        bottom_recy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        bottomAdapter = new RecyclerViewAdapter(this,R.layout.max_item_layout,bottomList);
        bottom_recy.setAdapter(bottomAdapter);

        bottomAdapter.setCallBack(new RecyclerViewAdapter.CallBack() {
            @Override
            public <T> void convert(IViewHolder holder, T bean, int position) {
                ImageView maxImage = (ImageView) holder.getView(R.id.max_image);
                Glide.with(MainActivity.this).load(bean).into(maxImage);
            }
        });

        bottomAdapter.setOnItemClickListner(new RecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) top_recy.getLayoutManager();
                linearLayoutManager.scrollToPositionWithOffset(position,0);  // 定位到某个item,并将其置顶显示

            }
        });





        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecyActivity.class));
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GridActivity.class));
            }
        });


    }
}
