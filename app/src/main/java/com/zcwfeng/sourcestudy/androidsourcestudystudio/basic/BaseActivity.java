package com.zcwfeng.sourcestudy.androidsourcestudystudio.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.AtomicFile;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.utils.ActivityCollector;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.utils.LogUtil;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.basic Copyright (C), 2015,zcw<br/>
 * @date 2015/7/24 <br/>
 */
public class BaseActivity extends AppCompatActivity {

    private GestureDetectorCompat mGestureDetectorBase;
    //抽象类，用于创建公共的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("BaseActivity", "----------->" + getClass().getSimpleName());
        mGestureDetectorBase = new GestureDetectorCompat(BaseActivity.this, mGestureListenerBase);


        ActivityCollector.addActivity(this);
    }


    protected final GestureDetector.SimpleOnGestureListener mGestureListenerBase = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // TODO Auto-generated method stub
            boolean flag = false;
            if (Math.abs(velocityX) >= 1000F
                    && (double) Math.abs(velocityX) >= 2d * (double) Math.abs(velocityY)) {
                if (velocityX > 0f) {
                    gestureFinish();
                    finish();
                    overridePendingTransition(0, R.anim.base_slide_right_out);
                    flag = true;
                }
            } else {
                flag = false;
            }
            return flag;

        }
    };
    protected void gestureFinish(){

    }
    protected void initGestureDetector(final View listView, View loadingLayout, View failedLayout,
                                       View noDaraLayout) {
        if (listView != null)
            listView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    boolean retVal = mGestureDetectorBase.onTouchEvent(event);
                    return listView.onTouchEvent(event) || retVal;
                }
            });
        if (loadingLayout != null)
            loadingLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    return mGestureDetectorBase.onTouchEvent(event);
                }
            });
        if (failedLayout != null)
            failedLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    return mGestureDetectorBase.onTouchEvent(event);
                }
            });
        if (noDaraLayout != null)
            noDaraLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    return mGestureDetectorBase.onTouchEvent(event);
                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);

    }
}
