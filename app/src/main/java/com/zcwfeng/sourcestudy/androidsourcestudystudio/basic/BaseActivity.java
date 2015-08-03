package com.zcwfeng.sourcestudy.androidsourcestudystudio.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    }
}
