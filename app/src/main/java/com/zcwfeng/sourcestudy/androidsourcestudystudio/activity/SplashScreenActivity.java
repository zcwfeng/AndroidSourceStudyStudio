package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

import org.androidannotations.annotations.EActivity;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.activity Copyright (C), 2015,zcw<br/>
 * @date 2015/7/24 <br/>
 */
@EActivity(R.layout.splash_screen_activity)
public class SplashScreenActivity extends BaseActivity {

    public Handler finishHandler;      // 判断结束的Handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startGuide();
    }

    public void startGuide() {
        finishHandler = new Handler();
        finishHandler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        stopGuide();
                    }
                }, 3000);
    }


    public void stopGuide() {
//         实现这个方法，结束后会调用
//        mediaPlayer.stop();
        //判断是否登录
//        if(LoginUtils.getInstance().isLogined()){
//            startActivity(new Intent(SplashScreenActivity.this, MainActivity_.class));
//        }else{
//            // 判断是否类似游戏上面的播放标志
//            if(isVideoPlay){
//                //登录之后进入home页面
//                startActivity(new Intent(SplashScreenActivity.this, LoginHomeActivity.class));
//            }else{
//                // 其他逻辑
//                startActivity(new Intent(SplashScreenActivity.this, LogoFristActivity.class));
//            }
//        }

        startActivity(new Intent(SplashScreenActivity.this, MainActivity_.class));
        finish();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        mediaPlayer.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
