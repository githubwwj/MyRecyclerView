package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateDatas();
        RecyclerView mRv = findViewById(R.id.linearRv);

        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
//        mRv.addItemDecoration(dividerItemDecoration);
        LinearItemDecoration2 linearItemDecoration = new LinearItemDecoration2(this);
        mRv.addItemDecoration(linearItemDecoration);

        //如果是横向滚动，后面的数值表示的是几行，如果是竖向滚动，后面的数值表示的是几列
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRv.setLayoutManager(gridLayoutManager);

        // 瀑瀑流管理器
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
//        mRv.setLayoutManager(layoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(this, mDatas);
        mRv.setAdapter(adapter);
    }


    private ArrayList<String> mDatas = new ArrayList<>();

    private void generateDatas() {
        for (int i = 1; i <= 100; i++) {
            mDatas.add("第 " + i + " 个item");
        }
    }

}