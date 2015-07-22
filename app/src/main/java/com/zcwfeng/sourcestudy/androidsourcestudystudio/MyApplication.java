package com.zcwfeng.sourcestudy.androidsourcestudystudio;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 11111 on 2015/7/9.
 */
public class MyApplication extends Application {
    public static MyApplication app;
    public MyApplication getInstance() {
        return app;
    }
    // 请求队列
    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        // 不必为每一次HTTP请求都创建一个RequestQueue对象，推荐在application中初始化
        requestQueue = Volley.newRequestQueue(this);
        // 初始化fresco库
        Fresco.initialize(this);
    }
}
