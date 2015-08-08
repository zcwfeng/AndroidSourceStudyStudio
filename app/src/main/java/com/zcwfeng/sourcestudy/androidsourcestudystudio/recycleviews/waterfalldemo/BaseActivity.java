package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/5
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        findViews();
        setViews();
    }

    /**
     * 找到所有的views
     */
    protected abstract void findViews();

    /**
     * 设置view的各种初始状态
     */
    protected abstract void setViews();

    protected abstract int getContentViewId();
}