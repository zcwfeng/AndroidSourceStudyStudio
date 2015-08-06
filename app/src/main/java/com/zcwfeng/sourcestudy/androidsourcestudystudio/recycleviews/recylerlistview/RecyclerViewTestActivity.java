package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.storage.StorageManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.antonioleiva.recyclerviewextensions.GridLayoutManager;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

import java.util.ArrayList;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview Copyright (C), 2015,zcw<br/>
 * @date 2015/8/3 <br/>
 */
public class RecyclerViewTestActivity extends ActionBarActivity {

    RecyclerView mRecylerListView;
    MSimpleAdapter mListViewAdapter;
    ArrayList<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        initDatas();
        initViews();
        mListViewAdapter = new MSimpleAdapter(mDatas, this);


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
            mDatas.add((char) i + "");
        }
    }

    public void addDatas() {

        mDatas.add(0, "Insert One");
        mListViewAdapter.updateInsertDatas(mDatas, 0);
    }


    public void delDatas() {
        mDatas.remove(0);
        mListViewAdapter.updateDelDatas(mDatas, 0);
    }


    public void setListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecylerListView.setLayoutManager(linearLayoutManager);
        mRecylerListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecylerListView.setItemAnimator(new DefaultItemAnimator());
        mRecylerListView.setAdapter(mListViewAdapter);
    }

    public void setGridView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this);
        mRecylerListView.setLayoutManager(mLayoutManager);
        mRecylerListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecylerListView.setItemAnimator(new DefaultItemAnimator());
        mRecylerListView.setAdapter(mListViewAdapter);
    }

    public void setWaterfallView() {
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecylerListView.setLayoutManager(mLayoutManager);
        mRecylerListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecylerListView.setItemAnimator(new DefaultItemAnimator());
        mRecylerListView.setAdapter(mListViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.add_data:
                addDatas();
                return true;

            case R.id.del_data:
                delDatas();
                return true;
            case R.id.listview:
                setListView();
                return true;

            case R.id.gridview:
                setGridView();
                return true;


            case R.id.waterfall:
                setWaterfallView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
