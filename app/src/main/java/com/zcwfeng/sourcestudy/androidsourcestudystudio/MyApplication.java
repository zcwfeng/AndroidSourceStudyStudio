package com.zcwfeng.sourcestudy.androidsourcestudystudio;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.CloseMe;

import java.util.ArrayList;

import io.rong.imkit.RongIM;


/**
 * Created by 11111 on 2015/7/9.
 */
public class MyApplication extends Application {
    public static MyApplication app;
    public static MyApplication getInstance() {
        return app;
    }
    // 请求队列
    public static RequestQueue requestQueue;



    /**
     * 关闭之前activity
     */
    private ArrayList<CloseMe> mCloseMeHistory = new ArrayList<CloseMe>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        // 不必为每一次HTTP请求都创建一个RequestQueue对象，推荐在application中初始化
        requestQueue = Volley.newRequestQueue(this);
        // 初始化fresco库
        Fresco.initialize(this);

//        RongIM.init(this);

    }

    public void notifyCloseMe() {
        for (CloseMe mCloseMe : mCloseMeHistory) {
            mCloseMe.closeMe();
        }
    }



    public void setCloseMe(ArrayList<CloseMe> mCloseMeHistory) {
        this.mCloseMeHistory = mCloseMeHistory;
    }

    public void removeCloseMeHistory(CloseMe mCloseMe) {
        mCloseMeHistory.remove(mCloseMe);
    }


    public void addCloseMeHistory(CloseMe mCloseMe) {
        mCloseMeHistory.add(mCloseMe);
    }


    public void clearHistory() {
        mCloseMeHistory.clear();
    }


    public void showShortToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

}
