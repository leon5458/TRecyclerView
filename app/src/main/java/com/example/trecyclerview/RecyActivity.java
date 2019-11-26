package com.example.trecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ********文件描述：********
 * ********作者：huleiyang********
 * ********创建时间：2019/11/26********
 * ********更改时间：2019/11/26********
 * ********版本号：1********
 */
public class RecyActivity extends AppCompatActivity{

    private RecyclerView recyclerView;

    RecyclerViewAdapter adapter;

    List<String> list = new ArrayList<>();


    private TextView back_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recy_activity_layout);

        back_txt = findViewById(R.id.back_txt);
        recyclerView = findViewById(R.id.recy);

        for (int i=0;i<30;i++){
            list.add("");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerViewAdapter(this,R.layout.recy_item_layout,list);
        recyclerView.setAdapter(adapter);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获得recyclerView的线性布局管理器
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                Log.i("------first", String.valueOf(firstVisibleItemPosition));


                //判断不滑动的情况
                if (newState ==RecyclerView.SCROLL_STATE_IDLE){
                    // 判断是否滚动超过一屏
                    if (firstVisibleItemPosition ==0){  //没有超过一屏幕
                        //这里隐藏是为了滑动之后显示了,在手动滑动到首页了,就隐藏
                        back_txt.setVisibility(View.GONE);

                    }else{
                        back_txt.setVisibility(View.VISIBLE);
                        back_txt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                recyclerView.smoothScrollToPosition(0);
                                recyclerView.scrollToPosition(0);
                                back_txt.setVisibility(View.GONE);
                            }
                        });
                    }
                }


            }


        });
    }
}
