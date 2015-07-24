package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.BuildConfig;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.activity Copyright (C), 2015,zcw<br/>
 * @date 2015/7/24 <br/>
 */
public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction() != null) {
                if (intent.getAction().equalsIgnoreCase(Intent.ACTION_MAIN)) {
                    if(BuildConfig.DEBUG) {
                        Log.e("framework","闪屏");
                    }
                    startActivity(new Intent(this, SplashScreenActivity_.class));
                } else if (intent.getAction().equalsIgnoreCase("finish")) {
                    finish();//安全退出
                } else {
                    // 推送可以在这里做，主activity跳转到这里（这是一个思路）
                }
            }
        } else {
            Log.e("framework","闪屏");
            startActivity(new Intent(this, SplashScreenActivity_.class));
        }
    }
}
