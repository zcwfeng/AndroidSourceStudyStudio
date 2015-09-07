package com.zcwfeng.sourcestudy.androidsourcestudystudio.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.AtomicFile;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.utils.ActivityCollector;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.utils.LogUtil;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.basic Copyright (C), 2015,zcw<br/>
 * @date 2015/7/24 <br/>
 */
public class BaseActivity extends AppCompatActivity {
    //抽象类，用于创建公共的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("BaseActivity", "----------->" + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);

    }
}
