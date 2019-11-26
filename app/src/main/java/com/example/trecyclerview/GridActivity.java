package com.example.trecyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * ********文件描述：********
 * ********作者：huleiyang********
 * ********创建时间：2019/11/26********
 * ********更改时间：2019/11/26********
 * ********版本号：1********
 */
public class GridActivity extends AppCompatActivity {

    private RecyclerView grid_recy;

    RecyclerViewAdapter adapter;

    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity_layout);

        grid_recy = findViewById(R.id.grid_recy);

        for (int i=0;i<50;i++){
            list.add("");
        }

        grid_recy.setLayoutManager(new GridLayoutManager(this,3));
        grid_recy.addItemDecoration(new DividerGridItemDecoration(this));
        adapter = new RecyclerViewAdapter(this,R.layout.grid_item_layout,list);
        grid_recy.setAdapter(adapter);
    }
}
