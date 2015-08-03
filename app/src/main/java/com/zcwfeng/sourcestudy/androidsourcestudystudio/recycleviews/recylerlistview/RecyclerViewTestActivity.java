package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

import java.util.ArrayList;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview Copyright (C), 2015,zcw<br/>
 * @date 2015/8/3 <br/>
 */
public class RecyclerViewTestActivity extends BaseActivity {

    RecyclerView mRecylerListView;
    MSimpleAdapter mListViewAdapter;
    ArrayList<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        initDatas();
        initViews();
        mListViewAdapter = new MSimpleAdapter(mDatas,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecylerListView.setLayoutManager(linearLayoutManager);
        mRecylerListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecylerListView.setItemAnimator(new DefaultItemAnimator());
        mRecylerListView.setAdapter(mListViewAdapter);


    }


    private void initViews() {
        mRecylerListView = (RecyclerView) findViewById(R.id.recyler_listview);

    }

    private void initDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.clear();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add((char)i  +"");
        }
    }

}
